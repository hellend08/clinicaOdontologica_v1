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
        // Password to be encoded
        String passSinCifrar = "digitalHouse";
        String passCifrado = passwordEncoder.encode(passSinCifrar);

        // Create and save user with encoded password
        Usuario usuarioAInsertar = new Usuario(
                "Ramon",
                "ramon99",
                passCifrado, // Encoded password
                "admin@admin.com",
                UsuarioRole.ROLE_ADMIN
        );
        usuarioRepository.save(usuarioAInsertar);

        logger.info("Usuario cargado con éxito");
    }
}
