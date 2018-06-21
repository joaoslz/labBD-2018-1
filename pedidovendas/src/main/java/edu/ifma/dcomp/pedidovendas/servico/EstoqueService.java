package edu.ifma.dcomp.pedidovendas.servico;

import edu.ifma.dcomp.pedidovendas.modelo.ItemPedido;
import edu.ifma.dcomp.pedidovendas.modelo.Pedido;
import edu.ifma.dcomp.pedidovendas.repositorio.PedidoRepository;
import edu.ifma.dcomp.pedidovendas.util.EMFactory;

import javax.persistence.EntityManager;

public class EstoqueService  {


	private final PedidoRepository repositorio;
	private final EntityManager manager;

	public EstoqueService(PedidoRepository repositorio) {

		this.manager = new EMFactory().getEntityManager();
		this.repositorio = new PedidoRepository(manager );
	}

	public void baixarItensEstoque(Pedido pedido) throws NegocioException {


		pedido = this.repositorio.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensParaOEstoque(Pedido pedido) {
		pedido = this.repositorio.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {

			item.getId().getProduto().adicionaEstoque(item.getQuantidade() );
		}
	}
	
}