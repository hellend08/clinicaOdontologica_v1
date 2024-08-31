package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    // Register a new odontologist
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }

    // Find all odontologists
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    // Find an odontologist by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    // Find an odontologist by name
    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<Optional<Odontologo>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(odontologoService.buscarPorNombre(nombre));
    }

    // Find an odontologist by matricula (license number)
    @GetMapping("/buscarPorMatricula/{matricula}")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
    }

    // Update an existing odontologist
    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
    }

    // Delete an odontologist by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Integer id) {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.noContent().build();
    }
}
