package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Register a new paciente
    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // Find a paciente by ID
    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    // Find all pacientes
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    // Find a paciente by name
    public Optional<Paciente> buscarPorNombre(String nombre) {
        return pacienteRepository.buscarPacientePorNombre(nombre);
    }

    // Find pacientes by email
    public Optional<Paciente> buscarPorEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }

    // Update a paciente
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteRepository.saveAndFlush(paciente);
    }

    // Delete a paciente by ID
    public void eliminarPaciente(Integer id) {
        pacienteRepository.deleteById(id);
    }
}
