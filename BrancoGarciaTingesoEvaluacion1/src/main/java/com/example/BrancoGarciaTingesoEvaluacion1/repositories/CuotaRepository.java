package com.example.BrancoGarciaTingesoEvaluacion1.repositories;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CuotaRepository extends CrudRepository<CuotaEntity, Long> {
    //List<CuotaEntity> findById_Student(Long studentId);

    //ArrayList<CuotaEntity> findByRut_cuota(String rut_cuota);

    List<CuotaEntity> findByAux(Long aux);


    @Query("SELECT c FROM CuotaEntity c WHERE c.rut_cuota = :rut_cuota")
    ArrayList<CuotaEntity> findByRut_cuota(@Param("rut_cuota") String rut_cuota);


    @Query("SELECT c FROM CuotaEntity c WHERE c.id_cuota = :id_cuota")
    CuotaEntity findById_cuota(Long id_cuota);


    //@Query(value = "SELECT * FROM usuarios WHERE usuarios.email = :email", nativeQuery = true)
    //UsuarioEntity findByEmailNativeQuery(@Param("email") String email);
    //@Query(value = "SELECT * FROM cuotas WHERE id_Student = :studentId")
    //List<CuotaEntity> findById_StudentQuery(Long studentId);
}


