import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.management.Query;

public class Grafo {

	Double[][] matrizAdy;
	List<Arista> [] listaAdy;
	int cantNodos;
	
	
	public Grafo(int cantNodos) {
		
		this.cantNodos= cantNodos;
		
		matrizAdy= new Double[cantNodos][cantNodos];
//		for (int i = 0; i < cantNodos; i++) {
//			matrizAdy[i] = new Double[i+1];
//		}
		
		
		listaAdy= new List[cantNodos];
		for (int i = 0; i < cantNodos; i++) {
			listaAdy[i]= new LinkedList<Arista>();
		}
	}
	
	public void cargarMatrizArchivo(String pathFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(pathFile));
		scanner.useLocale(Locale.ENGLISH);
		int cantElementos= scanner.nextInt();
		for(int i=0; i<cantElementos; i++) {
			this.matrizAdy[scanner.nextInt()][scanner.nextInt()]= 1.0;
		}
		scanner.close();
	}
	
	public void recorrido_DFS(int nodoActual) {
		
		int i=0;
		boolean encontrado;
		Stack<Integer> pilaDeNodos= new Stack<Integer>();
		boolean[] arrayVisitados = new boolean[cantNodos];
		Arrays.fill(arrayVisitados, false);
		
		pilaDeNodos.push(nodoActual);
		arrayVisitados[nodoActual]= true;
		System.out.println("Nodo   " + nodoActual );
		
		while(!pilaDeNodos.isEmpty()) {
			encontrado= false;
			nodoActual= pilaDeNodos.peek();
			
			while(i< this.cantNodos && encontrado != true) {
				if(this.matrizAdy[nodoActual][i] != null && arrayVisitados[i] == false) {
					pilaDeNodos.push(i);
					arrayVisitados[i]= true;
					System.out.println("Nodo   " + i  );
					encontrado= true;
				}
				i++;
			}
			
			if(i< this.cantNodos)
				nodoActual= i;
			else {
				nodoActual= pilaDeNodos.pop();
				i=0;
			}
									
		}
		
	}
	
	
	public Integer[] recorrido_BFS(int nodoActual) {
	
		Queue<Integer> colaDeNodos = new LinkedList<Integer>();
		Integer[] arrayDeHops= new Integer[this.cantNodos];
		Arrays.fill(arrayDeHops, 5000);

		int distancia= 1;
		
		colaDeNodos.add(nodoActual);
		arrayDeHops[nodoActual]= 0;
		//System.out.println("Nodo   " + nodoActual);
		
		while(!colaDeNodos.isEmpty()) {
			
			nodoActual= colaDeNodos.peek();
			System.out.println("Nodo   " + nodoActual);
			for(int i=0; i<cantNodos; i++) {
				if(this.matrizAdy[nodoActual][i] != null && arrayDeHops[i] == 5000) {
					colaDeNodos.add(i);
					arrayDeHops[i]= distancia;
				}
			}
			distancia++;
			
			colaDeNodos.poll();
		}
	
		return arrayDeHops;
	}
	
	
}
