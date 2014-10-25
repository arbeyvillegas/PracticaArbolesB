/**
 * Super clase para la capa de logica de dominio
 */
package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import helper.ResultadoOperacion;
import dataAccess.DataSource;
import domain.component.BaseComponent;

/**
 * @author Arbey Villegas Carvajal
 *
 */
abstract class BaseLogic {
	/**
	 * Ruta de la carpeta donde se encuentran los archivos
	 */
	static String rutaCarpeta;
	/**
	 * Objeto para accesar a los datos
	 */
	DataSource dataSource;
	/**
	 * Nombre del archivo
	 */
	String nombreArchivo;
	
	/**
	 * Inicia y configura la fuente de datos
	 */
	abstract void iniciarDataSource();
	/**
	 * Guardar un registro en la fuente de datos
	 * @param entidad Entidad que se va a guardar
	 * @return Resulta de la operacion de guardado
	 */
	abstract ResultadoOperacion guardar(BaseComponent entidad);
	
	/**
	 * Consultar todas las entidades de la fuente de datos
	 * @return Listado con diccionario de todos los registro de la fuente de datos
	 * @throws IOException Excepcion de entrada/salida
	 */
	List<Map<String,String>> consultar() throws IOException{
		this.iniciarDataSource();
		List<Map<String,String>> resultado=new ArrayList<Map<String,String>>();
		try {
			this.dataSource.abrirConexion(false);
			resultado=this.dataSource.buscarEntidades();
		} finally{
			this.dataSource.cerrarConexion();
		}
		return resultado;
	}
}
