package ui.evento;
import dados.eventos.Ciclone;
import dados.eventos.Evento;
import excecoes.CampoVazio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaCiclone extends JanelaGenerica {
    private JTextField velocidade, precipitacao;
    private JLabel labelVelocidade, labelPrecipitacao;
    private Evento evento;
    private JButton finalizarCad;
    private TratadorEventos tratador;

    public JanelaCiclone(Evento e){
        super();
        this.evento = e;
        this.setTitle("Ciclone");
        labelVelocidade = new JLabel("Velocidade: ");
        velocidade = new JTextField(30);
        labelPrecipitacao = new JLabel("Precipitação: ");
        precipitacao = new JTextField(30);
        finalizarCad = new JButton("Finalizar cadastro");
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(criarPainel(labelVelocidade, velocidade));
        container.add(criarPainel(labelPrecipitacao, precipitacao));
        container.add(finalizarCad);

        tratador = new JanelaCiclone.TratadorEventos();
        finalizarCad.addActionListener(tratador);

        this.add(container);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public void completaInst(Evento e){
        Ciclone c = (Ciclone) e;
        c.setVelocidade(Double.parseDouble(velocidade.getText().trim()));
        c.setPrecipitacao(Double.parseDouble((precipitacao.getText())));
    }

    class TratadorEventos extends Component implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finalizarCad) {
                try {
                    if (velocidade.getText().isEmpty() || precipitacao.getText().isEmpty()) {
                        throw new CampoVazio("Preencha todos os campos!");
                    }
                    completaInst(evento);
                    JOptionPane.showMessageDialog(JanelaCiclone.this, "Cadastro finalizado! Feche essa janela e retorne para a janela inicial! ");
                    JanelaCiclone.this.dispose();
                } catch (CampoVazio t) {
                    JOptionPane.showMessageDialog(JanelaCiclone.this, t.getMessage());
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(JanelaCiclone.this, "Ambos os campos devem ser preenchidos com valores numéricos! ");
                }
            }
        }
    }
}
