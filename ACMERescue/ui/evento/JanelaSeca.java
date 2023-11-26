package ui.evento;

import dados.*;
import excecoes.CampoVazio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaSeca extends JanelaGenerica {
    private JButton criarEvento;
    private JTextField estiagem;
    private JLabel labelEstiagem;
    private JButton finalizarCad;
    private TratadorEventos tratador;
    private Evento evento;
    public JanelaSeca(Evento e){
        super();
        this.evento = e;
        this.setTitle("Seca");
        labelEstiagem = new JLabel("Estiagem: ");
        estiagem = new JTextField(30);
        finalizarCad = new JButton("Finalizar cadastro");
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(criarPainel(labelEstiagem, estiagem));
        container.add(finalizarCad);

        tratador = new JanelaSeca.TratadorEventos();
        finalizarCad.addActionListener(tratador);

        this.add(container);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public void completaInst(Evento e){
        Seca s = (Seca) e;
        s.setEstiagem(Integer.parseInt(estiagem.getText().trim()));
    }

    class TratadorEventos extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == finalizarCad){
                try {
                    if(estiagem.getText().isEmpty()) {
                        throw new CampoVazio("Preencha o campo de estiagem!");
                    }
                    completaInst(evento);
                    JOptionPane.showMessageDialog(JanelaSeca.this, "Cadastro finalizado! Feche essa janela e retorne para a janela inicial! ");
                    JanelaSeca.this.dispose();
                } catch (CampoVazio t){
                    JOptionPane.showMessageDialog(JanelaSeca.this, t.getMessage());
                } catch(NumberFormatException n){
                    JOptionPane.showMessageDialog(JanelaSeca.this, "Um valor numérico inteiro deve ser inserido! ");
                }
            }
        }
    }
}
