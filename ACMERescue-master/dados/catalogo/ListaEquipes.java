package dados.catalogo;

import dados.Equipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaEquipes {
    private ArrayList<Equipe> equipesCadastradas;

    public ListaEquipes(){
        equipesCadastradas = new ArrayList<>();
    }

    public void addEquipe(Equipe e){
        equipesCadastradas.add(e);
    }

    public ArrayList<Equipe> getEquipesCadastradas() {
        ArrayList<Equipe> cloneLista = (ArrayList<Equipe>) equipesCadastradas.clone();
        return cloneLista;
    }

    public void ordenarEquipesPorCodinome() {
        Collections.sort(equipesCadastradas, Comparator.comparing(Equipe::getCodinome));
    }
}
