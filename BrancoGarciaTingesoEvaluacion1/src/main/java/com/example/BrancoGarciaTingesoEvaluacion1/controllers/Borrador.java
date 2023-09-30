package com.example.BrancoGarciaTingesoEvaluacion1.controllers;

public class Borrador {
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
}
