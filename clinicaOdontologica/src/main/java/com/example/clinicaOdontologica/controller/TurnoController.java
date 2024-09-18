package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.exception.BadRequestException;
import com.example.clinicaOdontologica.exception.ResourceNotFoundException;
import com.example.clinicaOdontologica.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Registrar un nuevo turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno registrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de turno inválidos o paciente/odontólogo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
        logger.info("Registrando nuevo turno para Paciente ID " + turno.getPaciente().getId() +
                " y Odontólogo ID " + turno.getOdontologo().getId());
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (!pacienteBuscado.isPresent()) {
            logger.warn("No se encontró el Paciente con ID: " + turno.getPaciente().getId());
            throw new BadRequestException("El paciente con ID " + turno.getPaciente().getId() + " no existe.");
        }

        if (!odontologoBuscado.isPresent()) {
            logger.warn("No se encontró el Odontólogo con ID: " + turno.getOdontologo().getId());
            throw new BadRequestException("El odontólogo con ID " + turno.getOdontologo().getId() + " no existe.");
        }

        turno.setPaciente(pacienteBuscado.get());
        turno.setOdontologo(odontologoBuscado.get());
        Turno turnoRegistrado = turnoService.registrarTurno(turno);
        logger.info("Turno registrado con éxito:" + turnoRegistrado);
        return ResponseEntity.ok(turnoRegistrado);
    }

    // Buscar todos los turnos
    @Operation(summary = "Buscar todos los turnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de turnos encontrada"),
            @ApiResponse(responseCode = "204", description = "No se encontraron turnos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {
        logger.info("Buscando todos los turnos.");
        List<Turno> turnosEncontrados = turnoService.buscarTodos();
        logger.info("Se encontraron " + turnosEncontrados.size() + " turnos.");
        return ResponseEntity.ok(turnosEncontrados);
    }

    // Buscar un turno por ID
    @Operation(summary = "Buscar un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno encontrado"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        logger.info("Buscando turnos por ID: " + id);
        Turno turnoId = turnoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + id));
        logger.info("Turno encontrado: " + turnoId);
        return ResponseEntity.ok(turnoId);
    }

    // Buscar turnos por paciente ID
    @Operation(summary = "Buscar turnos por paciente ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de turnos encontrada"),
            @ApiResponse(responseCode = "204", description = "No se encontraron turnos para el paciente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
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
    @Operation(summary = "Buscar turnos por odontólogo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de turnos encontrada"),
            @ApiResponse(responseCode = "204", description = "No se encontraron turnos para el odontólogo"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
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
    @Operation(summary = "Buscar turnos por fecha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de turnos encontrada"),
            @ApiResponse(responseCode = "204", description = "No se encontraron turnos en esa fecha"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
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

    @Operation(summary = "Actualizar un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "ID del turno no coincide con el cuerpo de la solicitud"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizarTurno(@PathVariable("id") Integer id, @RequestBody Turno turno) {
        logger.info("Actualizando turno con ID: " + id);

        if (turno.getId() == null || !turno.getId().equals(id)) {
            logger.error("ID del turno en el cuerpo del request no coincide con el ID en la URL.");
            return ResponseEntity.badRequest().build();
        }

        Optional<Turno> turnoExistente = turnoService.buscarPorId(id);

        if (turnoExistente.isEmpty()) {
            logger.warn("Turno con ID " + id + " no encontrado.");
            return ResponseEntity.notFound().build();
        }

        Turno turnoActualizado = turnoService.actualizarTurno(turno);
        logger.info("Turno actualizado con éxito: " + turnoActualizado);
        return ResponseEntity.ok(turnoActualizado);
    }



    // Eliminando turno por ID
    @Operation(summary = "Eliminar un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turno eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
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
