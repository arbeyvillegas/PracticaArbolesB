/**
 * Fachada de la logica de dominio
 */
package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import viewModel.*;
import domain.component.*;
import helper.*;


/**
 * @author Arbey Villegas
 *
 */
public class Fachada {
	/**
	 * Ruta de la carpeta donde se encuentran los archivos
	 */
	public static String RutaCarpeta;
	/**
	 * Objeto que controla la logica de dominio de las identificiones
	 */
	private IdentificacionLogic identificacionLogic;
	/**
	 * Objeto que controla la logica de dominio de los talleres
	 */
	private TallerLogic tallerLogic;
	/**
	 * Objeto que controla la logica de dominio de los taxis
	 */
	private TaxiLogic taxiLogic;
	
	/**
	 * Constructor de la clase
	 */
	public Fachada () {
		identificacionLogic=new IdentificacionLogic();
		tallerLogic=new TallerLogic();
		taxiLogic=new TaxiLogic();
	}
	
	/**
	 * Guardar el objeto de identificación
	 * @param identificacion
	 * @return Resultado de la operación
	 */
	public ResultadoOperacion guardarIdentificacion(Identificacion identificacion) {
		IdentificacionLogic.rutaCarpeta=Fachada.RutaCarpeta;
		return identificacionLogic.guardar(identificacion);
	}
	/**
	 * Guardar registro de taxi
	 * @param taxi Entidad que contiene los datos de taxi
	 * @return Resultado de la operacion de guardado
	 */
	public ResultadoOperacion guardarTaxi(Taxi taxi) {
		TaxiLogic.rutaCarpeta=Fachada.RutaCarpeta;
		return taxiLogic.guardar(taxi);
	}
	
	/**
	 * Guardar registro de taller
	 * @param taller Entidad que contiene los datos de taller
	 * @return Resultado de la operación de guardado
	 */
	public ResultadoOperacion guardarTaller(Taller taller) {
		TallerLogic.rutaCarpeta=Fachada.RutaCarpeta;
		return tallerLogic.guardar(taller);
	}
	
	/**
	 * Consultar identificaciones segun taxi
	 * @param numTaxi Numero de taxi de datos a consultar
	 * @return Resultado de la operacion de consulta
	 */
	public ResultadoConsultarPorTaxi consultarPorTaxi(String numTaxi) {
		TallerLogic.rutaCarpeta=Fachada.RutaCarpeta;
		ResultadoConsultarPorTaxi resultado=new ResultadoConsultarPorTaxi();
		try {
			List<Identificacion> identificaciones=new ArrayList<Identificacion>();
			String codigoConductor=this.taxiLogic.consultarConductor(numTaxi);
			String codigoPropietario=this.taxiLogic.consultarPropietario(numTaxi);
			if(codigoConductor!=null) {
				Identificacion conductor=identificacionLogic.consultar(codigoConductor, "Conductor");
				if(conductor!=null) {
					identificaciones.add(conductor);
				}
			}
			if(codigoPropietario!=null) {
				Identificacion propietario=identificacionLogic.consultar(codigoPropietario, "Propietario");
				if(propietario!=null) {
					identificaciones.add(propietario);
				}
			}
			
			if(identificaciones.size()>0) {
				resultado.setIdentificacones(identificaciones);
			}else {
				resultado.setMensaje("No se encontró la información");
				resultado.setResultado(false);
			}
		} catch (IOException e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}catch(Exception e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}
		return resultado;
	}
	
	/**
	 * Consultar datos de taxi por filtro especifico
	 * @param filtro Filtro para consultar los datos de taxi
	 * @return Resultado de la operacion de consulta
	 */
	public ResultadoConsultarTaxiPorOtro consultarTaxiPorOtro(Filtro filtro) {
		TallerLogic.rutaCarpeta=Fachada.RutaCarpeta;
		ResultadoConsultarTaxiPorOtro resultado=new ResultadoConsultarTaxiPorOtro();
		String campo=null;
		switch(filtro.getNombre()) {
			case "propietario":
				campo=TaxiLogic.CampoPropietario;
				break;
			case "conductor":
				campo=TaxiLogic.CampoConductor;
				break;
			case "taller":
				campo=TaxiLogic.CampoTaller;
				break;
		}
		try {
			resultado.setTaxis(this.taxiLogic.consultar(campo, filtro.getValor()));
		} catch (IOException e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}catch (Exception e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}
		return resultado;
	}
	
