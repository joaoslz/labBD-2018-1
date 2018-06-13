package teste;

import edu.ifma.lbd.frete.model.Cliente;
import edu.ifma.lbd.frete.model.Frete;
import edu.ifma.lbd.frete.repository.FreteRepository;
import edu.ifma.lbd.frete.repository.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TestaFreteRepository {

    public static void main(String[] args) {

        EntityManager manager = new JPAUtil().getEntityManager();

        FreteRepository freteRepository = new FreteRepository(manager);


        final List<Frete> fretes = freteRepository.consultarFretesDo(manager.find(Cliente.class, 1));

        fretes.forEach(System.out::println );

        System.out.println("-------------------");
        System.out.println(freteRepository.freteDeMaiorValor() );


        System.out.println("-------------------");
        System.out.println(freteRepository.buscaCidadeComMaisFretes().getNome() );
    }
}
