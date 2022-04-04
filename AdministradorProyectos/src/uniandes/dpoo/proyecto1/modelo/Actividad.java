package uniandes.dpoo.proyecto1.modelo;

import java.time.LocalDateTime;

/**
 * @author Usuario
 *
 */
public class Actividad 
{
	/**
	 * El titulo de la actividad.
	 */
	private String titulo;
	
	/**
	 * La descripcion de la actividad.
	 */
	private String descripcion;
	
	/**
	 * El tipo de actividad.
	 */
	private String tipo;
	
	/**
	 * La fecha de finalizacion de la actividad.
	 */
	private LocalDateTime fechaRealizacion;
	
	/**
	 * La fecha de inicio de la actividad.
	 */
	private LocalDateTime fechaInicio;
	
	/**
	 * El participante encargado de la actividad.
	 */
	private Participante participante;
	
	/**
	 * Indicador de si la actividad ya fue completada o no.
	 */
	private boolean completada;

	/**
	 * Crea una actividad la cual ya fue completada.
	 * @param titulo El titulo de la actividad.
	 * @param descripcion La descripcion de la actividad.
	 * @param tipo El tipo de actividad.
	 * @param fechaRealizacion La fecha de finalizacion de la actividad.
	 * @param FechaInicio La fecha de inicio de la actividad.
	 * @param participante El participante encargado de la actividad.
	 */
	public Actividad(String titulo, String descripcion, String tipo, LocalDateTime fechaRealizacion,
			LocalDateTime fechaInicio, Participante participante) 
	{
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fechaRealizacion = fechaRealizacion;
		this.fechaInicio = fechaInicio;
		this.participante = participante;
		this.completada = true;
	}
	
	/**
	 * Crea una actividad que no ha sido completada.
	 * @param titulo El titulo de la actividad.
	 * @param descripcion La descripcion de la actividad.
	 * @param tipo El tipo de actividad.
	 * @param FechaInicio La fecha de inicio de la actividad.
	 * @param participante El participante encargado de la actividad.
	 */
	public Actividad(String titulo, String descripcion, String tipo,
			LocalDateTime fechaInicio, Participante participante) 
	{
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fechaInicio = fechaInicio;
		this.participante = participante;
	}

	/**
	 * @return Devuelve el titulo de la actividad.
	 */
	public String getTitulo() 
	{
		return titulo;
	}
	
	/**
	 * @return Devuelve la descripcion de la actividad.
	 */
	public String getDescripcion() 
	{
		return descripcion;
	}

	/**
	 * @return Devuelve el tipo de la actividad.
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @return Devuelve la fecha de finalizacion de la actividad.
	 */
	public LocalDateTime getFechaRealizacion() 
	{
		return fechaRealizacion;
	}

	/**
	 * @return Devuelve la fecha de inicio de la actividad.
	 */
	public LocalDateTime getfechaInicio() 
	{
		return fechaInicio;
	}

	/**
	 * @return Devuelve el participante encargado de la actividad.
	 */
	public Participante getParticipante() 
	{
		return participante;
	}
	
	/**
	 * @return Devuelve true si la actividad ya fue completada y false si no lo ha sido completada.
	 */
	public boolean isCompletada()
	{
		return completada;
	}
	
	/**
	 * Finaliza la actividad
	 */
	public void completarActividad()
	{
		this.completada = true;
	}
	
	/**
	 * Agrega la fecha de realizacion de la actividad y completa la actividad.
	 * @param pFechaRealizacion La fecha de realizacion de la actividad.
	 */
	public void agregarFechaRealizacion(LocalDateTime pFechaRealizacion)
	{
		fechaRealizacion = pFechaRealizacion;
		completarActividad();
	}
}
