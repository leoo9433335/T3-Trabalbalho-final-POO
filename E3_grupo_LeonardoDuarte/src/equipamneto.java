public class equipamneto {
    int codigo;

    String nome;

    int preco;

    Tipo tipo;


    public equipamneto(int codigo, String nome, int preco, Tipo tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco=preco;
        this.tipo = tipo;
    }




    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String toString() {
        return "Codigo: " + codigo + ", Nome: " + nome +",Preço:"+preco+"Tipo:"+tipo;
    }

}
