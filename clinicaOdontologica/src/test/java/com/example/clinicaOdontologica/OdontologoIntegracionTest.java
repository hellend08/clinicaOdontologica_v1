package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;
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

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegracionTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;

    public void cargarOdontologos() {
        odontologoService.registrarOdontologo(new Odontologo(1, "Juan", "Maldonado", "MP10"));
        odontologoService.registrarOdontologo(new Odontologo(2, "Daniela", "Paz", "MP20"));
    }

    @Test
    public void testRegistrarOdontologo() throws Exception {
        cargarOdontologos();

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Carlos\",\"apellido\":\"Rodríguez\",\"matricula\":\"MP30\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }
}