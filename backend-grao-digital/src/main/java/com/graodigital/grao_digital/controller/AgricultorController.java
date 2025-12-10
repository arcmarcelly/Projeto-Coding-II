package com.graodigital.grao_digital.controller;

import com.graodigital.grao_digital.model.Agricultor;
import com.graodigital.grao_digital.repository.AgricultorRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/agricultores")
@CrossOrigin(origins = "http://localhost:3001") // porta do seu React
public class AgricultorController {

    private final AgricultorRepository agricultorRepository;

    public AgricultorController(AgricultorRepository agricultorRepository) {
        this.agricultorRepository = agricultorRepository;
    }

    @GetMapping
    public List<Agricultor> listar() {
        return agricultorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agricultor> buscar(@PathVariable Integer id) {
        return agricultorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agricultor criar(@RequestBody Agricultor agricultor) {
        return agricultorRepository.save(agricultor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agricultor> atualizar(@PathVariable Integer id, @RequestBody Agricultor dados) {
        return agricultorRepository.findById(id).map(a -> {
            a.setNome(dados.getNome());
            a.setTelefone(dados.getTelefone());
            a.setLocalEndereco(dados.getLocalEndereco());
            a.setCPF(dados.getCPF());
            a.setTipoCultura(dados.getTipoCultura());
            a.setUsuario(dados.getUsuario());
            return ResponseEntity.ok(agricultorRepository.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!agricultorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        agricultorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
