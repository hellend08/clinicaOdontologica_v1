package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.exception.ResourceNotFoundException;
import com.example.clinicaOdontologica.service.TurnoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(TurnoController.class);

    // Registrar turno
    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
        logger.info("Registrando nuevo turno para Paciente ID " + turno.getPaciente().getId() +
                " y Odontólogo ID " + turno.getOdontologo().getId());
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            Turno turnoRegistrado = turnoService.registrarTurno(turno);
            logger.info("Turno registrado con éxito:" + turnoRegistrado);
            return ResponseEntity.ok(turnoRegistrado); // si el retorno es correcto , seria un 200
        } else {
            if (!pacienteBuscado.isPresent()) {
                logger.warn("No se encontró el Paciente con ID: " + turno.getPaciente().getId());
            }
            if (!odontologoBuscado.isPresent()) {
                logger.warn("No se encontró el Odontólogo con ID: " + turno.getOdontologo().getId());
            }
            return ResponseEntity.badRequest().build(); // Return 400 if any entity is not found
        }
    }


    // Buscar todos los turnos
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {
        logger.info("Buscando todos los turnos.");
        List<Turno> turnosEncontrados = turnoService.buscarTodos();
        logger.info("Se encontraron " + turnosEncontrados.size() + " turnos.");
        return ResponseEntity.ok(turnosEncontrados);
    }

    // Buscar un turno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        logger.info("Buscando turnos por ID: " + id);
        Turno turnoId = turnoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + id));
        logger.info("Turno encontrado: " + turnoId);
        return ResponseEntity.ok(turnoId);
    }

    // Buscar turnos por paciente ID
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Turno>> buscarPorPacienteId(@PathVariable Integer pacienteId) {
        logger.info("Buscando turnos por Paciente ID: " + pacienteId);
        List<Turno> turnoPacienteId = turnoService.buscarPorPacienteId(pacienteId);
        if (turnoPacienteId.isEmpty()) {
            logger.warn("No se encontraron turnos para el paciente ID: " + pacienteId);
        } else {
            logger.info("Se encontraron " + turnoPacienteId.size() + " turnos para el paciente ID: " + pacienteId);
        }
        return ResponseEntity.ok(turnoPacienteId);
    }

    // Buscar turnos por odontóogo ID
    @GetMapping("/odontologo/{odontologoId}")
    public ResponseEntity<List<Turno>> buscarPorOdontologoId(@PathVariable Integer odontologoId) {
        logger.info("Buscando turnos por Odontólogo ID: " + odontologoId);
        List<Turno> turnoOdontologoId = turnoService.buscarPorOdontologoId(odontologoId);
        if (turnoOdontologoId.isEmpty()){
            logger.warn("No se encontraron turnos para el paciente ID: " + odontologoId);
        } else {
            logger.info("Se encontraron " + turnoOdontologoId.size() + " turnos para el odontólogo ID: " + odontologoId);
        }
        return ResponseEntity.ok(turnoOdontologoId);
    }

    // Buscar turnos por fecha
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Turno>> buscarPorFecha(@PathVariable LocalDate fecha) {
        logger.info("Buscando turnos por fecha: " + fecha);
        List<Turno> turnoFecha = turnoService.buscarPorFecha(fecha);
        if (turnoFecha.isEmpty()) {
            logger.warn("No se encontró el turno con fecha: " + fecha);
        } else {
            logger.info("Se encontraron " + turnoFecha.size() + " turnos por fecha.");
        }
        return ResponseEntity.ok(turnoFecha);
    }

    // Actualizando turno existente
    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        logger.info("Actualizando turno: " + turno);
        Turno turnoActualizado = turnoService.actualizarTurno(turno);
        logger.info("Turno actualizado con éxito: " + turnoActualizado);
        return ResponseEntity.ok(turnoActualizado);
    }

    // Eliminando turno por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Integer id) throws ResourceNotFoundException {
        logger.info("Eliminando turno por ID: " + id);
        Turno turno = turnoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + id));
        turnoService.eliminarTurno(id);
        logger.info("Turno con ID " + id + " eliminado con éxito.");
        return ResponseEntity.noContent().build();
    }
}
