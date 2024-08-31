package com.example.clinicaOdontologica.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Odontologo {
    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;
}
