package com.example.clinicaOdontologica.dto;

import java.time.LocalDate;

    public class TurnoDto {
        private Integer pacienteId;
        private Integer odontologoId;
        private LocalDate fecha;

        // Constructors, Getters, and Setters

        public TurnoDto() {
        }

        public TurnoDto(Integer pacienteId, Integer odontologoId, LocalDate fecha) {
            this.pacienteId = pacienteId;
            this.odontologoId = odontologoId;
            this.fecha = fecha;
        }

        public Integer getPacienteId() {
            return pacienteId;
        }

        public void setPacienteId(Integer pacienteId) {
            this.pacienteId = pacienteId;
        }

        public Integer getOdontologoId() {
            return odontologoId;
        }

        public void setOdontologoId(Integer odontologoId) {
            this.odontologoId = odontologoId;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public void setFecha(LocalDate fecha) {
            this.fecha = fecha;
        }
    }


