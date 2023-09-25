package com.example.BrancoGarciaTingesoEvaluacion1.services;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuotaService {
    @Autowired
    CuotaRepository cuotaRepository;

    public List<CuotaEntity> getAll(){
        return (List<CuotaEntity>) cuotaRepository.findAll();
    }

    public CuotaEntity saveData(CuotaEntity cuota){
        return cuotaRepository.save(cuota);
    }

    /*
    public List<CuotaEntity> getCuotasByStudentId(Long studentId) {
        // Aquí puedes implementar la lógica para obtener las cuotas por ID de estudiante
        // Puedes usar cuotaRepository para consultar la base de datos
        // Asumiendo que tienes un método en CuotaRepository para buscar cuotas por ID de estudiante
        return cuotaRepository.findById_Student(studentId);
    } */



}
