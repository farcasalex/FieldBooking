package com.fieldbooking.server.service.implementation;

import com.fieldbooking.server.model.entity.Facility;
import com.fieldbooking.server.repository.FacilityRepository;
import com.fieldbooking.server.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl implements AbstractService<Facility> {

    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility save(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    @Override
    public Optional<Facility> findById(Long id) {
        return facilityRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        facilityRepository.deleteById(id);
    }

    public List<Facility> searchByAddress(String string) {
        return facilityRepository.findAll()
                .stream()
                .filter(facility -> facility.getAddress().getCity().contains(string)
                        || facility.getAddress().getCountry().contains(string)
                        || facility.getAddress().getRegion().contains(string)
                        || facility.getAddress().getStreet().contains(string)
                        || facility.getAddress().getNumber().contains(string)
                        || facility.getAddress().getZipCode().contains(string))
                .collect(Collectors.toList());
    }
}
