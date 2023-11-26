package ui.equipamento;

import ui.evento.JanelaEvento;
import ui.evento.JanelaGenerica;
import dados.equipamentos.Escavadeira;
import dados.*;

public class JanelaEscavadeira extends JanelaGenerica {
    private Escavadeira escavadeira;

    public JanelaEscavadeira(Escavadeira e){
        escavadeira = e;
    }

}
