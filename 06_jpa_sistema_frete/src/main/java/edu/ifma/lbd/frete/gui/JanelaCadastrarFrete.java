package edu.ifma.lbd.frete.gui;

import edu.ifma.lbd.frete.model.Cidade;
import edu.ifma.lbd.frete.model.Cliente;
import edu.ifma.lbd.frete.model.Frete;
import edu.ifma.lbd.frete.service.FreteFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;


public class JanelaCadastrarFrete extends JFrame {

    private JButton jbntCadastrar;
    private JComboBox jcmbxCidadeDestino;
    private JComboBox jcmbxCliente;
    private JLabel jlabelCidadeDestino;
    private JLabel jlabelCliente;
    private JLabel jlabelDescricao;
    private JLabel jlabelPeso;
    private JLabel jlabelValor;
    private JTextField jtextDescricao;
    private JTextField jtextPeso;
    private JTextField jtextValor;

    private FreteFacade freteService = new FreteFacade();

    private DefaultComboBoxModel defaultComboBoxModel = null;
    private Iterator iterator = null;

    public JanelaCadastrarFrete() {
        initComponents();
    }

    private void initComponents() {

        instanciaComponentes();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Frete");

        defineRotuloDosJLabels();

        populaComboDeClientes();
        populaComboDeCidades();

        jtextValor.setEditable(false);
        jbntCadastrar.setText("Cadastrar");

        jbntCadastrar.addActionListener((evt) -> jbntCadastrarActionPerformed(evt));

        defineLayout();
        this.pack();

   }

    private void defineLayout() {
        this.setLayout(new GridLayout(6, 2));

        this.add(jlabelCliente);
        this.add(jcmbxCliente);
        this.add(jlabelCidadeDestino);
        this.add(jcmbxCidadeDestino);
        this.add(jlabelDescricao);
        this.add(jtextDescricao);
        this.add(jlabelPeso);
        this.add(jtextPeso);
        this.add(jtextValor);
        this.add(jbntCadastrar);
    }

    private void populaComboDeCidades() {
        defaultComboBoxModel = new DefaultComboBoxModel();

        List<Cidade> cidades = freteService.todasCidades();
        cidades.forEach(cidade -> defaultComboBoxModel.addElement(cidade));

        jcmbxCidadeDestino.setModel(defaultComboBoxModel);
    }

    private void populaComboDeClientes() {
        defaultComboBoxModel = new DefaultComboBoxModel();

        List<Cliente> clientes = freteService.todosClientes();
        clientes.forEach(cliente -> defaultComboBoxModel.addElement(cliente));

        jcmbxCliente.setModel(defaultComboBoxModel);
    }

    private void defineRotuloDosJLabels() {
        jlabelCliente.setText("Cliente:");
        jlabelCidadeDestino.setText("Cidade Destino:");
        jlabelDescricao.setText("Descrição:");
        jlabelPeso.setText("Peso:");
        jlabelValor.setText("Valor:");
    }

    private void instanciaComponentes() {
        jlabelCliente = new JLabel();
        jlabelCidadeDestino = new JLabel();
        jlabelDescricao = new JLabel();
        jlabelPeso = new JLabel();
        jlabelValor = new JLabel();
        jcmbxCliente = new JComboBox();
        jcmbxCidadeDestino = new JComboBox();
        jtextDescricao = new JTextField();
        jtextPeso = new JTextField();
        jtextValor = new JTextField();
        jbntCadastrar = new JButton();
    }

    private void jbntCadastrarActionPerformed(ActionEvent evt) {

        Frete frete = new Frete();

        try {
            Cliente cliente = (Cliente) jcmbxCliente.getSelectedItem();
            frete.setCliente(cliente);

            Cidade cidade = (Cidade) jcmbxCidadeDestino.getSelectedItem();
            frete.setCidade(cidade);

            frete.setDescricao(jtextDescricao.getText());
            frete.setPeso(Double.parseDouble(jtextPeso.getText()));
            jtextValor.setText(frete.calcularFrete().toString());

            freteService.salvar(frete);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Dados incorretos", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                JanelaCadastrarFrete jcadastrarFrete = new JanelaCadastrarFrete();

                jcadastrarFrete.setSize(400, 300);

                /* Centraliza a interface gráfica */
                jcadastrarFrete.setLocationRelativeTo(null);

                jcadastrarFrete.setVisible(true);
            }
        });
    }
}
