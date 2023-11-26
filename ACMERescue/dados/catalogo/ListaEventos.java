package dados.catalogo;

import dados.eventos.Evento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class ListaEventos {
    private int quantEventos;
    private ArrayList<Evento> listaEventos;

    public ListaEventos() {
        listaEventos = new ArrayList<>();
        quantEventos = 0;
    }

    public int getQuantEventos() {
        return quantEventos;
    }

    public ArrayList<Evento> getListaEventos() {
        ArrayList<Evento> cloneLista = (ArrayList<Evento>) listaEventos.clone();
        return cloneLista;
    }

    public void mostraEventos() {
        for (Evento i : listaEventos) {
            i.eventoToString();
        }
    }

    public boolean verificaCodigo(String codigo) {
        if(listaEventos.isEmpty()) return true;
        for (Evento i : listaEventos) {
            if (codigo.equalsIgnoreCase(i.getCodigo())) {
                return false;
            }
        }
        return true;
    }

    public boolean formatoCodigo(String cd){
        try{
            int codigoValido = Integer.parseInt(cd);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public boolean addEvento(Evento e) {
        if (verificaCodigo(e.getCodigo())) {
            listaEventos.add(e);
            quantEventos++;
            return true;
        } else {
            return false;
        }
    }

    public void ordEventos() {
        Collections.sort(listaEventos, new Comparator<Evento>() {
            public int compare(Evento eventoUm, Evento eventoDois) {
                int codUm = Integer.parseInt(eventoUm.getCodigo().trim());
                int codDois = Integer.parseInt(eventoDois.getCodigo().trim());

                return Integer.compare(codUm, codDois);
            }
        }
        );
    }
}

