package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.entity.Domicilio;
import com.example.clinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacienteIntegracionTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MockMvc mockMvc;

    public void cargarPacientes() {
        Domicilio domicilio1 = new Domicilio(1,"Calle 1", 123, "Localidad1", "Provincia1");
        Domicilio domicilio2 = new Domicilio(2,"Calle 2", 456, "Localidad2", "Provincia2");

        pacienteService.registrarPaciente(new Paciente(1,"Juan", "Perez", "12345", LocalDate.now(), domicilio1, "juan@example.com"));
        pacienteService.registrarPaciente(new Paciente(2,"Maria", "Garcia", "67890", LocalDate.now(), domicilio2, "maria@example.com"));
    }

    @Test
    public void testListarPacientes() throws Exception {
        cargarPacientes(); // Preload some test data

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Check that the response is not empty
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }
}
