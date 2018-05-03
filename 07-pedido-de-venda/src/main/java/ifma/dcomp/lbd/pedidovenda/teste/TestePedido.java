package ifma.dcomp.lbd.pedidovenda.teste;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ifma.dcomp.lbd.pedidovenda.model.Cliente;
import ifma.dcomp.lbd.pedidovenda.model.EnderecoEntrega;
import ifma.dcomp.lbd.pedidovenda.model.FormaPagamento;
import ifma.dcomp.lbd.pedidovenda.model.ItemPedido;
import ifma.dcomp.lbd.pedidovenda.model.Pedido;
import ifma.dcomp.lbd.pedidovenda.model.Produto;
import ifma.dcomp.lbd.pedidovenda.model.StatusPedido;
import ifma.dcomp.lbd.pedidovenda.model.Usuario;


public class TestePedido {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedidoVendaPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction transacao = manager.getTransaction();
		transacao.begin();
		
		Cliente cliente = manager.find(Cliente.class, 1L);
		Produto produto = manager.find(Produto.class, 1L);
		Usuario vendedor = manager.find(Usuario.class, 1L);
		
		EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
		enderecoEntrega.setLogradouro("Rua das Flores");
		enderecoEntrega.setNumero("1000");
		enderecoEntrega.setCidade("São Luís");
		enderecoEntrega.setUf("MA");
		enderecoEntrega.setCep("65004-123");
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		
		pedido.setDataCriacao(LocalDateTime.now() );
		pedido.setDataEntrega(LocalDate.now().plusDays(10) );
		
		pedido.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
		
		pedido.setObservacao("Aberto das 08 às 18h");
		
		pedido.setStatus(StatusPedido.ORCAMENTO);
		
		pedido.setValorDesconto(BigDecimal.ZERO);
		pedido.setValorFrete(BigDecimal.ZERO);
		pedido.setValorTotal(new BigDecimal(23.2));
		
		pedido.setVendedor(vendedor);
		pedido.setEnderecoEntrega(enderecoEntrega);
		
		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setQuantidade(10);
		item.setValorUnitario(new BigDecimal(2.32));
		item.setPedido(pedido);
		
		pedido.getItens().add(item);
		
		manager.persist(pedido);
		
		transacao.commit();
	}
	
}
