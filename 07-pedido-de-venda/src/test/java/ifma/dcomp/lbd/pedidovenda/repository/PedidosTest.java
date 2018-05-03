package ifma.dcomp.lbd.pedidovenda.repository;


import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ifma.dcomp.lbd.pedidovenda.builer.PedidoBuilder;
import ifma.dcomp.lbd.pedidovenda.builer.UsuarioBuilder;
import ifma.dcomp.lbd.pedidovenda.model.Cliente;
import ifma.dcomp.lbd.pedidovenda.model.Pedido;
import ifma.dcomp.lbd.pedidovenda.model.TipoPessoa;
import ifma.dcomp.lbd.pedidovenda.model.Usuario;

public class PedidosTest {
	
	
	private EntityManager manager;
	
	private static EntityManagerFactory emf;
	private Pedidos pedidos;


	@BeforeClass
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("pedidoVendaPUTest");
	}
	
	@Before
	public void antes() {
		this.manager = emf.createEntityManager();
		this.manager.getTransaction().begin();
		
		this.pedidos= new Pedidos(manager ); 
		
	}
	
	@After
	public void depois() {
		manager.getTransaction().rollback();
	}
	
	@AfterClass
	public static void fim() {
		emf.close();
	}

	

    @Test
    public void deveTrazerPedidosEntreguesPorPeriodo() {

        // criando as datas
    	LocalDate dataInicio = LocalDate.now().minusDays(10);
    	LocalDate dataFim = LocalDate.now();
        
        
    	//deveria ter um builder para cliente
		Cliente cliente = new Cliente();
		cliente.setNome("João da Silva");
		cliente.setEmail("joao@silva.com");
		cliente.setDocumentoReceitaFederal("93655818696");
		cliente.setTipo(TipoPessoa.FISICA);
		
		Usuario vendedor = UsuarioBuilder.umUsuario().constroi();
		cliente = manager.merge(cliente );
		vendedor = manager.merge(vendedor );
		
		manager.flush();
		
    	
    	// criando as locações, cada um com uma data
        Pedido pedidoMuitoAntigo      = PedidoBuilder.umPedido()
        		                          .realizadoPorUm(vendedor)
        		                          .para(cliente)
        		                          .comDataEntrega(dataInicio.minusDays(5))
        		                          .comValor(10.0)
        		                          .constroi();
	
        
        Pedido pedidoNoInicioDoPeriodo= PedidoBuilder.umPedido().realizadoPorUm(vendedor).para(cliente).comDataEntrega(dataInicio).comValor(20.0).constroi();
		Pedido pedidoNoFimDoPeriodo   = PedidoBuilder.umPedido().realizadoPorUm(vendedor).para(cliente).comDataEntrega(dataFim).comValor(30.0).constroi();
		Pedido pedidoNoPeriodo        = PedidoBuilder.umPedido().realizadoPorUm(vendedor).para(cliente).comDataEntrega(dataInicio.plusDays(5)).comValor(40.0).constroi();
		
			
        // persistindo os objetos no banco
        pedidos.salva(pedidoMuitoAntigo);
        pedidos.salva(pedidoNoInicioDoPeriodo);
        pedidos.salva(pedidoNoFimDoPeriodo);
        pedidos.salva(pedidoNoPeriodo);
        
        manager.flush();
        
        // invocando o metodo para testar
        List<Pedido> listaPedidos = pedidos.entreguesPorPeriodo(dataInicio, dataFim);

        
        // garantindo que a query funcionou
        assertEquals(3, listaPedidos.size() );
       
        
        Assert.assertThat(20.0, CoreMatchers.is(listaPedidos.get(0).getValorTotal().doubleValue()) );
        Assert.assertThat(30.0, CoreMatchers.is(listaPedidos.get(1).getValorTotal().doubleValue()) );
        Assert.assertThat(40.0, CoreMatchers.is(listaPedidos.get(2).getValorTotal().doubleValue()) );
      
    }

}
