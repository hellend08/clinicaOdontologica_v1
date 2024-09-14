package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.exception.ResourceNotFoundException;
import com.example.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger = LogManager.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;

    // Registrar un nuevo paciente
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        logger.info("Iniciando el registro de un nuevo paciente: " + paciente);
        Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);
        logger.info("Paciente registrado exitosamente: " + pacienteRegistrado);
        return ResponseEntity.ok(pacienteRegistrado);
    }

    // Buscar todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() {
        logger.info("Buscando todos los pacientes...");
        List<Paciente> pacientes = pacienteService.buscarTodos();
        logger.info("Se encontraron " + pacientes.size() + " pacientes.");
        return ResponseEntity.ok(pacientes);
    }

    // Buscar un paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        logger.info("Buscando paciente con ID: " + id);
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        if (paciente.isPresent()) {
            logger.info("Paciente encontrado: " + paciente.get());
            return ResponseEntity.ok(paciente);
        } else {
            logger.warn("No se encontró paciente con ID: " + id);
            throw new ResourceNotFoundException("Paciente con ID " + id + " no encontrado");
        }
    }

    // Buscar un paciente por nombre
    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<Optional<Paciente>> buscarPorNombre(@PathVariable String nombre) {
        logger.info("Buscando paciente con nombre: " + nombre);
        Optional<Paciente> paciente = pacienteService.buscarPorNombre(nombre);
        if (paciente.isPresent()) {
            logger.info("Paciente encontrado por nombre: " + paciente.get());
        } else {
            logger.warn("No se encontró paciente con nombre: " + nombre);
        }
        return ResponseEntity.ok(paciente);
    }

    // Buscar un paciente por email
    @GetMapping("/buscarPorEmail/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) {
        logger.info("Buscando paciente con email: " + email);
        Optional<Paciente> paciente = pacienteService.buscarPorEmail(email);
        if (paciente.isPresent()) {
            logger.info("Paciente encontrado por email: " + paciente.get());
        } else {
            logger.warn("No se encontró paciente con email: " + email);
        }
        return ResponseEntity.ok(paciente);
    }

    // Actualizar un paciente existente
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Integer id, @RequestBody Paciente paciente) throws ResourceNotFoundException {
        logger.info("Actualizando paciente con ID: " + id);

        // Check if the patient exists
        Optional<Paciente> existingPaciente = pacienteService.buscarPorId(id);
        if (!existingPaciente.isPresent()) {
            logger.warn("No se encontró paciente con ID: " + id + " para actualizar.");
            throw new ResourceNotFoundException("Paciente con ID " + id + " no encontrado para actualizar.");
        }

        // Set the ID to the provided one in the path variable
        paciente.setId(id);

        // Update the patient
        Paciente pacienteActualizado = pacienteService.actualizarPaciente(paciente);
        logger.info("Paciente actualizado exitosamente: " + pacienteActualizado);
        return ResponseEntity.ok(pacienteActualizado);
    }


    // Eliminar un paciente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        logger.info("Eliminando paciente con ID: " + id);
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        if (paciente.isPresent()) {
            pacienteService.eliminarPaciente(id);
            logger.info("Paciente con ID " + id + " eliminado exitosamente.");
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("No se pudo eliminar paciente con ID: " + id + " porque no existe");
            throw new ResourceNotFoundException("Paciente con ID " + id + " no encontrado para eliminar");
        }
    }
}
