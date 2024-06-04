package com.swaad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaad.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
