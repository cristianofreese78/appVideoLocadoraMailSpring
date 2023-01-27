package com.example.appVideoLocadoraMail.repositories;

import com.example.appVideoLocadoraMail.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {
    boolean existsByNome(String nome);
}
