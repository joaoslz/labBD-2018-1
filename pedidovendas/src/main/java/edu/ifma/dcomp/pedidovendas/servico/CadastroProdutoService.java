package edu.ifma.dcomp.pedidovendas.servico;

import edu.ifma.dcomp.pedidovendas.modelo.Produto;
import edu.ifma.dcomp.pedidovendas.repositorio.ProdutoRepository;
import edu.ifma.dcomp.pedidovendas.util.EMFactory;

import javax.persistence.EntityManager;

public class CadastroProdutoService {

    private final ProdutoRepository repositorio;
    private final EntityManager manager;

    public CadastroProdutoService() {
        this.manager = new EMFactory().getEntityManager();
        repositorio = new ProdutoRepository(manager );
    }


    public Produto salva(Produto produto) throws NegocioException {

        Produto produtoExistente = repositorio.porSku(produto.getSku() );

        if ( produtoExistente != null && !produtoExistente.equals(produto) ) {
            throw new NegocioException("Já existe um produto com o SKU informado.");
        }
        manager.getTransaction().begin();
        produto = repositorio.salva(produto);
        manager.getTransaction().commit();

        return produto;
    }

}
