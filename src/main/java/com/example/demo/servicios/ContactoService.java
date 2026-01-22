package com.example.demo.servicios;

import com.example.demo.entidades.Contacto;

import java.util.List;

public interface ContactoService {
    List<Contacto> obtenerTodos();
    Contacto obtenerPorId(Long id);
    Contacto guardar(Contacto contacto);
    void eliminar(Long id);
}
