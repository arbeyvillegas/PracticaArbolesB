/**
 * Filtro de consulta
 */
package viewModel;

/**
 * @author Arbey Villegas
 *
 */
public class Filtro {
	/**
	 * Nombre del campo con el cual se filtra
	 */
	private String nombre;
	/**
	 * Valor con el cual se filtra
	 */
	private String valor;
	/**
	 * @return nombre del campo con el cual se filtra
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre del campo con el cual se filtra
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return valor con el cual se filtra
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor con el cual se filtra
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
}
