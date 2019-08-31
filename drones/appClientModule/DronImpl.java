import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import interfaces.ITipoEntrega;

public class DronImpl implements ITipoEntrega {

	Map<String, String> valoresI = new HashMap<String, String>();
	Map<String, String> valoresD = new HashMap<String, String>();
	Map<String, String> cardinalidad = new HashMap<String, String>();

	public DronImpl() {
		super();
		valoresI.put("Y", "-X");
		valoresI.put("-Y", "X");
		valoresI.put("X", "Y");
		valoresI.put("-X", "-Y");

		valoresD.put("Y", "X");
		valoresD.put("-Y", "-X");
		valoresD.put("X", "-Y");
		valoresD.put("-X", "Y");

		cardinalidad.put("Y", "N");
		cardinalidad.put("-Y", "S");
		cardinalidad.put("X", "E");
		cardinalidad.put("-X", "O");
	}

	@Override
	public void hacerEnvio(String archivoMovimiento) {
		BufferedReader bf = null;
		String[] coordenadas = null;
		try {
			bf = new BufferedReader(new FileReader(archivoMovimiento));
			coordenadas = this.ejecutarEnvio(bf);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] ejecutarEnvio(BufferedReader bf) {
		String sCadena = null;
		Main app = new Main();
		String[] coordenadas = new String[3];
		try {
			while ((sCadena = bf.readLine()) != null) {
				coordenadas = this.coordenadas(sCadena);
				this.imprimirCoordenadas(sCadena, coordenadas);
			}
			bf.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return coordenadas;
	}

	public void imprimirCoordenadas(String ruta, String[] coordenadas) {
		System.out.println(Thread.currentThread() + " " + ruta + " - (" + coordenadas[0] + "," + coordenadas[1] + ","
				+ coordenadas[2] + ")");
	}

	public String[] coordenadas(String cadena) {
		int x = 0;
		int y = 0;
		String orientacion = "Y";
		String[] coordenadas = new String[3];
		char[] cadenaArray = cadena.toCharArray();
		for (char cad : cadenaArray) {
			if (cad == 'A') {
				switch (orientacion) {
				case "Y":
					y = Math.abs(y);
					y++;
					break;
				case "-Y":
					y = (y < 0) ? y - 1 : (y + 1) * -1;
					break;
				case "-X":
					x = (x < 0) ? x - 1 : (x + 1) * -1;
					break;
				case "X":
					x = Math.abs(x);
					x++;
					break;
				}
			} else {
				if (cad == 'I') {
					orientacion = this.valoresI.get(orientacion);
				}
				if (cad == 'D') {
					orientacion = this.valoresD.get(orientacion);
				}
			}
		}

		coordenadas[0] = String.valueOf(x);
		coordenadas[1] = String.valueOf(y);
		coordenadas[2] = this.cardinalidad.get(orientacion);
		return coordenadas;
	}

}
