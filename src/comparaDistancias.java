import java.util.Comparator;

public class comparaDistancias implements Comparator<DistanciaCosto> {

	@Override
	public int compare(DistanciaCosto arg0, DistanciaCosto arg1) {

		return arg0.getDistancia() - arg1.getDistancia();
	}

}
