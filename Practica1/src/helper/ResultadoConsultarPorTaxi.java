/**
 * Resultado de la consulta de propietario y conductor de taxi
 */
package helper;

import java.util.List;

import domain.component.Identificacion;

/**
 * @author Arbey Villegas
 *
 */
public class ResultadoConsultarPorTaxi extends ResultadoOperacion{
	/**
	 * Identificaciones que contiene propietario y conductor de taxi
	 */
	private List<Identificacion> identificacones;

	/**
	 * @return identificaciones que contiene propietario y conductor de taxi
	 */
	public List<Identificacion> getIdentificacones() {
		return identificacones;
	}

	/**
	 * @param identificaciones que contiene propietario y conductor de taxi
	 */
	public void setIdentificacones(List<Identificacion> identificacones) {
		this.identificacones = identificacones;
	}
}
