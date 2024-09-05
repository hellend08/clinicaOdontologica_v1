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

    // Buscar un odontólogo por nombre
    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<Optional<Odontologo>> buscarPorNombre(@PathVariable String nombre) {
        logger.info("Buscando odontólogo con nombre: " + nombre);
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorNombre(nombre);
        if (odontologoEncontrado.isPresent()) {
            logger.info("Odontólogo encontrado: ");
        }
        return ResponseEntity.ok(odontologoEncontrado);
    }

    // Buscar odontologo por matrícula (license number)
    @GetMapping("/buscarPorMatricula/{matricula}")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@PathVariable String matricula) {
        logger.info("Buscando odontólogo con matrícula: " + matricula);
        Optional<Odontologo> odontologoMatricula = odontologoService.buscarPorMatricula(matricula);
        if (odontologoMatricula.isPresent()) {
            logger.info("Odontólogo encontrado: " + odontologoMatricula.get());
        } else {
            logger.warn("No se encontró un odontólogo con matrícula: " + matricula);
        }
        return ResponseEntity.ok(odontologoMatricula);
    }

    // Actualizando odontólogo existente
    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        logger.info("Actualizando odontólogo: " + odontologo);
        Odontologo odontologoActualizado = odontologoService.actualizarOdontologo(odontologo);
        logger.info("Odontólogo actualizado exitosamente: " + odontologoActualizado);
        return ResponseEntity.ok(odontologoActualizado);
    }

    // Eliminando odontólogo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Integer id) {
        logger.info("Eliminando odontólogo con id: " + id);
        odontologoService.eliminarOdontologo(id);
        logger.info("Odontólogo con ID " + id + " eliminado con éxito.");
        return ResponseEntity.noContent().build();
    }
}
