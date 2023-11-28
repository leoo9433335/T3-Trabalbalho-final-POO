package dados;

public class Equipe {

	private String codinome;

	private int quantidade;

	private double latitude;

	private double longitude;

	public Equipe(String codinome, int quantidade, double latitude, double longitude) {
		this.codinome = codinome;
		this.quantidade = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCodinome() {
		return codinome;
	}

	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String equipeToString() {
		return "\nCodinome: "+getCodinome()+"\nQuantidade de membros: "+getQuantidade()+"\nLatitude: "+getLatitude()+"\nLongitude: "+getLongitude();
	}
}
