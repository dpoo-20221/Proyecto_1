package uniandes.dpoo.proyecto1.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Usuario
 *
 */
public class Proyecto 
{
	/**
	 * El nombre del proyecto.
	 */
	private String nombre;
	
	/**
	 * La fecha en la cual se comenzo el proyecto.
	 */
	private LocalDateTime fechaInicio;
	
	/**
	 * La fecha en la que se finalizo o se espera finalizar el proyecto.
	 */
	private LocalDateTime fechaFinal;
	
	/**
	 * Una lista con los participantes del proyecto.
	 */
	private ArrayList<Participante> participantes;
	
	/**
	 * La descripcion del proyecto.
	 */
	private String descripcion;
	
	/**
	 * Una lista con todas las actividades del proyecto.
	 */
	private ArrayList<Actividad> actividades;
	
	/**
	 * Una lista con todos los tipos de actividades disponibles en el proyecto.
	 */
	private ArrayList<String> tiposDisponibles;
	
	/**
	 * Un contador con el numero de proyectos en la aplicacion.
	 */
	public static int contadorProyectos;
	
	/**
	 * Un id unico para cada proyeceto.
	 */
	private int idProyecto;

	/**
	 * Crea un nuevo proyecto del cual se sabe la fecha de finalizacion o la fecha tentativa de terminacion del mismo.
	 * @param nombre El nombre del proyecto.
	 * @param fechaInicio La fecha de inicio del proyecto.
	 * @param fechaFinal La fecha de finalizacion o tentativa de finalizacion del proyecto.
	 * @param participante El participante que creo el proyecto.
	 * @param descripcion La descripcion del proyecto.
	 * @param tiposDisponibles La lista con los tipos de actividades disponibles del proyecto.
	 */
	public Proyecto(String nombre, LocalDateTime fechaInicio, LocalDateTime fechaFinal, Participante participante,
			String descripcion, ArrayList<String> tiposDisponibles) 
	{
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.descripcion = descripcion;
		this.actividades = new ArrayList<Actividad>();
		this.participantes = new ArrayList<Participante>();
		this.tiposDisponibles = tiposDisponibles;
		this.participantes.add(participante);
		contadorProyectos++;
		this.idProyecto = contadorProyectos;
	}
	
	/**
	 * Crea un nuevo proyecto del cual no se sabe la fecha de finalizacion o la fecha tentativa de terminacion del mismo.
	 * @param nombre El nombre del proyecto.
	 * @param fechaInicio La fecha de inicio del proyecto.
	 * @param participante El participante que creo el proyecto.
	 * @param descripcion La descripcion del proyecto.
	 * @param tiposDisponibles La lista con los tipos de actividades disponibles del proyecto.
	 */
	public Proyecto(String nombre, LocalDateTime fechaInicio, Participante participante,
			String descripcion, ArrayList<String> tiposDisponibles) 
	{
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.participantes = new ArrayList<Participante>();
		this.participantes.add(participante);
		this.descripcion = descripcion;
		this.actividades = new ArrayList<Actividad>();
		this.tiposDisponibles = tiposDisponibles;
		contadorProyectos++;
		this.idProyecto = contadorProyectos;
		this.fechaFinal = null;
	}

	/**
	 * @return Devuelve el nombre del proyeto.
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @return Devuelve el ID del proyecto.
	 */
	public int getIdProyecto()
	{
		return idProyecto;
	}

	/**
	 * @return Devuelve la fehca de inicio del proyecto.
	 */
	public LocalDateTime getFechaInicio() 
	{
		return fechaInicio;
	}

	/**
	 * @return Devuelve la fecha de finalizacion del proyecto.
	 */
	public LocalDateTime getFechaFinal() 
	{
		return fechaFinal;
	}

	/**
	 * @return Devuelve la lista con los participantes del proyecto.
	 */
	public ArrayList<Participante> getParticipantes() 
	{
		return participantes;
	}

	/**
	 * @return Devuelve la descripcion del proyecto.
	 */
	public String getDescripcion() 
	{
		return descripcion;
	}

	/**
	 * @return Devuelve la lista con las actividades del proyecto.
	 */
	public ArrayList<Actividad> getActividades() 
	{
		return actividades;
	}

	/**
	 * @return Devuelve la lista con los tipos de actividades dle proyecto.
	 */
	public ArrayList<String> getTiposDisponibles() 
	{
		return tiposDisponibles;
	}

	/**
	 * Se define la fecha de finalizacion del proyecto.
	 * @param pFecha La fecha de finalizacion del proyecto.
	 */
	public void agregarFechaFinal(LocalDateTime pFecha)
	{
		fechaFinal = pFecha;
	}

	/**
	 * Se crea una nueva actividad que ya fue realizada.
	 * @param pTitulo El titulo de la actividad.
	 * @param pDescripcion La descripcion de la actividad.
	 * @param pTipo El tipo de actividad.
	 * @param pFechaRealizacion La fecha de finalizacion de la actividad.
	 * @param pFechaInicio La fecha de inicio de la actividad.
	 * @param pParticipante El participante que realizo la actividad.
	 */
	public void crearNuevaActividadRealizada(String pTitulo, String pDescripcion, String pTipo, LocalDateTime pFechaRealizacion,
	LocalDateTime pFechaInicio, Participante pParticipante)
	{
		Actividad actividad = new Actividad(pTitulo,pDescripcion,pTipo,pFechaRealizacion,pFechaInicio,pParticipante);
		actividades.add(actividad);
	}
	
	/**
	 * Se crea una nueva actividad que ya no ha sido realizada.
	 * @param pTitulo El titulo de la actividad.
	 * @param pDescripcion La descripcion de la actividad.
	 * @param pTipo El tipo de actividad.
	 * @param pFechaInicio La fecha de inicio de la actividad.
	 * @param pParticipante El participante que va a realizar la actividad.
	 */
	public void  crearNuevaActividadPendiente(String pTitulo, String pDescripcion, String pTipo,
			LocalDateTime pFechaInicio, Participante pParticipante) 
	{
		Actividad actividad = new Actividad(pTitulo,pDescripcion,pTipo,pFechaInicio,pParticipante);
		actividades.add(actividad);
	}

	/**
	 * Agrega un nuevo participante al proyecto.
	 * @param participante El participante que se desea agregar.
	 */
	public void agregarParticipante(Participante participante)
	{
		participantes.add(participante);
	}
}
