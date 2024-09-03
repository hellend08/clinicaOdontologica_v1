package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clinicaOdontologica.repository.OdontologoRepository;
import com.example.clinicaOdontologica.repository.PacienteRepository;
import com.example.clinicaOdontologica.dto.TurnoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private static final Logger log = LoggerFactory.getLogger(TurnoService.class);

    // Register a new turno
    //public Turno registrarTurno(Turno turno) {
        //return turnoRepository.save(turno);
    //}

    public Turno registrarTurno(TurnoDto turno) {
        log.info("Registering Turno: {}", turno);
        try {
            Odontologo odontologo = odontologoRepository.findById(turno.getOdontologoId())
             .orElseThrow(() -> new IllegalArgumentException("Invalid odontologo ID"));
            Paciente paciente = pacienteRepository.findById(turno.getPacienteId())
                  .orElseThrow(() -> new IllegalArgumentException("Invalid paciente ID"));

             Turno turno2 = new Turno();
            turno2.setOdontologo(odontologo);
             turno2.setPaciente(paciente);
             turno2.setFecha(turno.getFecha());

             return turnoRepository.save(turno2);
            //Turno savedTurno = turnoRepository.save(turno);
            //log.info("Saved Turno: {}", savedTurno);
            //return savedTurno;
        } catch (Exception e) {
            log.error("Error registering turno", e);
            throw e;
        }
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
