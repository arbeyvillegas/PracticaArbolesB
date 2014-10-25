/**
 * Resulta de la consulta de talleres
 */
package helper;

import java.util.ArrayList;
import java.util.List;

import viewModel.TallerViewModel;

/**
 * @author Arbey Villegas Carvajal
 *
 */
public class ResultadoListado2 extends ResultadoOperacion {
	/**
	 * Listado de talleres
	 */
	private List<TallerViewModel> talleres;
	/**
	 * Constructuro de la clase
	 */
	public ResultadoListado2() {
		super();
		this.setTalleres(new ArrayList<TallerViewModel>());
	}

	/**
	 * @return listado de talleres
	 */
	public List<TallerViewModel> getTalleres() {
		return talleres;
	}

	/**
	 * @param listado de talleres
	 */
	public void setTalleres(List<TallerViewModel> talleres) {
		this.talleres = talleres;
	}
}
