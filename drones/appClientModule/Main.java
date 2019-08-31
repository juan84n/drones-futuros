import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import servicios.EntregaService;

import java.lang.Math;

public class Main {

	public Main() {
		super();
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Main app = new Main();
		app.crearEntrega("archivos/in.txt", executor).
			whenCompleteAsync((coordenadas, e) -> {
				// executor.shutdown();
			}, executor);
		
		app.crearEntrega("archivos/in2.txt", executor).
		whenCompleteAsync((coordenadas, e) -> {
			// executor.shutdown();
		}, executor);
	}

	public CompletableFuture<String[]> crearEntrega(String ruta, ExecutorService executor) {
		CompletableFuture<String[]> dron = CompletableFuture.supplyAsync(() -> {
			EntregaService service = new EntregaService(new DronImpl());
			service.enviarEntrega(ruta);
			return null;
		}, executor);

		return dron;
	}
}