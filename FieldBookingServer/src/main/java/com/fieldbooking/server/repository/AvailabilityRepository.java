package com.fieldbooking.server.repository;

import com.fieldbooking.server.model.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByFieldFacilityId(Long id);
}
