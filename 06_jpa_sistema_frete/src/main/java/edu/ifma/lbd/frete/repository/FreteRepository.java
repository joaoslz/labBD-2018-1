package edu.ifma.lbd.frete.repository;

import edu.ifma.lbd.frete.model.Cidade;
import edu.ifma.lbd.frete.model.Cliente;
import edu.ifma.lbd.frete.model.Frete;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public final class FreteRepository {

    private final EntityManager manager;

    public FreteRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void salvar(Frete frete) {

        try {
            manager.getTransaction().begin();
            manager.persist(frete);
            manager.getTransaction().commit();

        } catch (IllegalStateException e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

     public List<Frete> consultarFretesDo(Cliente cliente) {

        return manager
                .createQuery("from Frete f where f.cliente = :cliente", Frete.class)
                .setParameter("cliente", cliente )
                .getResultList();
    }

    public BigDecimal freteDeMaiorValor() {
        return manager.createQuery("select max(f.valor) from Frete f", BigDecimal.class)
                      .getSingleResult();
    }

    public Cidade buscaCidadeComMaisFretes() {

        return manager.createQuery("select f.cidade from Frete f group by f.cidade " +
                                           "order by count(f.cidade) desc", Cidade.class )
                .getResultList().get(0);
  }


    /*
     * Recuperar uma lista com todos os fretes de um determinado cliente
   	 * Recuperar o frete de maior valor (custo)
     * Recuperar a cidade que é destinatária da maior quantidade de fretes
    */

}
