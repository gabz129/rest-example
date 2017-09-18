package com.gabz129.restexample.service;

import com.gabz129.restexample.entity.Address;
import com.gabz129.restexample.entity.Phone;
import com.gabz129.restexample.repository.ContactRepository;
import com.gabz129.restexample.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ContactServiceTest {

    @MockBean
    ContactRepository contactRepository;

    @SpyBean
    ContactService contactService;

    Contact contact;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Phone phone = new Phone.Builder()
                .withId(1L)
                .withMobile("123-456-7894")
                .build();
        Address address = new Address.Builder()
                .withAddressId(1L)
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
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        contactService.createContact(contact);
        verify(contactService).createContact(contact);
    }

    @Test(expected = NullPointerException.class)
    public void createContactNull(){
        when(contactRepository.save(any(Contact.class))).thenReturn(any(Contact.class));
        contactService.createContact(null);
    }

    @Test
    public void retrieveOneContact(){
        when(contactRepository.findOne(anyLong())).thenReturn(any(Contact.class));
        contactService.retrieveContact(1L);
        verify(contactService).retrieveContact(1L);
    }

    @Test(expected = NullPointerException.class)
    public void retrieveOneContactNullId(){
        when(contactRepository.findOne(anyLong())).thenReturn(any(Contact.class));
        contactService.retrieveContact(null);
    }

    @Test
    public void retrieveAllContacts(){
        List<Contact> expectedContacts = new ArrayList<>();
        expectedContacts.add(contact);
        expectedContacts.add(contact);

        when(contactRepository.findAll()).thenReturn(expectedContacts);
        List<Contact> result = contactService.retrieveAllContacts();
        assertThat(result, hasSize(2));
    }

    @Test
    public void updateContact(){
        Phone newPhone = new Phone.Builder()
                .withMobile("123-456-7894")
                .build();
        Address newAddress = new Address.Builder()
                .withStreet("3RD street")
                .withState("NY")
                .withCountry("United States")
                .withZip(10016)
                .build();
        Contact newContact = new Contact.Builder()
                .withName("Another Name")
                .withEmail("aa@aa.com")
                .withBirthdate(new Date())
                .withPhone(newPhone)
                .withAddress(newAddress)
                .build();
        when(contactRepository.findOne(1L)).thenReturn(contact);
        when(contactRepository.save(any(Contact.class))).thenReturn(newContact);
        Contact updatedContact = contactService.updateContact(1L, contact);
        assertThat("Name should be updated", updatedContact.getName(), is(newContact.getName()));
        assertThat("Email should be updated", updatedContact.getEmail(), is(newContact.getEmail()));
        verify(contactRepository).findOne(1L);
        verify(contactRepository).save(contact);
        verify(contactService).updateContact(1L, contact);
    }

    @Test
    public void deleteContact(){
        Mockito.doNothing().when(contactRepository).delete(anyLong());
        contactService.deleteContact(1L);
        verify(contactService).deleteContact(1L);
    }

    @Test(expected = NullPointerException.class)
    public void deleteContactNullId(){
        Mockito.doNothing().when(contactRepository).delete(anyLong());
        contactService.deleteContact(null);
    }
}
