package com.synchromed.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping("/api/paciente")
public class PacienteAgendaController {

    // Simula un agente NLP (Procesamiento de Lenguaje) para determinar Especialidad
    @PostMapping("/agendar")
    public ResponseEntity<String> procesarReserva(
            @RequestParam String nombres,
            @RequestParam String dni,
            @RequestParam String fechaNacimiento,
            @RequestParam String sintomas) {

        if (StringUtils.isBlank(sintomas)) {
            return ResponseEntity.badRequest().body("Debe especificar sus síntomas para que el agente lo evalúe.");
        }

        String atencionDetectada = "Medicina General (Evaluación Inicial)"; // Por defecto
        String s = sintomas.toLowerCase();

        // Lógica del Agente: Patrones de síntomas clave
        if (s.contains("sangre") || s.contains("fractura") || s.contains("accidente") || 
            s.contains("desmayo") || s.contains("pecho") || s.contains("asfixia")) {
            atencionDetectada = "🚨 EMERGENCIA (Derivación Inmediata al Topico)";
        } else if (s.contains("fiebre") || s.contains("escalofrios") || s.contains("gripe")) {
            atencionDetectada = "🩺 INFECTOLOGÍA / MEDICINA INTERNA";
        } else if (s.contains("hueso") || s.contains("cintura") || s.contains("espalda") || s.contains("musculo")) {
            atencionDetectada = "🦴 TRAUMATOLOGÍA";
        } else if (s.contains("estomago") || s.contains("vomito") || s.contains("diarrea") || s.contains("nauseas")) {
            atencionDetectada = "🦠 GASTROENTEROLOGÍA";
        } else if (s.contains("ojo") || s.contains("vision") || s.contains("ver")) {
            atencionDetectada = "👁️ OFTALMOLOGÍA";
        } else if (s.contains("diente") || s.contains("muela") || s.contains("boca")) {
            atencionDetectada = "🦷 ODONTOLOGÍA";
        }

        String respuesta = "Hola, " + nombres + ".\n\n" +
                           "Basado en tus síntomas, nuestro Agente Inteligente ha determinado que la especialidad ideal es:\n\n" +
                           "➤ " + atencionDetectada + "\n\n" +
                           "Tu pre-registro hospitalario se ha procesado con tu DNI (" + dni + "). Pasa por recepción para confirmar tu turno.";

        return ResponseEntity.ok(respuesta);
    }
}
