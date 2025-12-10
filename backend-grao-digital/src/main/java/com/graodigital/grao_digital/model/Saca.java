package com.graodigital.grao_digital.model;

import com.graodigital.grao_digital.model.enums.StatusSaca;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Saca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSaca;

    private Double pesoKg;

    private LocalDate dataFracionamento;

    private String qrCode;

    @Enumerated(EnumType.STRING)
    private StatusSaca status;

    // Relacionamento com Lote
    @ManyToOne
    @JoinColumn(name = "id_lote", nullable = false)
    private Lote lote;

    // Getters e Setters
    public Integer getIdSaca() {
        return idSaca;
    }

    public void setIdSaca(Integer idSaca) {
        this.idSaca = idSaca;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public LocalDate getDataFracionamento() {
        return dataFracionamento;
    }

    public void setDataFracionamento(LocalDate dataFracionamento) {
        this.dataFracionamento = dataFracionamento;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public StatusSaca getStatus() {
        return status;
    }

    public void setStatus(StatusSaca status) {
        this.status = status;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }
}
