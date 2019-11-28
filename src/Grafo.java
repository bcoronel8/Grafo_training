import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Grafo {

	protected Integer[][] matrizAdy;
	protected List<Arista>[] listaAdy;
	protected int cantNodos;
	protected int cantAristas; // no es necesario realmente
	public int[] precedentes;// esto corresponderia a una class dijktra
	private final int INFINITO = 8000;

	public Grafo(int cantNodos, int cantAristas) {

		this.cantNodos = cantNodos;
		this.cantAristas = cantAristas;
		this.precedentes = new int[this.cantNodos + 1];

		matrizAdy = new Integer[cantNodos][cantNodos];
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				this.matrizAdy[i][j] = INFINITO;
			}
		}

		listaAdy = new List[cantNodos];
		for (int i = 0; i < cantNodos; i++) {
			listaAdy[i] = new LinkedList<Arista>();
		}
	}

	public void agregarAristaMat(int origen, int destino, int peso) {
		this.matrizAdy[origen - 1][destino - 1] = peso;

	}

	public void recorrido_DFS(int nodoActual) {

		int nodoReco;
		int nodoAdyacente;
		Stack<Integer> pilaDeNodos = new Stack<Integer>();
		boolean[] arrayVisitados = new boolean[cantNodos + 1];
		Arrays.fill(arrayVisitados, false);

		pilaDeNodos.push(nodoActual);
		arrayVisitados[nodoActual - 1] = true;

		while (!pilaDeNodos.isEmpty()) {
			nodoReco = pilaDeNodos.peek();
			pilaDeNodos.pop();

			if (arrayVisitados[nodoReco] == false) {
				arrayVisitados[nodoReco] = true;
				System.out.println("Nodo " + nodoReco);

			}

			Iterator<Arista> iterador = this.listaAdy[nodoReco - 1].iterator();
			while (iterador.hasNext()) {
				nodoAdyacente = iterador.next().nodoDestino;
				if (arrayVisitados[nodoAdyacente] == false) {
					pilaDeNodos.push(nodoAdyacente);
				}
			}
		}

	}

	public Integer[] recorrido_BFS(int nodoActual) {

		Queue<Integer> colaDeNodos = new LinkedList<Integer>();
		Integer[] arrayDeHops = new Integer[this.cantNodos + 1];
		Arrays.fill(arrayDeHops, INFINITO);

		int distancia = 0;

		colaDeNodos.add(nodoActual);
		arrayDeHops[nodoActual] = distancia;

		while (!colaDeNodos.isEmpty()) {

			nodoActual = colaDeNodos.poll();
			System.out.println("Nodo   " + nodoActual);
			Iterator<Arista> iterador = this.listaAdy[nodoActual - 1].iterator();
			while (iterador.hasNext()) {
				int nodoReco = iterador.next().getNodoDestino();
				if (arrayDeHops[nodoReco] == INFINITO) {

					colaDeNodos.add(nodoReco);
					arrayDeHops[nodoReco] = arrayDeHops[nodoActual] + 1;
				}
			}
		}
		return arrayDeHops;
	}

