package com.example.appVideoLocadoraMail.dtos;

import jakarta.validation.constraints.NotBlank;

public class FilmeDto {
    @NotBlank private String tituloFilme;
    @NotBlank private String duracaoFilme;
    @NotBlank private String categoriaFilme;
    @NotBlank private String diretorFilme;
    @NotBlank private String AtorFilme;
    @NotBlank private String paisFilme;
    @NotBlank private String statusFilme;

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
        return AtorFilme;
    }

    public void setAtorFilme(String atorFilme) {
        this.AtorFilme = atorFilme;
    }

    public String getPaisFilme() {
        return paisFilme;
    }

    public void setPaisFilme(String paisFilme) {
        this.paisFilme = paisFilme;
    }

    public String getStatusFilme() { return statusFilme; }

    public void setStatusFilme(String statusFilme) { this.statusFilme = statusFilme; }
}
