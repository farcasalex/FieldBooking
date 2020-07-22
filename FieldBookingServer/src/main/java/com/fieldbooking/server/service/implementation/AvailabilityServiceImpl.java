package com.fieldbooking.server.service.implementation;

import com.fieldbooking.server.model.entity.Availability;
import com.fieldbooking.server.repository.AvailabilityRepository;
import com.fieldbooking.server.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AbstractService<Availability> {

    private final AvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public Availability save(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public Optional<Availability> findById(Long id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        availabilityRepository.deleteById(id);
    }

    public List<Availability> findAllByFieldFacilityId(Long id){
        return availabilityRepository.findByFieldFacilityId(id);
    }
}
