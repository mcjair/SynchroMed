package com.synchromed.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/api/paciente")
public class PacienteAgendaController {

    @PostMapping("/agendar")
    public ResponseEntity<String> procesarReserva(
            @RequestParam String nombres,
            @RequestParam String dni,
            @RequestParam String fechaNacimiento,
            @RequestParam String sintomas) {

        if (StringUtils.isBlank(sintomas)) {
            return ResponseEntity.badRequest().body("Debe especificar sus síntomas para que el agente lo evalúe.");
        }

        // CÁLCULO DE EDAD DETALLADO
        LocalDate fNac = LocalDate.parse(fechaNacimiento);
        Period edad = Period.between(fNac, LocalDate.now());
        String edadTexto = edad.getYears() + " años, " + edad.getMonths() + " meses y " + edad.getDays() + " días";

        // LÓGICA DEL AGENTE NLP
        String atencionDetectada = "Medicina General (Evaluación Inicial)"; 
        String color = "#3b82f6"; // Azul por defecto
        String s = sintomas.toLowerCase();

        if (s.contains("sangre") || s.contains("fractura") || s.contains("accidente") || 
            s.contains("desmayo") || s.contains("pecho") || s.contains("asfixia")) {
            atencionDetectada = "🚨 EMERGENCIA (Derivación Inmediata al Topico)";
            color = "#ef4444"; // Rojo
        } else if (s.contains("fiebre") || s.contains("escalofrios") || s.contains("gripe")) {
            atencionDetectada = "🩺 INFECTOLOGÍA / MEDICINA INTERNA";
            color = "#f59e0b"; // Naranja
        } else if (s.contains("hueso") || s.contains("cintura") || s.contains("espalda") || s.contains("musculo") || s.contains("dolor")) {
            atencionDetectada = "🦴 TRAUMATOLOGÍA";
            color = "#f59e0b"; // Naranja
        } else if (s.contains("estomago") || s.contains("vomito") || s.contains("diarrea") || s.contains("nauseas")) {
            atencionDetectada = "🦠 GASTROENTEROLOGÍA";
        }

        // Construcción de la respuesta minimalista y ordenada en HTML
        String htmlResponse = 
            "<div style='text-align: left; padding: 10px; font-size: 0.95rem; line-height: 1.6;'>" +
                "<b>Resumen del Paciente:</b><br>" +
                "👤 Nombre: " + nombres.toUpperCase() + "<br>" +
                "🆔 DNI: " + dni + "<br>" +
                "⏳ Edad Exacta: <span style='color: #94a3b8'>" + edadTexto + "</span><br><br>" +
                "<b>Diagnóstico de Derivación:</b><br>" +
                "Basado en tus síntomas, nuestro Agente Inteligente ha determinado:<br>" +
                "<div style='margin-top: 10px; padding: 12px; border-left: 4px solid " + color + "; background: rgba(255,255,255,0.05); border-radius: 4px;'>" +
                    "<span style='font-size:1.1em; font-weight: 600; color: " + color + ";'>➤ " + atencionDetectada + "</span>" +
                "</div><br>" +
                "<i style='color: #64748b;'>Su pre-registro hospitalario se ha procesado. Acerquese a recepción para confirmar el turno.</i>" +
            "</div>";

        return ResponseEntity.ok(htmlResponse);
    }
}
