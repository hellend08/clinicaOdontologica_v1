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
public class Domicilio {
    private Integer id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

}
