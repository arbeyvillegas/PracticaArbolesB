/**
 * Componen de dominio que representa los taxis
 */
package domain.component;

/**
 * @author Arbey Villegas
 *
 */
public class Taxi extends BaseComponent{
	/**
	 * Numero del taxi
	 */
	private short numeroTaxi;
	/**
	 * Placa del taxi
	 */
	private String placa;
	/**
	 * Modelo del taxi
	 */
	private short modelo;
	/**
	 * Marca del taxi
	 */
	private String marca;
	/**
	 * Propietario del taxi
	 */
	private int propietario;
	/**
	 * Conductor del taxi
	 */
	private int conductor;
	/**
	 * Taller donde atienden el taxi
	 */
	private short taller;
	
	
	/**
	 * @return Numero del taxi
	 */
	public short getNumeroTaxi() {
		return numeroTaxi;
	}
	/**
	 * @param Numero del taxi
	 */
	public void setNumeroTaxi(short numeroTaxi) {
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
	 * @return modelo del taxi
	 */
	public short getModelo() {
		return modelo;
	}
	/**
	 * @param modelo del taxi
	 */
	public void setModelo(short modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return marca del taxi
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca del taxi
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return propietario del taxi
	 */
	public int getPropietario() {
		return propietario;
	}
	/**
	 * @param propietario del taxi
	 */
	public void setPropietario(int propietario) {
		this.propietario = propietario;
	}
	/**
	 * @return conductor del taxi
	 */
	public int getConductor() {
		return conductor;
	}
	/**
	 * @param conductor del taxi
	 */
	public void setConductor(int conductor) {
		this.conductor = conductor;
	}
	/**
	 * @return taller donde atienden al taxi
	 */
	public short getTaller() {
		return taller;
	}
	/**
	 * @param taller donde atienden al taxi
	 */
	public void setTaller(short taller) {
		this.taller = taller;
	}
	
	
}
