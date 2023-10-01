package com.example.BrancoGarciaTingesoEvaluacion1.repositories;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.ExamenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExamenRepository extends CrudRepository<ExamenEntity, Long> {
    @Query("SELECT rut, AVG(puntaje_examen), COUNT(*) FROM ExamenEntity GROUP BY rut")
    List<Object[]> groupMean();
}
