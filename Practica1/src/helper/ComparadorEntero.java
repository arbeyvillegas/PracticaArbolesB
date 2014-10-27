/**
 * 
 */
package helper;

import java.util.Comparator;
/**
 * @author M
 *
 */
public class ComparadorEntero implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		int resultado=0;
		if(o1.equals(o2)) {
			resultado=0;
		}else if(o1<o2) {
			resultado=-1;
		}else {
			resultado=1;
		}
		return resultado;
	}

}
