package com.example.clinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String localidad;

    @Column(nullable = false)
    private String provincia;

    @OneToMany(mappedBy = "domicilio")
    @JsonIgnore
    private List<Paciente> pacientes = new ArrayList<>();
}
