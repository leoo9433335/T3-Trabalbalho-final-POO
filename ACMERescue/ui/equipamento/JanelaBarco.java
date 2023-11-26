package ui.equipamento;

import dados.eventos.Evento;
import dados.eventos.Terremoto;
import excecoes.CampoVazio;
import ui.evento.JanelaEvento;
import ui.evento.*;
import dados.equipamentos.*;
import dados.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaBarco extends JanelaGenerica {
    private JTextField capacidadePessoas;
    private JLabel labelCapacPessoas;
    private JButton finalizarCad;
    private TratadorEventos tratador;
    private Barco barco;
    public JanelaBarco(Barco b){
        super();
        barco = b;
        this.setTitle("Barco");

        labelCapacPessoas = new JLabel("Capacidade de pessoas: ");
        capacidadePessoas = new JTextField(30);
        finalizarCad = new JButton("Finalizar cadastro");

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(criarPainel(labelCapacPessoas, capacidadePessoas));

        tratador = new TratadorEventos();

        finalizarCad.addActionListener(tratador);
        container.add(finalizarCad);

        this.add(container);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        }

        public void completaInst(Barco b){
            b.setCapacidadePessoas(Integer.parseInt(capacidadePessoas.getText().trim()));
        }

        class TratadorEventos extends Component implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == finalizarCad){
                    try {
                        if(capacidadePessoas.getText().isEmpty()) {
                            throw new CampoVazio("Preencha o campo de capacidade de pessoas!");
                        }
                        completaInst(barco);
                        JOptionPane.showMessageDialog(JanelaBarco.this, "Cadastro finalizado! Feche essa janela e retorne para a janela inicial! ");
                        dispose();
                    } catch (CampoVazio t){
                        JOptionPane.showMessageDialog(JanelaBarco.this, t.getMessage());
                    } catch(NumberFormatException n){
                        JOptionPane.showMessageDialog(JanelaBarco.this, "Um valor numérico inteiro deve ser inserido! ");
                    }
                }
            }
        }
    }


