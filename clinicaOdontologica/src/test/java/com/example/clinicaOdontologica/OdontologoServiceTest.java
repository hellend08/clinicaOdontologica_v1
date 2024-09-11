package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OdontologoServiceTest {

    private static final Logger logger = LogManager.getLogger(OdontologoServiceTest.class);

    @Autowired
    private OdontologoService odontologoService;

    private Odontologo odontologo;

    @BeforeEach
    void setUp() {
        odontologo = new Odontologo();
        odontologo.setNombre("John");
        odontologo.setApellido("Doe");
        odontologo.setMatricula("ODO123456");
        logger.info("Odontólogo de prueba creado: {}", odontologo);
    }

    @Test
    void testRegistrarOdontologo() {
        logger.info("Iniciando prueba de registro de odontólogo");
        Odontologo savedOdontologo = odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontólogo registrado: {}", savedOdontologo);

        assertNotNull(savedOdontologo.getId());
        assertEquals(odontologo.getNombre(), savedOdontologo.getNombre());
        assertEquals(odontologo.getApellido(), savedOdontologo.getApellido());
        assertEquals(odontologo.getMatricula(), savedOdontologo.getMatricula());
        logger.info("Prueba de registro de odontólogo completada con éxito");
    }

    @Test
    void testBuscarPorId() {
        logger.info("Iniciando prueba de búsqueda por ID");
        Odontologo savedOdontologo = odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontólogo registrado para búsqueda: {}", savedOdontologo);

        Optional<Odontologo> foundOdontologo = odontologoService.buscarPorId(savedOdontologo.getId());
        logger.info("Odontólogo encontrado por ID: {}", foundOdontologo.orElse(null));

        assertTrue(foundOdontologo.isPresent());
        assertEquals(savedOdontologo.getId(), foundOdontologo.get().getId());
        assertEquals(savedOdontologo.getNombre(), foundOdontologo.get().getNombre());
        logger.info("Prueba de búsqueda por ID completada con éxito");
    }

    @Test
    void testBuscarTodos() {
        logger.info("Iniciando prueba de búsqueda de todos los odontólogos");
        odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontólogo registrado para búsqueda en lista: {}", odontologo);

        List<Odontologo> odontologos = odontologoService.buscarTodos();
        logger.info("Se encontraron {} odontólogos", odontologos.size());

        assertFalse(odontologos.isEmpty());
        assertTrue(odontologos.stream().anyMatch(o -> o.getNombre().equals(odontologo.getNombre())));
        logger.info("Prueba de búsqueda de todos los odontólogos completada con éxito");
    }

    @Test
    void testBuscarPorNombre() {
        logger.info("Iniciando prueba de búsqueda por nombre");
        odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontólogo registrado para búsqueda por nombre: {}", odontologo);

        Optional<Odontologo> foundOdontologo = odontologoService.buscarPorNombre(odontologo.getNombre());
        logger.info("Odontólogo encontrado por nombre: {}", foundOdontologo.orElse(null));

        assertTrue(foundOdontologo.isPresent());
        assertEquals(odontologo.getNombre(), foundOdontologo.get().getNombre());
        logger.info("Prueba de búsqueda por nombre completada con éxito");
    }

    @Test
    void testBuscarPorMatricula() {
        logger.info("Iniciando prueba de búsqueda por matrícula");
        odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontólogo registrado para búsqueda por matrícula: {}", odontologo);

        Optional<Odontologo> foundOdontologo = odontologoService.buscarPorMatricula(odontologo.getMatricula());
        logger.info("Odontólogo encontrado por matrícula: {}", foundOdontologo.orElse(null));

        assertTrue(foundOdontologo.isPresent());
        assertEquals(odontologo.getMatricula(), foundOdontologo.get().getMatricula());
        logger.info("Prueba de búsqueda por matrícula completada con éxito");
    }

    @Test
    void testActualizarOdontologo() {
        logger.info("Iniciando prueba de actualización de odontólogo");
        Odontologo savedOdontologo = odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontólogo original: {}", savedOdontologo);

        savedOdontologo.setNombre("Jane");
        logger.info("Actualizando nombre del odontólogo a: {}", savedOdontologo.getNombre());

        Odontologo updatedOdontologo = odontologoService.actualizarOdontologo(savedOdontologo);
        logger.info("Odontólogo actualizado: {}", updatedOdontologo);

        assertEquals("Jane", updatedOdontologo.getNombre());
        assertEquals(savedOdontologo.getId(), updatedOdontologo.getId());
        logger.info("Prueba de actualización de odontólogo completada con éxito");
    }

    @Test
    void testEliminarOdontologo() {
        logger.info("Iniciando prueba de eliminación de odontólogo");
        Odontologo savedOdontologo = odontologoService.registrarOdontologo(odontologo);
        logger.info("Odontólogo registrado para eliminación: {}", savedOdontologo);

        odontologoService.eliminarOdontologo(savedOdontologo.getId());
        logger.info("Odontólogo eliminado con ID: {}", savedOdontologo.getId());

        Optional<Odontologo> deletedOdontologo = odontologoService.buscarPorId(savedOdontologo.getId());
        logger.info("Intento de encontrar odontólogo eliminado: {}", deletedOdontologo.orElse(null));

        assertFalse(deletedOdontologo.isPresent());
        logger.info("Prueba de eliminación de odontólogo completada con éxito");
    }
}