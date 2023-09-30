package com.example.BrancoGarciaTingesoEvaluacion1.repositories;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.ExamenEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExamenRepository extends CrudRepository<ExamenEntity, Long> {
    @Query("SELECT rut, AVG(puntaje_examen), COUNT(*) FROM ExamenEntity GROUP BY rut")
    List<Object[]> groupMean();
}
