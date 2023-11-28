package dados.equipamentos;
import dados.Combustivel;

import dados.equipamentos.Equipamento;

public class Escavadeira extends Equipamento {

	private Combustivel combustivel;

	private double carga;

	public Escavadeira(int i, String s, double d){
		super(i, s, d);
		combustivel = null;
		carga = 0;
	}

	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}

	public void setCarga(double carga) {
		this.carga = carga;
	}

	public String escavadeiraToString(){
		return "---ESCAVADEIRA---"+equipamentoToString()+"\nCombustível: "+combustivel+"\nCarga (em metros cúbicos): "+carga;
	}

}
