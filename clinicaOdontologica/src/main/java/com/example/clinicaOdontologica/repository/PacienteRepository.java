package com.example.clinicaOdontologica.repository;
import com.example.clinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    // Find all patients
    List<Paciente> findAll();

    // Find patient by ID
    Optional<Paciente> findById(Integer id);

    // Find patient by name
    @Query("SELECT p FROM Paciente p WHERE p.nombre = ?1")
    Optional<Paciente> buscarPacientePorNombre(String nombre);

    // Save a new patient
    <S extends Paciente> S save(S paciente);

    // Delete a patient by ID
    void deleteById(Integer id);

    // Update a patient (already part of JpaRepository)
    <S extends Paciente> S saveAndFlush(S paciente);

    // Custom method to find patients by email
    Optional<Paciente> findByEmail(String email);
}
