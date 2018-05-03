package ifma.dcomp.lbd.pedidovenda.repository;

import java.util.List;

import javax.persistence.EntityManager;

import ifma.dcomp.lbd.pedidovenda.model.Cliente;

public class Clientes  {

	
	private EntityManager manager;
	
	public Cliente buscaPorId(Long id) {
		return this.manager.find(Cliente.class, id);
	}
	
	public List<Cliente> buscaPorNome(String nome) {
		return this.manager.createQuery("from Cliente " +
				"where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	
}