package com.example.BrancoGarciaTingesoEvaluacion1.controllers;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.services.CuotaService;
import com.example.BrancoGarciaTingesoEvaluacion1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/cuota")
public class CuotaController {
    @Autowired
    private CuotaService cuotaService;

    @GetMapping("/get")
    @ResponseBody
    public List<CuotaEntity> getCuota(){
        return cuotaService.getAll();
    }

    // Para obtener fila por id
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<CuotaEntity> getCuotaById(@PathVariable Long id){
        return cuotaService.getById(id);
    }


    @GetMapping("/estudiante/{studentId}")
    @ResponseBody
    public List<CuotaEntity> mostrarCuotasPorEstudiante(@PathVariable Long studentId) {
        return cuotaService.getCuotasByStudentId(studentId);
    }

    /*
    @GetMapping("/rut/{rut}")
    @ResponseBody
    public List<CuotaEntity> mostrarCuotasPorRut(@PathVariable String rut_cuota) {
        return cuotaService.getCuotasByRut(rut_cuota);
    }

    @PostMapping("/buscar")
    public String buscarCuotasPorRut(@RequestParam("rut") String rutCuota, Model model) {
        List<CuotaEntity> cuotas = cuotaService.getCuotasByRut(rutCuota);
        model.addAttribute("cuotas", cuotas);
        return "cuotas-list"; // Nombre de la vista que mostrará los resultados
    }*/



    // este funciona
    @PostMapping("/cuotasPorRut")
    @ResponseBody
    public List<CuotaEntity> cuotasRut(@RequestBody CuotaEntity cuota){
        return cuotaService.getCuotasByRut(cuota);
    }

    // http://localhost:8000/cuota/cuotasPorRut



    @PostMapping("/lascuotasRut")
    @ResponseBody
    public ModelAndView saveStudent(Model model, @RequestParam("rut_cuota") String rut_cuota) {
        CuotaEntity installment_1 = new CuotaEntity();
        installment_1.setRut_cuota(rut_cuota);
        // return cuotaService.getCuotasByRut(installment_1);
        ArrayList<CuotaEntity> c = cuotaService.getCuotasByRut(installment_1);

        ModelAndView modelAndView = new ModelAndView("search_rut");
        modelAndView.addObject("cuotas", c);
        return modelAndView;
        //return "search_rut";
    }

    // funciona
    @GetMapping("/search_by_rut")
    public String showRegister() {
        return "search_rut";
    }


    /*
    @PostMapping("/pagar/{idCuota}")
    public ResponseEntity<String> pagarCuota(@PathVariable Long idCuota) {
        cuotaService.pagarCuota(idCuota);
        return ResponseEntity.ok("Cuota pagada exitosamente");
    }
    */

    /*
    @PostMapping("/pagar/{idCuota}")
    public void pagarCuota(@PathVariable Long idCuota) {
        cuotaService.pagarCuota(idCuota);
    }*/


    @PostMapping("/pagar")
    public String pagarCuota(@RequestParam("cuotaId") Long cuotaId) {
        // Obtener la cuota por su ID desde el servicio
        CuotaEntity cuota = cuotaService.getCuotaById(cuotaId);

        if (cuota == null) {
            // Manejar el caso en el que la cuota no existe (puedes redirigir o mostrar un mensaje de error)
            return "redirect:/pagina_de_error";
        }

        // Realizar el procesamiento de pago aquí
        // Esto podría incluir la actualización del estado de la cuota a "pagado"
        cuota.setEstado_cuota(1); // 1 para pagado
        cuota.setFecha_pago(new Date()); // Establecer la fecha de pago actual, por ejemplo

        // Guardar la cuota actualizada en la base de datos
        cuotaService.saveData(cuota);

        // Redirigir a una página de éxito o donde desees
        return "search_rut";
    }
}
