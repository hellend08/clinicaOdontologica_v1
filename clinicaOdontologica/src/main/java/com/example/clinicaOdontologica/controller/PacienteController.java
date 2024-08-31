package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Register a new patient
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
    }

    // Find all patients
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    // Find a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    // Find a patient by name
    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<Optional<Paciente>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(pacienteService.buscarPorNombre(nombre));
    }

    // Find a patient by email
    @GetMapping("/buscarPorEmail/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(pacienteService.buscarPorEmail(email));
    }

    // Update an existing patient
    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
    }

    // Delete a patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Integer id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
