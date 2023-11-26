package ui.equipamento;
import excecoes.CampoVazio;
import ui.evento.JanelaGenerica;
import dados.equipamentos.Escavadeira;
import dados.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaEscavadeira extends JanelaGenerica {
    private Escavadeira escavadeira;
    private JTextField carga;
    private JLabel labelCarga, labelCombustivel;
    private JComboBox<String> tipoCombustivel;

    private JButton finalizarCad;
    private TratadorEventos tratador;

    public JanelaEscavadeira(Escavadeira e){
        super();
        escavadeira = e;
        labelCarga = new JLabel("Carga (em metros cúbicos): ");
        carga = new JTextField(30);

        labelCombustivel = new JLabel("Tipo de combustível: ");
        String[] opcoes = {"Diesel", "Gasolina", "Álcool"};
        tipoCombustivel = new JComboBox<>(opcoes);

        this.setTitle("Escavadeira");
        finalizarCad = new JButton("Finalizar cadastro");

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(criarPainel(labelCarga, carga));
        container.add(criarPainel(labelCombustivel, tipoCombustivel));

        tratador = new TratadorEventos();

        finalizarCad.addActionListener(tratador);
        container.add(finalizarCad);

        this.add(container);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public JPanel criarPainel(JLabel label, JComboBox e) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.add(label);
        painel.add(e);
        return painel;
    }

    public void defineCombustivel(String tipo) {
        switch (tipo) {
            case "Diesel":
                escavadeira.setCombustivel(Combustivel.DIESEL);
            case "Gasolina":
                escavadeira.setCombustivel(Combustivel.DIESEL);
            case "Álcool":
                escavadeira.setCombustivel(Combustivel.ALCOOL);
        }
    }

    public void completaInst(){
        escavadeira.setCarga(Double.parseDouble(carga.getText().trim()));
        defineCombustivel((String) tipoCombustivel.getSelectedItem());
    }

    class TratadorEventos extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == finalizarCad){
                try {
                    if(carga.getText().isEmpty()) {
                        throw new CampoVazio("Preencha o campo de carga!");
                    }
                    completaInst();
                    JOptionPane.showMessageDialog(JanelaEscavadeira.this, "Cadastro finalizado! Feche essa janela e retorne para a janela inicial! ");
                    dispose();
                } catch (CampoVazio t){
                    JOptionPane.showMessageDialog(JanelaEscavadeira.this, t.getMessage());
                } catch(NumberFormatException n){
                    JOptionPane.showMessageDialog(JanelaEscavadeira.this, "Um valor numérico deve ser inserido no campo de carga! ");
                }
            }
        }
    }
}
