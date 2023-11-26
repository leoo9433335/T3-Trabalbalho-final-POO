package ui.equipe;
import dados.Equipe;
import dados.catalogo.ListaEquipes;
import ui.evento.JanelaGenerica;
import ui.telainicial.Home;

import java.util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JanelaEquipe extends JanelaGenerica {
    private JPanel container;

    private JLabel labelCodinome, labelQuantidade, labelLongitude, labelLatitude;
    private JTextField codinomeTextField, quantidadeTextField, longitudeTextField, latitudeTextField;
    private JButton cadastrarEquipe, fecharPrograma, equipesCadastradas, limparDados;
    //private List<Equipe> equipes;
    private ListaEquipes equipes;
    private Home janelaAnterior;
    private JFrame frame;
    private JTextArea area;
    private JScrollPane scrollPane;
    private TratadorEventos tratador;

    public JanelaEquipe(Home p) {
        super();
        janelaAnterior = p;

        equipes = new ListaEquipes();

        frame = new JFrame("Cadastro de Equipes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        labelCodinome = new JLabel("Codinome: ");
        codinomeTextField = new JTextField(30);

        labelQuantidade = new JLabel("Quantidade de membros: ");
        quantidadeTextField = new JTextField(30);

        labelLatitude = new JLabel("Latitude: ");
        latitudeTextField = new JTextField(30);

        labelLongitude = new JLabel("Longitude: ");
        longitudeTextField = new JTextField(30);

        cadastrarEquipe = new JButton("Cadastrar equipe");
        fecharPrograma = new JButton("Sair");
        equipesCadastradas = new JButton("Equipes cadastradas");
        limparDados = new JButton("Limpar dados");

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(codinomeTextField);
        container.add(quantidadeTextField);
        container.add(longitudeTextField);
        container.add(latitudeTextField);
        container.add(cadastrarEquipe);
        container.add(limparDados);
        container.add(equipesCadastradas);
        container.add(fecharPrograma);
        container.add(criarPainel(labelCodinome, codinomeTextField));
        container.add(criarPainel(labelQuantidade, quantidadeTextField));
        container.add(criarPainel(labelLatitude, latitudeTextField));
        container.add(criarPainel(labelLongitude, longitudeTextField));

        area = new JTextArea(20, 50);
        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane);

        tratador = new TratadorEventos();
        fecharPrograma.addActionListener(tratador);
        cadastrarEquipe.addActionListener(tratador);
        equipesCadastradas.addActionListener(tratador);
        limparDados.addActionListener(tratador);


        frame.add(container);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.pack();
    }

    class TratadorEventos extends Component implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==fecharPrograma){
                frame.setVisible(false);
                janelaAnterior.exibir();
            }

            if(e.getSource()==cadastrarEquipe){
                criaObjeto();
            }

            if(e.getSource()==equipesCadastradas){
                equipes.ordenarEquipesPorCodinome();
                area.setText("");
                for(Equipe eq: equipes.getEquipesCadastradas()){
                    area.append(eq.equipeToString());
                    area.append("\n\n");
                }
            }

            if(e.getSource()==limparDados){
                limparCampos();
            }
        }
    }





//
//        this.limparOsDadosButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JanelaEquipe.this.limparCampos();
//            }
//        });

    public void criaObjeto() {
        String codinome = codinomeTextField.getText();
        Iterator var2 = equipes.getEquipesCadastradas().iterator();

        Equipe equipe;
        do {
            if (!var2.hasNext()) {
                int quantidade;
                try {
                    quantidade = Integer.parseInt(quantidadeTextField.getText());
                } catch (NumberFormatException var10) {
                    area.setText("Erro: Quantidade inválida. Insira um valor numérico inteiro.");
                    quantidadeTextField.setText("");
                    return;
                }

                double longitude;
                try {
                    longitude = Double.parseDouble(this.longitudeTextField.getText());
                } catch (NumberFormatException var9) {
                    area.setText("Erro: Longitude inválida. Insira um valor numérico.");
                    latitudeTextField.setText("");
                    return;
                }

                double latitude;
                try {
                    latitude = Double.parseDouble(this.latitudeTextField.getText());
                } catch (NumberFormatException var8) {
                    area.setText("Erro: Latitude inválida. Insira um valor numérico.");
                    latitudeTextField.setText("");
                    return;
                }

                Equipe e = new Equipe(codinome, quantidade, latitude, longitude);
                equipes.addEquipe(e);
                area.setText("Equipe Cadastrada:   \n" + e.equipeToString());
                return;
            }

            equipe = (Equipe)var2.next();
        } while(!equipe.getCodinome().equals(codinome));

        area.setText("Erro: Uma equipe já existe com este codinome");
        codinomeTextField.setText("");
    }

    public void limparCampos() {
        codinomeTextField.setText("");
        quantidadeTextField.setText("");
        longitudeTextField.setText("");
        latitudeTextField.setText("");
        longitudeTextField.setText("");
        area.setText("");
    }

    public void exibir() {
        frame.setVisible(true);
    }
}
