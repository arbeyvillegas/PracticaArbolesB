/**
 * Logica de dominio de taxis
 */
package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import helper.ResultadoOperacion;
import dataAccess.DataSource;
import domain.component.BaseComponent;
import domain.component.Taxi;

/**
 * @author Arbey Villegas
 *
 */
class TaxiLogic extends BaseLogic {

	/**
	 * Nombre del campo numero
	 */
	static String CampoNumero="numero";
	/**
	 * Nombre del campo placa
	 */
	static String CampoPlaca="placa";
	/**
	 * Nombre del campo modelo
	 */
	static String CampoModelo="modelo";
	/**
	 * Nombre del campo marca
	 */
	static String CampoMarca="marca";
	/**
	 * Nombre del campo propietario
	 */
	static String CampoPropietario="propietario";
	/**
	 * Nombre del campo conductor
	 */
	static String CampoConductor="conductor";
	/**
	 * Nombre del campo taller
	 */
	static String CampoTaller="taller";
	
	/**
	 * Constructor de la clase
	 */
	TaxiLogic(){
		super();
		this.nombreArchivo="taxi.txt";
	}
	
	@Override
	void iniciarDataSource() {
		if(this.dataSource==null){
			this.dataSource=new DataSource(IdentificacionLogic.rutaCarpeta,this.nombreArchivo);
			this.dataSource.insertarOpcionConfig(TaxiLogic.CampoNumero, 0, 3);
			this.dataSource.insertarOpcionConfig(TaxiLogic.CampoPlaca, 3, 7);
			this.dataSource.insertarOpcionConfig(TaxiLogic.CampoModelo, 10, 4);
			this.dataSource.insertarOpcionConfig(TaxiLogic.CampoMarca, 14, 20);
			this.dataSource.insertarOpcionConfig(TaxiLogic.CampoPropietario, 34, 8);
			this.dataSource.insertarOpcionConfig(TaxiLogic.CampoConductor, 42, 8);
			this.dataSource.insertarOpcionConfig(TaxiLogic.CampoTaller, 50, 3);
		}
	}

	@Override
	ResultadoOperacion guardar(BaseComponent entidad) {
		Taxi taxi=(Taxi)entidad;
		this.iniciarDataSource();
		ResultadoOperacion resultado=new ResultadoOperacion();
		resultado.setMensaje("Registro guardado satisfactoriamente");
		try {
			this.dataSource.abrirConexion();
			this.dataSource.insertar(TaxiLogic.CampoNumero,String.valueOf(taxi.getCodigo()));
			this.dataSource.insertar(TaxiLogic.CampoPlaca,taxi.getPlaca());
			this.dataSource.insertar(TaxiLogic.CampoModelo,String.valueOf(taxi.getModelo()));
			this.dataSource.insertar(TaxiLogic.CampoMarca,taxi.getMarca());
			this.dataSource.insertar(TaxiLogic.CampoPropietario,String.valueOf(taxi.getPropietario()));
			this.dataSource.insertar(TaxiLogic.CampoConductor,String.valueOf(taxi.getConductor()));
			this.dataSource.insertar(TaxiLogic.CampoTaller,String.valueOf(taxi.getTaller()));
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
	 * Consultar el conductor del taxi
	 * @param numTaxi Numero de taxi con el cual buscar los conductores
	 * @return Codigo del conductor correspondiente al taxi
	 * @throws IOException Excepcion de entrada/salida
	 */
	String consultarConductor(String numTaxi) throws IOException {
		this.iniciarDataSource();
		String resultado=null;
		try {
			this.dataSource.abrirConexion(false);
			resultado=this.dataSource.buscarValorCampo(TaxiLogic.CampoNumero, numTaxi, TaxiLogic.CampoConductor);
		}finally {
			this.dataSource.cerrarConexion();
		}	
		return resultado;
	}
	
	/**
	 * Consultar el propietario del taxi
	 * @param numTaxi Numero de taxi para consultar
	 * @return Codigo del conductor propietario del taxi
	 * @throws IOException Excepcion de entrada/salida
	 */
	String consultarPropietario(String numTaxi)throws IOException {
		this.iniciarDataSource();
		String resultado=null;
		try {
			this.dataSource.abrirConexion(false);
			resultado=this.dataSource.buscarValorCampo(TaxiLogic.CampoNumero, numTaxi, TaxiLogic.CampoPropietario);
		}finally {
			this.dataSource.cerrarConexion();
		}	
		return resultado;
	}
	/**
	 * Consultar listado de taxis concidentes
	 * @param campo Campo sobre el cual se va a buscar el taxi
	 * @param valor Valor del campo para buscar el taxi
	 * @return Listado de entidades tipo Taxi
	 * @throws IOException Excepcion de entrada/salida
	 */
	List<Taxi> consultar(String campo,String valor) throws IOException{
		this.iniciarDataSource();
		List<Taxi> resultado=new ArrayList<Taxi>();
		try {
			this.dataSource.abrirConexion(false);
			List<Map<String,String>> entidades=this.dataSource.buscarEntidades(campo, valor);
			for(int i=0;i<entidades.size();i++) {
				resultado.add(this.construirTaxi(entidades.get(i)));	
			}
		} finally {
			this.dataSource.cerrarConexion();
		}
		
		return resultado;
	}
	
	/**
	 * Construir la entidad de taxi
	 * @param valores Valores con los cuales se construira la entidad
	 * @return Entidad de tipo Taxi
	 */
	Taxi construirTaxi(Map<String,String> valores) {
		Taxi entidad=new Taxi();
		int conductor=Integer.parseInt(valores.get(TaxiLogic.CampoConductor));
		entidad.setConductor(conductor);
		entidad.setMarca(valores.get(TaxiLogic.CampoMarca));
		short modelo=Short.parseShort(valores.get(TaxiLogic.CampoModelo));
		entidad.setModelo(modelo);
		short numero=Short.parseShort(valores.get(TaxiLogic.CampoNumero));
		entidad.setCodigo(numero);
		entidad.setPlaca(valores.get(TaxiLogic.CampoPlaca));
		int propietario=Integer.parseInt(valores.get(TaxiLogic.CampoPropietario));
		entidad.setPropietario(propietario);
		short taller=Short.parseShort(valores.get(TaxiLogic.CampoTaller));
		entidad.setTaller(taller);
		return entidad;
	}
}
