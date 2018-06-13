package edu.ifma.lbd.frete.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "frete")
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="cidade_id")
    private Cidade cidade;

    private String descricao;

    private Double peso;
    private BigDecimal valor;

    public int getId() {
        return this.id;
    }

    public void setId(int codigoFrete) {
        this.id = codigoFrete;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal calcularFrete() {
        this.valor = new BigDecimal(this.peso * 10)
                          .add(this.cidade.getTaxa() );

        return this.getValor();
    }


    @Override
    public String toString() {
        return "Frete{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", cidade=" + cidade +
                ", descricao='" + descricao + '\'' +
                ", peso=" + peso +
                ", valor=" + valor +
                '}';
    }
}

 
