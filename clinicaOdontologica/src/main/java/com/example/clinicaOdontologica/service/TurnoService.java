package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    // Register a new turno
    public Turno registrarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    // Find a turno by ID
    public Optional<Turno> buscarPorId(Integer id) {
        return turnoRepository.findById(id);
    }

    // Find all turnos
    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    // Find turnos by paciente ID
    public List<Turno> buscarPorPacienteId(Integer pacienteId) {
        return turnoRepository.findByPacienteId(pacienteId);
    }

    // Find turnos by odontologo ID
    public List<Turno> buscarPorOdontologoId(Integer odontologoId) {
        return turnoRepository.findByOdontologoId(odontologoId);
    }

    // Find turnos by date
    public List<Turno> buscarPorFecha(LocalDate fecha) {
        return turnoRepository.findByFecha(fecha);
    }

    // Update a turno
    public Turno actualizarTurno(Turno turno) {
        return turnoRepository.saveAndFlush(turno);
    }

    // Delete a turno by ID
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }
}