	/**
	 * Consultar el listado completo de taxis
	 * @return Resultado de la operacion de consulta
	 */
	public ResultadoListado1 consultarListado1() {
		BaseLogic.rutaCarpeta=Fachada.RutaCarpeta;
		ResultadoListado1 resultado=new ResultadoListado1();
		try {
			List<Map<String,String>> taxis=this.taxiLogic.consultar();
			resultado.setTaxis(new ArrayList<TaxiViewModel>());
			for(int i=0;i<taxis.size();i++) {
				Taxi taxi=this.taxiLogic.construirTaxi(taxis.get(i));
				Identificacion conductor=this.identificacionLogic.consultar(String.valueOf(taxi.getConductor()), "Conductor");
				Identificacion propietario=this.identificacionLogic.consultar(String.valueOf(taxi.getPropietario()), "Propietario");
				
				//establecer los valores
				TaxiViewModel taxiViewModel=new TaxiViewModel();
				taxiViewModel.setNumeroTaxi(taxi.getNumeroTaxi());
				taxiViewModel.setPlaca(taxi.getPlaca());
				if(conductor!=null)
					taxiViewModel.setNombreConductor(conductor.getNombre());
				if(propietario!=null)
					taxiViewModel.setNombrePropietario(propietario.getNombre());
				resultado.getTaxis().add(taxiViewModel);
			}			
		} catch (IOException e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		} catch (Exception e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}
		return resultado;
	}
	
	/**
	 * Consultar el listado de talleres
	 * @return Resultado de la operacion de consulta
	 */
	public ResultadoListado2 consultarListado2() {
		BaseLogic.rutaCarpeta=Fachada.RutaCarpeta;
		ResultadoListado2 resultado=new ResultadoListado2();
		try {
			List<Map<String,String>> talleres=this.tallerLogic.consultar();
			for(int i=0;i<talleres.size();i++) {
				Taller taller=this.tallerLogic.construirTaller(talleres.get(i));
				List<Taxi> taxis=this.taxiLogic.consultar(TaxiLogic.CampoTaller,String.valueOf(taller.getCodigo()));
				
				
				//establecer los valores
				TallerViewModel tallerViewModel=new TallerViewModel();
				tallerViewModel.setCodigoTaller(taller.getCodigo());
				tallerViewModel.setNombreTaller(taller.getNombre());
				StringBuffer buffer=new StringBuffer();
				for(int j=0;j<taxis.size();j++) {
					if(j>0)
						buffer.append(",");
					buffer.append(taxis.get(j).getNumeroTaxi());
				}
				tallerViewModel.setCodigosTaxi(buffer.toString());
				resultado.getTalleres().add(tallerViewModel);
			}			
		} catch (IOException e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		} catch (Exception e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}
		return resultado;
	}
	
	/**
	 * Consultar listado de identificaciones para llenar lista desplegable
	 * @return Resultado de la operacion de consulta
	 */
	public ResultadoCombo consultarComboIdentificaciones() {
		BaseLogic.rutaCarpeta=Fachada.RutaCarpeta;
		ResultadoCombo resultado=new ResultadoCombo();
		
		try {
			List<Map<String,String>> identificaciones=this.identificacionLogic.consultar();
			for(int i=0;i<identificaciones.size();i++) {
				Map<String,String> codigoValorMap=identificaciones.get(i);
				CodigoValorViewModel codigosValores=new CodigoValorViewModel();
				codigosValores.setCodigo(codigoValorMap.get(IdentificacionLogic.CampoCedula));
				codigosValores.setValor(codigoValorMap.get(IdentificacionLogic.CampoNombre));
				resultado.getListadoCombo().add(codigosValores);
			}
		} catch (IOException e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		} catch (Exception e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}
		return resultado;
	}
	
	
	/**
	 * Consulta de listado de talleres para llenar lista desplegable
	 * @return Resultado de la operacion de consulta
	 */
	public ResultadoCombo consultarComboTalleres() {
		BaseLogic.rutaCarpeta=Fachada.RutaCarpeta;
		ResultadoCombo resultado=new ResultadoCombo();
		
		try {
			List<Map<String,String>> talleres=this.tallerLogic.consultar();
			for(int i=0;i<talleres.size();i++) {
				Map<String,String> codigoValorMap=talleres.get(i);
				CodigoValorViewModel codigosValores=new CodigoValorViewModel();
				codigosValores.setCodigo(codigoValorMap.get(TallerLogic.CampoCodigo));
				codigosValores.setValor(codigoValorMap.get(TallerLogic.CampoNombre));
				resultado.getListadoCombo().add(codigosValores);
			}
		} catch (IOException e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		} catch (Exception e) {
			resultado.setResultado(false);
			resultado.setMensaje(e.getMessage());
		}
		return resultado;
	}
}
