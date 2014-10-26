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
	 * Apuntador de ubicación del registro cuando se guarda
	 */
	private long filePointer;
	
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
	
	public long getFilePointer() {
		return this.filePointer;
	}
	
	/**
	 * @param mensaje resultante de la operacion
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void setFilePointer(long filePointer) {
		this.filePointer=filePointer;
	}
}