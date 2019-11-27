import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Grafo {

	protected Integer[][] matrizAdy;
	protected List<Arista>[] listaAdy;
	protected int cantNodos;
	protected int cantAristas;
	public int[] precedentes;

	public Grafo(int cantNodos, int cantAristas) {

		this.cantNodos = cantNodos;
		this.cantAristas = cantAristas;
		this.precedentes = new int[this.cantNodos + 1];

		matrizAdy = new Integer[cantNodos][cantNodos];
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				this.matrizAdy[i][j] = 8000;
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

	public void cargarMatrizArchivo(String pathFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(pathFile));
		scanner.useLocale(Locale.ENGLISH);
		int cantElementos = scanner.nextInt();
		for (int i = 0; i < cantElementos; i++) {
			this.matrizAdy[scanner.nextInt()][scanner.nextInt()] = 1;
		}
		scanner.close();
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
			if (visitoTodosLosAdy(nodoReco, arrayVisitados)) {
				pilaDeNodos.pop();

			}

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

	private boolean visitoTodosLosAdy(int nodoARecorrer, boolean[] visitados) {

		Iterator<Arista> iterador = this.listaAdy[nodoARecorrer - 1].iterator();
		while (iterador.hasNext()) {
			if (visitados[iterador.next().getNodoDestino()] == false)
				return false;
		}
		return true;
	}

	public Integer[] recorrido_BFS(int nodoActual) {

		Queue<Integer> colaDeNodos = new LinkedList<Integer>();
		Integer[] arrayDeHops = new Integer[this.cantNodos + 1];
		Arrays.fill(arrayDeHops, 5000);

		int distancia = 0;

		colaDeNodos.add(nodoActual);
		arrayDeHops[nodoActual] = distancia;

		while (!colaDeNodos.isEmpty()) {

			nodoActual = colaDeNodos.peek();
			colaDeNodos.poll();
			System.out.println("Nodo   " + nodoActual);
			Iterator<Arista> iterador = this.listaAdy[nodoActual - 1].iterator();
			while (iterador.hasNext()) {
				int nodoReco = iterador.next().getNodoDestino();
				if (arrayDeHops[nodoReco] == 5000) {

					colaDeNodos.add(nodoReco);
					arrayDeHops[nodoReco] = arrayDeHops[nodoActual] + 1;
				}
			}

		}

		return arrayDeHops;
	}

/////////////////////////////////////////////////////////////
	public int[] dijkstra(int nodoInicio) {

		int[] distancias = new int[this.cantNodos + 1];
		List<Integer> conjuntoSolucion = new ArrayList<Integer>();
		PriorityQueue<DistanciaCosto> colaDistancias = new PriorityQueue<DistanciaCosto>(new comparaDistancias());
		int w;
		Arrays.fill(this.precedentes, nodoInicio);

		conjuntoSolucion.add(nodoInicio);
		cargarDistancias(distancias, nodoInicio);
		cargarDistanciasCola(distancias, colaDistancias, conjuntoSolucion);

		while (conjuntoSolucion.size() < this.cantNodos) {

			w = colaDistancias.poll().getNodo();
			conjuntoSolucion.add(w);

			for (int i = 1; i < distancias.length; i++) {
				if (!conjuntoSolucion.contains(i)) {
					if (distancias[i] > (distancias[w] + this.matrizAdy[w - 1][i - 1])) {
						precedentes[i] = w;
					}

					distancias[i] = Math.min(distancias[i], distancias[w] + this.matrizAdy[w - 1][i - 1]);
				}
			}

			cargarDistanciasCola(distancias, colaDistancias, conjuntoSolucion);

		}

		return distancias;
	}

	private void cargarDistanciasCola(int[] distancias, PriorityQueue<DistanciaCosto> colaDistancias,
			List<Integer> conjuntoSolucion) {
		if (!colaDistancias.isEmpty()) {
			colaDistancias.removeAll(colaDistancias);
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
		Integer[][] matrizFx = this.matrizAdy;

		for (int i = 0; i < matrizFx.length; i++) {
			matrizFx[i][i] = 0;
		}

		for (int k = 0; k < matrizFx.length; k++) {
			for (int i = 0; i < matrizFx.length; i++) {
				for (int j = 0; j < matrizFx[i].length; j++) {
					matrizFx[i][j] = Math.min(matrizFx[i][j], matrizFx[i][k] + matrizFx[k][j]);
				}
			}
		}

		return matrizFx;
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

	private void convierteMatrizBooleana(boolean[][] matrizAx) {
		for (int i = 0; i < matrizAx.length; i++) {
			for (int j = 0; j < matrizAx[i].length; j++) {
				if (i == j)
					matrizAx[i][j] = false;
				else if (this.matrizAdy[i][j] != 800)
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
