package uniandes.dpoo.proyecto1.modelo;

import java.util.ArrayList;

/**
 * @author Usuario
 *
 */
public class Participante 
{
	/**
	 * El nombre del participante.
	 */
	private String nombre;
	
	/**
	 * El correo del participante.
	 */
	private String correo;
	
	/**
	 * La lista con los ID de los proyectos en los que esta el participante.
	 */
	private ArrayList<Integer> listaIdProyectos;
	
	/**
	 * Crea un participante nuevo que no esta en ningun proyecto.
	 * @param pNombre Nombre del participante.
	 * @param pCorreo Correo del participante.
	 */
	public Participante(String pNombre, String pCorreo) 
	{
		this.nombre = pNombre;
		this.correo = pCorreo;
		this.listaIdProyectos = new ArrayList<Integer>();
	}
	
	/**
	 * Crea un nuevo participante que esta en algunos proyectos.
	 * @param pNombre Nombre del participante.
	 * @param pCorreo Correo del participante.
	 * @param pListaIdProyectos La lista con los ID de los proyectos en los que esta el participante.
	 */
	public Participante(String pNombre, String pCorreo, ArrayList<Integer> pListaIdProyectos)
	{
		this.nombre = pNombre;
		this.correo = pCorreo;
		this.listaIdProyectos = pListaIdProyectos;
	}

	/**
	 * @return Devuelve el nombre del participante.
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * @return Devuelve el correo del participante.
	 */
	public String getCorreo()
	{
		return correo;
	}

	/**
	 * @return Devuelve la lista con los ID de los proyectos en los que esta el participante.
	 */
	public ArrayList<Integer> getListaIdProyectos()
	{
		return listaIdProyectos;
	}
	
	/**
	 * Se agrega a la lista el ID del proyecto en el que esta el participante.
	 * @param pIdProyecto El ID del proyecto que se desea agregar.
	 */
	public void agregarProyecto(int pIdProyecto)
	{
		listaIdProyectos.add(pIdProyecto);
	}
}
