package edu.ifma.lbd.frete.model;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente implements java.io.Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String nome;
	private String endereco;
	private String telefone;

	public Cliente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int codigoCliente) {
		this.id = codigoCliente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
        
    public String toString() {
               return this.nome;
        }
}

