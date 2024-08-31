package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    // Register a new odontologist
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    // Find an odontologist by ID
    public Optional<Odontologo> buscarPorId(Integer id) {
        return odontologoRepository.findById(id);
    }

    // Find all odontologists
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    // Find an odontologist by name
    public Optional<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.buscarOdontologoPorNombre(nombre);
    }

    // Find an odontologist by matricula (license number)
    public Optional<Odontologo> buscarPorMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }

    // Update an odontologist
    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        return odontologoRepository.saveAndFlush(odontologo);
    }

    // Delete an odontologist by ID
    public void eliminarOdontologo(Integer id) {
        odontologoRepository.deleteById(id);
    }
}
