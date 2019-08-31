package servicios;

import interfaces.ITipoEntrega;

public class EntregaService {
	public ITipoEntrega tipoEntrega;
	
	public EntregaService(ITipoEntrega tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}
	
	public void enviarEntrega(String ruta) {
		this.tipoEntrega.hacerEnvio(ruta);	
	}
	
}
