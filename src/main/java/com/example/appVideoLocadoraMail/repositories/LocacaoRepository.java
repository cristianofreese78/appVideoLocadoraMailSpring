package com.example.appVideoLocadoraMail.repositories;

import com.example.appVideoLocadoraMail.models.ClienteModel;
import com.example.appVideoLocadoraMail.models.FilmeModel;
import com.example.appVideoLocadoraMail.models.LocacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LocacaoRepository extends JpaRepository<LocacaoModel, UUID> {
    boolean existsByIdClienteAndIdFilme(ClienteModel clienteAux, FilmeModel filmeAux);
}
