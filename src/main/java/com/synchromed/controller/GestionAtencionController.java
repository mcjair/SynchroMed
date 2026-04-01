package com.synchromed.controller;

import com.synchromed.model.PersonalSalud;
import com.synchromed.dao.InsumoDAO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/agenda")
public class GestionAtencionController {
    private InsumoDAO insumoDao = new InsumoDAO();

    @PostMapping("/programar")
    public ResponseEntity<String> programarWeb(@RequestParam String medicoNombre, @RequestParam int horasActuales, @RequestParam String procedimiento) {
        PersonalSalud medico = new PersonalSalud(1, medicoNombre, horasActuales);
        String msg = programarAtencion(medico, procedimiento);
        return ResponseEntity.ok(msg);
    }

    public String programarAtencion(PersonalSalud medico, String procedimiento) {
        // 1. Validar Salud del Profesional (Bien Común)
        if (!medico.puedeAtender()) {
            return "ERROR: Médico no disponible por exceso de carga laboral o estrés (>8hr).";
        }

        // 2. Validar Recursos (Mitigación de Tiempo)
        if (!insumoDao.hayStockSuficiente(procedimiento)) {
            return "ALERTA: Insumos insuficientes. Reabastecimiento requerido.";
        }

        return "CITA SINCRONIZADA: Procediendo con la atención de " + procedimiento + " en SynchroMed.";
    }
}
