package com.example.BrancoGarciaTingesoEvaluacion1.controllers;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.ExamenEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.ExamenRepository;
import com.example.BrancoGarciaTingesoEvaluacion1.services.ExamenService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Controller
@RequestMapping("/examen")
public class ExamenController {
    @Autowired
    ExamenService examenService;
    @Autowired
    private ExamenRepository examenRepository;

    /*
    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        examenService.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        examenService.leerCsv("Acopio.csv");
        return "redirect:/fileUpload";
    }*/

    @PostMapping("/subirExcel")
    public String subirExcel(@RequestParam("file") MultipartFile file){
        examenService.guardar(file);
        String filename = file.getOriginalFilename();
        examenService.leerCsv(filename);
        return "excel_page";
    }

    /*
    @GetMapping("/prom")
    public ArrayList<ExamenEntity> promedios(){
        return examenService.meanRut();
    }*/


    /*
    @PostMapping("/subirExcel")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        examenService.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        examenService.leerCsv("Acopio.csv");
        return "redirect:/subirExcel";
    }*/



    /*
    @PostMapping("/subir-excel")
    public String subirExcel(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int count = 0; // contador para ignorar la primera linea del archivo
            while ((line = bufferedReader.readLine()) != null && count > 0) {
                // Procesa cada línea del archivo aquí
                String[] datos = line.split(";"); // Suponiendo que los datos estén separados por punto y coma

                if (datos.length == 3) { // Asegúrate de que la línea tenga el formato correcto
                    ExamenEntity examen = new ExamenEntity();
                    examen.setRut(datos[0]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    examen.setFecha_examen(LocalDate.parse(datos[1], formatter));

                    examen.setPuntaje_examen(Float.parseFloat(datos[2])); // Convierte el puntaje a flotante

                    // Guardar el examen en la base de datos
                    examenService.saveData(examen);
                }
                count++;
            }

            bufferedReader.close();
            String s = "Archivo Excel subido y procesado con éxito.";
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar el archivo Excel: " + e.getMessage());
            String s = "Error al cargar el archivo Excel.";
        }
        return "excel_page";
    } */



    @GetMapping("/excelExamenes")
    public String excel(){
        return "excel_page";
    }

}
