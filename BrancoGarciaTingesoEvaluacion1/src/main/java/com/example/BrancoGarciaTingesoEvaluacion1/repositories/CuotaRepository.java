package com.example.BrancoGarciaTingesoEvaluacion1.repositories;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaRepository extends CrudRepository<CuotaEntity, Long> {
    //List<CuotaEntity> findById_Student(Long studentId);
}

