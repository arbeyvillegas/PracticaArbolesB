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
	 * Posici�n donde comienza el campo
	 */
	private int posicion;
	/**
	 * Longitud m�xima del campo
	 */
	private int longitud;
	/**
	 * Constructor de la clase
	 * @param posicion Posici�n de comienzo del campo dentro de la linea
	 * @param longitud Longitud maxima que permite el campo
	 */
	public ConfigCampo(int posicion,int longitud) {
		this.posicion=posicion;
		this.longitud=longitud;
	}
	
	/**
	 * Posici�n donde comienza el campo
	 */
	public int getPosicion() {
		return this.posicion;
	}
	/**
	 * Longitud m�xima del campo
	 */
	public int getLongitud() {
		return this.longitud;
	}
}
