package ui.atendimento;

import dados.eventos.Evento;
import dados.Atendimento;
import excecoes.*;
import excecoes.EventoComAtendimento;
import ui.evento.*;
import ui.telainicial.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JanelaAtendimento extends JanelaGenerica {
    private Home janelaAnterior;
    private TratadorEventos tratador;
    private ArrayList<Evento> listaEventos;
    private JList<Evento> listaEventosCampo;
    private JLabel labelCampoEventos, labelPesqCodigo;
    private JTextField pesqCodigo;
    private JButton selecionarEvento, atualizarAtendimento, sair, pesquisar;
    private DefaultListModel modelo;

    private JPanel container;
    private JFrame frame;

    public JanelaAtendimento(Home h){
        super();
        frame = new JFrame("Cadastro de Atendimento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        tratador = new TratadorEventos();

        janelaAnterior = h;


        labelCampoEventos = new JLabel("Eventos cadastrados: ");

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        listaEventos = janelaAnterior.getDadosEventos().getListaEventos();

        modelo = new DefaultListModel<>();

        for (Evento ev : listaEventos) {
            modelo.addElement(ev);
        }


        listaEventosCampo = new JList<>(modelo);
        listaEventosCampo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaEventosCampo.setCellRenderer(new EventoListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(listaEventosCampo);

        labelPesqCodigo = new JLabel("Pesquisar atendimento por código");
        pesqCodigo = new JTextField(30);

        pesquisar = new JButton("Pesquisar");
        pesquisar.addActionListener(tratador);

        selecionarEvento = new JButton("Selecionar evento");
        selecionarEvento.addActionListener(tratador);

        atualizarAtendimento = new JButton("Atualizar atendimento");
        atualizarAtendimento.addActionListener(tratador);

        sair = new JButton("Sair");
        sair.addActionListener(tratador);

        container.add(scrollPane);
        container.add(criarPainel(labelPesqCodigo, pesqCodigo));
        container.add(selecionarEvento);
        container.add(pesquisar);
        container.add(atualizarAtendimento);
        container.add(sair);

        frame.add(container);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.pack();
    }

    public Home getJanelaAnterior() {
        return janelaAnterior;
    }

    public void exibir() {
        try {
            if (janelaAnterior.getDadosEventos().estaVazio()) {
                throw new ListaEventosVazia("Não existem eventos cadastrados!");
            } else {
                frame.setVisible(true);
            }
        } catch(ListaEventosVazia e){
            JOptionPane.showMessageDialog(JanelaAtendimento.this, e.getMessage());
        }
    }

    private class EventoListCellRenderer extends JLabel implements ListCellRenderer<Evento> {
        public EventoListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Evento> list, Evento value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(value.eventoToString());
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return this;
        }
    }

    private class TratadorEventos extends Component implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == selecionarEvento) {
                    try{
                        if (listaEventosCampo.getSelectedValue().getAtendimento() == null) {
                            CadastroAtendimento c = new CadastroAtendimento(listaEventosCampo.getSelectedValue(), JanelaAtendimento.this);
                            frame.setVisible(false);
                            c.exibir();
                        } else {
                            throw new EventoComAtendimento("Não é possível cadatrar um atendimento para esse evento pois já existe um atendimento cadastrado");
                        }
                    } catch(EventoComAtendimento t){
                        JOptionPane.showMessageDialog(JanelaAtendimento.this, t.getMessage());
                    }
                }

            if(e.getSource()==sair){
                frame.setVisible(false);
                janelaAnterior.exibir();
            }

            if(e.getSource()==atualizarAtendimento){
                try {
                    Evento a = listaEventosCampo.getSelectedValue();
                    if (a.getAtendimento() == null) {
                        throw new EventoSemAtendimento("Não é possível atualizar o atendimento pois não há atendimento cadastrado para esse evento.");
                    } else {
                        AtualizarAtendimento b = new AtualizarAtendimento(JanelaAtendimento.this, a);
                        frame.setVisible(false);
                        b.exibir();
                    }
                } catch (EventoSemAtendimento t){
                    JOptionPane.showMessageDialog(JanelaAtendimento.this, t.getMessage());
                }
            }

            if(e.getSource()==pesquisar){
                try{
                    if(pesqCodigo.getText().isEmpty()){
                        new CampoVazio("O campo de pesquisar por código deve estar preenchido com uma sequência de valores numéricos");
                    } else {
                        int cd = Integer.parseInt(pesqCodigo.getText());
                        Atendimento at = janelaAnterior.getDadosAtendimentos().encontraAtendCodigo(cd);
                        if(at == null){
                            throw new AtendimentoInexistente("Não existe atendimento com esse código! ");
                        } else {
                            AtualizarAtendimento b = new AtualizarAtendimento(JanelaAtendimento.this, at.getEvento());
                            frame.setVisible(false);
                            b.exibir();
                            b.defineTextoInicial(at.atendimentoToString());
                        }
                    }
                } catch(CampoVazio m){
                    JOptionPane.showMessageDialog(JanelaAtendimento.this, m.getMessage());
                } catch (NumberFormatException y){
                    JOptionPane.showMessageDialog(JanelaAtendimento.this, y.getMessage());
                } catch (AtendimentoInexistente i){
                    JOptionPane.showMessageDialog(JanelaAtendimento.this, i.getMessage());
                }
            }
            }
    }
    }


