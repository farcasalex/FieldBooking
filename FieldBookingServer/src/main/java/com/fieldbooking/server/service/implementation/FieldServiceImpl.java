package com.fieldbooking.server.service.implementation;

import com.fieldbooking.server.model.entity.Field;
import com.fieldbooking.server.repository.FieldRepository;
import com.fieldbooking.server.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FieldServiceImpl implements AbstractService<Field> {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Field save(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    @Override
    public Optional<Field> findById(Long id) {
        return fieldRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        fieldRepository.deleteById(id);
    }

    public List<Field> searchByAddress(String string) {
        return fieldRepository.findAll()
                .stream()
                .filter(field -> field.getFacility().getAddress().getCity().contains(string)
                        || field.getFacility().getAddress().getCountry().contains(string)
                        || field.getFacility().getAddress().getRegion().contains(string)
                        || field.getFacility().getAddress().getStreet().contains(string)
                        || field.getFacility().getAddress().getNumber().contains(string)
                        || field.getFacility().getAddress().getZipCode().contains(string))
                .collect(Collectors.toList());
    }

    public List<Field> findByFacilityId(Long id){
        return fieldRepository.findByFacility_Id(id);
    }
}
