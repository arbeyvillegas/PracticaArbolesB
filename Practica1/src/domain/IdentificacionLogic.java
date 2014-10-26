/**
 * Logica de dominio para identificaciones
 */
package domain;

import helper.ResultadoOperacion;

import java.io.IOException;
import java.util.Map;

import dataAccess.DataSource;
import domain.component.BaseComponent;
import domain.component.Identificacion;

/**
 * @author Arbey Villegas
 *
 */
class IdentificacionLogic extends BaseLogic {
	/**
	 * Nombre de campo cedula
	 */
	static String CampoCedula="cedula";
	/**
	 * Nombre de campo nombre
	 */
	static String CampoNombre="nombre";
	
	/**
	 * Constructor de la clase
	 */
	IdentificacionLogic(){
		this.nombreArchivo="identificaciones.txt";
	}
	
		ResultadoOperacion guardar(BaseComponent entidad) {
		Identificacion identificacion=(Identificacion)entidad;
		this.iniciarDataSource();
		ResultadoOperacion resultado=new ResultadoOperacion();
		resultado.setMensaje("Registro guardado satisfactoriamente");
		try {
			this.dataSource.abrirConexion();
			this.dataSource.insertar(IdentificacionLogic.CampoCedula,String.valueOf(identificacion.getCodigo()));
			this.dataSource.insertar(IdentificacionLogic.CampoNombre,identificacion.getNombre());
			resultado.setFilePointer(this.dataSource.confirmarTransaccion());			
		} catch (IOException e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}catch(Exception e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}finally {
			this.dataSource.cerrarConexion();
		}
		return resultado;
	}
	
	/**
	 * consultar los datos de identificaciones por numero de identificacion
	 * @param numIdentificacion Numero de identificacion a consultar
	 * @param tipo Tipo de identificacion, propietario o conductor
	 * @return Entidad de tipo Identificacion
	 * @throws IOException Excepcion de entrada/salida
	 */
	Identificacion consultar(String numIdentificacion,String tipo) throws IOException {
		Identificacion identificacion=null;
		this.iniciarDataSource();
		try {
			this.dataSource.abrirConexion(false);
			Map<String,String> resultado=this.dataSource.buscarEntidad(IdentificacionLogic.CampoCedula, numIdentificacion);
			if(resultado!=null) {
				identificacion=construirIdentificacion(resultado, tipo);
			}
		}finally {
			this.dataSource.cerrarConexion();
		}
		return identificacion;
	}
	
	
	/**
	 * Construir una entidad de tipo Identificacion
	 * @param mapa Diccionario con los datos para construir la entidad
	 * @param tipo Tipo de entidad, conductor o propietario
	 * @return Entidad tipo Identificacion
	 */
	private Identificacion construirIdentificacion(Map<String,String> mapa,String tipo) {
		Identificacion entidad=new Identificacion();
		
		entidad.setTipo(tipo);
		int cedula=Integer.parseInt(mapa.get(IdentificacionLogic.CampoCedula));
		entidad.setCodigo(cedula);
		entidad.setNombre(mapa.get(IdentificacionLogic.CampoNombre));
		
		return entidad;
	}
	
	@Override
	void iniciarDataSource() {
		if(this.dataSource==null){
			this.dataSource=new DataSource(IdentificacionLogic.rutaCarpeta,this.nombreArchivo);
			this.dataSource.insertarOpcionConfig("cedula", 0, 8);
			this.dataSource.insertarOpcionConfig("nombre", 8, 30);
		}
	}
}
