package edu.ifma.lbd.frete.repository;

import edu.ifma.lbd.frete.model.Cidade;

import javax.persistence.EntityManager;
import java.util.List;

public class CidadeRepository {


    private final EntityManager manager;

    public CidadeRepository(EntityManager manager) {
        this.manager = manager;
    }


    public List<Cidade> todasCidades() {
        return manager
                .createQuery("from Cidade" , Cidade.class )
                .getResultList();
    }



    public Cidade consultarCidade(int id) {
        return manager.createQuery("from Cidade c where c.id = :id", Cidade.class)
                .setParameter("id", id)
                .getSingleResult();
    }


}
