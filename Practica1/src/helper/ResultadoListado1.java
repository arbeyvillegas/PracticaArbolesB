/**
 * Resultado de la consulta de taxis
 */
package helper;

import java.util.List;

import viewModel.TaxiViewModel;

/**
 * @author Arbey Villegas
 *
 */
public class ResultadoListado1 extends ResultadoOperacion {
	/**
	 * Listado de taxis
	 */
	private List<TaxiViewModel> taxis;

	/**
	 * @return Listado de taxis
	 */
	public List<TaxiViewModel> getTaxis() {
		return taxis;
	}

	/**
	 * @param listado de taxis
	 */
	public void setTaxis(List<TaxiViewModel> taxis) {
		this.taxis = taxis;
	}
}
