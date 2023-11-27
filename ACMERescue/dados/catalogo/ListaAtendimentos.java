package dados.catalogo;
import dados.Atendimento;
import dados.Equipe;
import dados.equipamentos.Equipamento;
import dados.eventos.Evento;

import java.util.ArrayList;

public class ListaAtendimentos {
    private ArrayList<Atendimento> listaAtendimentos;
    private int quantAtendimentos;

    public ListaAtendimentos(){
        listaAtendimentos = new ArrayList<>();
    }

    public ArrayList<Atendimento> getListaAtendimentos() {
        ArrayList<Atendimento> cloneLista = (ArrayList<Atendimento>) listaAtendimentos.clone();
        return cloneLista;
    }

    public boolean verificaCodigo(int codigo) {
        if(listaAtendimentos.isEmpty()) return false;
        for (Atendimento i : listaAtendimentos) {
            if (codigo == i.getCodigo()){
                return true;
            }
        }
        return false;
    }

    public void addAtendimento(Atendimento a){
        listaAtendimentos.add(a);
    }

    public int getQuantAtendimentos(){
        quantAtendimentos = listaAtendimentos.size();
        return quantAtendimentos;
    }


}
