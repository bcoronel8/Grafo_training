import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * // Grafo grafito = new Grafo(3, 4); // grafito.agregarAristaMat(1, 2, 8); //
		 * grafito.agregarAristaMat(1, 3, 5); // grafito.agregarAristaMat(2, 1, 2); //
		 * grafito.agregarAristaMat(3, 2, 1); // grafito.agregarAristaMat(2, 3, 800); //
		 * grafito.agregarAristaMat(3, 1, 800);
		 * 
		 * // Integer[][] matrizFloyd = grafito.floyd(); // // for (int i = 0; i <
		 * matrizFloyd.length; i++) { // System.out.println(); // for (int j = 0; j <
		 * matrizFloyd.length; j++) { // System.out.print(" " + matrizFloyd[i][j] +
		 * " "); // } // }
		 * 
		 * // boolean[][] matrizWarshall = grafito.warshall(); // // for (int i = 0; i <
		 * matrizWarshall.length; i++) { // System.out.println(); // for (int j = 0; j <
		 * matrizWarshall.length; j++) { // System.out.print(" " + matrizWarshall[i][j]
		 * + " "); // } // }
		 */

		/*
		 * grafito.cargarMatrizArchivo("grafo.in"); Integer[] hops=
		 * grafito.recorrido_BFS(0); for (int i = 0; i < hops.length; i++) {
		 * System.out.print(hops[i] + "\t\t"); }
		 */

//		Grafo grafito = new Grafo(5, 7);
//		grafito.agregarAristaMat(1, 2, 10);
//		grafito.agregarAristaMat(1, 4, 30);
//		grafito.agregarAristaMat(1, 5, 100);
//		grafito.agregarAristaMat(2, 3, 50);
//		grafito.agregarAristaMat(3, 5, 10);
//		grafito.agregarAristaMat(4, 3, 20);
//		grafito.agregarAristaMat(4, 5, 60);
//
//		int[] distancias = grafito.dijkstra(1);
//		int[] prec = grafito.precedentes;
//		for (int i = 1; i < prec.length; i++) {
//			System.out.println(" " + prec[i]);
//		}

//		PrimKruskal grafito = new PrimKruskal(6, 20);
//		grafito.agregarAristaLst(new Arista(1, 3, 1));
//		grafito.agregarAristaLst(new Arista(1, 2, 6));
//		grafito.agregarAristaLst(new Arista(1, 4, 5));
//		grafito.agregarAristaLst(new Arista(2, 3, 5));
//		grafito.agregarAristaLst(new Arista(2, 5, 3));
//		grafito.agregarAristaLst(new Arista(3, 5, 6));
//		grafito.agregarAristaLst(new Arista(3, 4, 5));
//		grafito.agregarAristaLst(new Arista(3, 6, 4));
//		grafito.agregarAristaLst(new Arista(5, 6, 6));
//		grafito.agregarAristaLst(new Arista(4, 6, 2));
//		grafito.agregarAristaLst(new Arista(3, 1, 1));
//		grafito.agregarAristaLst(new Arista(2, 1, 6));
//		grafito.agregarAristaLst(new Arista(4, 1, 5));
//		grafito.agregarAristaLst(new Arista(3, 2, 5));
//		grafito.agregarAristaLst(new Arista(5, 2, 3));
//		grafito.agregarAristaLst(new Arista(5, 3, 6));
//		grafito.agregarAristaLst(new Arista(4, 3, 5));
//		grafito.agregarAristaLst(new Arista(6, 3, 4));
//		grafito.agregarAristaLst(new Arista(6, 5, 6));
//		grafito.agregarAristaLst(new Arista(6, 4, 2));
//
//		grafito.prim();
//
//		System.out.println("Costo total " + grafito.getCostoTotal());

		Grafo grafito = new Grafo(11, 12);
		grafito.agregarAristaLst(new Arista(1, 2, 0));
		grafito.agregarAristaLst(new Arista(1, 9, 0));
		grafito.agregarAristaLst(new Arista(2, 3, 0));
		grafito.agregarAristaLst(new Arista(2, 6, 0));
		grafito.agregarAristaLst(new Arista(3, 4, 0));
		grafito.agregarAristaLst(new Arista(9, 5, 0));
		grafito.agregarAristaLst(new Arista(9, 10, 0));
		grafito.agregarAristaLst(new Arista(5, 6, 0));
		grafito.agregarAristaLst(new Arista(6, 10, 0));
		grafito.agregarAristaLst(new Arista(6, 7, 0));
		grafito.agregarAristaLst(new Arista(7, 8, 0));
		grafito.agregarAristaLst(new Arista(10, 11, 0));

		Integer[] arrayHops = grafito.recorrido_BFS(1);
		for (int i = 1; i < arrayHops.length; i++)
			System.out.println(" i " + i + "  Valor " + arrayHops[i]);
//		}

	}
}
