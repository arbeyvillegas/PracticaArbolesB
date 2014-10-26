/**
 * 
 */
package domain.component;

/**
 * @author M
 * @param <T>
 *
 */
public class BInfo implements Comparable<BInfo> {
	private long direccion;
	private int dato;
	
	public void setDireccion(long direccion) {
		this.direccion=direccion;
	}
	
	public void setDato(int dato) {
		this.dato=dato;
	}
	
	public long getDireccion() {
		return direccion;
	}
	
	public int getDato() {
		return dato;
	}

	@Override
	public int compareTo(BInfo o) {
		
		int resultado=0;
		if(this.dato==o.dato)
			resultado=0;
		else if(this.dato<o.dato)
			resultado=-1;
		else
			resultado=1;
		return resultado;
	}
}
