package ifma.dcomp.lbd.pedidovenda.teste;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ifma.dcomp.lbd.pedidovenda.model.Categoria;
import ifma.dcomp.lbd.pedidovenda.model.Produto;


public class TesteProduto {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedidoVendaPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction transacao = manager.getTransaction();
		
		transacao.begin();
		
		// instanciamos a categoria pai
		Categoria categoriaPai = new Categoria();
		categoriaPai.setDescricao("Informática");
		
		// instanciamos a categoria filha
		Categoria categoriaFilha = new Categoria();
		categoriaFilha.setDescricao("Smartphone");
		
		
		Categoria categoriaFilha2 = new Categoria();
		categoriaFilha2.setDescricao("UltraBook");

		
//		categoriaFilha.setCategoriaPai(categoriaPai);
//		categoriaFilha2.setCategoriaPai(categoriaPai);
		
		
		// adicionamos a categoria Refrigerantes como filha de Informática
		categoriaPai.adicionaSubCategoria(categoriaFilha);
		categoriaPai.adicionaSubCategoria(categoriaFilha2);
		
		// ao persistir a categoria pai (Informática), a filha (Notebook) 
		// deve ser persistida também
		manager.persist(categoriaPai);
		
		// instanciamos e persistimos um produto
		Produto produto = new Produto();
		produto.setCategoria(categoriaFilha);
		produto.setNome("Notebook HP Core i7");
		produto.setQuantidadeEstoque(10);
		produto.setSku("NOT3143");
		produto.setValorUnitario(new BigDecimal(2900.89) );
		
		manager.persist(produto);
		
		transacao.commit();
		
		manager.close();
		factory.close();
	}
	
}
