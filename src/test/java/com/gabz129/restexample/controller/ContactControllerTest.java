package com.gabz129.restexample.controller;

import com.gabz129.restexample.entity.Address;
import com.gabz129.restexample.entity.Phone;
import com.gabz129.restexample.service.ContactService;
import com.gabz129.restexample.MessageCodes;
import com.gabz129.restexample.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringRunner.class)
public class ContactControllerTest {
    @SpyBean
    private ContactController contactController;

    @MockBean
    private ContactService contactService;

    private Contact contact;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Phone phone = new Phone.Builder()
                .withMobile("123-456-7894")
                .build();
        Address address = new Address.Builder()
                .withStreet("3RD street")
                .withState("NY")
                .withCountry("United States")
                .withZip(10016)
                .build();
        contact = new Contact.Builder()
                .withContactId(1L)
                .withName("Gabriel")
                .withEmail("aa@aa.com")
                .withBirthdate(new Date())
                .withPhone(phone)
                .withAddress(address)
                .build();
    }

    @Test
    public void createContact(){
        Mockito.doNothing().when(contactService).createContact(any(Contact.class));
        ResponseEntity response = contactController.createContact(contact);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(MessageCodes.CREATE_CONTACT_OK.getMessage()));
    }

    @Test
    public void createContactFailed(){
        Mockito.doThrow(NullPointerException.class).when(contactService).createContact(any(Contact.class));
        ResponseEntity response = contactController.createContact(contact);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(((ContactResponse)response.getBody()).getStatus(), is(MessageCodes.CREATE_CONTACT_ERROR.getMessage()));
    }

    @Test
    public void retrieveAllContacts(){
        Mockito.when(contactService.retrieveAllContacts()).thenReturn(Arrays.asList(contact));
        ResponseEntity response = contactController.retrieveAllContacts();
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat((List<Contact>)response.getBody(), hasSize(1));
        assertThat("Check Name of first Item",((List<Contact>)response.getBody()).get(0).getName(), is("Gabriel"));
    }

    @Test
    public void retrieveAllContactsFailed(){
        Mockito.when(contactService.retrieveAllContacts()).thenThrow(NullPointerException.class);
        ResponseEntity response = contactController.retrieveAllContacts();
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(((ContactResponse)response.getBody()).getStatus(), is(MessageCodes.RETRIEVE_CONTACT_ERROR.getMessage()));
    }

    @Test
    public void retrieveOneContact(){
        Mockito.when(contactService.retrieveContact(any())).thenReturn(contact);
        ResponseEntity response = contactController.retrieveContact(1L);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(((Contact)response.getBody()).getName(), is("Gabriel"));
    }

    @Test
    public void retrieveOneContactFailed(){
        Mockito.when(contactService.retrieveContact(any())).thenThrow(NullPointerException.class);
        ResponseEntity response = contactController.retrieveContact(1L);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(((ContactResponse)response.getBody()).getStatus(), is(MessageCodes.RETRIEVE_CONTACT_ERROR.getMessage()));
    }

    @Test
    public void updateContact(){
        contact.setName("ChangedName");
        Mockito.when(contactService.updateContact(anyLong(), any())).thenReturn(contact);
        ResponseEntity response = contactController.updateContact(1L, contact);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(((Contact)response.getBody()).getName(), is("ChangedName"));
    }

    @Test
    public void updateContactFailed(){
        Mockito.when(contactService.updateContact(anyLong(),any())).thenThrow(NullPointerException.class);
        ResponseEntity response = contactController.updateContact(1L, contact);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(((ContactResponse)response.getBody()).getStatus(), is(MessageCodes.UPDATE_CONTACT_ERROR.getMessage()));
    }

    @Test
    public void deleteContact(){
        Mockito.doNothing().when(contactService).deleteContact(anyLong());
        ResponseEntity response = contactController.deleteContact(1L);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(MessageCodes.DELETE_CONTACT_OK.getMessage()));
    }

    @Test
    public void deleteContactFailed(){
        Mockito.doThrow(NullPointerException.class).when(contactService).deleteContact(anyLong());
        ResponseEntity response = contactController.deleteContact(1L);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(((ContactResponse)response.getBody()).getStatus(), is(MessageCodes.DELETE_CONTACT_ERROR.getMessage()));
    }
}
