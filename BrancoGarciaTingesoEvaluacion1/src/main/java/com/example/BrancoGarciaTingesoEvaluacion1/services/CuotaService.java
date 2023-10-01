package com.example.BrancoGarciaTingesoEvaluacion1.services;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Optional<CuotaEntity> getById(Long id){
        return cuotaRepository.findById(id);
    }

    public List<CuotaEntity> getCuotasByStudentId(Long studentId) {
        // Aquí puedes implementar la lógica para obtener las cuotas por ID de estudiante
        // Puedes usar cuotaRepository para consultar la base de datos
        // Asumiendo que tienes un método en CuotaRepository para buscar cuotas por ID de estudiante
        return cuotaRepository.findByAux(studentId);
    }

    public void pagarCuota(Long idCuota) {
        Optional<CuotaEntity> optionalCuota = cuotaRepository.findById(idCuota);
        if (optionalCuota.isPresent()) {
            CuotaEntity cuota = optionalCuota.get();
            cuota.setEstado_cuota(1); // Cambia el estado de pendiente a pagado
            cuota.setFecha_pago(LocalDate.now()); // Establece la fecha de pago actual
            cuotaRepository.save(cuota); // Guarda la cuota actualizada
        }
    }

    // para ver si la cuota esta atrasada
    public boolean isLate(CuotaEntity inst){
        //newData.setFecha_examen(LocalDate.now());

        // en el caso que se haya pagado la cuota
        LocalDate due_date = inst.getFecha_vencimiento();
        if(inst.getEstado_cuota() == 1){
            LocalDate pay_date = inst.getFecha_pago();
            // si la fecha de vencimiento de la cuota
            // es anterior a la fecha de pago, está atrasada
            if(due_date.isBefore(pay_date)){
                return true;
            }
        }
        // en caso que la cuota aun no haya sido pagada
        // y actualmente se ha sobrepasado la fecha de vencimiento
        else{
            // si la cuota tiene una fecha de vencimiento
            // que es anterior a la fecha actual
            if(due_date.isBefore(LocalDate.now())){
                return true;
            }
        }
        return false;
    }


    public CuotaEntity getCuotaById(Long cuotaId) {
        // Implementa la lógica para recuperar una cuota por su ID desde la base de datos
        // Por ejemplo:
        return cuotaRepository.findById_cuota(cuotaId);
        // Suponiendo que tienes un repositorio JPA llamado cuotaRepository
    }

    /*
    public List<CuotaEntity> getCuotasByRut(String rut_cuota){
        return cuotaRepository.findByRut_cuota(rut_cuota);
    }*/

    /*
    public List<CuotaEntity> getCuotasByStudentId(Long studentId) {
        // Aquí puedes implementar la lógica para obtener las cuotas por ID de estudiante
        // Puedes usar cuotaRepository para consultar la base de datos
        // Asumiendo que tienes un método en CuotaRepository para buscar cuotas por ID de estudiante
        return cuotaRepository.findById_Student(studentId);
    } */

    public ArrayList<CuotaEntity> getCuotasByRut(CuotaEntity installment){
        ArrayList<CuotaEntity> installments_list = cuotaRepository.findByRut_cuota(installment.getRut_cuota());
        return installments_list;
    }

    public void verAtrasos(String rut){

    }

}
