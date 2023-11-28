package ui.equipamento;
import dados.equipamentos.Barco;
import dados.equipamentos.CaminhaoTanque;
import dados.equipamentos.Equipamento;
import excecoes.CampoVazio;
import ui.evento.JanelaGenerica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaCamTanque extends JanelaGenerica {
    private CaminhaoTanque caminhaoTanque;
    private JTextField capacidade;
    private JLabel labelCapac;
    private JButton finalizarCad;
    private TratadorEventos tratador;

    public JanelaCamTanque(CaminhaoTanque e){
        super();
        caminhaoTanque = e;
        this.setTitle("Caminhão tanque");
        labelCapac = new JLabel("Capacidade (milhares de litros): ");
        capacidade = new JTextField(30);
        finalizarCad = new JButton("Finalizar cadastro");

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(criarPainel(labelCapac, capacidade));

        tratador = new TratadorEventos();

        finalizarCad.addActionListener(tratador);
        container.add(finalizarCad);
        this.add(container);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }


    public void completaInst(CaminhaoTanque c){
        c.setCapacidade(Double.parseDouble(capacidade.getText().trim()));
    }

    private class TratadorEventos extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == finalizarCad){
                try {
                    if(capacidade.getText().isEmpty()) {
                        throw new CampoVazio("Preencha o campo de capacidade!");
                    }
                    completaInst(caminhaoTanque);
                    JOptionPane.showMessageDialog(JanelaCamTanque.this, "Cadastro finalizado! Feche essa janela e retorne para a janela inicial! ");
                    dispose();
                } catch (CampoVazio t){
                    JOptionPane.showMessageDialog(JanelaCamTanque.this, t.getMessage());
                } catch(NumberFormatException n){
                    JOptionPane.showMessageDialog(JanelaCamTanque.this, "Um valor numérico deve ser inserido! ");
                }
            }
        }
    }
}

