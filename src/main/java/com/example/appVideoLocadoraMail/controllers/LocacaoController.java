package com.example.appVideoLocadoraMail.controllers;

import com.example.appVideoLocadoraMail.dtos.LocacaoDto;
import com.example.appVideoLocadoraMail.models.ClienteModel;
import com.example.appVideoLocadoraMail.models.EmailModel;
import com.example.appVideoLocadoraMail.models.FilmeModel;
import com.example.appVideoLocadoraMail.models.LocacaoModel;
import com.example.appVideoLocadoraMail.repositories.LocacaoRepository;
import com.example.appVideoLocadoraMail.services.ClienteService;
import com.example.appVideoLocadoraMail.services.EmailService;
import com.example.appVideoLocadoraMail.services.FilmeService;
import com.example.appVideoLocadoraMail.services.LocacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/efetuarLocacao")
public class LocacaoController {
    final LocacaoService locacaoService;
    final ClienteService clienteService;
    final FilmeService filmeService;
    private final LocacaoRepository locacaoRepository;

    @Autowired
    EmailService emailService;
    public LocacaoController(LocacaoService locacaoService, ClienteService clienteService,
                             FilmeService filmeservice, LocacaoRepository locacaoRepository){
        this.locacaoService = locacaoService;
        this.clienteService = clienteService;
        this.filmeService = filmeservice;
        this.locacaoRepository = locacaoRepository;
    }

    @PostMapping
    public ResponseEntity<Object> saveLocacao(@RequestBody @Valid LocacaoDto locacaoDto){
        //Modelos utilizados para carregar FilmeModel e ClienteModel no registro LocacaoModel a ser salvo no BD
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(locacaoDto.getIdCliente());
        ClienteModel cliente = clienteModelOptional.get();
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(locacaoDto.getIdFilme());
        FilmeModel filme = filmeModelOptional.get();

        //Testes de consist??ncia
        if(locacaoService.existsByIdClienteAndIdFilme(cliente, filme)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: loca????o j?? cadastrada");}
        //existsByIdLocacao TRUE"Conflito: loca????o j?? cadastrada"
        //existsByIdCliente FALSE"Conflito: cliente n??o cadastrado"
        //existsByIdFilme FALSE"Conflito: filme n??o cadastrado"
        //existsByTituloFilme TRUE"Conflito: filme j?? locado"

        //Atribui????es no registro LocacaoModel a ser salvo no BD
        var locacaoModel =  new LocacaoModel();
        BeanUtils.copyProperties(locacaoDto, locacaoModel);
        locacaoModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UCT")));
        locacaoModel.setIdCliente(cliente);
        locacaoModel.setIdFilme(filme);

        //locacaoResponse recebe retorno da opera????o CREATE(save) no BD. Se 201 ent??o ir?? enviar email ao cliente
        ResponseEntity<Object> locacaoResponse = ResponseEntity.status(HttpStatus.CREATED).body(locacaoService.save(locacaoModel));
        if (locacaoResponse.getStatusCode().value()==201) {
            EmailModel emailModel = new EmailModel();
            emailModel.setEmailFrom("email@gmail.com");
            emailModel.setEmailTo("email@terra.com.br");
            emailModel.setOwnerRef("Locadora Devs2Blu");
            emailModel.setSubject("Confirma????o de Loca????o");
            String nomeCliente = cliente.getNome();
            String tituloFilme = filme.getTituloFilme();
            Date datalocacao = new Date();
            emailModel.setText("Ola, senhor(a) " + nomeCliente + ", o filme " + tituloFilme +
                    " foi alugado no dia " + datalocacao.toString());
            emailService.sendEmail(emailModel);
        }
        return locacaoResponse;
    }

    @GetMapping
    public ResponseEntity<List<LocacaoModel>> getLocacoes(){
        return ResponseEntity.status(HttpStatus.OK).body(locacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLocacao(@PathVariable (value = "id") UUID id){
        Optional<LocacaoModel> locacaoModelOptional = locacaoService.findById(id);

        if(!locacaoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loca????o n??o encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(locacaoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLocacao(@PathVariable(value = "id") UUID id){
        Optional<LocacaoModel> locacaoModelOptional = locacaoService.findById(id);

        if(!locacaoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflito: Loca????o n??o encontrada!");
        }
       else{
            locacaoService.delete(locacaoModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Loca????o removida com sucesso!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLocacao(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid LocacaoDto locacaoDto){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(locacaoDto.getIdCliente());
        ClienteModel clientePut = clienteModelOptional.get();
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(locacaoDto.getIdFilme());
        FilmeModel filmePut = filmeModelOptional.get();

        Optional<LocacaoModel> locacaoModelOptional = locacaoService.findById(id);

        if(!locacaoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loca????o n??o encontrada!");
        }

        var locacaoModel = locacaoModelOptional.get();

        locacaoDto.setDataRegistro(locacaoModelOptional.get().getDataRegistro());
        BeanUtils.copyProperties(locacaoDto, locacaoModel);
        locacaoModel.setId(locacaoModelOptional.get().getId());
        locacaoModel.setIdCliente(clientePut);
        locacaoModel.setIdFilme(filmePut);

        return ResponseEntity.status(HttpStatus.OK).body(locacaoService.save(locacaoModel));
    }
}


