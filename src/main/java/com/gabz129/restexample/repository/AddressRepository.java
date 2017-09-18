package com.gabz129.restexample.repository;

import com.gabz129.restexample.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
