/**
 * ViewModel que representa los datos del taller
 */
package viewModel;

/**
 * @author Arbey Villegas
 *
 */
public class TallerViewModel {
	/**
	 * Codigo del taller
	 */
	private int codigoTaller;
	/**
	 * Nombre del taller
	 */
	private String nombreTaller;
	/**
	 * Codigos de taxis que atiende el taller
	 */
	private String codigosTaxi;
	/**
	 * @return codigo del taller
	 */
	public int getCodigoTaller() {
		return codigoTaller;
	}
	/**
	 * @param codigo del taller
	 */
	public void setCodigoTaller(int codigoTaller) {
		this.codigoTaller = codigoTaller;
	}
	/**
	 * @return nombre del taller
	 */
	public String getNombreTaller() {
		return nombreTaller;
	}
	/**
	 * @param nombre del taller
	 */
	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}
	/**
	 * @return codigos de los taxis que atiende el taller
	 */
	public String getCodigosTaxi() {
		return codigosTaxi;
	}
	/**
	 * @param codigos de los taxis que atiende el taller
	 */
	public void setCodigosTaxi(String codigosTaxi) {
		this.codigosTaxi = codigosTaxi;
	}
}
