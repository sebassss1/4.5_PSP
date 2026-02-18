package com.example.demo.entidades;

public class Contacto {
    private Long id;
    private String nombre;
    private String telefono;
    private String email;

    // Constructor 1: Vacío (Para Spring/JSON)
    public Contacto() {}

    // Constructor 2: Con 4 parámetros (Para AgendaController y Seguridad)
    public Contacto(Long id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Constructor 3: Con 2 parámetros (ESTE ES EL QUE NECESITA ClientePrueba)
    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // ... Mantén todos tus Getters y Setters abajo ...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}