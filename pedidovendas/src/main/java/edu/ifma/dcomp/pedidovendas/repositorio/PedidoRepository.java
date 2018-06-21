package edu.ifma.dcomp.pedidovendas.repositorio;

import edu.ifma.dcomp.pedidovendas.modelo.Pedido;

import javax.persistence.EntityManager;

public class PedidoRepository {


    private final EntityManager manager;


    public PedidoRepository(EntityManager manager) {
        this.manager = manager;
    }


    public Pedido porId(Integer id) {
        return this.manager.find(Pedido.class, id);
    }


    public Pedido salva(Pedido pedido) {
       return manager.merge( pedido );
    }




}
