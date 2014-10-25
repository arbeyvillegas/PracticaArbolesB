/**
 * Determina la configuracion de los campos de los archivos planos
 */
package dataAccess;

/**
 * @author Arbey Villegas Carvajal
 *
 */
public class ConfigCampo {
	/**
	 * Posición donde comienza el campo
	 */
	private int posicion;
	/**
	 * Longitud máxima del campo
	 */
	private int longitud;
	/**
	 * Constructor de la clase
	 * @param posicion Posición de comienzo del campo dentro de la linea
	 * @param longitud Longitud maxima que permite el campo
	 */
	public ConfigCampo(int posicion,int longitud) {
		this.posicion=posicion;
		this.longitud=longitud;
	}
	
	/**
	 * Posición donde comienza el campo
	 */
	public int getPosicion() {
		return this.posicion;
	}
	/**
	 * Longitud máxima del campo
	 */
	public int getLongitud() {
		return this.longitud;
	}
}
