package com.example.clinicaOdontologica.repository;

import com.example.clinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {

    // Find all odontologists
    List<Odontologo> findAll();

    // Find odontologist by ID
    Optional<Odontologo> findById(Integer id);

    // Find odontologist by name
    @Query("SELECT o FROM Odontologo o WHERE o.nombre = ?1")
    Optional<Odontologo> buscarOdontologoPorNombre(String nombre);

    // Save a new odontologist
    <S extends Odontologo> S save(S odontologo);

    // Delete an odontologist by ID
    void deleteById(Integer id);

    // Update an odontologist (already part of JpaRepository)
    <S extends Odontologo> S saveAndFlush(S odontologo);

    // Custom method to find odontologist by matricula (license number)
    Optional<Odontologo> findByMatricula(String matricula);
}
