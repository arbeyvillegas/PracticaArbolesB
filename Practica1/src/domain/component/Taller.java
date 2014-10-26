/**
 * Componente de dominio que representa el Taller
 */
package domain.component;

/**
 * @author Arbey Villegas
 *
 */
public class Taller extends BaseComponent{
	/**
	 * Codigo del taller
	 */
//	public short codigo;
	/**
	 * Nombre del taller
	 */
	public String nombre;
	/**
	 * Telefono del taller
	 */
	public int telefono;
	/**
	 * Direccion del taller
	 */
	public String direccion;
	/***Propiedades***/
	/**
	 * @return codigo del taller
	 */
//	public short getCodigo() {
//		return codigo;
//	}
	/**
	 * @param codigo del taller
	 */
//	public void setCodigo(short codigo) {
//		this.codigo = codigo;
//	}
	/**
	 * @return nombre del taller
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre del taller
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return telefono del taller
	 */
	public int getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono del taller
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return direccion del taller
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion del taller
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	
}