package com.example.clinicaOdontologica.security;

import com.example.clinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarioService);
        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        // Admin role: Manage CRUD operations for pacientes and odontologos
                        .requestMatchers(HttpMethod.POST, "/pacientes", "/odontologos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pacientes/**", "/odontologos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pacientes/**", "/odontologos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes", "/odontologos").hasRole("ADMIN")

                        // User role: Create turnos
                        .requestMatchers(HttpMethod.POST, "/turnos").hasRole("USER")

                        // Any authenticated request
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());
        return http.build();
    }

}