/////////////////////////////////////////////////////////////
	public int[] dijkstra(int nodoInicio) {

		int[] distancias = new int[this.cantNodos + 1];// Para tener indices mas claros
		List<Integer> conjuntoSolucion = new ArrayList<Integer>();
		PriorityQueue<DistanciaCosto> colaDistancias = new PriorityQueue<DistanciaCosto>(new comparaDistancias());
		int w;
		Arrays.fill(this.precedentes, nodoInicio); /// Solo de precisar la construccion del camino

		conjuntoSolucion.add(nodoInicio);
		cargarDistancias(distancias, nodoInicio);
		cargarDistanciasCola(distancias, colaDistancias, conjuntoSolucion);

		while (conjuntoSolucion.size() < this.cantNodos) {

			w = colaDistancias.poll().getNodo();
			conjuntoSolucion.add(w);

			for (int i = 1; i < distancias.length; i++) {
				if (!conjuntoSolucion.contains(i)) {
					if (distancias[i] > (distancias[w] + this.matrizAdy[w - 1][i - 1])) {/// Solo de precisar la
																							/// construccion del camino
						precedentes[i] = w;
					}
					distancias[i] = Math.min(distancias[i], distancias[w] + this.matrizAdy[w - 1][i - 1]);
				}
			}
			cargarDistanciasCola(distancias, colaDistancias, conjuntoSolucion); // Se vuelve a cargar la cola, con el
																				// nuevo conjunto solucion
		}

		return distancias;
	}

	// Carga las distancias que no esten contenidas en el conjutno solucion a la
	// cola
	// En un tipo de dato DistanciaCosto.
	// Si la cola tiene contenido, se la vacia y se carga con cada conjuntoSolucion
	// nuevo
	private void cargarDistanciasCola(int[] distancias, PriorityQueue<DistanciaCosto> colaDistancias,
			List<Integer> conjuntoSolucion) {
		if (!colaDistancias.isEmpty()) {
			colaDistancias.clear();
		}

		for (int i = 1; i < distancias.length; i++) {
			if (!conjuntoSolucion.contains(i))
				colaDistancias.add(new DistanciaCosto(distancias[i], i));
		}

	}

	private void cargarDistancias(int[] distancias, int nodoInicio) {
		for (int i = 0; i < distancias.length - 1; i++) {
			distancias[i + 1] = this.matrizAdy[nodoInicio - 1][i];
		}

	}

/////////////////////////////////////////////////////////////	
	public Integer[][] floyd() {
		Integer[][] matrizFx = new Integer[this.cantNodos][this.cantNodos];
		clonarMatrizFloyd(matrizFx, this.matrizAdy);

		for (int k = 0; k < matrizFx.length; k++) {
			for (int i = 0; i < matrizFx.length; i++) {
				for (int j = 0; j < matrizFx[i].length; j++) {
					matrizFx[i][j] = Math.min(matrizFx[i][j], matrizFx[i][k] + matrizFx[k][j]);
				}
			}
		}

		return matrizFx;
	}

	// Copia la matriz, y setea 0 en DP
	private void clonarMatrizFloyd(Integer[][] matrizFx, Integer[][] matrizAdy2) {
		for (int i = 0; i < matrizFx.length; i++) {
			for (int j = 0; j < matrizFx.length; j++) {
				if (i == j) {
					matrizFx[i][j] = 0;
				} else {
					matrizFx[i][j] = matrizAdy2[i][j];
				}
			}
		}
	}

/////////////////////////////////////////////////////////////
	public boolean[][] warshall() {
		boolean[][] matrizAx = new boolean[this.cantNodos][this.cantNodos];
		/// Creacion de la Matriz booleana
		convierteMatrizBooleana(matrizAx);
		/// Procedimiento de warshall
		for (int k = 0; k < matrizAx.length; k++) {
			for (int i = 0; i < matrizAx.length; i++) {
				for (int j = 0; j < matrizAx[i].length; j++) {
					if (i != j)
						matrizAx[i][j] = matrizAx[i][j] || (matrizAx[i][k] && matrizAx[k][j]);
				}
			}
		}

		return matrizAx;
	}

	/// Convierte Matriz a Booleana, False en DP
	// Y true si hay contenido que no sea "infinito" en la matriz
	private void convierteMatrizBooleana(boolean[][] matrizAx) {
		for (int i = 0; i < matrizAx.length; i++) {
			for (int j = 0; j < matrizAx[i].length; j++) {
				if (i == j)
					matrizAx[i][j] = false;
				else if (this.matrizAdy[i][j] != INFINITO)
					matrizAx[i][j] = true;
				else
					matrizAx[i][j] = false;
			}
		}
	}
/////////////////////////////////////////////////////////////

	public void agregarAristaLst(Arista aristaMinima) {

		this.listaAdy[aristaMinima.getNodoOrigen() - 1].add(aristaMinima);
	}

}
