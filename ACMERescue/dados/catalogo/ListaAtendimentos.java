package dados.catalogo;
import dados.Atendimento;
import dados.ConfereCodigo;
import dados.Equipe;
import dados.equipamentos.Equipamento;
import dados.eventos.Evento;

import java.util.ArrayList;

public class ListaAtendimentos implements ConfereCodigo {
    private ArrayList<Atendimento> listaAtendimentos;
    private int quantAtendimentos;

    public ListaAtendimentos(){
        listaAtendimentos = new ArrayList<>();
    }

    public ArrayList<Atendimento> getListaAtendimentos() {
        ArrayList<Atendimento> cloneLista = (ArrayList<Atendimento>) listaAtendimentos.clone();
        return cloneLista;
    }

    @Override
    public boolean formatoCodigo(String cd){
        try{
            int codigoValido = Integer.parseInt(cd);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
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
