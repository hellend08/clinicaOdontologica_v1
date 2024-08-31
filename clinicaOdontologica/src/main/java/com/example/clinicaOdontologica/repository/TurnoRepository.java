package com.example.clinicaOdontologica.repository;

import com.example.clinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

    // Find all turnos
    List<Turno> findAll();

    // Find turno by ID
    Optional<Turno> findById(Integer id);

    // Find turnos by paciente ID
    List<Turno> findByPacienteId(Integer pacienteId);

    // Find turnos by odontologo ID
    List<Turno> findByOdontologoId(Integer odontologoId);

    // Custom method to find turnos by fecha (date)
    List<Turno> findByFecha(LocalDate fecha);
}
