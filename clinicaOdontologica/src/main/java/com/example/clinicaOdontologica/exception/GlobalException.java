package com.example.clinicaOdontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    //al manejo de exception hay que hacerlo de manera individual
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratamientoRNFE(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mensaje: "+rnfe.getMessage());
    }

    // Manejo de BadRequestException
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratamientoBadRequest(BadRequestException bre) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + bre.getMessage());
    }

}
