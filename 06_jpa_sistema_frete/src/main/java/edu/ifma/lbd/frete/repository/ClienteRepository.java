package edu.ifma.lbd.frete.repository;

import edu.ifma.lbd.frete.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepository {


    private final EntityManager manager;

    public ClienteRepository(EntityManager manager) {
        this.manager = manager;
    }


    public List<Cliente> todosClientes() {
        return manager
                .createQuery("from Cliente" , Cliente.class )
                .getResultList();
    }

    public Cliente consultarCliente(int id) {
        return manager.createQuery("from Cliente c where c.id = :id", Cliente.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
