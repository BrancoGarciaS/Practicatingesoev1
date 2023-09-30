package com.example.BrancoGarciaTingesoEvaluacion1.services;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    // para mostrar los datos de todos los estudiantes (get)
    public ArrayList<StudentEntity> getStudents(){
        return (ArrayList<StudentEntity>) studentRepository.findAll();
    }

    public Optional<StudentEntity> getStudenById(long id){
        return studentRepository.findById(id);
    }

    // para guardar los datos de los estudiantes (post)
    public StudentEntity saveStudents(StudentEntity usuario){
        return studentRepository.save(usuario);
    }




    //para obtener estudiante por id
    public Optional<StudentEntity> getById(Long id){
        return studentRepository.findById(id);
    }
    public StudentEntity getByRut(StudentEntity student){
        try {
            // usuario al que se le quiere buscar por rut
            StudentEntity student_rut = studentRepository.getStudentByRut(student.getRut()).get();
            if (student_rut.getRut().equals(student.getRut())) {
                // en caso que coincidan los ruts se retorna el estudiante de la base de datos
                return student_rut;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
        //return studentRepository.findByRutQuery(rut);
    }



    public List<CuotaEntity> getCuotasByRut(StudentEntity student){
        try {
            // usuario al que se le quiere buscar por rut
            StudentEntity student_rut = studentRepository.getStudentByRut(student.getRut()).get();
            if (student_rut.getRut().equals(student.getRut())) {
                // en caso que coincidan los ruts se retorna las cuotas del estudiante de la base de datos
                return student_rut.getCuotas();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
        //return studentRepository.findByRutQuery(rut);
    }


    // para borrar un estudiante por id
    public String deleteById (Long id){
        try {
            studentRepository.deleteById(id);
            return "Usuario eliminado";
        } catch (Exception e) {
            return "No existe usuario con este ID";
        }
    }

}
