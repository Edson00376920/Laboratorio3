package com.example.laboratorio3.exceptions;

import com.example.laboratorio3.domain.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request
    ) {
        // Extrae el path eliminando el prefijo "uri=" que añade Spring por defecto
        String path = request.getDescription(false).replace("uri=", "");

        ApiErrorResponse errorDTO = ApiErrorResponse.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(path)
                .build();

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
