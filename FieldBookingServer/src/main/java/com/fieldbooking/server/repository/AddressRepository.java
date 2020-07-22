package com.fieldbooking.server.repository;

import com.fieldbooking.server.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
