package com.synchromed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebDashboardController {

    @GetMapping("/")
    public String index(Model model) {
        // Exponemos las variables al frontend (Thymeleaf) - Concepto de Semana 1
        model.addAttribute("tituloPlataforma", "SynchroMed Server");
        model.addAttribute("estadoServidor", "ÓPTIMO - En línea");
        model.addAttribute("usuarioResponsable", "Administrador / Recepción");
        model.addAttribute("limiteHorasLaborales", "4 / 8 HRs");
        
        return "index";
    }
}
