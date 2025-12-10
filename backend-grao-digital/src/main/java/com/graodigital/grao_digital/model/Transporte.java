package com.graodigital.grao_digital.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transporte")
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransporte;

    @Column(nullable = false, length = 100)
    private String nomeMotorista;

    @Column(nullable = false)
    private LocalDateTime dataHoraSaida;

    private LocalDateTime dataHoraChegada;

    @Column(length = 200)
    private String localEntrega;

    @ManyToOne
    @JoinColumn(name = "idSaca")
    private Saca saca;

    @ManyToOne
    @JoinColumn(name = "idAgricultor")
    private Agricultor agricultor;

    // Getters e Setters
    public Integer getIdTransporte() { return idTransporte; }
    public void setIdTransporte(Integer idTransporte) { this.idTransporte = idTransporte; }

    public String getNomeMotorista() { return nomeMotorista; }
    public void setNomeMotorista(String nomeMotorista) { this.nomeMotorista = nomeMotorista; }

    public LocalDateTime getDataHoraSaida() { return dataHoraSaida; }
    public void setDataHoraSaida(LocalDateTime dataHoraSaida) { this.dataHoraSaida = dataHoraSaida; }

    public LocalDateTime getDataHoraChegada() { return dataHoraChegada; }
    public void setDataHoraChegada(LocalDateTime dataHoraChegada) { this.dataHoraChegada = dataHoraChegada; }

    public String getLocalEntrega() { return localEntrega; }
    public void setLocalEntrega(String localEntrega) { this.localEntrega = localEntrega; }

    public Saca getSaca() { return saca; }
    public void setSaca(Saca saca) { this.saca = saca; }

    public Agricultor getAgricultor() { return agricultor; }
    public void setAgricultor(Agricultor agricultor) { this.agricultor = agricultor; }
}
