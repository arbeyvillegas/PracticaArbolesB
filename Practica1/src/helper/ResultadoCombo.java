/**
 * Resulta de la consulta de listados para listas desplegables
 */
package helper;

import java.util.ArrayList;
import java.util.List;

import viewModel.CodigoValorViewModel;

/**
 * @author Arbey Villegas
 *
 */
public class ResultadoCombo extends ResultadoOperacion {
	/**
	 * Listado para llenar la lista desplegable
	 */
	private List<CodigoValorViewModel> listadoCombo;

	/**
	 * Contructor de la clase
	 */
	public ResultadoCombo() {
		super();
		this.listadoCombo=new ArrayList<CodigoValorViewModel>();
	}
	/**
	 * @return listado para llenar la lista desplegable
	 */
	public List<CodigoValorViewModel> getListadoCombo() {
		return listadoCombo;
	}

	/**
	 * @param listado para llenar la lista desplegable
	 */
	public void setListadoCombo(List<CodigoValorViewModel> listadoCombo) {
		this.listadoCombo = listadoCombo;
	}
}
