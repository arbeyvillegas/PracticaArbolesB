/**
 * ViewModel que representa los datos de lista desplegable
 */
package viewModel;

/**
 * @author Arbey Villegas
 *
 */
public class CodigoValorViewModel {
	/**
	 * Codigo del ViewModel
	 */
	private String codigo;
	/**
	 * Valor del ViewModel
	 */
	private String valor;
	/**
	 * @return codigo del ViewModel
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo del ViewModel
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return valor del ViewModel
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor del ViewModel
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
}
