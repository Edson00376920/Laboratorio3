package com.example.laboratorio3.domain.dto.response;


import lombok.*;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse<T> {
    private String message;
    private int statusCode;
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String path;
    private T data;
}