package edu.ifma.lbd.frete.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="cidade")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String uf;
	private BigDecimal taxa;


	public int getId() {
		return this.id;
	}

	public void setId(int codigoCidade) {
		this.id = codigoCidade;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cidade)) return false;
		Cidade cidade = (Cidade) o;
		return getId() == cidade.getId();
	}

	@Override
	public int hashCode() {

		return Objects.hash(getId());
	}

	public String toString() {
               return this.nome;
        }



}

