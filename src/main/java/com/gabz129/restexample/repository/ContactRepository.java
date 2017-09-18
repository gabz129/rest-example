package com.gabz129.restexample.repository;

import com.gabz129.restexample.entity.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    //Testing purpose
    @Query(value = "select contactId from Contact")
    List<Long> findIdAll();
}
