package ifma.dcomp.lbd.pedidovenda.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ifma.dcomp.lbd.pedidovenda.model.Grupo;
import ifma.dcomp.lbd.pedidovenda.model.Usuario;

public class TesteUsuario {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedidoVendaPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction transacao = manager.getTransaction();
		transacao.begin();
		
		Usuario usuario = new Usuario();
		usuario.setNome("Maria");
		usuario.setEmail("maria@vitoria.com");
		usuario.setSenha("123");
		
		
		Grupo grupo = new Grupo();
		grupo.setNome("Vendedores");
		grupo.setDescricao("Vendedores da empresa");
		
		usuario.adicionaNovo(grupo );
		
		manager.persist(usuario);
		
		transacao.commit();
		manager.close();
		factory.close();
	}
	
}
