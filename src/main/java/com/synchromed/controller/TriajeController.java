package com.synchromed.controller;

import com.google.common.base.Preconditions; // Google Guava (Semana 10)
import org.apache.commons.lang3.StringUtils; // Apache Commons (Semana 10)
import com.synchromed.model.Paciente;

public class TriajeController {

    /**
     * Procesa el triaje inicial. 
     * Implementa lógica de validación para asegurar datos íntegros.
     */
    public String clasificarPaciente(Paciente paciente, String sintomas, double temp) {
        // Validación con Google Guava: El paciente no puede ser nulo
        Preconditions.checkNotNull(paciente, "El registro del paciente es obligatorio.");
        
        // Validación con Apache Commons: Síntomas no pueden estar vacíos
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
