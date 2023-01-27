package com.example.appVideoLocadoraMail.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClienteDto {
    @NotBlank private String nome;
    @NotBlank private String endereco;
    private LocalDateTime dataRegistro;
}
