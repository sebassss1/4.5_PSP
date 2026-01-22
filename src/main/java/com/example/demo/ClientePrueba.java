package com.example.demo;


import com.example.demo.entidades.Contacto;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ClientePrueba {

    private static final String BASE_URL = "http://localhost:8080/contactos";

    public static void main(String[] args) {

        // Crear un nuevo contacto
        Contacto nuevoContacto = new Contacto("Juan", "987654321");

        ClientePrueba cliente = new ClientePrueba();
        cliente.realizarPruebas(nuevoContacto);
    }

    public void realizarPruebas(Contacto nuevoContacto) {

        // Agregar contacto
        nuevoContacto = agregarContacto(nuevoContacto);
        System.out.println("Contacto agregado: " + nuevoContacto);

        // Listar contactos
        listarContactos();

        // Obtener contacto por ID
        obtenerContactoPorId(nuevoContacto.getId());

        // Eliminar contacto
        eliminarContacto(nuevoContacto.getId());

        // Listar contactos después de eliminar
        listarContactos();
    }

    private Contacto agregarContacto(Contacto contacto) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Contacto> response =
                restTemplate.postForEntity(BASE_URL, contacto, Contacto.class);
        return response.getBody();
    }

    private void listarContactos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Contacto[]> response =
                restTemplate.getForEntity(BASE_URL, Contacto[].class);

        Contacto[] contactos = response.getBody();
        System.out.println("Lista de contactos:");
        for (Contacto c : contactos) {
            System.out.println(c);
        }
    }

    private void obtenerContactoPorId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Contacto contacto =
                restTemplate.getForObject(BASE_URL + "/" + id, Contacto.class);

        System.out.println("Contacto obtenido por ID: " + contacto);
    }

    private void eliminarContacto(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(BASE_URL + "/" + id);
        System.out.println("Contacto eliminado con ID: " + id);
    }


}