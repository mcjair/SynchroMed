package com.synchromed.service;

import com.synchromed.controller.TriajeController;
import com.synchromed.model.Paciente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriajeTest {
    
    @Test
    public void testClasificacionPrioridadRoja() {
        TriajeController controller = new TriajeController();
        Paciente p = new Paciente(1, "Juan Perez", "12345678", "Estable");
        
        // Simulación de temperatura alta (Emergencia)
        String resultado = controller.clasificarPaciente(p, "Fiebre alta y dolor de pecho", 39.5);
        
        assertEquals("PRIORIDAD: ROJA (Atención Inmediata)", resultado, 
            "El sistema debe detectar emergencias críticas por temperatura.");
    }
}
