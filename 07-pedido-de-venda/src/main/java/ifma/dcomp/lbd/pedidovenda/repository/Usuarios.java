package ifma.dcomp.lbd.pedidovenda.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import ifma.dcomp.lbd.pedidovenda.model.Usuario;

public class Usuarios {
	
	private EntityManager manager;
	
	public Usuarios(EntityManager manager) {
		this.manager = manager;
	}
	
	
	public void salvaNovo(Usuario usuario) {
		manager.persist(usuario);
	}

	
	public Usuario atualiza(Usuario usuario) {
		return manager.merge(usuario);
	}
	
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	public List<Usuario> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	

	public Usuario porNome(String nome) {
		
		return this.manager
				.createQuery("from Usuario where nome = :nome", Usuario.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}

	public Usuario porEmail(String email) {
		
		Usuario usuario = null;
		
		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
	
		} catch (NoResultException e) {
			System.out.println("Usuário não encontrado ...");
		}
		
		return usuario;
	}


	public void exclui(Usuario usuario) {
		manager.remove(usuario );
		
	}


	
}