package dados.equipamentos;

public class Equipamento {

	private int id;

	private String nome;

	private double custoDia;

	public Equipamento(int i, String s, double d){
		id = i;
		nome = s;
		custoDia = d;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCustoDia(double custoDia) {
		this.custoDia = custoDia;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getCustoDia() {
		return custoDia;
	}

	public String equipamentoToString(){
		return "\nID: "+getId()+"\nNome: "+getNome()+"\nCusto por dia: R$ "+getCustoDia();
	}


}
