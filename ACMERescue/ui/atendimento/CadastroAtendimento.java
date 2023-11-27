package ui.atendimento;

import dados.Atendimento;
import dados.StatusAtendimento;
import dados.catalogo.ListaAtendimentos;
import dados.catalogo.ListaEventos;
import excecoes.CampoVazio;
import excecoes.CodigoInvalido;
import ui.evento.*;
import ui.telainicial.Home;
import dados.eventos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroAtendimento extends JanelaGenerica {
    private JPanel container;
    private Evento evento;
    private JTextField codigo, dataInicio, duracao;
    private JButton criarAtendimento, limparCampos, atendimentosCad, sairCad;
    private JLabel labelCodigo, labelDataInicio, labelDuracao;

    private String status;
    private JTextArea area;
    private TratadorEventos tratador;

    private JanelaAtendimento janelaAnterior;
    private ListaAtendimentos listaAtendimentos;
    private JScrollPane scrollPane;
    private JFrame frame;

    public CadastroAtendimento(Evento e, JanelaAtendimento j) {
        super();
        janelaAnterior = j;
        evento = e;
        tratador = new TratadorEventos();
        listaAtendimentos = janelaAnterior.getJanelaAnterior().getDadosAtendimentos();

        frame = new JFrame("Cadastro de Atendimento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        labelCodigo = new JLabel("Código: ");
        codigo = new JTextField(30);

        labelDataInicio = new JLabel("Data de início: ");
        dataInicio = new JTextField(30);

        //labelDuracao = new JLabel("Duração: ");
        //duracao = new JTextField(30);


        criarAtendimento = new JButton("Criar atendimento");
        limparCampos = new JButton("Limpar campos");
        atendimentosCad = new JButton("Apresentar todos atendimentos");
        sairCad = new JButton("Sair");


        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(codigo);
        container.add(dataInicio);
        //container.add(duracao);
        container.add(criarAtendimento);
        container.add(limparCampos);
        container.add(atendimentosCad);
        container.add(sairCad);
        container.add(criarPainel(labelCodigo, codigo));
        container.add(criarPainel(labelDataInicio, dataInicio));
        //container.add(criarPainel(labelDuracao, duracao));

        area = new JTextArea(20, 50);
        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane);

        criarAtendimento.addActionListener(tratador);
//        tratador = new JanelaEvento.TratadorEventos();
//        criarEvento.addActionListener(tratador);
//        limparCampos.addActionListener(tratador);
//        eventosCad.addActionListener(tratador);
//        sairCad.addActionListener(tratador);

        frame.add(container);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.pack();
    }

    public void exibir() {
        frame.setVisible(true);
    }

    private class TratadorEventos extends Component implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == criarAtendimento) {
                try {
                    String codigoStr = codigo.getText().trim();
                    int codigoInt = Integer.parseInt(codigoStr);
                    String dataStr = dataInicio.getText().trim();

                    if (codigoStr.isEmpty() || dataStr.isEmpty()) {
                        throw new CampoVazio("Todos os campos para inserir as informações devem ser preenchidos! ");
                    }
                        if (listaAtendimentos.verificaCodigo(codigoInt)) {
                            throw new CodigoInvalido("Não é possível realizar o cadastro desse atendimento pois esse código já foi inserido! ");
                        } else {
                            Atendimento a = new Atendimento(codigoInt, dataStr);
                            evento.setAtendimento(a);
                            listaAtendimentos.addAtendimento(a);
                            int t = listaAtendimentos.getQuantAtendimentos();
                            area.setText("Evento cadastrado! " + t);
                        }
                } catch (CampoVazio z) {
                    area.setText(z.getMessage());
                    JOptionPane.showMessageDialog(CadastroAtendimento.this, z.getMessage());
                } catch (CodigoInvalido c) {
                    area.setText(c.getMessage());
                    JOptionPane.showMessageDialog(CadastroAtendimento.this, c.getMessage());
                }

//            if(e.getSource() == atendimentosCad){
//            }
            }
        }
    }
}
