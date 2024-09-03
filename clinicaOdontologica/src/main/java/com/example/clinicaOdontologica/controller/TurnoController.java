package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.example.clinicaOdontologica.dto.TurnoDto;


@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    // Register a new turno
    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody TurnoDto turno) {
        return ResponseEntity.ok(turnoService.registrarTurno(turno));
    }

    // Find all turnos
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    // Find a turno by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    // Find turnos by paciente ID
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Turno>> buscarPorPacienteId(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(turnoService.buscarPorPacienteId(pacienteId));
    }

    // Find turnos by odontologo ID
    @GetMapping("/odontologo/{odontologoId}")
    public ResponseEntity<List<Turno>> buscarPorOdontologoId(@PathVariable Integer odontologoId) {
        return ResponseEntity.ok(turnoService.buscarPorOdontologoId(odontologoId));
    }

    // Find turnos by fecha (date)
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Turno>> buscarPorFecha(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(turnoService.buscarPorFecha(fecha));
    }

    // Update an existing turno
    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.actualizarTurno(turno));
    }

    // Delete a turno by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Integer id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }
}
