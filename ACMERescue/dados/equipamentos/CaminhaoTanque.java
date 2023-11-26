package dados.equipamentos;

public class CaminhaoTanque extends Equipamento {

	private double capacidade;

	public CaminhaoTanque(int i, String s, double d){
		super(i, s, d);
		capacidade = 0;
	}

	public void setCapacidade(double capacidade) {
		this.capacidade = capacidade;
	}

	public String camTanqueToString(){
		return "---CAMINHAO TANQUE---"+equipamentoToString()+"\nCapacidade: "+capacidade+" mil litros";
	}



}
