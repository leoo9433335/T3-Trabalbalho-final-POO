package ui.atendimento;

import dados.eventos.Evento;
import excecoes.ListaEventosVazia;
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
    private JLabel labelCampoEventos;
    private JButton selecionarEvento;
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

        selecionarEvento = new JButton("Selecionar evento");
        selecionarEvento.addActionListener(tratador);


        container.add(scrollPane);
        container.add(selecionarEvento);


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
                if(listaEventosCampo.getSelectedValue().getAtendimento() == null){
                    CadastroAtendimento c = new CadastroAtendimento(listaEventosCampo.getSelectedValue(), JanelaAtendimento.this);
                    frame.setVisible(false);
                    c.exibir();
                }
                }
            }
    }
    }


