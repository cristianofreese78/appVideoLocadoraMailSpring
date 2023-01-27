package com.example.appVideoLocadoraMail.services;

import com.example.appVideoLocadoraMail.models.FilmeModel;
import com.example.appVideoLocadoraMail.repositories.FilmeRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {
    final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public FilmeModel save(FilmeModel filmeModel) {
        return filmeRepository.save(filmeModel);
    }

    public boolean existsBytituloFilmeAndDiretorFilme(String tituloFilme, String diretorFilme) {
        return filmeRepository.existsBytituloFilmeAndDiretorFilme(tituloFilme,diretorFilme );
    }

    public List<FilmeModel> findAll() {
        return filmeRepository.findAll();
    }

    public Optional<FilmeModel> findById(UUID id) {
        return filmeRepository.findById(id);
    }

    @Transactional
    public void delete(FilmeModel filmeModel) {
        filmeRepository.delete(filmeModel);
    }
}
