package com.example.clinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Odontologo {
    private Integer id;
    private String nombre;
    private String apellido;
    private String numeroMatricula;

    public Odontologo(String apellido, String nombre, String numeroMatricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroMatricula = numeroMatricula;
    }

    @JsonCreator
    public Odontologo(@JsonProperty("id") Long id) {
        this.id = Math.toIntExact(id);
    }

    public Odontologo(Integer id, String numeroMatricula, String nombre, String apellido) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
