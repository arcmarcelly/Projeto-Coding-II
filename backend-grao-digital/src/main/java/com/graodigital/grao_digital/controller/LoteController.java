package com.graodigital.grao_digital.controller;

import com.graodigital.grao_digital.model.Fornecedor;
import com.graodigital.grao_digital.model.Lote;
import com.graodigital.grao_digital.repository.FornecedorRepository;
import com.graodigital.grao_digital.repository.LoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteRepository loteRepository;
    private final FornecedorRepository fornecedorRepository;

    // Construtor sem @Autowired é suficiente
    public LoteController(LoteRepository loteRepository, FornecedorRepository fornecedorRepository) {
        this.loteRepository = loteRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    // LISTAR TODOS OS LOTES
    @GetMapping
    public ResponseEntity<List<Lote>> listar() {
        List<Lote> lotes = loteRepository.findAll();
        if (lotes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lotes);
    }

    //buscar lote
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        Optional<Lote> lote = loteRepository.findById(id);
        if (lote.isPresent()) {
            return ResponseEntity.ok(lote.get());
        } else {
            return ResponseEntity.status(404)
                    .body("Lote com ID " + id + " não encontrado.");
        }
    }


    // CRIAR LOTE
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Lote lote) {
        if (lote.getTipoSemente() == null || lote.getTipoSemente().isEmpty()) {
            return ResponseEntity.badRequest().body("Campo 'tipoSemente' é obrigatório.");
        }
        if (lote.getQtdKg() == null) {
            return ResponseEntity.badRequest().body("Campo 'qtdKg' é obrigatório.");
        }
        if (lote.getFornecedor() == null || lote.getFornecedor().getIdFornecedor() == null) {
            return ResponseEntity.badRequest().body("Campo 'fornecedor' é obrigatório.");
        }

        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(lote.getFornecedor().getIdFornecedor());
        if (fornecedor.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Fornecedor com ID " + lote.getFornecedor().getIdFornecedor() + " não encontrado.");
        }

        lote.setFornecedor(fornecedor.get());
        Lote novoLote = loteRepository.save(lote);
        return ResponseEntity.ok(novoLote);
    }

    // ATUALIZAR LOTE
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Lote dados) {
        Optional<Lote> loteOptional = loteRepository.findById(id);
        if (loteOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Lote com ID " + id + " não encontrado.");
        }

        Lote l = loteOptional.get();

        if (dados.getTipoSemente() != null && !dados.getTipoSemente().isEmpty()) {
            l.setTipoSemente(dados.getTipoSemente());
        }

        if (dados.getQtdKg() != null) {
            l.setQtdKg(dados.getQtdKg());
        }

        if (dados.getFornecedor() != null && dados.getFornecedor().getIdFornecedor() != null) {
            Optional<Fornecedor> fornecedor = fornecedorRepository.findById(dados.getFornecedor().getIdFornecedor());
            if (fornecedor.isEmpty()) {
                return ResponseEntity.status(404)
                        .body("Fornecedor com ID " + dados.getFornecedor().getIdFornecedor() + " não encontrado.");
            }
            l.setFornecedor(fornecedor.get());
        }

        Lote atualizado = loteRepository.save(l);
        return ResponseEntity.ok(atualizado);
    }

    // DELETAR LOTE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        if (!loteRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Lote com ID " + id + " não encontrado.");
        }
        loteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
