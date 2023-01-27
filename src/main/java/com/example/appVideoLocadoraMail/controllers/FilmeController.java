package com.example.appVideoLocadoraMail.controllers;

import com.example.appVideoLocadoraMail.dtos.FilmeDto;
import com.example.appVideoLocadoraMail.services.FilmeService;
import com.example.appVideoLocadoraMail.models.FilmeModel;
import com.example.appVideoLocadoraMail.repositories.FilmeRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/filmeLocadora")
public class FilmeController {
    final FilmeService filmeService;
    private final FilmeRepository filmeRepository;

    public FilmeController(FilmeService filmeService,
                           FilmeRepository filmeRepository){
        this.filmeService = filmeService;
        this.filmeRepository = filmeRepository;
    }

    @PostMapping
    public ResponseEntity<Object> saveFilme(@RequestBody @Valid FilmeDto filmeDto){

       if(filmeService.existsBytituloFilmeAndDiretorFilme(filmeDto.getTituloFilme(), filmeDto.getDiretorFilme())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Filme já cadastrado");
        }

        var filmeModel =  new FilmeModel();
        BeanUtils.copyProperties(filmeDto, filmeModel);
        filmeModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UCT")));
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.save(filmeModel));
    }

    @GetMapping
    public ResponseEntity<List<FilmeModel>> getFilmes(){
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.findAll());
    }

   @GetMapping("/{id}")
    public ResponseEntity<Object> getFilme(@PathVariable (value = "id") UUID id){

        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);

        if(!filmeModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmeModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFilme(@PathVariable(value = "id") UUID id){
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);

        if(!filmeModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflito: Filme não encontrado!");
        }

        if(filmeModelOptional.get().getStatusFilme().equals("Locado")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Filme locado. Impossível exlcluir");
        }
        else{
            filmeService.delete(filmeModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Filme removido com sucesso!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFilme(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid FilmeDto filmeDto){

        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);

        if(!filmeModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }

        var filmeModel = filmeModelOptional.get();

        BeanUtils.copyProperties(filmeDto, filmeModel);
        filmeModel.setId(filmeModelOptional.get().getId());
        filmeModel.setDataRegistro(filmeModelOptional.get().getDataRegistro());
        ResponseEntity<Object> filmeResponse = ResponseEntity.status(HttpStatus.OK).body(filmeService.save(filmeModel));
        return filmeResponse;
    }
}
