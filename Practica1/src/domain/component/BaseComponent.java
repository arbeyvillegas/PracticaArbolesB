/**
 * Super clase de los componentes del dominio
 */
package domain.component;

/**
 * @author Arbey Villegas
 *
 */
public abstract class BaseComponent {
	int codigo;
	
	public void setCodigo(int codigo) {
		this.codigo=codigo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
}
	
