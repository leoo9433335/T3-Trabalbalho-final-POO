package dados.equipamentos;

public class Barco extends Equipamento {

	private int capacidadePessoas;


	public Barco(int i, String s, double d){
		super(i, s, d);
		capacidadePessoas = 0;
	}

	public void setCapacidadePessoas(int capacidadePessoas) {
		this.capacidadePessoas = capacidadePessoas;
	}

	public String barcoToString(){
		return "---BARCO---"+equipamentoToString()+"\nCapacidade de pessoas: "+capacidadePessoas;
	}
}
