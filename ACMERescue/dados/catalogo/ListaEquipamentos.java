package dados.catalogo;
import dados.Equipe;
import dados.equipamentos.Equipamento;
import java.util.Collections;
import java.util.Comparator;
import dados.eventos.Evento;

import java.util.ArrayList;

public class ListaEquipamentos {
    private ArrayList<Equipamento> equipamentosCadastrados;

    public ListaEquipamentos(){
        equipamentosCadastrados = new ArrayList<>();
    }

    public void addEquipamento(Equipamento e){
        equipamentosCadastrados.add(e);
    }

    public boolean verificaId(int id){
        if(equipamentosCadastrados.isEmpty()) return true;
        for (Equipamento e : equipamentosCadastrados) {
            if (id == e.getId()) {
                return false;
            }
        }
        return true;
    }

    public int getQuantEquipamentos(){
        return equipamentosCadastrados.size();
    }

    public void ordEquipamentos(){
        Collections.sort(equipamentosCadastrados, Comparator.comparingInt(Equipamento::getId));
    }

    public ArrayList<Equipamento> getListaEquipamentos() {
        ArrayList<Equipamento> cloneLista = (ArrayList<Equipamento>) equipamentosCadastrados.clone();
        return cloneLista;
    }
}

