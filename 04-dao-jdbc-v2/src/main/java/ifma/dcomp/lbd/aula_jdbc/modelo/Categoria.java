package ifma.dcomp.lbd.aula_jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private final Integer id;
    private final String nome;
    
    private final List<Produto> produtos = new ArrayList<>();

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Integer getId() {
		return id;
	}
    
    public String getNome() {
		return nome;
	}

	public void adiciona(Produto produto) {
		produtos.add(produto);
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
}