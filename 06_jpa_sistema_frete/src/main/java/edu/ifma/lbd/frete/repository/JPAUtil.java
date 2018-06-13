package edu.ifma.lbd.frete.repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("fretePU");

    public EntityManager getEntityManager() {
        return factory.createEntityManager();

    }
}

