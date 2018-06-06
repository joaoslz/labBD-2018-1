package ifma.dcomp.lbd.pedidovenda.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import ifma.dcomp.lbd.pedidovenda.model.Produto;

public class Produtos {


	private final EntityManager manager;

	public Produtos(EntityManager manager) {
		this.manager = manager;
	}


    public Produto salva(Produto produto) {
		manager.getTransaction().begin();

		Produto produtoSalvo = manager.merge(produto);

		manager.getTransaction().commit();

		return produtoSalvo;
	}
	

	public void remove(Produto produto) {
	
		try {
			
			manager.getTransaction().begin();
			produto = buscaPorId(produto.getId());
			manager.remove(produto);
			
			manager.getTransaction().commit();

		} catch (PersistenceException e) {
			manager.getTransaction().rollback();
			throw new IllegalStateException("Produto não pode ser excluído.");
		}
	}

	public Produto buscaPorSku(String sku) {


		try {
			Produto produtoDoBanco =
					manager
					.createQuery("from Produto where upper(sku) = :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase())
					.getSingleResult();

			return produtoDoBanco;

		} catch (NoResultException  | NonUniqueResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Produto buscaPorId(Long id) {
		return manager.find(Produto.class, id);
	}

	
	public List<Produto> buscaPorNome(String nome) {
		return this.manager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
}