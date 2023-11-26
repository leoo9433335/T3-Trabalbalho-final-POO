package ui.evento;
import dados.eventos.Evento;
import dados.eventos.Terremoto;
import excecoes.CampoVazio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaTerremoto extends JanelaGenerica {
    private JTextField magnitude;
    private JLabel labelMagnitude;
    private JButton finalizarCad;
    private Evento evento;
    private TratadorEventos tratador;

    public JanelaTerremoto(Evento e){
        super();
        this.evento = e;
        this.setTitle("Terremoto");
        labelMagnitude = new JLabel("Magnitude: ");
        magnitude = new JTextField(30);
        finalizarCad = new JButton("Finalizar cadastro");
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(criarPainel(labelMagnitude, magnitude));
        container.add(finalizarCad);

        tratador = new JanelaTerremoto.TratadorEventos();
        finalizarCad.addActionListener(tratador);

        this.add(container);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public void completaInst(Evento e){
        Terremoto t = (Terremoto) e;
        t.setMagnitude(Double.parseDouble(magnitude.getText().trim()));
    }

    class TratadorEventos extends Component implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == finalizarCad){
                try {
                    if(magnitude.getText().isEmpty()) {
                        throw new CampoVazio("Preencha o campo de magnitude!");
                    }
                    completaInst(evento);
                    JOptionPane.showMessageDialog(JanelaTerremoto.this, "Cadastro finalizado! Feche essa janela e retorne para a janela inicial! ");
                    JanelaTerremoto.this.dispose();
                } catch (CampoVazio t){
                    JOptionPane.showMessageDialog(JanelaTerremoto.this, t.getMessage());
                } catch(NumberFormatException n){
                    JOptionPane.showMessageDialog(JanelaTerremoto.this, "Um valor numérico deve ser inserido! ");
                }
            }
        }
    }
}
