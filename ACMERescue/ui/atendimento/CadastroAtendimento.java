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
    private JTextField codigo, dataInicio;
    private JButton criarAtendimento, limpar, atendimentosCad, sairCad;
    private JLabel labelCodigo, labelDataInicio;

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

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        labelCodigo = new JLabel("Código: ");
        codigo = new JTextField(30);

        labelDataInicio = new JLabel("Data de início: ");
        dataInicio = new JTextField(30);

        //labelDuracao = new JLabel("Duração: ");
        //duracao = new JTextField(30);


        criarAtendimento = new JButton("Criar atendimento");
        container.add(criarAtendimento);
        criarAtendimento.addActionListener(tratador);

        limpar = new JButton("Limpar campos");
        container.add(limpar);
        limpar.addActionListener(tratador);

        atendimentosCad = new JButton("Apresentar todos atendimentos");
        container.add(atendimentosCad);
        atendimentosCad.addActionListener(tratador);

        sairCad = new JButton("Sair");
        container.add(sairCad);
        sairCad.addActionListener(tratador);

        container.add(codigo);
        container.add(dataInicio);
        container.add(criarPainel(labelCodigo, codigo));
        container.add(criarPainel(labelDataInicio, dataInicio));

        area = new JTextArea(20, 50);
        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane);

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
                    String dataInicioStr = dataInicio.getText().trim();

                    if (codigoStr.isEmpty() || dataInicioStr.isEmpty()) {
                        throw new CampoVazio("Todos os campos para inserir as informações devem ser preenchidos! ");
                    } else {
                        if (!listaAtendimentos.formatoCodigo(codigoStr)) {
                            throw new CodigoInvalido("Apenas valores numéricos devem ser inseridos no campo de código! ");
                        } else {
                            int cod = Integer.parseInt(codigoStr);
                            if(listaAtendimentos.verificaCodigo(cod)){
                                throw new CodigoInvalido("Nao é possível cadastrar esse atendimento pois esse código já foi inserido. ");
                            } else{
                                Atendimento a = new Atendimento(cod, dataInicioStr);
                                evento.setAtendimento(a);
                                listaAtendimentos.addAtendimento(a);
                                int t = listaAtendimentos.getQuantAtendimentos();
                                area.setText("Evento cadastrado! " + t);
                            }
                        }
                    }
                } catch (CampoVazio | CodigoInvalido z) {
                    area.setText(z.getMessage());
                    JOptionPane.showMessageDialog(CadastroAtendimento.this, z.getMessage());
                }
            }

            if (e.getSource() == limpar) {
                codigo.setText("");
                dataInicio.setText("");
                area.setText("");
            }

            if(e.getSource() == sairCad){
                frame.setVisible(false);
                janelaAnterior.exibir();
            }

            if(e.getSource() == atendimentosCad){
                area.setText("");
                for (Atendimento ev : listaAtendimentos.getListaAtendimentos()) {
                    area.append(ev.atendimentoToString());
                    area.append("\n\n");
                }
            }
        }
    }
}
