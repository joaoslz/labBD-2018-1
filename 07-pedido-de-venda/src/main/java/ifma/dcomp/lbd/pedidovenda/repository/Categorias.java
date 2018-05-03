package ifma.dcomp.lbd.pedidovenda.repository;

import java.util.List;

import javax.persistence.EntityManager;

import ifma.dcomp.lbd.pedidovenda.model.Categoria;

public class Categorias {

	private EntityManager manager;
	
	public Categoria buscaPorId(Long id) {
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> buscaApenasCategoriasPai() {
		return manager.createQuery("from Categoria where categoriaPai is null", 
				Categoria.class).getResultList();
	}
	
	
	public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
		return manager.createQuery("from Categoria where categoriaPai = :raiz", 
				Categoria.class).setParameter("raiz", categoriaPai).getResultList();
	}
	
}