package com.gabz129.restexample.controller;

import com.gabz129.restexample.service.ContactService;
import com.gabz129.restexample.MessageCodes;
import com.gabz129.restexample.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    @RequestMapping(value = "/create",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createContact(@RequestBody Contact contact){
        LOGGER.info("Creating contact");
        try {
            contactService.createContact(contact);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(MessageCodes.CREATE_CONTACT_OK.getMessage());
        }catch (Exception e){
            LOGGER.error("Error in createContact: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ContactResponse(MessageCodes.CREATE_CONTACT_ERROR.getMessage(), e.getMessage()));
        }
    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity retrieveAllContacts() {
        LOGGER.info("Retrieving Contacts");
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(contactService.retrieveAllContacts());
        }catch (Exception e){
            LOGGER.error("Error in retrieveAllContacts: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ContactResponse(MessageCodes.RETRIEVE_CONTACT_ERROR.getMessage(),e.getMessage()));
        }
    }

    @RequestMapping(value = "/{id}",
                    method = RequestMethod.GET )
    public ResponseEntity retrieveContact(@PathVariable("id") Long id){
        LOGGER.info("Retrieving specific contact with id: {}", id);
        try {
            Contact contactRetrieved = contactService.retrieveContact(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(contactRetrieved!=null? contactRetrieved: MessageCodes.RETRIEVE_CONTACT_NOT_FOUND.getMessage());
        }catch (Exception e){
            LOGGER.error("Error in retrieveContact: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ContactResponse(MessageCodes.RETRIEVE_CONTACT_ERROR.getMessage(), e.getMessage()));
        }
    }

    @RequestMapping(value = "/update/{id}",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateContact(@PathVariable("id") Long id, @RequestBody Contact contact){
        LOGGER.info("Updating contact with id: {}", id);
        try {
            Contact contactUpdated = contactService.updateContact(id, contact);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(contactUpdated!=null? contactUpdated: MessageCodes.UPDATE_CONTACT_NOT_FOUND.getMessage());
        }catch (Exception e){
            LOGGER.error("Error in updateContact: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ContactResponse(MessageCodes.UPDATE_CONTACT_ERROR.getMessage(), e.getMessage()));
        }
    }

    @RequestMapping(value = "delete/{id}",
                    method = RequestMethod.DELETE)
    public ResponseEntity deleteContact(@PathVariable("id") Long id){
        LOGGER.info("Deleting contact with id: {}", id);
        try {
            contactService.deleteContact(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(MessageCodes.DELETE_CONTACT_OK.getMessage());
        }catch (Exception e){
            LOGGER.error("Error in deleteContact: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ContactResponse(MessageCodes.DELETE_CONTACT_ERROR.getMessage(), e.getMessage()));
        }
    }
}
