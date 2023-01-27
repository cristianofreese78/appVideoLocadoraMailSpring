package com.example.appVideoLocadoraMail.repositories;

import com.example.appVideoLocadoraMail.models.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeModel, UUID> {

   boolean existsBytituloFilmeAndDiretorFilme(String tituloFilme, String diretorFilme);
}
