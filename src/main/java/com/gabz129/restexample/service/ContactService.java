package com.gabz129.restexample.service;

import com.gabz129.restexample.entity.Address;
import com.gabz129.restexample.entity.Contact;
import com.gabz129.restexample.entity.Phone;
import com.gabz129.restexample.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    /**
     * Get specific contact by Id
     * @param id
     *      {@link Long} Contact id
     * @return {@link Contact} retrieved contact
     */
    public Contact retrieveContact(final Long id){
        requireNonNull(id, "Id is required");
        LOGGER.info("Retrieve contact from service retrieveContact");
        return contactRepository.findOne(id);
    }

    /**
     * List all contacts from db
     * @return {@link List} of {@link Contact}
     */
    public List<Contact> retrieveAllContacts(){
        LOGGER.info("Retrieve contacts from service retrieveAllContacts");
        return (List<Contact>)contactRepository.findAll();
//        return (List<Long>)contactRepository.findIdAll();
    }

    /**
     * Create a contact
     * @param contact
     *      {@link Contact} to be created
     */
    public void createContact(final Contact contact){
        requireNonNull(contact, "Contact is required");
        LOGGER.info("Create contact from service createContact");
        contactRepository.save(contact);
    }

    /**
     * Update a contact
     * @param contact
     *      {@link Contact} to be updated
     * @return {@link Contact} updated
     */
    public Contact updateContact(final Long id, final Contact contact){
        requireNonNull(contact, "Contact is required");
        LOGGER.info("Update contact from service updateContact");
        Contact contactUpdated = contactRepository.findOne(id);
        if(contactUpdated != null){
            contactUpdated.setName(contact.getName());
            contactUpdated.setBirthdate(contact.getBirthdate());
            contactUpdated.setCompany(contact.getCompany());
            contactUpdated.setEmail(contact.getEmail());
            contactUpdated.setFavorite(contact.getFavorite());
            contactUpdated.setLargeImageURL(contact.getLargeImageURL());
            contactUpdated.setSmallImageURL(contact.getSmallImageURL());
            contactUpdated.setWebsite(contact.getWebsite());
            //Phone
            Phone phoneUpdated = contactUpdated.getPhone();
            phoneUpdated.setHome(contact.getPhone().getHome());
            phoneUpdated.setMobile(contact.getPhone().getMobile());
            phoneUpdated.setWork(contact.getPhone().getWork());
            contactUpdated.setPhone(phoneUpdated);
            //Address
            Address addressUpdated = contactUpdated.getAddress();
            addressUpdated.setCity(contact.getAddress().getCity());
            addressUpdated.setCountry(contact.getAddress().getCountry());
            addressUpdated.setLatitude(contact.getAddress().getLatitude());
            addressUpdated.setLongitude(contact.getAddress().getLongitude());
            addressUpdated.setState(contact.getAddress().getState());
            addressUpdated.setStreet(contact.getAddress().getStreet());
            addressUpdated.setZip(contact.getAddress().getZip());
            contactUpdated.setAddress(addressUpdated);
            return contactRepository.save(contactUpdated);
        }
        return contactUpdated;
    }

    /**
     * Delete contact by Id
     * @param id
     *      {@link Long} id of the contact
     */
    public void deleteContact(final Long id){
        requireNonNull(id, "Id is required");
        LOGGER.info("Delete contact from service deleteContact");
        contactRepository.delete(id);
    }
}
