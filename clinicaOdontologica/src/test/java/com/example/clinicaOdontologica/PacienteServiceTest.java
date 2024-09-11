package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.entity.Domicilio;
import com.example.clinicaOdontologica.service.PacienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PacienteServiceTest {

    private static final Logger logger = LogManager.getLogger(PacienteServiceTest.class);

    @Autowired
    private PacienteService pacienteService;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle Test");
        domicilio.setNumero(123);
        domicilio.setLocalidad("Localidad Test");
        domicilio.setProvincia("Provincia Test");

        paciente = new Paciente();
        paciente.setNombre("John");
        paciente.setApellido("Doe");
        paciente.setCedula("123456789");
        paciente.setFechaIngreso(LocalDate.now());
        paciente.setEmail("john.doe@example.com");
        paciente.setDomicilio(domicilio);

        logger.info("Paciente de prueba creado: {}", paciente);
    }

    @Test
    void testRegistrarPaciente() {
        logger.info("Iniciando prueba de registro de paciente");
        Paciente savedPaciente = pacienteService.registrarPaciente(paciente);
        logger.info("Paciente registrado: {}", savedPaciente);

        assertNotNull(savedPaciente.getId());
        assertEquals(paciente.getNombre(), savedPaciente.getNombre());
        assertEquals(paciente.getApellido(), savedPaciente.getApellido());
        assertEquals(paciente.getCedula(), savedPaciente.getCedula());
        assertEquals(paciente.getEmail(), savedPaciente.getEmail());
        logger.info("Prueba de registro de paciente completada con éxito");
    }

    @Test
    void testBuscarPorId() {
        logger.info("Iniciando prueba de búsqueda por ID");
        Paciente savedPaciente = pacienteService.registrarPaciente(paciente);
        logger.info("Paciente registrado para búsqueda: {}", savedPaciente);

        Optional<Paciente> foundPaciente = pacienteService.buscarPorId(savedPaciente.getId());
        logger.info("Paciente encontrado por ID: {}", foundPaciente.orElse(null));

        assertTrue(foundPaciente.isPresent());
        assertEquals(savedPaciente.getId(), foundPaciente.get().getId());
        assertEquals(savedPaciente.getNombre(), foundPaciente.get().getNombre());
        logger.info("Prueba de búsqueda por ID completada con éxito");
    }

    @Test
    void testBuscarTodos() {
        logger.info("Iniciando prueba de búsqueda de todos los pacientes");
        pacienteService.registrarPaciente(paciente);
        logger.info("Paciente registrado para búsqueda en lista: {}", paciente);

        List<Paciente> pacientes = pacienteService.buscarTodos();
        logger.info("Se encontraron {} pacientes", pacientes.size());

        assertFalse(pacientes.isEmpty());
        assertTrue(pacientes.stream().anyMatch(p -> p.getNombre().equals(paciente.getNombre())));
        logger.info("Prueba de búsqueda de todos los pacientes completada con éxito");
    }

    @Test
    void testBuscarPorNombre() {
        logger.info("Iniciando prueba de búsqueda por nombre");
        pacienteService.registrarPaciente(paciente);
        logger.info("Paciente registrado para búsqueda por nombre: {}", paciente);

        Optional<Paciente> foundPaciente = pacienteService.buscarPorNombre(paciente.getNombre());
        logger.info("Paciente encontrado por nombre: {}", foundPaciente.orElse(null));

        assertTrue(foundPaciente.isPresent());
        assertEquals(paciente.getNombre(), foundPaciente.get().getNombre());
        logger.info("Prueba de búsqueda por nombre completada con éxito");
    }

    @Test
    void testBuscarPorEmail() {
        logger.info("Iniciando prueba de búsqueda por email");
        pacienteService.registrarPaciente(paciente);
        logger.info("Paciente registrado para búsqueda por email: {}", paciente);

        Optional<Paciente> foundPaciente = pacienteService.buscarPorEmail(paciente.getEmail());
        logger.info("Paciente encontrado por email: {}", foundPaciente.orElse(null));

        assertTrue(foundPaciente.isPresent());
        assertEquals(paciente.getEmail(), foundPaciente.get().getEmail());
        logger.info("Prueba de búsqueda por email completada con éxito");
    }

    @Test
    void testActualizarPaciente() {
        logger.info("Iniciando prueba de actualización de paciente");
        Paciente savedPaciente = pacienteService.registrarPaciente(paciente);
        logger.info("Paciente original: {}", savedPaciente);

        savedPaciente.setNombre("Jane");
        logger.info("Actualizando nombre del paciente a: {}", savedPaciente.getNombre());

        Paciente updatedPaciente = pacienteService.actualizarPaciente(savedPaciente);
        logger.info("Paciente actualizado: {}", updatedPaciente);

        assertEquals("Jane", updatedPaciente.getNombre());
        assertEquals(savedPaciente.getId(), updatedPaciente.getId());
        logger.info("Prueba de actualización de paciente completada con éxito");
    }

    @Test
    void testEliminarPaciente() {
        logger.info("Iniciando prueba de eliminación de paciente");
        Paciente savedPaciente = pacienteService.registrarPaciente(paciente);
        logger.info("Paciente registrado para eliminación: {}", savedPaciente);

        pacienteService.eliminarPaciente(savedPaciente.getId());
        logger.info("Paciente eliminado con ID: {}", savedPaciente.getId());

        Optional<Paciente> deletedPaciente = pacienteService.buscarPorId(savedPaciente.getId());
        logger.info("Intento de encontrar paciente eliminado: {}", deletedPaciente.orElse(null));

        assertFalse(deletedPaciente.isPresent());
        logger.info("Prueba de eliminación de paciente completada con éxito");
    }
}