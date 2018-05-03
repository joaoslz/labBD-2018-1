package ifma.dcomp.lbd.pedidovenda.builer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import ifma.dcomp.lbd.pedidovenda.model.Cliente;
import ifma.dcomp.lbd.pedidovenda.model.EnderecoEntrega;
import ifma.dcomp.lbd.pedidovenda.model.FormaPagamento;
import ifma.dcomp.lbd.pedidovenda.model.ItemPedido;
import ifma.dcomp.lbd.pedidovenda.model.Pedido;
import ifma.dcomp.lbd.pedidovenda.model.StatusPedido;
import ifma.dcomp.lbd.pedidovenda.model.Usuario;

public class PedidoBuilder {

	private Pedido pedido;
	
	
	private PedidoBuilder() { 	}

	public static PedidoBuilder umPedido() {
		PedidoBuilder builder = new PedidoBuilder();
		
		builder.pedido = new Pedido();
		
		builder.pedido.setDataCriacao(LocalDateTime.now() );

		builder.pedido.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
		builder.pedido.setObservacao("Aberto das 08 às 18h");
		builder.pedido.setStatus(StatusPedido.ORCAMENTO);
		
		builder.pedido.setValorDesconto(BigDecimal.ZERO);
		builder.pedido.setValorFrete(BigDecimal.ZERO);
		builder.pedido.setValorTotal(new BigDecimal(23.2));
		
		builder.pedido.setEnderecoEntrega(defineEnderecoDeEntrega() );

		
		
		return builder;
	}

	private static EnderecoEntrega defineEnderecoDeEntrega() {
		EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
		enderecoEntrega.setLogradouro("Rua das Flores");
		enderecoEntrega.setNumero("1000");
		enderecoEntrega.setCidade("São Luís");
		enderecoEntrega.setUf("MA");
		enderecoEntrega.setCep("65004-123");
		
		
		return enderecoEntrega;
	}

	public PedidoBuilder comValor(double valor) {
		
		this.pedido.setValorTotal(new BigDecimal(valor) );
		return this;
	}

	
	public PedidoBuilder doVendedor(Usuario vendedor) {
		this.pedido.setVendedor(vendedor );
		return this;
	}
	
	
	public PedidoBuilder entregaNo(EnderecoEntrega endereco ) {
		this.pedido.setEnderecoEntrega(endereco );
		return this;
		
	}
	
	public PedidoBuilder comDataEntrega(LocalDate dataEntrega ) {
		this.pedido.setDataEntrega(dataEntrega );
		return this;
	}

	
	public PedidoBuilder comItens(ItemPedido... itens) {
        
		for (ItemPedido item : itens) {
			this.pedido.adiciona(item );
		}
		
		return this;
	}
	
	
	public Pedido constroi() {
		return this.pedido;
	}

	public PedidoBuilder para(Cliente cliente) {
		this.pedido.setCliente(cliente);
		return this;
	}

	public PedidoBuilder realizadoPorUm(Usuario vendedor) {
		this.pedido.setVendedor(vendedor );
		return this;
	}
	

}
