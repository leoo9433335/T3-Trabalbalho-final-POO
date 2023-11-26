package ui.equipamento;
import dados.equipamentos.*;
import excecoes.CampoVazio;
import excecoes.CodigoInvalido;
import ui.evento.*;
import dados.catalogo.ListaEquipamentos;
import ui.telainicial.Home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class JanelaEquipamento extends JanelaGenerica {
    private JPanel container;
    private ListaEquipamentos listaEquipamentos;
    private JTextField id, nome, custoDia;
    private JButton cadastrarEquipamento, limparCampos, equipaCadastrados, sairCad;
    private JLabel labelId, labelNome, labelCustoDia, opcoesLabel;
    private JTextArea area;
    private TratadorEventos tratador;
    private Home janelaAnterior;
    private JComboBox<String> listaOpcoes;
    private JanelaCamTanque janelaCamTanque;
    private JanelaBarco janelaBarco;
    private JanelaEscavadeira janelaEscavadeira;
    private JScrollPane scrollPane;
    private JFrame frame;


        public JanelaEquipamento(Home h) {
            super();

            frame = new JFrame("Cadastro de Equipamentos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);

            janelaAnterior = h;

            listaEquipamentos = new ListaEquipamentos();

            labelId = new JLabel("ID: ");
            id = new JTextField(30);

            labelNome = new JLabel("Nome: ");
            nome = new JTextField(30);

            labelCustoDia = new JLabel("Custo diário: ");
            custoDia = new JTextField(30);

            cadastrarEquipamento = new JButton("Cadastrar equipamento");
            equipaCadastrados = new JButton("Equipamentos cadastrados");
            limparCampos = new JButton("Limpar campos");
            sairCad = new JButton("Sair");

            opcoesLabel = new JLabel("Tipo de equipamento: ");
            String[] opcoes = {"Caminhão tanque", "Barco", "Escavadeira"};
            listaOpcoes = new JComboBox<>(opcoes);

            container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

            container.add(id);
            container.add(nome);
            container.add(custoDia);
            container.add(cadastrarEquipamento);
            container.add(equipaCadastrados);
            container.add(limparCampos);
            container.add(sairCad);

            container.add(criarPainel(labelId, id));
            container.add(criarPainel(labelNome, nome));
            container.add(criarPainel(labelCustoDia, custoDia));
            container.add(criarPainel(opcoesLabel, listaOpcoes));

            area = new JTextArea(20, 50);
            scrollPane = new JScrollPane(area);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            container.add(scrollPane);


            tratador = new TratadorEventos();
            cadastrarEquipamento.addActionListener(tratador);
            limparCampos.addActionListener(tratador);
            equipaCadastrados.addActionListener(tratador);
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

        public Equipamento defineInstancia(String tipo, int i, String s, Double d) {
            switch (tipo) {
                case "Caminhão tanque":
                    CaminhaoTanque c = new CaminhaoTanque(i, s, d);
                    janelaCamTanque = new JanelaCamTanque(c);
                    janelaCamTanque.setVisible(true);
                    return c;
                case "Barco":
                    Barco b = new Barco(i, s, d);
                    janelaBarco = new JanelaBarco(b);
                    janelaBarco.setVisible(true);
                    return b;
                case "Escavadeira":
                    Escavadeira e = new Escavadeira(i, s, d);
                    janelaEscavadeira = new JanelaEscavadeira(e);
                    janelaEscavadeira.setVisible(true);
                    return e;
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

        class TratadorEventos extends Component implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cadastrarEquipamento) {
                    try {
                        String idStr = id.getText().trim();
                        String nomeStr = nome.getText().trim();
                        String custoDiaStr = custoDia.getText().trim();
                        String tipoEquipamento = (String) listaOpcoes.getSelectedItem();

                        if (idStr.isEmpty() || nomeStr.isEmpty() || custoDiaStr.isEmpty()) {
                            throw new CampoVazio("Todos os campos para inserir as informações devem ser preenchidos! ");
                        }

                        try {
                            int idInt = Integer.parseInt(idStr.trim());
                            double custoDiaDoub = Double.parseDouble(custoDiaStr.trim());
                            boolean confere = listaEquipamentos.verificaId(idInt);

                        if (confere) {
                            listaEquipamentos.addEquipamento(defineInstancia(tipoEquipamento, idInt, nomeStr, custoDiaDoub));
                            int t = listaEquipamentos.getQuantEquipamentos();
                            area.setText("Equipamento cadastrado! " + t);
                        } else {
                            throw new CodigoInvalido("Não foi possível realizar o cadastro desse evento pois o ID inserido já existe. ");
                        }
                        } catch (NumberFormatException ex) {
                            area.setText("Os campos de ID e custo diário devem ser preenchidos com valores numéricos! ");
                            JOptionPane.showMessageDialog(ui.equipamento.JanelaEquipamento.this, "Os campos de ID e custo diário  devem ser preenchidos com valores numéricos! ");
                        } catch (CodigoInvalido i) {
                            area.setText(i.getMessage());
                            JOptionPane.showMessageDialog(ui.equipamento.JanelaEquipamento.this, i.getMessage());
                        }
                    } catch (CampoVazio r) {
                        area.setText(r.getMessage());
                        JOptionPane.showMessageDialog(ui.equipamento.JanelaEquipamento.this, r.getMessage());
                    }

                }

                if (e.getSource() == limparCampos) {
                    id.setText("");
                    custoDia.setText("");
                    nome.setText("");
                    area.setText("");
                }

                if (e.getSource() == equipaCadastrados) {
                    listaEquipamentos.ordEquipamentos();
                    area.setText("");
                    for (Equipamento eq : listaEquipamentos.getListaEquipamentos()) {
                        if (eq instanceof CaminhaoTanque) {
                            area.append(((CaminhaoTanque) eq).camTanqueToString());
                            area.append("\n\n");
                        }
                        if (eq instanceof Barco) {
                            area.append(((Barco) eq).barcoToString());
                            area.append("\n\n");
                        }
                        if (eq instanceof Escavadeira) {
                            area.append(((Escavadeira) eq).escavadeiraToString());
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

