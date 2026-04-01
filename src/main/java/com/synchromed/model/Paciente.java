package com.synchromed.model;

public class Paciente {
    private int idPaciente;
    private String nombre;
    private String dni;
    private int nivelPrioridad;

    public Paciente(int idPaciente, String nombre, String dni) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.dni = dni;
    }

    public int getIdPaciente() { return idPaciente; }
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public int getNivelPrioridad() { return nivelPrioridad; }
    public void setNivelPrioridad(int nivelPrioridad) { this.nivelPrioridad = nivelPrioridad; }
}
