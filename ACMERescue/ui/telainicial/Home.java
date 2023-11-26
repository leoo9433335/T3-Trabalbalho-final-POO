
package ui.telainicial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import excecoes.CampoVazio;
import ui.equipe.JanelaEquipe;
import ui.equipamento.JanelaEquipamento;
import ui.evento.*;


public class Home extends JFrame {
    private JButton evento, equipamento, equipe;
    private JanelaEvento janelaEvento;
    private JanelaEquipe janelaEquipe;
    private JanelaEquipamento janelaEquipamento;
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
//        janelaEvento = new JanelaEvento(this);
//        janelaEquipe = new JanelaEquipe(this);
        //janelaEvento.setJanelaAnterior(this);

        evento = new JButton("Cadastrar evento");
        evento.addActionListener(tratador);
        equipamento = new JButton("Cadastrar equipamento");
        equipamento.addActionListener(tratador);
        equipe = new JButton("Cadastrar equipe");
        equipe.addActionListener(tratador);

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


//            if (e.getSource() == equipe){
//                frame.setVisible(false);
//                JanelaEquipe paginaCadEquipe = new JanelaEquipe(Home.this);
//                paginaCadEquipe.exibir();
//            }
        }
        }
    }

