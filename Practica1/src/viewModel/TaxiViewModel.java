/**
 * ViewModel que representa los datos del taxi
 */
package viewModel;

/**
 * @author Arbey Villegas
 *
 */
public class TaxiViewModel {
	/**
	 * Numero del taxi
	 */
	private int numeroTaxi;
	/**
	 * Placa del taxi
	 */
	private String placa;
	/**
	 * Nombre del propietario del taxi
	 */
	private String nombrePropietario;
	/**
	 * Nombre del conductor del taxi
	 */
	private String nombreConductor;
	/**
	 * @return numero del taxi
	 */
	public int getNumeroTaxi() {
		return numeroTaxi;
	}
	/**
	 * @param numero del taxi
	 */
	public void setNumeroTaxi(int numeroTaxi) {
		this.numeroTaxi = numeroTaxi;
	}
	/**
	 * @return placa del taxi
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa del taxi
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return nombre del propietario del taxi
	 */
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	/**
	 * @param nombre del propietario del taxi
	 */
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	/**
	 * @return nombre del conductor del taxi
	 */
	public String getNombreConductor() {
		return nombreConductor;
	}
	/**
	 * @param nombre del conductor del taxi
	 */
	public void setNombreConductor(String nombreConductor) {
		this.nombreConductor = nombreConductor;
	}
}
