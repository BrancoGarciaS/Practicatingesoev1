package com.example.BrancoGarciaTingesoEvaluacion1.controllers;

import com.example.BrancoGarciaTingesoEvaluacion1.repositories.ExamenRepository;
import com.example.BrancoGarciaTingesoEvaluacion1.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/examen")
public class ExamenController {
    @Autowired
    ExamenService examenService;
    @Autowired
    private ExamenRepository examenRepository;

    @PostMapping("/subirExcel")
    public String subirExcel(@RequestParam("file") MultipartFile file){
        examenService.guardar(file);
        String filename = file.getOriginalFilename();
        examenService.leerCsv(filename);
        return "excel_page";
    }

    @GetMapping("/calcularPromedio")
    public String calculoProm(){
        examenService.asignarPromedios();
        return "excel_page";
    }

    @GetMapping("/excelExamenes")
    public String excel(){
        return "excel_page";
    }

}
