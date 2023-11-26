
package ui.telainicial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import excecoes.CampoVazio;
import ui.evento.JanelaEvento;
import ui.evento.JanelaSeca;


public class Home extends JFrame {
    private JButton evento, equipamento, equipe;
    private JanelaEvento janelaEvento;
    private JFrame frame;
    private TratadorEventos tratador;

    public Home() {
        super();
        this.setTitle("ACMERescue");
        //setSize(800, 500);
        tratador = new TratadorEventos();

        // Crie um JPanel para usar o CardLayout
        frame = new JFrame();
        frame.setTitle("Tela inicial");
        frame.setSize(800, 500);
        //frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));

        // Adicione a JanelaEvento ao cardPanel
        janelaEvento = new JanelaEvento(this);
        //janelaEvento.setJanelaAnterior(this);

        evento = new JButton("Cadastrar evento");
        evento.addActionListener(tratador);
        equipamento = new JButton("Cadastrar equipamento");
        equipe = new JButton("Cadastrar equipe");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(evento);
        buttonPanel.add(equipamento);
        buttonPanel.add(equipe);


        // Adicione o buttonPanel ao cardPanel com um nome associado
        frame.add(buttonPanel);
        frame.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);
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
            if(e.getSource() == evento) {
                frame.setVisible(false);
                JanelaEvento paginaSecundaria = new JanelaEvento(Home.this);
                paginaSecundaria.exibir();
            }
            }
        }
    }

