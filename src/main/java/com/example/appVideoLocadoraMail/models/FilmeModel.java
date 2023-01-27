package com.example.appVideoLocadoraMail.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name="TB_FILME")
public class FilmeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private UUID idFilme;
    @Column(nullable = false, length = 80) private String tituloFilme;
    @Column(nullable = false) private String duracaoFilme;
    @Column(nullable = false, length = 20) private String categoriaFilme;
    @Column(nullable = false, length = 20) private String diretorFilme;
    @Column(nullable = false, length = 80) private String atorFilme;
    @Column(nullable = false, length = 80) private String paisFilme;
    @Column(nullable = false) private LocalDateTime dataRegistro;
    @Column(nullable = false, length = 20) private String statusFilme;

    public UUID getId() {
        return idFilme;
    }

    public void setId(UUID idCliente) {
        this.idFilme = idCliente;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getDuracaoFilme() {
        return duracaoFilme;
    }

    public void setDuracaoFilme(String duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    public String getCategoriaFilme() {
        return categoriaFilme;
    }

    public void setCategoriaFilme(String categoriaFilme) {
        this.categoriaFilme = categoriaFilme;
    }

    public String getDiretorFilme() {
        return diretorFilme;
    }

    public void setDiretorFilme(String diretorFilme) {
        this.diretorFilme = diretorFilme;
    }

    public String getAtorFilme() {
        return atorFilme;
    }

    public void setAtorFilme(String atorFilme) {
        this.atorFilme = atorFilme;
    }

    public String getPaisFilme() {
        return paisFilme;
    }

    public void setPaisFilme(String paisFilme) {
        this.paisFilme = paisFilme;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getStatusFilme() { return statusFilme; }

    public void setStatusFilme(String statusFilme) { this.statusFilme = statusFilme; }
}
