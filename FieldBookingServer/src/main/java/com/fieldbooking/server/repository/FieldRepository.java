package com.fieldbooking.server.repository;

import com.fieldbooking.server.model.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByFacility_Id(Long id);
}
