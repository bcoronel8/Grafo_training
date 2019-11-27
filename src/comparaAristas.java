import java.util.Comparator;

public class comparaAristas implements Comparator<Arista> {

	@Override
	public int compare(Arista arg0, Arista arg1) {
		return (int) (arg0.getPeso() - arg1.getPeso());
	}

}
