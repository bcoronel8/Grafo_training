import java.io.FileNotFoundException;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		Grafo grafito = new Grafo(10);
		grafito.cargarMatrizArchivo("grafo.in");
		Integer[] hops= grafito.recorrido_BFS(0);
		for (int i = 0; i < hops.length; i++) {
			System.out.print(hops[i] + "\t\t");
		}
	}
}
