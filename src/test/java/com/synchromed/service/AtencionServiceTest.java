package com.synchromed.service;

import com.synchromed.model.PersonalSalud;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba de Software (Semana 13): Validando la regla de negocio de prevención de Burnout.
 */
public class AtencionServiceTest {

    @Test
    public void testValidarCargaLaboral_Excedida() {
        // Simulamos un médico con 9 horas de trabajo (Límite es 8)
        PersonalSalud medicoSobrecargado = new PersonalSalud(1, "Dr. Pérez", 9);
        
        // La lógica debe retornar 'false' para proteger al profesional y al paciente
        assertFalse(medicoSobrecargado.puedeAtender(), 
            "El sistema debería bloquear la atención si el médico excede las 8 horas.");
    }

    @Test
    public void testValidarCargaLaboral_Optima() {
        PersonalSalud medicoDisponible = new PersonalSalud(2, "Dra. Gomez", 4);
        
        assertTrue(medicoDisponible.puedeAtender(), 
            "El sistema debe permitir la atención si el médico está dentro de su horario.");
    }
}
