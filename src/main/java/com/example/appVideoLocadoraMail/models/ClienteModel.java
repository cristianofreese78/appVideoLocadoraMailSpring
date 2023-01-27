package com.example.appVideoLocadoraMail.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name="TB_CLIENTE")
public class ClienteModel implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private UUID idCliente;
    @Column(nullable = false, length = 80) private String nome;
    private LocalDateTime dataRegistro;
}
