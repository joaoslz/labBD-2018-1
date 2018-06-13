package edu.ifma.lbd.frete.service;

import edu.ifma.lbd.frete.model.Cidade;
import edu.ifma.lbd.frete.model.Cliente;
import edu.ifma.lbd.frete.model.Frete;
import edu.ifma.lbd.frete.repository.CidadeRepository;
import edu.ifma.lbd.frete.repository.ClienteRepository;
import edu.ifma.lbd.frete.repository.FreteRepository;
import edu.ifma.lbd.frete.repository.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class FreteFacade {

    private final ClienteRepository clienteRepository;
    private final CidadeRepository cidadeRepository;
    private final FreteRepository freteRepository;

    public FreteFacade() {

        EntityManager manager = new JPAUtil().getEntityManager();

        this.clienteRepository = new ClienteRepository(manager);
        this.cidadeRepository = new CidadeRepository(manager );
        this.freteRepository = new FreteRepository(manager );
    }

    public List<Cliente> todosClientes() {
        return clienteRepository.todosClientes();
    }

    public List<Cidade> todasCidades() {
        return cidadeRepository.todasCidades();
    }

    public void salvar(Frete frete) {
        this.freteRepository.salvar(frete );
    }
}
