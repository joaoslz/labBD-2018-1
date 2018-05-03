package ifma.dcomp.lbd.pedidovenda.service;

import ifma.dcomp.lbd.pedidovenda.model.Produto;
import ifma.dcomp.lbd.pedidovenda.repository.Produtos;

public class CadastroProdutoService  {

	

	private Produtos produtos;
	
	
	public Produto salvar(Produto produto) {
		
		Produto produtoExistente = produtos.buscaPorSku(produto.getSku() );
		
		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			
			throw new IllegalArgumentException("Já existe um produto com o SKU informado.");
		}
		
		return produtos.salva(produto );
	}
	
}