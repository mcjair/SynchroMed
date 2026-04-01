package com.synchromed.model;

/**
 * Clase alineada con los estándares de Programación Orientada a Objetos[cite: 97, 101].
 */
public class PersonalSalud {
    private int id;
    private String nombre;
    private int horasAcumuladas;

    public PersonalSalud(int id, String nombre, int horasAcumuladas) {
        this.id = id;
        this.nombre = nombre;
        this.horasAcumuladas = horasAcumuladas;
    }

    // Lógica de Negocio: Mitigación de riesgos y salud ocupacional
    public boolean puedeAtender() {
        return this.horasAcumuladas <= 8; 
    }
}
