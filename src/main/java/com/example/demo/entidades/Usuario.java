package com.example.demo.entidades;

public class Usuario {
    private String username;
    private String encryptedPass;
    private Rol rol;

    public Usuario(String username, String encryptedPass, Rol rol) {
        this.username = username;
        this.encryptedPass = encryptedPass;
        this.rol = rol;
    }
    // Getters y Setters...
    public String getUsername() { return username; }
    public String getEncryptedPass() { return encryptedPass; }
    public Rol getRol() { return rol; }
}