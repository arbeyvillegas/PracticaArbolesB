/**
 * Entidad que controla la logica de dominio de talleres
 */
package domain;

import helper.ResultadoOperacion;

import java.io.IOException;
import java.util.Map;

import dataAccess.DataSource;
import domain.component.BaseComponent;
import domain.component.Taller;

/**
 * @author Arbey Villegas
 *  
 */
class TallerLogic extends BaseLogic{
	/**
	 * Nombre del campo codigo
	 */
	static String CampoCodigo="codigo";
	/**
	 * Nombre del campo nombre
	 */
	static String CampoNombre="nombre";
	/**
	 * Nombre del campo telefono
	 */
	static String CampoTelefono="telefono";
	/**
	 * Nombre del campo direccion
	 */
	static String CampoDireccion="direccion";
	/**
	 * Constructor de la clase
	 */
	TallerLogic(){
		this.nombreArchivo="taller.txt";
	}
	
	
	@Override
	ResultadoOperacion guardar(BaseComponent entidad) {
		Taller taller=(Taller)entidad;
		this.iniciarDataSource();
		ResultadoOperacion resultado=new ResultadoOperacion();
		resultado.setMensaje("Registro guardado satisfactoriamente");
		try {
			this.dataSource.abrirConexion();
			this.dataSource.insertar(TallerLogic.CampoCodigo,String.valueOf(taller.getCodigo()));
			this.dataSource.insertar(TallerLogic.CampoNombre,taller.getNombre());
			this.dataSource.insertar(TallerLogic.CampoTelefono,String.valueOf(taller.getTelefono()));
			this.dataSource.insertar(TallerLogic.CampoDireccion,taller.getDireccion());
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
	
	
	@Override
	void iniciarDataSource() {
		if(this.dataSource==null){
			this.dataSource=new DataSource(IdentificacionLogic.rutaCarpeta,this.nombreArchivo);
			this.dataSource.insertarOpcionConfig(TallerLogic.CampoCodigo, 0, 3);
			this.dataSource.insertarOpcionConfig(TallerLogic.CampoNombre, 3, 10);
			this.dataSource.insertarOpcionConfig(TallerLogic.CampoTelefono, 13, 7);
			this.dataSource.insertarOpcionConfig(TallerLogic.CampoDireccion, 20, 20);
		}
	}
	
	/**
	 * Construir la entidad Taller por medio de un diccionario con los datos
	 * @param valores Diccionario con los datos para contruir la entidad
	 * @return Entidad de tipo Taller
	 */
	Taller construirTaller(Map<String,String> valores) {
		Taller entidad=new Taller();
		short codigo=Short.parseShort(valores.get(TallerLogic.CampoCodigo));
		entidad.setCodigo(codigo);
		entidad.setDireccion(valores.get(TallerLogic.CampoDireccion));
		entidad.setNombre(valores.get(TallerLogic.CampoNombre));
		int telefono=Integer.parseInt(valores.get(TallerLogic.CampoTelefono));
		entidad.setTelefono(telefono);
		return entidad;
	}
}
