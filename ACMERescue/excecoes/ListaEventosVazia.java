package excecoes;

import dados.catalogo.ListaEventos;

public class ListaEventosVazia extends Exception{
    public ListaEventosVazia(String msg){
        super(msg);
    }
}
