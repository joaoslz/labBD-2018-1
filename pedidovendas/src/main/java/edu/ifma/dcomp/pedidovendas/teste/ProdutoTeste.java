package edu.ifma.dcomp.pedidovendas.teste;

import edu.ifma.dcomp.pedidovendas.util.EMFactory;

import javax.persistence.EntityManager;

public class ProdutoTeste {

    public static void main(String[] args) {
        EMFactory emFactory = new EMFactory();

        EntityManager manager = emFactory.getEntityManager();

        manager.close();
        emFactory.close();



    }
}
