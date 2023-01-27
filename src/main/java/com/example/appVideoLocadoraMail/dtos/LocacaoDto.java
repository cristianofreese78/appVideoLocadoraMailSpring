package com.example.appVideoLocadoraMail.dtos;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public class LocacaoDto {
    private UUID idCliente;
    private UUID idFilme;
    private LocalDateTime dataRegistro;
}
