package com.example.appVideoLocadoraMail.controllers;

import com.example.appVideoLocadoraMail.dtos.ClienteDto;
import com.example.appVideoLocadoraMail.services.ClienteService;
import com.example.appVideoLocadoraMail.models.ClienteModel;
import com.example.appVideoLocadoraMail.repositories.ClienteRepository;
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
@RequestMapping("/clienteLocadora")
public class ClienteController {
    final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteService clienteService,
                            ClienteRepository clienteRepository){
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteDto clienteDto){

        if(clienteService.existsByNome(clienteDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Cliente já cadastrado");
        }

        var clienteModel =  new ClienteModel();
        BeanUtils.copyProperties(clienteDto, clienteModel);
        clienteModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UCT")));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> getClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCliente(@PathVariable (value = "id") UUID id){

        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);

        if(!clienteModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") UUID id){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);

        if(!clienteModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflito: Cliente não encontrado!");
        }

       else{
            clienteService.delete(clienteModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Cliente removido com sucesso!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid ClienteDto clienteDto){

        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);

        if(!clienteModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }

        var clienteModel = clienteModelOptional.get();

        BeanUtils.copyProperties(clienteDto, clienteModel);
        clienteModel.setIdCliente(clienteModelOptional.get().getIdCliente());
        clienteModel.setDataRegistro(clienteModelOptional.get().getDataRegistro());

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(clienteModel));

    }
}