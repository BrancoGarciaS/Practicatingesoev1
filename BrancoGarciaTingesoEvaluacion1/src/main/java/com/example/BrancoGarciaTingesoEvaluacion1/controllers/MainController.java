package com.example.BrancoGarciaTingesoEvaluacion1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MainController {
    @GetMapping()
    public String showRegister() {
        return "homepage";
    }
}
