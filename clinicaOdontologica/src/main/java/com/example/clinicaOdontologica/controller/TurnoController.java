package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.service.OdontologoService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    // Register a new turno
    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        // Check if both Paciente and Odontologo are present
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            // Set the found entities back to the turno object
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.registrarTurno(turno)); // si el retorno es correcto , seria un 200
        } else {
            return ResponseEntity.badRequest().build(); // Return 400 if any entity is not found
        }
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
