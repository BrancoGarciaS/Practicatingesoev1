package com.example.BrancoGarciaTingesoEvaluacion1.services;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    // para mostrar los datos de todos los estudiantes (get)
    public ArrayList<StudentEntity> getStudents(){
        return (ArrayList<StudentEntity>) studentRepository.findAll();
    }

    // para guardar los datos de los estudiantes (post)
    public StudentEntity saveStudents(StudentEntity usuario){
        return studentRepository.save(usuario);
    }




    //para obtener estudiante por id
    public Optional<StudentEntity> getById(Long id){
        return studentRepository.findById(id);
    }
    public Optional<StudentEntity> getByRut(String rut){
        return studentRepository.findByRut(rut);
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
