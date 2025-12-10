package com.graodigital.grao_digital.controller;

import com.graodigital.grao_digital.model.Fornecedor;
import com.graodigital.grao_digital.repository.FornecedorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorController(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    // ------------------------------
    // LISTAR TODOS OS FORNECEDORES
    // ------------------------------
    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        if (fornecedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fornecedores);
    }

    // ------------------------------
    // BUSCAR FORNECEDOR POR ID
    // ------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isEmpty()) {
            return ResponseEntity.status(404).body("Fornecedor com ID " + id + " não encontrado.");
        }
        return ResponseEntity.ok(fornecedor.get());
    }

    // ------------------------------
    // CRIAR FORNECEDOR
    // ------------------------------
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Fornecedor fornecedor) {
        try {
            if (fornecedor.getNome() == null || fornecedor.getNome().isEmpty()) {
                return ResponseEntity.badRequest().body("Campo 'nome' é obrigatório.");
            }
            if (fornecedor.getContato() == null || fornecedor.getContato().isEmpty()) {
                return ResponseEntity.badRequest().body("Campo 'contato' é obrigatório.");
            }

            Fornecedor novoFornecedor = fornecedorRepository.save(fornecedor);
            return ResponseEntity.ok(novoFornecedor);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Erro ao salvar fornecedor: " + e.getMessage());
        }
    }

    // ------------------------------
    // ATUALIZAR FORNECEDOR
    // ------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Fornecedor dados) {
        try {
            Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
            if (fornecedorOptional.isEmpty()) {
                return ResponseEntity.status(404).body("Fornecedor com ID " + id + " não encontrado.");
            }

            Fornecedor f = fornecedorOptional.get();
            if (dados.getNome() != null && !dados.getNome().isEmpty()) f.setNome(dados.getNome());
            if (dados.getContato() != null && !dados.getContato().isEmpty()) f.setContato(dados.getContato());

            Fornecedor atualizado = fornecedorRepository.save(f);
            return ResponseEntity.ok(atualizado);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

    // ------------------------------
    // DELETAR FORNECEDOR
    // ------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        try {
            if (!fornecedorRepository.existsById(id)) {
                return ResponseEntity.status(404).body("Fornecedor com ID " + id + " não encontrado.");
            }
            fornecedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Erro ao deletar fornecedor: " + e.getMessage());
        }
    }
}
