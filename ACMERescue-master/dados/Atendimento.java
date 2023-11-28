package dados;
import dados.eventos.Evento;
import dados.equipamentos.Equipamento;

public class Atendimento {

	private int codigo;

	private String dataInicio;

	private int duracao;

	private StatusAtendimento status;
	private Evento evento;

	public Atendimento(int c, String data){
		codigo = c;
		dataInicio = data;
		duracao = 0;
		status = StatusAtendimento.PENDENTE;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setStatus(StatusAtendimento status) {
		this.status = status;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEvento() {
		return evento;
	}

	public StatusAtendimento getStatus() {
		return status;
	}

	public double calculaCusto() {
		return 0;
	}

	public String atendimentoToString(){
		return "\n---ATENDIMENTO---\nCódigo: "+codigo+"\nData de início: "+dataInicio+"\nDuração: "+duracao+"\nStatus : "+status;
	}


	public static double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
		double R = 6371;  // raio da Terra em quilômetros

		// converter graus para radianos
		lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);

		// diferenças de coordenadas
		double dlat = lat2 - lat1;
		double dlon = lon2 - lon1;

		// fórmula de Haversine
		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
				Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon / 2) * Math.sin(dlon / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		// distância em quilômetros
		double distance = R * c;

		return distance;
	}

}
