package com.synchromed.controller;

import com.synchromed.model.PersonalSalud;
import com.synchromed.dao.InsumoDAO;

public class GestionAtencionController {
    private InsumoDAO insumoDao = new InsumoDAO();

    public String programarAtencion(PersonalSalud medico, String procedimiento) {
        // 1. Validar Salud del Profesional (Bien Común)
        if (!medico.puedeAtender()) {
            return "ERROR: Médico no disponible por exceso de carga laboral.";
        }

        // 2. Validar Recursos (Mitigación de Tiempo)
        if (!insumoDao.hayStockSuficiente(procedimiento)) {
            return "ALERTA: Insumos insuficientes. Reabastecimiento requerido.";
        }

        return "CITA SINCRONIZADA: Procediendo con la atención en SynchroMed.";
    }
}
