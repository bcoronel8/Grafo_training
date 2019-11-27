
public class Arista {

	int nodoOrigen;
	int nodoDestino;
	int peso;

	public Arista(int nodoOrigen, int nodoDestino, int peso) {
		super();
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.peso = peso;
	}

	public Arista() {

		this.nodoOrigen = 0;
		this.nodoDestino = 0;
		this.peso = 0;
	}

	public int getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoOrigen(int nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	public int getNodoDestino() {
		return nodoDestino;
	}

	public void setNodoDestino(int nodoDestino) {
		this.nodoDestino = nodoDestino;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

}
