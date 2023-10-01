package com.example.BrancoGarciaTingesoEvaluacion1.repositories;


import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByRut(String rut);
    @Query("SELECT s FROM StudentEntity s WHERE s.rut = :rut")
    Optional<StudentEntity> findByRutQuery(@Param("rut") String rut);
    Optional<StudentEntity> getStudentByRut(String rut);
}
