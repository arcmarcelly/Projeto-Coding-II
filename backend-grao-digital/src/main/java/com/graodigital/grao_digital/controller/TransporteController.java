package com.graodigital.grao_digital.controller;

import com.graodigital.grao_digital.model.Transporte;
import com.graodigital.grao_digital.model.Saca;
import com.graodigital.grao_digital.model.Agricultor;
import com.graodigital.grao_digital.repository.TransporteRepository;
import com.graodigital.grao_digital.repository.SacaRepository;
import com.graodigital.grao_digital.repository.AgricultorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {

    private final TransporteRepository transporteRepository;
    private final SacaRepository sacaRepository;
    private final AgricultorRepository agricultorRepository;

    public TransporteController(
            TransporteRepository transporteRepository,
            SacaRepository sacaRepository,
            AgricultorRepository agricultorRepository
    ) {
        this.transporteRepository = transporteRepository;
        this.sacaRepository = sacaRepository;
        this.agricultorRepository = agricultorRepository;
    }

    @GetMapping
    public List<Transporte> listar() {
        return transporteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transporte> buscar(@PathVariable Integer id) {
        return transporteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Transporte dados) {
        try {
            Transporte t = new Transporte();

            t.setNomeMotorista(dados.getNomeMotorista());
            t.setDataHoraSaida(dados.getDataHoraSaida());
            t.setDataHoraChegada(dados.getDataHoraChegada());
            t.setLocalEntrega(dados.getLocalEntrega());

            if (dados.getSaca() != null) {
                Saca s = sacaRepository.findById(dados.getSaca().getIdSaca())
                        .orElseThrow(() -> new RuntimeException("Saca não encontrada"));
                t.setSaca(s);
            }

            if (dados.getAgricultor() != null) {
                Agricultor a = agricultorRepository.findById(dados.getAgricultor().getIdAgricultor())
                        .orElseThrow(() -> new RuntimeException("Agricultor não encontrado"));
                t.setAgricultor(a);
            }

            return ResponseEntity.ok(transporteRepository.save(t));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Transporte dados) {
        try {
            return transporteRepository.findById(id).map(t -> {

                t.setNomeMotorista(dados.getNomeMotorista());
                t.setDataHoraSaida(dados.getDataHoraSaida());
                t.setDataHoraChegada(dados.getDataHoraChegada());
                t.setLocalEntrega(dados.getLocalEntrega());

                if (dados.getSaca() != null) {
                    Saca s = sacaRepository.findById(dados.getSaca().getIdSaca())
                            .orElseThrow(() -> new RuntimeException("Saca não encontrada"));
                    t.setSaca(s);
                }

                if (dados.getAgricultor() != null) {
                    Agricultor a = agricultorRepository.findById(dados.getAgricultor().getIdAgricultor())
                            .orElseThrow(() -> new RuntimeException("Agricultor não encontrado"));
                    t.setAgricultor(a);
                }

                return ResponseEntity.ok(transporteRepository.save(t));

            }).orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!transporteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        transporteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
