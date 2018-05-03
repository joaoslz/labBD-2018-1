package ifma.dcomp.lbd.pedidovenda.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import ifma.dcomp.lbd.pedidovenda.model.Pedido;

public class Pedidos {


	private EntityManager manager;
	
    public Pedidos(EntityManager manager) {
		this.manager = manager;
	}

    
	public void salva(Pedido pedido) {
		
		if (pedido.getId() == null)
		   this.manager.persist(pedido );

		else 
		   this.manager.persist(pedido );
	}

	
	public Pedido porId(Long id) {
		return this.manager.find(Pedido.class, id);
	}


	public List<Pedido> entreguesPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
			
			String jpql = "from Pedido pedido "
					     + "where pedido.dataEntrega between :dataInicio and :dataFim";
			
			return manager
					.createQuery(jpql, Pedido.class)
					.setParameter("dataInicio", dataInicio )
					.setParameter("dataFim", dataFim )
					.getResultList();
			
		
	}
	
}