package ui.atendimento;
import dados.eventos.Evento;
import dados.StatusAtendimento;
import dados.catalogo.ListaEventos;
import excecoes.CampoVazio;
import ui.evento.JanelaEvento;
import ui.evento.JanelaGenerica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarAtendimento extends JFrame {
    private JPanel container;

    private JLabel labelDuracao, labelStatus;
    private JTextField duracaoTextField;
    private JButton atualizarAtendimento, sair, limparDados;
    private ListaEventos listaEventos;

    private JComboBox<String> listaStatus;
    private JanelaAtendimento janelaAnterior;
    private JFrame frame;
    private JTextArea area;
    private JScrollPane scrollPane;
    private Evento evento;
    private TratadorEventos tratador;

    public AtualizarAtendimento(JanelaAtendimento j, Evento e) {
        super();
        janelaAnterior = j;
        evento = e;

        listaEventos = janelaAnterior.getJanelaAnterior().getDadosEventos();
        tratador = new TratadorEventos();

        frame = new JFrame("Atualização de um atendimento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        labelDuracao = new JLabel("Duração (em horas): ");
        duracaoTextField = new JTextField(30);

        labelStatus = new JLabel("Status do atendimento: ");
        String[] opcoes = {"Executando", "Finalizado", "Cancelado"};
        listaStatus = new JComboBox<>(opcoes);

        atualizarAtendimento = new JButton("Atualizar atendimento");
        atualizarAtendimento.addActionListener(tratador);

        sair = new JButton("Sair");
        sair.addActionListener(tratador);

        limparDados = new JButton("Limpar dados");
        limparDados.addActionListener(tratador);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(criarPainel(labelDuracao, duracaoTextField));
        container.add(criarPainel(labelStatus, listaStatus));

        container.add(atualizarAtendimento);
        container.add(limparDados);
        container.add(sair);


        area = new JTextArea(20, 50);
        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane);

        frame.add(container);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.pack();
    }

    public JPanel criarPainel(JLabel label, JComboBox e) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.add(label);
        painel.add(e);
        return painel;
    }

    public JPanel criarPainel(JLabel label, JTextField campoTexto) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.add(label);
        painel.add(campoTexto);
        return painel;
    }

    public void defineStatus(String s, Evento e) {
        switch (s) {
            case "Executando":
                e.getAtendimento().setStatus((StatusAtendimento.EXECUTANDO));
                break;
            case "Finalizado":
                e.getAtendimento().setStatus((StatusAtendimento.FINALIZADO));
                break;
            case "Cancelado":
                e.getAtendimento().setStatus((StatusAtendimento.CANCELADO));
                break;
        }
    }

    public void defineTextoInicial(String s){
        area.setText(s);
    }

    public void mensagemSucesso(String s) {
        switch (s) {
            case "Executando":
                area.setText("Atendimento em execução! ");
                break;
            case "Finalizado":
                area.setText("Atendimento finalizado! ");
                break;
            case "Cancelado":
                area.setText("Atendimento cancelado! ");
                break;
        }
    }

    public void exibir() {
        frame.setVisible(true);
    }

    private class TratadorEventos extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == atualizarAtendimento) {
                try{
                    if(duracaoTextField.getText().isEmpty()){
                        throw new CampoVazio("O campo de duração deve ser preenchido! ");
                    } else {
                        if(listaEventos.formatoCodigo(duracaoTextField.getText())) {
                            String status = (String) listaStatus.getSelectedItem();
                            defineStatus(status, evento);
                            mensagemSucesso(status);
                        } else {
                            throw new NumberFormatException("O campo de duração deve ser preenchido com um valor numérico inteiro!");
                        }
                    }
                } catch (CampoVazio v){
                    area.setText(v.getMessage());
                    JOptionPane.showMessageDialog(AtualizarAtendimento.this, v.getMessage());
                } catch (NumberFormatException n){
                    area.setText(n.getMessage());
                    JOptionPane.showMessageDialog(AtualizarAtendimento.this, n.getMessage());
                }
            }

            if(e.getSource() == sair){
                frame.setVisible(false);
                janelaAnterior.exibir();
            }

            if(e.getSource() == limparDados){
                duracaoTextField.setText("");
                area.setText("");
            }
        }
    }
}


