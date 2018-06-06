package edu.ifma.dcomp.lbd.teste;

import edu.ifma.dcomp.lbd.model.Categoria;
import edu.ifma.dcomp.lbd.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.System.*;

public class ConsultaProdutoTest {


    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("estoque");

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

//        consulta01(manager);
//        consulta02(manager);
//         consulta03(manager);
        consulta04(manager);

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }

    private static void consulta01(EntityManager manager) {

        String jpql = "select p from Produto p" ;

        Query query = manager.createQuery(jpql, Produto.class );
        List<Produto> produtos = query.getResultList();

        //produtos.forEach(p -> System.out.println(p )  );

        produtos.forEach(System.out::println  );

    }

    private static void consulta02(EntityManager manager) {
        Categoria categoria = manager.find(Categoria.class, 3);

        String jpql = "select  p from Produto p where p.categoria = :pCategoria" ;

        

        List<Produto> produtos =
                manager.createQuery(jpql, Produto.class)
                .setParameter("pCategoria", categoria)
                .getResultList();


        produtos.forEach(p -> System.out.println(p) );
    }

    private static void consulta03(EntityManager manager) {

        String jpql = "select avg(p.preco) from Produto p";

        Query query = manager.createQuery(jpql, Double.class);

        Double media = (Double) query.getSingleResult();

        System.out.println("A média é: " + media );


/*
        String jpql = "select sum(p.preco) from Produto p";

        Query query = manager.createQuery(jpql, BigDecimal.class);

        BigDecimal soma = (BigDecimal) query.getSingleResult();

        System.out.println("A soma é: " + soma);
*/
    }

    private static void consulta04(EntityManager manager) {

        String jpql = "select distinct sum(p.preco) " +
                      "from Produto p " +
                      "group by p.categoria " +
                      "order by p.preco desc";

        Query query = manager.createQuery(jpql);

        List resultados = query.getResultList();
        resultados.forEach(r-> System.out.println(r) );


    }

}
