import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimKruskal extends Grafo {

	private int costoTotal;

	public PrimKruskal(int cantNodos, int cantAristas) {
		super(cantNodos, cantAristas);
		this.costoTotal = 0;

	}

	public Grafo prim() {

		// El nodo de partida es indiferente del algoritmo
		int nodoAIniciar = 1;
		List<Integer> conjuntoSolucion = new ArrayList<Integer>();
		PriorityQueue<Arista> colaAristas = new PriorityQueue<Arista>(new comparaAristas());
		// El arbol a retornar y construir, tendra n nodos, y n-1 aristas
		Grafo arbolAbarcadorCostoMinimo = new Grafo(this.cantNodos, this.cantNodos - 1);

		conjuntoSolucion.add(nodoAIniciar);
		añadirAdyacentesACola(colaAristas, conjuntoSolucion);
		Arista aristaMinima;

		while (conjuntoSolucion.size() < this.cantNodos) {

			// Se pregunta ante la posibilidad de que haya quedo en el tope de la cola
			// una arista que sea invalida por hacia un nodoDestino que se agrego
			// recientemente al conjunto solucion
			aristaMinima = colaAristas.poll();
			while (conjuntoSolucion.contains(aristaMinima.getNodoDestino())) {
				aristaMinima = colaAristas.poll();
			}

			arbolAbarcadorCostoMinimo.agregarAristaLst(aristaMinima);

			costoTotal += aristaMinima.getPeso();

			conjuntoSolucion.add(aristaMinima.getNodoDestino());

			añadirAdyacentesACola(colaAristas, conjuntoSolucion);

		}

		return arbolAbarcadorCostoMinimo;
	}

	private void añadirAdyacentesACola(PriorityQueue<Arista> colaAristas, List<Integer> conjuntoSolucion) {

		for (int i = 0; i < this.cantNodos; i++) {
///Se añaden las aristas a la cola, siempre y cuando el nodo destino, no pertenezca al conjutno solucion

			if (conjuntoSolucion.contains(i + 1)) {
				Iterator iterador = this.listaAdy[i].iterator();
				while (iterador.hasNext()) {
					Arista arista = (Arista) iterador.next();
					if (!conjuntoSolucion.contains(arista.getNodoDestino())) {
						colaAristas.add(arista);
					}
				}
			}
		}
	}

	public int getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}

}
