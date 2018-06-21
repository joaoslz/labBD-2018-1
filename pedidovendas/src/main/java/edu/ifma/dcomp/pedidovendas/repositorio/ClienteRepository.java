package edu.ifma.dcomp.pedidovendas.repositorio;

import edu.ifma.dcomp.pedidovendas.modelo.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepository {

    private final EntityManager manager;

    public ClienteRepository(EntityManager manager) {
        this.manager = manager;
    }


    public Cliente buscaPor(Long id) {
        return this.manager.find(Cliente.class, id);
    }


    public List<Cliente> porNome(String nome) {
        return this.manager.createQuery("from Cliente " +
                "where upper(nome) like :nome", Cliente.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }
}
