/**
 * Resultado de la consulta de taxis por filtro
 */
package helper;

import java.util.List;

import domain.component.Taxi;

/**
 * @author Arbey Villegas
 *
 */
public class ResultadoConsultarTaxiPorOtro extends ResultadoOperacion {
	/**
	 * Listado de taxis resultado de la consulta
	 */
	private List<Taxi> taxis;

	/**
	 * @return listado de taxis resultado de la consulta
	 */
	public List<Taxi> getTaxis() {
		return taxis;
	}

	/**
	 * @param listado de taxis resultado de la consulta
	 */
	public void setTaxis(List<Taxi> taxis) {
		this.taxis = taxis;
	}
}
