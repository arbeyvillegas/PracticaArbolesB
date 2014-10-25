/**
 * Resultado general de las operaciones de consulta e insercion
 */
package helper;

/**
 * @author Arbey Villegas
 *
 */
public class ResultadoOperacion {
	/**
	 * Resultado de la ejecucion
	 */
	private boolean resultado;
	/**
	 * Mensaje resultante de la ejecucion
	 */
	private String mensaje;
	
	/**
	 * Constructor de la clase
	 */
	public ResultadoOperacion() {
		this.resultado=true;
	}
	/**
	 * @return resultado de la operacion
	 */
	public boolean isResultado() {
		return resultado;
	}
	/**
	 * @param resultado de la operacion
	 */
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	/**
	 * @return mensaje resultante de la operacion
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje resultante de la operacion
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}