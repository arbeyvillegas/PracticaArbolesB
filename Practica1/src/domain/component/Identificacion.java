package domain.component;

public class Identificacion extends BaseComponent{
	
	/**
	 * Numero de cedula
	 */
//	private int cedula;
	/**
	 * Nombre de la entidad
	 */
	private String nombre;
	/**
	 * Tipo para la identificacion, ya sea propietario o conductor
	 */
	private String tipo;
	
	
	
	/**
	 * @return Numero de cedula
	 */
//	public int getCedula() {
//		return cedula;
//	}
	/**
	 * @param Numero de cedula
	 */
//	public void setCedula(int cedula) {
//		this.cedula = cedula;
//	}
	/**
	 * @return Nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param Nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return tipo de identificacion
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param Tipo de identificacion
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
