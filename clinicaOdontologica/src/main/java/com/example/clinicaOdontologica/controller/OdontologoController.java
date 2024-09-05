package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    private static final Logger logger = LogManager.getLogger(OdontologoController.class);

    // Registrar nuevo odontólogo
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        logger.info("Registrando nuevo odontólogo: " + odontologo);
        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontólogo registrado con éxito: " + odontologoRegistrado);
        return ResponseEntity.ok(odontologoRegistrado);
    }

    // Buscar todos los odontólogos
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        logger.info("Buscando todos los odontólogos." );
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        logger.info("Se encontraron " + odontologos.size() + " odontólogos.");
        return ResponseEntity.ok(odontologos);
    }

    // Buscando un odontólogo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Integer id) {
        logger.info("Buscando odontólogo con ID: " + id);
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        if (odontologoEncontrado.isPresent()) {
            logger.info("Odontólogo encontrado: " + odontologoEncontrado.get());
        } else {
            logger.warn("No se encontró un odontólogo con ID: "+ id);
        }
        return ResponseEntity.ok(odontologoEncontrado);
    }

    // Find an odontologist by name
    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<Optional<Odontologo>> buscarPorNombre(@PathVariable String nombre) {
        logger.info("Buscando odontologo con nombre: " + nombre);
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorNombre(nombre);
        if (odontologoEncontrado.isPresent()) {
            logger.info("Odontologo encontrado: ");
        }
        return ResponseEntity.ok(odontologoEncontrado);
    }

    // Find an odontologist by matricula (license number)
    @GetMapping("/buscarPorMatricula/{matricula}")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@PathVariable String matricula) {
        logger.info("Buscando odontólogo con matricula: " + matricula);
        Optional<Odontologo> odontologoMatricula = odontologoService.buscarPorMatricula(matricula);
        if (odontologoMatricula.isPresent()) {
            logger.info("Odontólogo encontrado: " + odontologoMatricula.get());
        } else {
            logger.warn("No se encontró un odontólogo con matrícula: " + matricula);
        }
        return ResponseEntity.ok(odontologoMatricula);
    }

    // Update an existing odontologist
    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        logger.info("Actualizando odontologo: " + odontologo);
        Odontologo odontologoActualizado = odontologoService.actualizarOdontologo(odontologo);
        logger.info("Odontologo actualizado exitosamente: " + odontologoActualizado);
        return ResponseEntity.ok(odontologoActualizado);
    }

    // Delete an odontologist by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Integer id) {
        logger.info("Eliminando odontologo con id: " + id);
        odontologoService.eliminarOdontologo(id);
        logger.info("Odontologo con ID " + id + " eliminado exitosamente.");
        return ResponseEntity.noContent().build();
    }
}
