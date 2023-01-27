package com.example.appVideoLocadoraMail.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="TB_LOCACAO")
public class LocacaoModel implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private UUID id;
   private LocalDateTime dataRegistro;

    @OneToOne @JoinColumn(name = "idCliente") private ClienteModel idCliente;

    @ManyToOne @JoinColumn(name = "idFilme") private FilmeModel idFilme;
}