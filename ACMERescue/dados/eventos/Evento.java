package dados.eventos;
import dados.ConfereCodigo;
import dados.Equipe;
import dados.Atendimento;
import dados.StatusAtendimento;


public class Evento {
	private String codigo;
	private String data;
	private double latitude;
	private double longitude;
	private Equipe equipeAtendimento;
	private StatusAtendimento statusAtendimento;
	private Atendimento atendimento;

	public Evento(String codigo, String data, double latitude, double longitude){
		this.codigo = codigo;
		this.data = data;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setEquipeAtendimento(Equipe equipeAtendimento) {
		this.equipeAtendimento = equipeAtendimento;
	}

	public Equipe getEquipeAtendimento() {
		return equipeAtendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public String eventoToString(){
		return "\nCódigo: "+getCodigo()+"\nData: "+getData()+"\nLatitude: "+getLatitude()+"\nLongitude: "+getLongitude();
	}
}
