package ui.evento;
import dados.catalogo.ListaEventos;
import dados.eventos.Ciclone;
import dados.eventos.Evento;
import dados.eventos.Seca;
import dados.eventos.Terremoto;
import excecoes.CampoVazio;
import excecoes.CodigoInvalido;
import ui.telainicial.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaEvento extends JanelaGenerica {
    private JPanel container;

    private ListaEventos listaEventos;
    private JTextField codigo, data, latitude, longitude;
    private JButton criarEvento, limparCampos, eventosCad, sairCad;
    private JLabel labelCodigo, labelData, labelLatitude, labelLongitude;
    private JTextArea area;
    private TratadorEventos tratador;
    private JLabel opcoesLabel;
    private Home janelaAnterior;
    private JComboBox<String> listaOpcoes;
    private JanelaCiclone janelaCiclone;
    private JanelaTerremoto janelaTerremoto;
    private JanelaSeca janelaSeca;
    private JScrollPane scrollPane;
    private JFrame frame;


    public JanelaEvento(Home h) {
        super();

        frame = new JFrame("Cadastro de Evento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        janelaAnterior = h;

        listaEventos = janelaAnterior.getDadosEventos();

        //this.setTitle("Cadastro de Eventos");

        labelCodigo = new JLabel("Código: ");
        codigo = new JTextField(30);

        labelData = new JLabel("Data: ");
        data = new JTextField(30);

        labelLatitude = new JLabel("Latitude: ");
        latitude = new JTextField(30);

        labelLongitude = new JLabel("Longitude: ");
        longitude = new JTextField(30);


        criarEvento = new JButton("Criar evento");
        limparCampos = new JButton("Limpar campos");
        eventosCad = new JButton("Apresentar todos eventos");
        sairCad = new JButton("Sair");

        opcoesLabel = new JLabel("Tipo de evento: ");
        String[] opcoes = {"Ciclone", "Terremoto", "Seca"};
        listaOpcoes = new JComboBox<>(opcoes);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(codigo);
        container.add(data);
        container.add(latitude);
        container.add(longitude);
        container.add(criarEvento);
        container.add(limparCampos);
        container.add(eventosCad);
        container.add(sairCad);
        container.add(criarPainel(labelCodigo, codigo));
        container.add(criarPainel(labelData, data));
        container.add(criarPainel(labelLatitude, latitude));
        container.add(criarPainel(labelLongitude, longitude));
        container.add(criarPainel(opcoesLabel, listaOpcoes));

        area = new JTextArea(20, 50);
        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane);


        tratador = new TratadorEventos();
        criarEvento.addActionListener(tratador);
        limparCampos.addActionListener(tratador);
        eventosCad.addActionListener(tratador);
        sairCad.addActionListener(tratador);

        frame.add(container);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.pack();
    }

    public void exibir() {
        frame.setVisible(true);
    }

    public JPanel getContainer() {
        return container;
    }

    public Evento defineInstancia(String tipo, String c, String d, double l, double lo) {
        switch (tipo) {
            case "Ciclone":
                Ciclone e = new Ciclone(c, d, l, lo);
                janelaCiclone = new JanelaCiclone(e);
                janelaCiclone.setVisible(true);
                return e;
            case "Terremoto":
                Terremoto t = new Terremoto(c, d, l, lo);
                janelaTerremoto = new JanelaTerremoto(t);
                janelaTerremoto.setVisible(true);
                return t;
            case "Seca":
                Seca s = new Seca(c, d, l, lo);
                janelaSeca = new JanelaSeca(s);
                janelaSeca.setVisible(true);
                return s;
        }
        return null;
    }

    public JPanel criarPainel(JLabel label, JComboBox e) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.add(label);
        painel.add(e);
        return painel;
    }

    public void setJanelaAnterior(Home janelaAnterior) {
        this.janelaAnterior = janelaAnterior;
    }

    private class TratadorEventos extends Component implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == criarEvento) {
                try {
                    String codigoStr = codigo.getText().trim();

                    String dataStr = data.getText().trim();
                    String latitudeStr = latitude.getText().trim();
                    String longitudeStr = longitude.getText().trim();
                    String tipoEvento = (String) listaOpcoes.getSelectedItem();

                    if (codigoStr.isEmpty() || dataStr.isEmpty() || latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
                        throw new CampoVazio("Todos os campos para inserir as informações devem ser preenchidos! ");
                    }

                    try {
                        double latitudeDoub = Double.parseDouble(latitudeStr.trim());
                        double longitudeDoub = Double.parseDouble(longitudeStr.trim());
                        boolean confFormato = listaEventos.formatoCodigo(codigoStr);
                        boolean confere = listaEventos.verificaCodigo(codigoStr);
                        if (confFormato) {
                            if (confere) {
                                listaEventos.addEvento(defineInstancia(tipoEvento, codigoStr, dataStr, latitudeDoub, longitudeDoub));
                                int t = listaEventos.getQuantEventos();
                                area.setText("Evento cadastrado! " + t);
                            } else {
                                throw new CodigoInvalido("Não foi possível realizar o cadastro desse evento pois o código inserido já existe. ");
                            }
                        } else {
                            throw new CodigoInvalido("O código deve possuir apenas caracteres numéricos! ");
                        }
                    } catch (NumberFormatException ex) {
                        area.setText("Os campos de latitude e longitude devem ser preenchidos com valores numéricos! ");
                        JOptionPane.showMessageDialog(JanelaEvento.this, "Os campos de latitude e longitude devem ser preenchidos com valores numéricos! ");
                    } catch (CodigoInvalido i) {
                        area.setText(i.getMessage());
                        JOptionPane.showMessageDialog(JanelaEvento.this, i.getMessage());
                    }
                } catch (CampoVazio r) {
                    area.setText(r.getMessage());
                    JOptionPane.showMessageDialog(JanelaEvento.this, r.getMessage());
                }

            }

            if (e.getSource() == limparCampos) {
                codigo.setText("");
                data.setText("");
                latitude.setText("");
                longitude.setText("");
                area.setText("");
            }

            if (e.getSource() == eventosCad) {
                listaEventos.ordEventos();
                area.setText("");
                for (Evento ev : listaEventos.getListaEventos()) {
                    if (ev instanceof Terremoto) {
                        area.append(((Terremoto) ev).terremotoToString());
                        area.append("\n\n");
                    }
                    if (ev instanceof Ciclone) {
                        area.append(((Ciclone) ev).cicloneToString());
                        area.append("\n\n");
                    }
                    if (ev instanceof Seca) {
                        area.append(((Seca) ev).secaToString());
                        area.append("\n\n");
                    }
                }
            }

            if (e.getSource() == sairCad) {
                frame.setVisible(false);
                janelaAnterior.exibir();
            }
        }
    }
}
