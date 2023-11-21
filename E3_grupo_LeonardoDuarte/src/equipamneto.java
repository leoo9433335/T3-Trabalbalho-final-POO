public class equipamneto {
    int codigo;

    String nome;


    public equipamneto(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
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
        return "Codigo: " + codigo + ", Nome: " + nome ;
    }

}
