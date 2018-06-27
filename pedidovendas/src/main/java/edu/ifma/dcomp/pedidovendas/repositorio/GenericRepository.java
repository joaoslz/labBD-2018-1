package edu.ifma.dcomp.pedidovendas.repositorio;

import javax.persistence.EntityManager;

public class GenericRepository<T> {

    private final EntityManager manager;

    public GenericRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void salva(T t) {
        this.manager.persist(t );
    }

    public T buscaPorId(Class<T> clazz, Integer id) {
        return this.manager.find(clazz, id);
    }
}
