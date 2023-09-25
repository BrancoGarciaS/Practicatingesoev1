package com.example.BrancoGarciaTingesoEvaluacion1.controllers;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.services.CuotaService;
import com.example.BrancoGarciaTingesoEvaluacion1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

/*
@Controller
@RequestMapping("/cuota")
public class CuotaController {
    //@Autowired
    //private StudentService studentService; // Asegúrate de que tengas una referencia al servicio de estudiantes.

    @Autowired
    private CuotaService cuotaService;

    /*
    @GetMapping("/{studentId}/cuotas")
    public String showCuotas(@PathVariable Long studentId, Model model) {
        // Obtén el estudiante por su ID
        Optional<StudentEntity> student = studentService.getById(studentId);

        if (student.isPresent()) {
            // Obtén la lista de cuotas asociadas al estudiante
            List<CuotaEntity> cuotas = student.get().getCuotas();

            // Agrega la lista de cuotas al modelo para mostrarlas en la vista
            model.addAttribute("cuotas", cuotas);

            return "cuotas-list"; // Nombre de la vista que mostrará las cuotas
        } else {
            // Manejo de error si el estudiante no se encuentra
            model.addAttribute("errorMessage", "Estudiante no encontrado");
            return "error-page"; // Puedes crear una vista de error personalizada
        }
    }*/

    /*
    @RequestMapping("/cuotas/{studentId}")
    public String mostrarCuotasPorEstudiante(@PathVariable Long studentId, Model model) {
        List<CuotaEntity> cuotas = cuotaService.getCuotasByStudentId(studentId);
        model.addAttribute("cuotas", cuotas);
        return "cuotas-list"; // Asegúrate de que la vista JSP se llame "cuotas-list.jsp"
    } */

//}
