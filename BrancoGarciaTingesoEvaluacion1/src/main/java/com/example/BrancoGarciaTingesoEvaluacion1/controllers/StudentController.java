package com.example.BrancoGarciaTingesoEvaluacion1.controllers;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.services.CalculoCuotas;
import com.example.BrancoGarciaTingesoEvaluacion1.services.CuotaService;
import com.example.BrancoGarciaTingesoEvaluacion1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping()
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    CalculoCuotas calculoCuotas;

    @Autowired
    CuotaService cuotaService;

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    // Para obtener todos los estudiantes
    @GetMapping("/get_students")
    @ResponseBody
    public ArrayList<StudentEntity> getStudents(){
        return studentService.getStudents();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public Optional<StudentEntity> getStudent_ID(@PathVariable long id){
        return studentService.getStudenById(id);
    }



    @PostMapping("/get_rut_student")
    @ResponseBody
    public StudentEntity getByRut(@RequestBody StudentEntity student){
        return studentService.getByRut(student);
    }

    @PostMapping("/cuotas/rut")
    @ResponseBody
    public List<CuotaEntity> getCuotasBy_Rut(@RequestBody StudentEntity student){
        return studentService.getCuotasByRut(student);
    }


    // Para registrar a un estudiante
    @PostMapping("/register_student")
    @ResponseBody
    public ModelAndView saveStudent(Model model, @RequestParam("name") String name,
                                    @RequestParam("last_name") String lastName,
                                    @RequestParam("email") String email,
                                    @RequestParam("rut") String rut,
                                    @RequestParam("senior_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date seniorDate,
                                    @RequestParam("senior_year") int senior_year,
                                    @RequestParam("school_name") String schoolName,
                                    @RequestParam("school_type") Long schoolType,
                                    @RequestParam("installments") Integer installments) {
        StudentEntity student = new StudentEntity();
        student.setName(name);
        student.setLast_name(lastName);
        student.setEmail(email);
        student.setRut(rut);
        student.setSenior_date(seniorDate); // fecha de nacimiento
        student.setSenior_year(senior_year);
        student.setSchool_name(schoolName);
        student.setSchool_type(schoolType);
        student.setNum_exams(0); // al principio tendría 0 examenes rendidos

        if(installments > 0){
            student.setPayment_type(1); // pago en cuotas
        }
        if(installments == 0){
            student.setPayment_type(0); // pago al contado
        }
         // tipo de pago (iniciara por defecto como contado)
        if(schoolType == 2 && installments > 7){
            student.setInstallments(7);
        }
        else if(schoolType == 3 && installments > 4){
            student.setInstallments(4);
        }
        else{
            student.setInstallments(installments); // iniciará con 0 cuotas pagas en total
        }

        Integer arancel = calculoCuotas.arancel_con_descuento(student);
        student.setTariff(arancel); // valor del arancel
        student.setExams_mean(0); // promedio de examenes
        student.setScore((double) 0);

        StudentEntity student_saved = studentService.saveStudents(student);
        if(student.getPayment_type() == 1){ // si paga en cuotas
            List<CuotaEntity> cuotas = new ArrayList<>();
            float monto_por_cuota = (float) student.getTariff() / student.getInstallments();
            for (int i = 0; i < student.getInstallments(); i++) {
                CuotaEntity cuota = new CuotaEntity();
                // Calcula la fecha de vencimiento al día 10 de cada mes
                int year = LocalDate.now().getYear();
                int month = LocalDate.now().getMonthValue() + i + 1;

                // Ajusta el año y el mes si se desborda
                if (month > 12) {
                    year += (month - 1) / 12;
                    month = (month - 1) % 12 + 1;
                }
                LocalDate fechaVencimiento = LocalDate.of(year, month, 10);
                // Calcula la fecha de inicio al día 5 de cada mes
                LocalDate fechaInicio = LocalDate.of(year, month, 5);
                cuota.setFecha_vencimiento(fechaVencimiento);
                cuota.setFecha_inicio(fechaInicio);
                cuota.setEstado_cuota(0); // 0 para pendiente (pago pendiente)
                cuota.setMonto(monto_por_cuota);
                cuota.setEstudiante(student_saved.getId_Student());
                cuota.setRut_cuota(rut);

                cuotaService.saveData(cuota);
                cuotas.add(cuota);
            }
            student.setCuotas(cuotas);
        }


        StudentEntity s = studentService.saveStudents(student_saved);
        ModelAndView modelAndView = new ModelAndView("student-details");
        modelAndView.addObject("student", s);
        return modelAndView;

        //model.addAttribute("student", student);
        //return "Registro con éxito";
    }

    // Para guardar los datos de un estudiante
    @PostMapping("/post_student")
    @ResponseBody
    public StudentEntity saveStudent(@RequestBody StudentEntity student) {
        return studentService.saveStudents(student);
    }



    // Para eliminar los datos de un estudiante (por id)
    @DeleteMapping("/delete_student/{id}")
    @ResponseBody
    public String deleteAreaById(@PathVariable Long id){
        return studentService.deleteById(id);
    }

    @GetMapping("/search_by_student")
    public String showStudentByRut() {
        return "search_student";
    }

    @PostMapping("/studentRut")
    @ResponseBody
    public ModelAndView saveStudent(@RequestParam("rut") String rut) {
        StudentEntity student = new StudentEntity();
        student.setRut(rut);
        StudentEntity s = studentService.getByRut(student);

        CuotaEntity installment_1 = new CuotaEntity();
        installment_1.setRut_cuota(rut);
        ArrayList<CuotaEntity> c = cuotaService.getCuotasByRut(installment_1);

        ModelAndView modelAndView = new ModelAndView("search_student");
        modelAndView.addObject("student", s);
        modelAndView.addObject("cuotas", c);
        return modelAndView;
        //return "search_rut";
    }

}
