package dados.eventos;

import dados.eventos.Evento;

public class Ciclone extends Evento {
	private double velocidade;
	private double precipitacao;

	public Ciclone(String codigo, String data, double latitude, double longitude){
		super(codigo, data, latitude, longitude);
		this.velocidade = 0;
		this.precipitacao = 0;
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	public double getVelocidade() {
		return velocidade;
	}

	public void setPrecipitacao(double precipitacao) {
		this.precipitacao = precipitacao;
	}

	public double getPrecipitacao() {
		return precipitacao;
	}

	public String cicloneToString(){
		return "---CICLONE---"+eventoToString()+"\nVelocidade: "+velocidade+"\nPrecipitação: "+precipitacao;
	}
}
