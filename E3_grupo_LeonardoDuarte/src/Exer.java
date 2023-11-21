import com.sun.source.tree.UsesTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exer {
    private JButton limparButton;
    private JButton confirmarButton;
    private JButton mostraDadosButton;
    private JTextField camponome;
    private JTextField campocodigo;
    private JButton encerrarButton;
    private JTextArea campomensagensconsluta;
    private JTextArea mostradadosequipamentos;

    public JPanel getPainel() {
        return painel;
    }

    private JPanel painel;
    private List<equipamneto> equipamnetoList;


    public Exer() {
        equipamnetoList = new ArrayList<>();




        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                camponome.setText("");
                campocodigo.setText("");

            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostradadosequipamentos.setText("Equipamento cadastrado");
                ADDCadastro();


            }
        });
        mostraDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Mostradados();
               mostradadosequipamentos.setText("Apos soliciatar dados caso os dados estejam repetidos aperte limpar e depois confirma,após os seguintes passos parte novamente mostra dados! ");

            }
        });
        encerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });


    }

    private void ADDCadastro() {
        try {
            int codi = Integer.parseInt(campocodigo.getText());
            if (Verificacod(codi)) {
                throw new Exception("ERRO");
            }
            String nome = camponome.getText();
            equipamneto equipamneto = new equipamneto(codi, nome);
            equipamnetoList.add(equipamneto);
        } catch (Exception e) {
       campomensagensconsluta.setText("Codigo já cadastrado!\n");
        }
    }




    private void Mostradados(){

try {

      Collections.sort(equipamnetoList, (e1,e2)->Integer.compare(e2.getCodigo(),e1.getCodigo()));

    for (equipamneto df : equipamnetoList) {
        campomensagensconsluta.append(df.toString() + "\n");

    }

}catch (Exception e){
    System.out.println("erro!!!");
}
    }

private boolean Verificacod(int e){

    for (equipamneto am:equipamnetoList) {
        if ((am.getCodigo()==e)){
            return true;
        }

    }
        return false;
}

}




