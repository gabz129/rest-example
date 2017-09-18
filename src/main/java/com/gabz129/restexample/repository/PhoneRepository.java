package com.gabz129.restexample.repository;

import com.gabz129.restexample.entity.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Long> {

}
