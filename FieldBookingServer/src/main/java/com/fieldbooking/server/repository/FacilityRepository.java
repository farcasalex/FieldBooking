package com.fieldbooking.server.repository;

import com.fieldbooking.server.model.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
