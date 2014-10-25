/**
 * Controladora de la practica 1
 */
package controller;

import java.util.LinkedHashMap;
import java.util.Map;

import helper.*;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import viewModel.Filtro;
import domain.*;
import domain.component.*;

/**
 * @author Arbey Villegas
 *
 */
@Controller
public class Practica1Controller implements ServletContextAware {
	/**
	 * Contexto del servlet
	 */
	private ServletContext servletContext;
	/**
	 * Llamado a la vista inicial
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value="/")
	public String index(Model model) {
		return "index";
	}
	/**
	 * Llamado a la vista taxi
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value="/taxi")
	public String taxi(Model model) {
		String rutaCarpeta=servletContext.getInitParameter("RutaCarpeta");
		Fachada.RutaCarpeta=rutaCarpeta;
		Fachada fachada=new Fachada();
		Taxi taxi=new Taxi();
		model.addAttribute("taxi", taxi);
		ResultadoCombo resultado1=fachada.consultarComboIdentificaciones();
		model.addAttribute("identificaciones", resultado1.getListadoCombo());
		ResultadoCombo resultado2=fachada.consultarComboTalleres();
		model.addAttribute("talleres", resultado2.getListadoCombo());
		return "taxi";
	}
	/**
	 * Llamado a la vista propietario
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value="/identificacion")
	public String identificacion(Model model) {
		Identificacion identificacion=new Identificacion();
		model.addAttribute("identificacion", identificacion);
		return "identificacion";
	}
	
	/**
	 * Llamado a la vista consultar
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value="/consultar")
	public String consultar(Model model) {
		Filtro filtro=new Filtro();
		model.addAttribute("filtro", filtro);
		
		Map<String,String> opciones = new LinkedHashMap<String,String>();
		opciones.put("taxi", "Por taxi");
		opciones.put("propietario", "Por propietario");
		opciones.put("conductor", "Por conductor");
		opciones.put("taller", "Por taller");
		model.addAttribute("filtroList", opciones);
		
		return "consultar";
	}
	/**
	 * Llamado a la vista taller
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value="/taller")
	public String taller(Model model) {
		Taller taller=new Taller();
		model.addAttribute("taller", taller);
		return "taller";
	}
	/**
	 * Llamado al guardado de los datos del taxi
	 * @param taxi Datos del guardado
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value = "/insertarTaxi", method = RequestMethod.POST)
    public String insertarTaxi(@ModelAttribute("taxi") Taxi taxi, Model model) {
		String rutaCarpeta=servletContext.getInitParameter("RutaCarpeta");
		Fachada.RutaCarpeta=rutaCarpeta;
		Fachada fachada=new Fachada();
		ResultadoOperacion resultado=fachada.guardarTaxi(taxi);
        model.addAttribute("message", resultado.getMensaje());
        return "resultadoInsercion";
    }
	
	/**
	 * Guardar los datos de identificacion
	 * @param identificacion Datos a guardar
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value = "/insertarIdentificacion", method = RequestMethod.POST)
    public String insertarIdentificacion(@ModelAttribute("identificacion") Identificacion identificacion, Model model) {
		String rutaCarpeta=servletContext.getInitParameter("RutaCarpeta");
		Fachada.RutaCarpeta=rutaCarpeta;
		Fachada fachada=new Fachada();
		ResultadoOperacion resultado=fachada.guardarIdentificacion(identificacion);
        model.addAttribute("message", resultado.getMensaje());
        return "resultadoInsercion";
    }
	
	/**
	 * Guardar los datos de taller
	 * @param taller Datos a guardar
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value = "/insertarTaller", method = RequestMethod.POST)
    public String insertarTaller(@ModelAttribute("taller") Taller taller, Model model) {
		String rutaCarpeta=servletContext.getInitParameter("RutaCarpeta");
		Fachada.RutaCarpeta=rutaCarpeta;
		Fachada fachada=new Fachada();
		ResultadoOperacion resultado=fachada.guardarTaller(taller);
        model.addAttribute("message", resultado.getMensaje());
        return "resultadoInsercion";
    }
	/**
	 * Realizar consultas por filtro especifico
	 * @param filtro Filtro de consulta
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value = "/consultarPorFiltro", method = RequestMethod.POST)
	public String consultarPorFiltro(@ModelAttribute("filtro") Filtro filtro, Model model) {
		String rutaCarpeta=servletContext.getInitParameter("RutaCarpeta");
		Fachada.RutaCarpeta=rutaCarpeta;
		Fachada fachada=new Fachada();
		String view="";
		ResultadoOperacion resultado=null;
		switch(filtro.getNombre()) {
			case "taxi":
				view="resultadoPorTaxi";
				resultado=fachada.consultarPorTaxi(filtro.getValor());
				model.addAttribute("message", resultado.getMensaje());
				model.addAttribute("taxi", filtro.getValor());
				model.addAttribute("list", ((ResultadoConsultarPorTaxi)resultado).getIdentificacones());
				break;
			default:
				view="resultadoPorOtro";
				resultado=fachada.consultarTaxiPorOtro(filtro);
				model.addAttribute("message", resultado.getMensaje());
				model.addAttribute("filtro",filtro);
				model.addAttribute("list", ((ResultadoConsultarTaxiPorOtro)resultado).getTaxis());
				break;
		}
        return view;
	}
	
	/**
	 * Realizar la consulta del listado taxis
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value="/listado1")
	public String listado1(Model model) {
		String rutaCarpeta=servletContext.getInitParameter("RutaCarpeta");
		Fachada.RutaCarpeta=rutaCarpeta;
		Fachada fachada=new Fachada();
		ResultadoListado1 resultado=fachada.consultarListado1();
		model.addAttribute("message", resultado.getMensaje());
		model.addAttribute("list", resultado.getTaxis());
		return "listado1";
	}
	
	/**
	 * Realizar la consulta del listado de talleres
	 * @param Modelo que controla los datos de vista
	 * @return Nombre de la vista
	 */
	@RequestMapping(value="/listado2")
	public String listado2(Model model) {
		String rutaCarpeta=servletContext.getInitParameter("RutaCarpeta");
		Fachada.RutaCarpeta=rutaCarpeta;
		Fachada fachada=new Fachada();
		ResultadoListado2 resultado=fachada.consultarListado2();
		model.addAttribute("message", resultado.getMensaje());
		model.addAttribute("list", resultado.getTalleres());
		return "listado2";
	}
	/**
	 * Contexto del servlet
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	}
}
