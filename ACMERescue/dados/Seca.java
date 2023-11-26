package dados;
import dados.Evento;

public class Seca extends Evento {
	private int estiagem;

	public Seca(String codigo, String data, double latitude, double longitude){
		super(codigo, data, latitude, longitude);
		this.estiagem = 0;
	}

	public void setEstiagem(int estiagem) {
		this.estiagem = estiagem;
	}

	public int getEstiagem() {
		return estiagem;
	}

	public String secaToString(){
		return "---SECA---"+eventoToString()+"\nEstiagem: "+estiagem;
	}
}
