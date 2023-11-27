package ui.telainicial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dados.catalogo.ListaAtendimentos;
import dados.catalogo.ListaEquipamentos;
import dados.catalogo.ListaEquipes;
import dados.catalogo.ListaEventos;
import excecoes.ListaEventosVazia;
import ui.atendimento.JanelaAtendimento;
import ui.equipe.JanelaEquipe;
import ui.equipamento.JanelaEquipamento;
import ui.evento.*;

public class Home extends JFrame {
    private JButton evento, equipamento, equipe, atendimento;
    private JanelaEvento janelaEvento;
    private JanelaEquipe janelaEquipe;
    private JanelaEquipamento janelaEquipamento;
    private JanelaAtendimento janelaAtendimento;
    private JFrame frame;
    private TratadorEventos tratador;
    private ListaEventos dadosEventos;
    private ListaEquipes dadosEquipes;
    private ListaEquipamentos dadosEquipamentos;
    private ListaAtendimentos dadosAtendimentos;

    public Home() {
        super();
        this.setTitle("ACMERescue");
        dadosEventos = new ListaEventos();
        dadosEquipes = new ListaEquipes();
        dadosEquipamentos = new ListaEquipamentos();
        dadosAtendimentos = new ListaAtendimentos();

        tratador = new TratadorEventos();

        frame = new JFrame();
        frame.setTitle("Tela inicial");
        frame.setSize(800, 500);

        evento = new JButton("Cadastrar evento");
        evento.addActionListener(tratador);
        equipamento = new JButton("Cadastrar equipamento");
        equipamento.addActionListener(tratador);
        equipe = new JButton("Cadastrar equipe");
        equipe.addActionListener(tratador);
        atendimento = new JButton("Cadastrar atendimento");
        atendimento.addActionListener(tratador);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(evento);
        buttonPanel.add(equipamento);
        buttonPanel.add(equipe);
        buttonPanel.add(atendimento);

        frame.add(buttonPanel);
        frame.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public ListaEventos getDadosEventos() {
        return dadosEventos;
    }

    public ListaEquipes getDadosEquipes() {
        return dadosEquipes;
    }

    public ListaEquipamentos getDadosEquipamentos() {
        return dadosEquipamentos;
    }

    public ListaAtendimentos getDadosAtendimentos() {
        return dadosAtendimentos;
    }

    public void exibir() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    class TratadorEventos extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == evento) {
                frame.setVisible(false);
                janelaEvento = new JanelaEvento(Home.this);
                janelaEvento.exibir();
            }

            if (e.getSource() == equipe) {
                frame.setVisible(false);
                janelaEquipe = new JanelaEquipe(Home.this);
                janelaEquipe.exibir();
            }

            if(e.getSource() == equipamento){
                frame.setVisible(false);
                janelaEquipamento = new JanelaEquipamento(Home.this);
                janelaEquipamento.exibir();
            }

            if(e.getSource() == atendimento){
                try {
                    if (dadosEventos.estaVazio()) {
                        throw new ListaEventosVazia("Não existem eventos cadastrados!");
                    } else {
                        frame.setVisible(false);
                        janelaAtendimento = new JanelaAtendimento(Home.this);
                        janelaAtendimento.exibir();
                    }
                } catch(ListaEventosVazia i){
                    JOptionPane.showMessageDialog(Home.this, i.getMessage());
                }
            }
            }
        }
}


