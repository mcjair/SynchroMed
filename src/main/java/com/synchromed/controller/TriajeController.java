package com.synchromed.controller;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import com.synchromed.model.Paciente;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/triaje")
public class TriajeController {

    // Endpoint Web asociado al Sistema Frontend (AJAX)
    @PostMapping("/clasificar")
    public ResponseEntity<String> clasificarWeb(@RequestParam String nombre, @RequestParam String dni, @RequestParam String sintomas, @RequestParam double temp) {
        try {
            Paciente paciente = new Paciente(1, nombre, dni);
            String resultado = clasificarPaciente(paciente, sintomas, temp);
            return ResponseEntity.ok(resultado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Lógica de error: " + e.getMessage());
        }
    }

    /**
     * Procesa el triaje inicial. 
     * Implementa lógica de validación para asegurar datos íntegros. (Manteniendo compatibilidad Test)
     */
    public String clasificarPaciente(Paciente paciente, String sintomas, double temp) {
        // Validación con Google Guava
        Preconditions.checkNotNull(paciente, "El registro del paciente es obligatorio.");
        
        // Validación con Apache Commons
        if (StringUtils.isBlank(sintomas)) {
            return "ALERTA: Debe ingresar los síntomas para procesar el triaje.";
        }

        // Lógica de Bien Común: Clasificación automática
        if (temp > 39.0) {
            return "PRIORIDAD: ROJA (Atención Inmediata)";
        } else if (temp > 37.5) {
            return "PRIORIDAD: AMARILLA (Urgencia Menor)";
        }
        
        return "PRIORIDAD: VERDE (Consulta Externa)";
    }
}
