/**
 * 
 */
package helper;

import javax.servlet.http.HttpSession;

import domain.BTree;
import domain.BxTree;
import domain.component.BInfo;

/**
 * @author M
 *
 */
public class CacheArboles {
	public final static String TipoArbol = "TipoArbol";
	public final static String Taxi = "Taxi";
	public final static String Taller = "Taller";
	public final static String Identificacion = "Identificacion";

	private HttpSession sesion;

	public CacheArboles(HttpSession session) {
		this.sesion = session;
	}

	public static <T> void guardarCache(HttpSession sesion, String clave,
			T objeto, boolean limpiar) {
		CacheArboles cache = new CacheArboles(sesion);
		cache.guardarCache(clave, objeto);
		if (limpiar)
			cache.limpiarCache();
	}

	public static <T> T obtenerCache(HttpSession sesion, String clave) {
		CacheArboles cache = new CacheArboles(sesion);
		return cache.ObtenerCache(clave);
	}

	public static void guardarNodoArbol(HttpSession sesion, String clave,
			int codigo, long direccion) {
		CacheArboles cache = new CacheArboles(sesion);
		String tipoArbol = cache.ObtenerCache(CacheArboles.TipoArbol);
		switch (tipoArbol) {
		case "B":
			CacheArboles.guardarNodoArbolB(cache, clave, codigo, direccion);
			break;
		case "BPlus":
			CacheArboles.guardarNodoArbolBPlus(cache, clave, codigo, direccion);
			break;
		}
	}

	private static void guardarNodoArbolB(CacheArboles cache, String clave,
			int codigo, long direccion) {
		BTree<BInfo> tree = cache.ObtenerCache(clave);
		if (tree == null)
			tree = new BTree<BInfo>();
		BInfo info = new BInfo();
		info.setDireccion(direccion);
		info.setDato(codigo);
		tree.insert(info);
		cache.guardarCache(clave, tree);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void guardarNodoArbolBPlus(CacheArboles cache, String clave,
			int codigo, long direccion) {
		BxTree<BInfo,Long> tree=cache.ObtenerCache(clave);
		if(tree==null)
			tree=new BxTree(3);
		BInfo info=new BInfo();
		info.setDireccion(direccion);
		info.setDato(codigo);
		tree.insert(info, direccion);
		cache.guardarCache(clave, tree);
	}

	public static BInfo getInfo(HttpSession sesion, String clave, int codigo) {
		CacheArboles cache = new CacheArboles(sesion);
		BTree<BInfo> tree = cache.ObtenerCache(clave);
		BInfo info = null;
		if (tree != null) {
			BInfo filtro = new BInfo();
			filtro.setDato(codigo);
			info = tree.findMember(filtro);
		}
		return info;
	}

	private <T> void guardarCache(String clave, T objeto) {
		this.sesion.setAttribute(clave, objeto);
	}

	@SuppressWarnings("unchecked")
	private <T> T ObtenerCache(String clave) {
		Object objeto = this.sesion.getAttribute(clave);
		if (objeto != null)
			return (T) objeto;
		else
			return null;
	}

	private void limpiarCache() {
		this.sesion.setAttribute(CacheArboles.Taller, null);
		this.sesion.setAttribute(CacheArboles.Identificacion, null);
		this.sesion.setAttribute(CacheArboles.Taxi, null);
	}

}
