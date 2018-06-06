package edu.ifma.dcomp.lbd.teste;

import edu.ifma.dcomp.lbd.model.Categoria;
import edu.ifma.dcomp.lbd.model.Produto;
import edu.ifma.dcomp.lbd.model.TipoProduto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class ProdutoTest {

/*    public static void main(String[] args) {


       EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("estoque");

        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        Produto produto = manager.find(Produto.class, 3);
        manager.remove(produto );

        manager.getTransaction().commit();


        manager.close();
        factory.close();
    }*/

    public static void main(String[] args) {


        Produto produto = new Produto
                ("Computador All-in-one", new BigDecimal(3000) );

        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("estoque");

        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        manager.persist(produto );

//        Produto produtoMerge = manager.merge(produto);
//        produtoMerge.setNome("Tablet Samsung");



        manager.getTransaction().commit();


        manager.close();
        factory.close();
    }


/*    public static void main(String[] args) {


       EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("estoque");

        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        Produto produto = manager.find(Produto.class, 1);

        produto.setNome("Notebook Dell Core I5");


        manager.getTransaction().commit();


        manager.close();
        factory.close();
    }*/




 /*   public static void main(String[] args) {


        Produto produto = new Produto("Tablet", new BigDecimal(1000) );


        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("estoque");

        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        manager.persist(produto );
        produto.setNome("Tablet Motorola");

        manager.getTransaction().commit();


        manager.close();
        factory.close();
    }*/

}
