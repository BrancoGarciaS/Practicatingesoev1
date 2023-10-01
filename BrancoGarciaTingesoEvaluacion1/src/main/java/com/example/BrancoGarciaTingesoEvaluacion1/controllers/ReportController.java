package com.example.BrancoGarciaTingesoEvaluacion1.controllers;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.ReportEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping()
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/report")
    public String report_search_rut(){
        return "reportRut";
    }

    @PostMapping("/report")
    public ModelAndView report_rut(@RequestParam("rut_report") String rut_report){
        ReportEntity r = reportService.createReport(rut_report);
        ModelAndView modelAndView = new ModelAndView("reportRut");
        modelAndView.addObject("report", r);
        return modelAndView;
    }


}
