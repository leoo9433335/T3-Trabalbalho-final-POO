package ui.evento;

import javax.swing.*;
import java.awt.*;

public class JanelaGenerica extends JFrame{
    private JFrame frame;

    public JanelaGenerica(){
        super();
    }

    public JPanel criarPainel(JLabel label, JTextField campoTexto) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.add(label);
        painel.add(campoTexto);
        return painel;
    }

    public void exibir() {
        frame.setVisible(true);
    }

}
