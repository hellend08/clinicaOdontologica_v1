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

    // Register a new odontologist
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        logger.info("Registrando nuevo odontologo: " + odontologo);
        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontologo registrado con éxito: " + odontologoRegistrado);
        return ResponseEntity.ok(odontologoRegistrado);
    }

    // Find all odontologists
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        logger.info("Buscando todos los odontologos." );
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        logger.info("Se encontraron " + odontologos.size() + " odontologos.");
        return ResponseEntity.ok(odontologos);
    }

    // Find an odontologist by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Integer id) {
        logger.info("Buscando odontologo con ID: " + id);
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        if (odontologoEncontrado.isPresent()) {
            logger.info("Odontologo encontrado: " + odontologoEncontrado.get());
        } else {
            logger.warn("No se encontró un odontologo con ID: "+ id);
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
        return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
    }

    // Update an existing odontologist
    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
    }

    // Delete an odontologist by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Integer id) {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.noContent().build();
    }
}
