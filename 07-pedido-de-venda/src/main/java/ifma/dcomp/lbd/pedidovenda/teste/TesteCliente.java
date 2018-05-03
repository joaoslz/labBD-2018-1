package ifma.dcomp.lbd.pedidovenda.teste;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import ifma.dcomp.lbd.pedidovenda.model.Cliente;
import ifma.dcomp.lbd.pedidovenda.model.Endereco;
import ifma.dcomp.lbd.pedidovenda.model.TipoPessoa;


public class TesteCliente {

	@Test
	public void deveSalvarUmNovoUsuario() {
		
		// cenário
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedidoVendaPUTest");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction transacao = manager.getTransaction();
		
		transacao.begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("João da Silva");
		cliente.setEmail("joao@silva.com");
		cliente.setDocumentoReceitaFederal("93655818696");
		
		cliente.setTipo(TipoPessoa.FISICA);
		
		System.out.println(TipoPessoa.FISICA.getTipo() );
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua do Sol");
		endereco.setNumero("100");
		endereco.setCidade("São Luís");
		endereco.setUf("MA");
		endereco.setCep("65000-000");
		
		endereco.setCliente(cliente);
		cliente.adicionaUmEndereco(endereco );
		
		// ação
		manager.persist(cliente);
		manager.flush();
		manager.clear();
		
		// verificação
        Cliente clienteDoBanco = manager.createQuery("from Cliente where email= :email", Cliente.class)
               .setParameter("email", "joao@silva.com")
               .getSingleResult();
        
      
        Assert.assertEquals(clienteDoBanco.getNome(), "João da Silva" );
		
        
        
		transacao.commit();

		manager.close();
		factory.close();
	}
	
	
	
	@Test
	public void deveExcluirUmUsuarioExistente() {
		
		// cenário
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedidoVendaPUTest");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction transacao = manager.getTransaction();
		
		transacao.begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("João Sousa");
		cliente.setEmail("joao@sousa.com");
		cliente.setDocumentoReceitaFederal("93655818634");
		
		cliente.setTipo(TipoPessoa.FISICA);
			
		// ação
		manager.persist(cliente);
		
		manager.flush();
		manager.clear();
		
		// verificação
        Cliente clienteDoBanco = manager.createQuery("from Cliente where email= :email", Cliente.class)
               .setParameter("email", "joao@sousa.com")
               .getSingleResult();
        
        Assert.assertEquals(clienteDoBanco.getNome(), "João Sousa" );
        
        cliente = clienteDoBanco;
		
        manager.remove(cliente );
        
        try {
         Cliente clienteExcluido = manager.createQuery("from Cliente where email= :email", Cliente.class)
                 .setParameter("email", "joao@sousa.com")
                 .getSingleResult();
         // ...
         Assert.fail("Usuarios não deveria existir no banco de dados");
         
        } catch(NoResultException e) {
        	// ... 
        }
        
        
		transacao.commit();

		manager.close();
		factory.close();
	}

	
	
/*	public static void main(String[] args) {
		
		// cenário
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedidoVendaPUTest");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction transacao = manager.getTransaction();
		
		transacao.begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("João da Silva");
		cliente.setEmail("joao@silva.com");
		cliente.setDocumentoReceitaFederal("93655818696");
		
		cliente.setTipo(TipoPessoa.FISICA);
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua do Sol");
		endereco.setNumero("100");
		endereco.setCidade("São Luís");
		endereco.setUf("MA");
		endereco.setCep("65000-000");
		
		endereco.setCliente(cliente);
		cliente.adicionaUmEndereco(endereco );
		
		// ação
		manager.persist(cliente);
		manager.flush();
		manager.clear();
		
		// verificação
		Cliente clienteDoBanco = manager.createQuery("from Cliente where email= :email", Cliente.class)
				.setParameter("email", "joao@silva.com")
				.getSingleResult();
		
		System.out.println("################ " + clienteDoBanco.getNome() );
		
		System.out.println("################ " + clienteDoBanco.getNome().equals("João da Silva") );
		
		
		
		transacao.commit();
		
		manager.close();
		factory.close();
		
		
		
		
	}
	
*/
}
