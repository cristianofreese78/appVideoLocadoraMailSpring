package com.example.appVideoLocadoraMail.services;

import com.example.appVideoLocadoraMail.models.ClienteModel;
import com.example.appVideoLocadoraMail.models.FilmeModel;
import com.example.appVideoLocadoraMail.models.LocacaoModel;
import com.example.appVideoLocadoraMail.repositories.LocacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocacaoService {
    final LocacaoRepository locacaoRepository;

    public LocacaoService(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    @Transactional
    public LocacaoModel save(LocacaoModel locacaoModel) {
        return locacaoRepository.save(locacaoModel);
    }

    public boolean existsByIdClienteAndIdFilme(ClienteModel clienteAux, FilmeModel filmeAux) {
        return locacaoRepository.existsByIdClienteAndIdFilme(clienteAux, filmeAux);
    }
    public List<LocacaoModel> findAll() {
        return locacaoRepository.findAll();
    }

    public Optional<LocacaoModel> findById(UUID id) {
        return locacaoRepository.findById(id);
    }

    @Transactional
    public void delete(LocacaoModel locacaoModel) {
        locacaoRepository.delete(locacaoModel);
    }
}

