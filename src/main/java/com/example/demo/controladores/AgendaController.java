package com.example.demo.controladores;


import org.springframework.web.bind.annotation.*;
import com.example.demo.entidades.Contacto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contactos")
public class AgendaController {

    private List<Contacto> contactos = new ArrayList<>(List.of(
            new Contacto(1L, "Aitor Tilla", "600111222", "aitor@mail.com"),
            new Contacto(2L, "Elena Nito", "600333444", "elena@mail.com")
    ));

    // Obtener todos los contactos (Para probar el GET)
    @GetMapping
    public List<Contacto> obtenerTodos() {
        return contactos;
    }

    // --- PARTE 2: MODIFICACIÓN (PUT) ---
    @PutMapping("/{id}")
    public Contacto modificarContacto(@PathVariable Long id, @RequestBody Contacto contactoActualizado) {
        Optional<Contacto> contactoExistente = contactos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (contactoExistente.isPresent()) {
            Contacto c = contactoExistente.get();
            c.setNombre(contactoActualizado.getNombre());
            c.setTelefono(contactoActualizado.getTelefono());
            c.setEmail(contactoActualizado.getEmail());
            return c;
        } else {
            // Si no existe, podrías lanzar una excepción o manejar el error
            return null;
        }
    }
}