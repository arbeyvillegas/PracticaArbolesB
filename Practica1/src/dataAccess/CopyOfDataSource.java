/**
 * Componente de acceso a datos o a los archivos planos
 */
package dataAccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


/**
 * Clase principal para acceder a los datos
 * @author Arbey Villegas
 * @since 23/08/2014
 */
public class CopyOfDataSource {
	/**
	 * Nombre del archivo al cual se va a afectar
	 */
	private String nombreArchivo;
	/**
	 * Ruta de la carpeta
	 */
	private String rutaCarpeta;
	/**
	 * Ruta completa del archivo
	 */
	private Path rutaArchivo;
	/**
	 * Configuración de los campos
	 */
	private HashMap<String,ConfigCampo> configCampos;
	/**
	 * Buffer para escritura de archivos
	 */
	BufferedWriter bufferedWriter;
	/**
	 * Buffer de lectura de archivos
	 */
	BufferedReader bufferedReader;
	/**
	 * Determina si existe el archivo creado
	 */
	private boolean existeArchivo;
	/**
	 * Constructor de la clase
	 * @param rutaCarpeta Ruta de la carpeta
	 * @param nombreArchivo Nombre del archivo
	 */
	public CopyOfDataSource(String rutaCarpeta,String nombreArchivo) {
		this.nombreArchivo=nombreArchivo;
		this.rutaCarpeta=rutaCarpeta;
		this.existeArchivo=false;
	}
	
	
	/**
	 * Acceder al archivo respectivo
	 * @throws IOException Excepcion de entrada/salida
	 */
	public void abrirConexion() throws IOException{
		this.abrirConexion(true);
	}
	
	/**
	 * Abrir la conexion con la fuente de datos
	 * @param escritura
	 * @throws IOException Excepcion de entrada/salida
	 */
	public void abrirConexion(boolean escritura) throws IOException {
		Path path=Paths.get(this.rutaCarpeta,this.nombreArchivo);
		
		if(Files.exists(path)) {
			this.rutaArchivo=path;
			this.existeArchivo=true;
		}
		
		if(escritura) {
			if(!this.existeArchivo)
				this.rutaArchivo=Files.createFile(path);
			this.prepararInsercion();
		}else {
			this.prepararLectura();
		}
	}
	
	/**
	 * Iniciar el buffer de lectura
	 * @throws IOException Excepcion de entrada/salida
	 */
	private void prepararLectura()throws IOException {
		this.bufferedReader=Files.newBufferedReader(this.rutaArchivo);
	}
	
	/**
	 * Iniciar el buffer de escritura
	 * @throws IOException Excepcion de entrada/salida
	 */
	private void prepararInsercion() throws IOException {
		this.bufferedWriter=Files.newBufferedWriter(this.rutaArchivo,StandardOpenOption.WRITE,StandardOpenOption.APPEND);
		if(this.existeArchivo)
			this.bufferedWriter.newLine();
	}
	
	/**
	 * Insertar un el valor de algun campo de un registro
	 * @param campo Campo que se quiere escribir en el archivo
	 * @param valor Valor que se va a escribir en el archivo
	 * @throws IOException Excepcion de entrada/salida
	 */
	public void insertar(String campo,String valor) throws IOException{
		ConfigCampo itemConfig=this.configCampos.get(campo);
		this.bufferedWriter.write(StringUtils.rightPad(valor,itemConfig.getLongitud()));
	}
	
	/**
	 * Buscar un valor especifico en archivo
	 * @param campoBuscar Campo con el cual se va a filtrar
	 * @param valorBuscar Valor del campo utilizado para filtrar
	 * @param campoRetorno Nombre del campo del valor que se devolverá
	 * @return Valor del campo
	 * @throws IOException Excepcion de entrada/salida
	 */
	public String buscarValorCampo(String campoBuscar, String valorBuscar,String campoRetorno) throws IOException{
		String linea=null;
		String resultado=null;
		boolean encontro=false;
		ConfigCampo configCampoBuscar=this.configCampos.get(campoBuscar);
		ConfigCampo configCampoRetorno=this.configCampos.get(campoRetorno);
		while(!encontro && (linea=this.bufferedReader.readLine())!=null) {
			String valorTmp=linea.substring(configCampoBuscar.getPosicion(), (configCampoBuscar.getPosicion()+configCampoBuscar.getLongitud()));
			if(valorBuscar.equals(valorTmp.trim())){
				resultado=linea.substring(configCampoRetorno.getPosicion(),(configCampoRetorno.getPosicion()+configCampoRetorno.getLongitud()));
				encontro=true;
			}
		}
		if(resultado!=null) {
			resultado=resultado.trim();
		}
		return resultado;
	}
	
