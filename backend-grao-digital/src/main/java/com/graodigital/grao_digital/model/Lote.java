package com.graodigital.grao_digital.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "lote")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLote")
    private Integer idLote;

    @Column(name = "tipo_semente")
    private String tipoSemente;

    @Column(name = "data_recebimento")
    private LocalDate dataRecebimento;

    @Column(name = "qtd_kg")
    private BigDecimal qtdKg;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor_id_fornecedor")  // CORRETO
    private Fornecedor fornecedor;
    
     public Lote() {}

    // getters e setters
    public Integer getIdLote() { return idLote; }
    public void setIdLote(Integer idLote) { this.idLote = idLote; }

    public String getTipoSemente() { return tipoSemente; }
    public void setTipoSemente(String tipoSemente) { this.tipoSemente = tipoSemente; }

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }

    public BigDecimal getQtdKg() { return qtdKg; }
    public void setQtdKg(BigDecimal qtdKg) { this.qtdKg = qtdKg; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
}
