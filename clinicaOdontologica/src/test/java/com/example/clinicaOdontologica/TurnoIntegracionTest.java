package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.entity.Domicilio;
import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.service.TurnoService;
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
@AutoConfigureMockMvc(addFilters = false) //no necesito login en esta clase
public class TurnoIntegracionTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;


    public void cargarTurnos(){
        Odontologo odontologo1= odontologoService.registrarOdontologo(new Odontologo(1,"Juan","Maldonado","MP10"));
        Odontologo odontologo2= odontologoService.registrarOdontologo(new Odontologo(2,"Daniela","Paz","MP20"));
        Paciente paciente1= pacienteService.registrarPaciente(new Paciente(1,"Matias","Santos","111111", LocalDate.of(2024,9,12),new Domicilio(1,"Calle 1",122,"Uruguay","Montevideo"),"matiassantos@digitalhouse.com"));
        Paciente paciente2= pacienteService.registrarPaciente(new Paciente(2,"Helen","Vasquez","1112221", LocalDate.of(2024,9,13),new Domicilio(2,"Calle 2",152,"Peru","Lima"),"helenvasquez@digitalhouse.com"));
        Turno turno1= turnoService.registrarTurno(new Turno(1,paciente1,odontologo1,LocalDate.of(2024,11,12)));
        Turno turno2= turnoService.registrarTurno(new Turno(2,paciente2,odontologo2,LocalDate.of(2024,12,20)));
    }

    @Test
    public void listarTodosLosTurnos() throws Exception{
        cargarTurnos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());

    }
}
