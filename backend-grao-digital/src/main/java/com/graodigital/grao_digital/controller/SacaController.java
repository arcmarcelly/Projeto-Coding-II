package com.graodigital.grao_digital.controller;

import com.graodigital.grao_digital.model.Saca;
import com.graodigital.grao_digital.model.Lote;
import com.graodigital.grao_digital.repository.SacaRepository;
import com.graodigital.grao_digital.repository.LoteRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/sacas")
public class SacaController {

    private final SacaRepository sacaRepository;
    private final LoteRepository loteRepository;

    public SacaController(SacaRepository sacaRepository, LoteRepository loteRepository) {
        this.sacaRepository = sacaRepository;
        this.loteRepository = loteRepository;
    }

    @GetMapping
    public List<Saca> listar() {
        return sacaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saca> buscar(@PathVariable Integer id) {
        return sacaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Saca saca) {
        try {
            // pego o id do lote enviado no JSON
            Integer idLote = saca.getLote().getIdLote();

            // busco o lote real do banco
            Lote loteReal = loteRepository.findById(idLote)
                    .orElseThrow(() -> new RuntimeException("Lote não encontrado: " + idLote));

            // defino o lote real dentro da saca
            saca.setLote(loteReal);

            // salvo agora sim com o lote válido
            Saca salva = sacaRepository.save(saca);

            return ResponseEntity.ok(salva);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Saca dados) {
        return sacaRepository.findById(id).map(s -> {
            try {
                // atualizar lote corretamente
                if (dados.getLote() != null) {
                    Lote loteReal = loteRepository.findById(dados.getLote().getIdLote())
                            .orElseThrow(() -> new RuntimeException("Lote não encontrado"));
                    s.setLote(loteReal);
                }

                s.setPesoKg(dados.getPesoKg());
                s.setDataFracionamento(dados.getDataFracionamento());
                s.setQrCode(dados.getQrCode());
                s.setStatus(dados.getStatus());

                return ResponseEntity.ok(sacaRepository.save(s));

            } catch (Exception e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!sacaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sacaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
