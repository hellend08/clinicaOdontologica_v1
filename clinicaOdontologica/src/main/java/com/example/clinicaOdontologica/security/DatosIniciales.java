package com.example.clinicaOdontologica.security;


import com.example.clinicaOdontologica.entity.Usuario;
import com.example.clinicaOdontologica.entity.UsuarioRole;
import com.example.clinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

@Component
public class DatosIniciales implements ApplicationRunner {
    private static final Logger logger= Logger.getLogger(DatosIniciales.class);


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "digitalHouse";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario usuarioAInsertar = new Usuario("Ramon", "ramon99", "admin@admin.com", passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);
        logger.info("usuario cargado con exito");

        String passSinCifrar2 = "HouseDigital";
        String passCifrado2 = passwordEncoder.encode(passSinCifrar2);
        Usuario usuarioAInsertar2 = new Usuario("Helena", "helena89", "admin2@admin.com", passCifrado2, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar2);
        logger.info("usuario2 cargado con exito");
    }}