	/**
	 * Buscar un registro completo en el archivo
	 * @param campoBuscar Nombre de campo utilizado para filtrar
	 * @param valorBuscar Valor del campo utilizado para filtrar
	 * @return Diccionario de datos con el registro buscado
	 * @throws IOException Excepcion de entrada/salida
	 */
	public Map<String,String> buscarEntidad(String campoBuscar, String valorBuscar) throws IOException{
		String linea=null;
		Map<String,String> resultado=null;
		String resultadoString=null;
		boolean encontro=false;
		ConfigCampo configCampoBuscar=this.configCampos.get(campoBuscar);
		while(!encontro && (linea=this.bufferedReader.readLine())!=null) {
			String valorTmp=linea.substring(configCampoBuscar.getPosicion(), (configCampoBuscar.getPosicion()+configCampoBuscar.getLongitud()));
			if(valorBuscar.equals(valorTmp.trim())){
				resultadoString=linea;
				encontro=true;
			}
		}
		if(encontro) {
			resultado=construirEntidad(resultadoString);
		}
		
		return resultado;
	}
	
	/**
	 * Construir una entidad de diccionario de datos encontrados
	 * @param linea Cadena de texto que contiene los datos encontrados
	 * @return Diccionario que contiene los datos encontrados
	 */
	private Map<String,String> construirEntidad(String linea){
		Map<String,String> resultado=new HashMap<String, String>();
		
		for(Map.Entry<String, ConfigCampo> item:this.configCampos.entrySet()) {
			ConfigCampo config=item.getValue();
			String valorCampo=linea.substring(config.getPosicion(),(config.getPosicion()+config.getLongitud()));
			resultado.put(item.getKey(), valorCampo.trim());
		}
		
		return resultado;
	}
	
	/**
	 * Buscar las entidades coincidentes
	 * @param campoBuscar Nombre del campo con el que se va a buscar
	 * @param valorBuscar Valor del campo con el que se va a buscar
	 * @return Listado de diccionario de entidades coincidentes en la busqueda
	 * @throws IOException Excepcion de entrada/salida
	 */
	public List<Map<String,String>> buscarEntidades(String campoBuscar, String valorBuscar) throws IOException{
		String linea=null;
		List<Map<String,String>> resultado=new ArrayList<Map<String,String>>();
		ConfigCampo configCampoBuscar=this.configCampos.get(campoBuscar);
		while((linea=this.bufferedReader.readLine())!=null) {
			String valorTmp=linea.substring(configCampoBuscar.getPosicion(), (configCampoBuscar.getPosicion()+configCampoBuscar.getLongitud()));
			if(valorBuscar.equals(valorTmp.trim())){
				resultado.add(this.construirEntidad(linea));
			}
		}		
		return resultado;
	}
	
	/**
	 * Buscar todas las entidades existentes en un archivo
	 * @return Listado de diccionario de todos los registros del archivo
	 * @throws IOException Excepcion de entrada/salida
	 */
	public List<Map<String,String>> buscarEntidades() throws IOException{
		String linea=null;
		List<Map<String,String>> resultado=new ArrayList<Map<String,String>>();
		while((linea=this.bufferedReader.readLine())!=null) {
			resultado.add(this.construirEntidad(linea));
		}		
		return resultado;
	}
	
	/**
	 * Insertar una opcion de configuracion
	 * @param nombreCampo Nombre del campo a configurar
	 * @param posicion Posicion del campo dentro del registro
	 * @param longitud Longitud del campo dentro del registro
	 */
	public void insertarOpcionConfig(String nombreCampo,int posicion,int longitud) {
		if(this.configCampos==null) {
			this.configCampos=new HashMap<String,ConfigCampo>();
		}
		this.configCampos.put(nombreCampo, new ConfigCampo(posicion, longitud));
	}
	
	/**
	 * Confirmar el guardado del registro
	 */
	public void confirmarTransaccion() {
		if(this.bufferedWriter!=null) {
			try {
				this.bufferedWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Cerrar la conexion del buffer de escritura o lectura
	 */
	public void cerrarConexion(){
		if(this.bufferedWriter!=null) {
			try {
				this.bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(this.bufferedReader!=null) {
			try {
				this.bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}