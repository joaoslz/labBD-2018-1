package edu.ifma.dcomp.pedidovendas.servico;

import edu.ifma.dcomp.pedidovendas.modelo.Pedido;
import edu.ifma.dcomp.pedidovendas.modelo.StatusPedido;
import edu.ifma.dcomp.pedidovendas.repositorio.PedidoRepository;
import edu.ifma.dcomp.pedidovendas.util.EMFactory;

import javax.persistence.EntityManager;

public class CancelamentoPedidoService  {



	private EstoqueService estoqueService;

    private final PedidoRepository repositorio;
	private final EntityManager manager;

	public CancelamentoPedidoService(PedidoRepository repositorio ) {

		this.manager = new EMFactory().getEntityManager();
		this.repositorio = new PedidoRepository(manager );
	}



	public Pedido cancela(Pedido pedido) throws NegocioException {
		pedido = this.repositorio.porId(pedido.getId());
		
		if (pedido.isNaoCancelavel() ) {

			throw new NegocioException("Pedido não pode ser cancelado no status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		if (pedido.isEmitido()) {
			this.estoqueService.retornarItensParaOEstoque(pedido );
		}
		
		pedido.setStatus(StatusPedido.CANCELADO );
		
		pedido = this.repositorio.salva(pedido );
		
		return pedido;
	}

}