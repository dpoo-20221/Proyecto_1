package uniandes.dpoo.proyecto1.consola;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import uniandes.dpoo.proyecto1.modelo.Actividad;
import uniandes.dpoo.proyecto1.modelo.Participante;
import uniandes.dpoo.proyecto1.modelo.Proyecto;
import uniandes.dpoo.proyecto1.procesamiento.LoaderAplicacion;

/**
 * @author Usuario
 *
 */
public class ConsolaAplicacion 
{/**
	 * Un mapa con todos los participantes registrados en la aplicacion.
	 */
	private Map<String, Participante> mapaUsuarios;
	
	/**
	 * La lista con todos los proyectos de la aplicacion.
	 */
	private ArrayList<Proyecto> proyectos;
	
	/**
	 * El usuario que va a utilizar la aplicacion.
	 */
	private Participante usuario;
	
	public ConsolaAplicacion()
	{
		iniciarAplicacion();
	}
	
	/**
	 * Se carga la informacion de la aplicacion y se inicia con el registro del usuario.
	 */
	public void iniciarAplicacion()
	{
		System.out.println(centrar("BIENVENID@ AL ADMINISTRADOR DE PROYECTOS", 50,"***"));
		mapaUsuarios = new HashMap<String, Participante>();
		proyectos = new ArrayList<Proyecto>();
		cargar();
		ingreso();
		comenzar();
		guardar();
	}
	
	/**
	 * Carga toda la informacion anterior de la aplicacion.
	 */
	public void cargar()
	{
		LoaderAplicacion loader = new LoaderAplicacion();
		loader.cargarArchivo();
		mapaUsuarios = loader.getParticipantes();
		proyectos = loader.getProyectos();
	}
	
	/**
	 * Guarda toda la informacion de los proyectos y de los usuarios.
	 */
	public void guardar()
	{
		new LoaderAplicacion().guardar(proyectos, mapaUsuarios);
	}
	
	/**
	 * Comienza la interaccion con la aplicacion una vez ingresado el usuario.
	 */
	public void comenzar() {
		//TODO: Realizar la implementacion por consola para preguntar al usuario que realizar.

		//Posibles peticiones del usuario:

		/**
		 * Crear un nuevo proyecto.
		 * Agregar participantes a un proyecto.
		 * Seleccionar un proyecto viejo.
		 * Agregar una actividad a un proyecto.
		 * Sacar un reporte de una actividad.
		 * Sacar un reporte de un proyecto.
		 * Sacar un reporte de un participante.
		 */

		Scanner teclado = new Scanner(System.in);
		boolean salir = false;
		int acción = 0;
		while (!salir) {
			System.out.println("¿Que acción desea realizar?");
			System.out.println("1. Crear un nuevo proyecto ");
			System.out.println("2. Seleccionar un proyecto viejo ");
			System.out.println("3. Salir del programa");
			acción = teclado.nextInt();
			if (acción == 3)
			{
				salir = true;
			}
			switch (acción) {
				case 1:
					System.out.println("Digite el nombre del Proyecto");
					String pNombre = teclado.next();
					LocalDate diaini = LocalDate.now();
					LocalTime horaini = LocalTime.now();

					LocalDateTime pFechaInicio = LocalDateTime.of(diaini, horaini);
					LocalDateTime pFechaFinal = LocalDateTime.of(diaini, horaini);

					System.out.println("Inserte la descripción del proyecto ");
					String pDescripcion = teclado.next();

					System.out.println("Digite los tipos disponibles separados por coma: , ");
					String tiposdisponibles = teclado.next();
					String[] Ptiposdisponibles = tiposdisponibles.split(",");
					ArrayList<String> pTiposDisponibles = new ArrayList<String>(Arrays.asList(Ptiposdisponibles));
					crearProyecto(pNombre, pFechaInicio, pFechaFinal, usuario, pDescripcion, pTiposDisponibles);
					System.out.println("Proyecto creado satisfractoriamente a las: " + pFechaInicio);
					break;
				case 2:
					System.out.print("Ingrese el Id del proyecto: ");
					int id3 = teclado.nextInt();
					for (int x = 0; x < proyectos.size(); x++)
					{
						if (id3 == x)
						{
							subMenú(id3);
						}
					}
			}
		}
	}

	public void subMenú(int id)
		{
			Scanner tecladoSub = new Scanner(System.in);
			boolean salir1 = false;
			int acción1 = 0;


			while (!salir1)
			{
				System.out.println("¿Que acción desea realizar con el proyecto escogido?");
				System.out.println("1. Agregar participantes a un proyecto ");
				System.out.println("2. Agregar una actividad a un proyecto ");
				System.out.println("3. Sacar un reporte de una actividad ");
				System.out.println("4. Sacar un reporte de un proyecto ");
				System.out.println("5. Sacar un reporte de un participante ");
				System.out.println("6. Salir de la aplicación ");
				acción1 = tecladoSub.nextInt();
				switch (acción1)
				{
					case 1:
						System.out.print("Ingrese el nombre del nuevo participante: ");
						String nombreNuevo = tecladoSub.next();
						System.out.println("Ingrese el correo del nuevo participante: ");
						String correoNuevo = tecladoSub.next();
						Participante participantes = new Participante(nombreNuevo, correoNuevo);

						if (mapaUsuarios.containsKey(correoNuevo))
						{
							System.out.println("El nuevo usuario ya se encuentra registrado");
						}
						else
						{
							for (int x = 0; x < proyectos.size(); x++)
							{
								if (id == x)
								{
									proyectos.get(x).agregarParticipante(participantes);
								}

							}

							System.out.print("El participante: " + nombreNuevo + " se agregó al proyecto con Id: " + id+"\n");
						}
						break;

					case 2:
						System.out.print("Ingrese el nombre de la nueva actividad: ");
						String titulo = tecladoSub.next();
						System.out.print("Ingrese la descripcion de la actividad:  ");
						String descripcion = tecladoSub.next();
						System.out.print("Ingrese el tipo de actividad: ");
						String tipo = tecladoSub.next();

						LocalDate diaActividad = LocalDate.now();
						LocalTime horaActividad = LocalTime.now();
						LocalDateTime fechaInicio = LocalDateTime.of(diaActividad, horaActividad);
						Participante pParticipanteA = new Participante(nombre, correo);

						for (int x = 0; x < proyectos.size(); x++)
						{
							if (id == x)
							{
								proyectos.get(x).crearNuevaActividadPendiente(titulo, descripcion, tipo, fechaInicio, pParticipanteA);
							}

						}
						break;

					case 3:
						
						for (int x = 0; x < proyectos.size(); x++)
						{
							if (id == x)
							{
								ArrayList<Actividad> actividades;
								actividades = proyectos.get(x).getActividades();
								for (int y = 0; y < actividades.size(); y++)
								{
									System.out.println(actividades.get(y).getTitulo()+"\n");
								}

								System.out.println("Seleccione la actividad de la cual desea obtener reporte empezando desde la 0 en adelante: ");

								int actividad = tecladoSub.nextInt();
								for (int y = 0; y < actividades.size(); y++)
								{
									if (actividad == y)
									{
										System.out.println("La descripción de la actividad es: "+actividades.get(y).getDescripcion()+"\n");
										System.out.println("La fecha de inicio de la actividad es: "+actividades.get(y).getfechaInicio()+"\n");
										System.out.println("¿La actividad está completada? "+actividades.get(y).isCompletada()+"\n");
									}
								}

								
							}
						}
					break;

					case 4:
						for (int x = 0; x < proyectos.size(); x++)
						{
							if (id == x)
							{
								System.out.print("Reporte del proyecto numero " + x+"\n");
								String nombreP = proyectos.get(x).getNombre();
								LocalDateTime fechaInicioP = proyectos.get(x).getFechaInicio();
								LocalDateTime fechaFinalP = proyectos.get(x).getFechaFinal();
								int participantesP = proyectos.get(x).getParticipantes().size();
								int actividadesP = proyectos.get(x).getActividades().size();
								//LocalDataTime tiempoPromedio = (fechaFinalP. - fechaInicioP);


								System.out.print("Nombre: " + nombreP+"\n");
								System.out.print("Numero de participantes: " + participantesP+"\n");
								System.out.print("Numero de actividades: " + actividadesP+"\n");
								System.out.print("Fecha de incio: " + fechaInicioP+"\n");
								System.out.print("Fecha final: " + fechaFinalP+"\n");
								System.out.print("Tiempo promedio: "); //+ //tiempoPromedio);

							}

						}
						break;
					case 5:
						System.out.print("Ingrese el correo del usuario: "+"\n");
						String correo = tecladoSub.next();
						if (mapaUsuarios.containsKey(correo))

						{
							System.out.print("Reporte del usuario:" + correo+"\n");
							String nombre = mapaUsuarios.get(correo).getNombre();
							String correoP = mapaUsuarios.get(correo).getCorreo();
							int proyectos = mapaUsuarios.get(correo).getListaIdProyectos().size();


							System.out.print("Nombre: " + nombre+"\n");
							System.out.print("Correo: " + correoP+"\n");
							System.out.print("Numero de proyectos: " + proyectos+"\n");

						}
						else
						{
							System.out.println("Ese correo no se encuentra registrado \n");

						}



						break;
					case 6: 
						{
							salir1 = true;
						}
					default:
						System.out.println("Solo números entre 1 y 5 y 6 para salir");

				}
			}

	}
	
	/**
	 * Permite y verifica el ingreso de un usuario a la aplicacion.
	 */
	private String nombre;
	private String correo;
	public void ingreso()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese su nombre: \n");  
		String nombre = sc.nextLine();
		System.out.println("Ingrese su correo: ");
		String correo = sc.nextLine(); 
		if(correo.contains("@"))
		{
			if(mapaUsuarios.containsKey(correo))
			{
				if(mapaUsuarios.get(correo).getNombre().equalsIgnoreCase(nombre))
				{
					usuario = mapaUsuarios.get(correo);
				}
				else 
				{
					System.out.println("Ese correo ya se encuentra registrado a otro nombre, intente con otro correo o cambie el nombre. \n");
					ingreso();
				}
			}
			else
			{
				crearParticipante(nombre, correo);
			}
		}
		else 
		{
			System.out.println("Por favor ingrese un correo valido \n");
			ingreso();
		}
	}
	
	/**
	 * Centra una cadena de caracteres en un espacio determinado con una cadena especifica iniciando y terminando.
	 * @param s La cadena que desea centrarse.
	 * @param ancho El espacio disponible para colocar el mensaje.
	 * @param a Los caracteres que desean ponerse antes y despues del mensaje centrado.
	 * @return La cadena centrada en el espacio determinado con los caracteres el inicio y al final.
	 */
	private String centrar(String s, int ancho, String a)
	{
        int lonText=s.length()-2*a.length();
        int tamañoCampo=(ancho/2)+(lonText/2);
        String n = s;
        String m= String.format("%" + tamañoCampo + "s", s);
        s = String.format("%" + tamañoCampo + "s", s).replace(" ","*");
        s = String.format("%-" + ancho  + "s", s).replace(" ","*");
        m= String.format("%-" + ancho + "s", m);
        if(ancho < a.length() + n.length())
        {
        	return a+m+a;
        }
        else
        {
        	return a+" ".repeat(a.length())+m+a;
        }
	}
	
	/**
	 * Centra una cadena de caracteres en un espacio determinado.
	 * @param s La cadena que desea centrarse.
	 * @param ancho El espacio disponible para colocar el mensaje.
	 * @return La cadena centrada en el espacio determinado.
	 */
	private String centrar(String s, int ancho)
	{
        int lonText=s.length();
        int tamañoCampo=(ancho/2)+(lonText/2);
        String m= String.format("%" + tamañoCampo + "s", s);
        s = String.format("%" + tamañoCampo + "s", s).replace(" ","*");
        s = String.format("%-" + ancho  + "s", s).replace(" ","*");
        m= String.format("%-" + ancho + "s", m);
        return m;
	}
	
	/**
	 * Crea un nuevo participante y lo agrega al mapa de usuarios.
	 * @param pNombre Nombre del participante.
	 * @param pCorreo Correo del participante.
	 * @return El participante que se creo.
	 */
	public Participante crearParticipante(String pNombre, String pCorreo)
	{
		usuario = new Participante(pNombre, pCorreo);
		mapaUsuarios.put(pCorreo, usuario);
		return usuario;
	}
	
	/**
	 * Crea un proyecto nuevo.
	 * @param pNombre El nombre del proyecto.
	 * @param pFechaInicio La fecha de inicio del proyecto.
	 * @param pFechaFinal La fecha de finalizacion o tentativa de finalizacion del proyecto.
	 * @param pParticipante El participante que creo el proyecto.
	 * @param pDescripcion La descripcion del proyecto.
	 * @param pTiposDisponibles La lista con los tipos de actividades disponibles del proyecto.
	 * @return
	 */
	public Proyecto crearProyecto(String pNombre, LocalDateTime pFechaInicio, LocalDateTime pFechaFinal, Participante pParticipante,
			String pDescripcion, ArrayList<String> pTiposDisponibles)
	{
		Proyecto proyecto = new Proyecto(pNombre, pFechaInicio, pFechaFinal,pParticipante, pDescripcion, pTiposDisponibles);
		proyectos.add(proyecto);
		return proyecto;
	}
	
	/**
	 * Agrega al mapa de usuarios un nuevo participante.
	 * @param participante El participante a ser agregado.
	 */
	public void agregarParticipante(Participante participante)
	{
		mapaUsuarios.put(participante.getCorreo(), participante);
	}
	
	/**
	 * Agrega a la lista de proyectos un nuevo proyecto.
	 * @param proyecto El proyecto a ser agregado.
	 */
	public void agregarProyecto(Proyecto proyecto)
	{
		proyectos.add(proyecto);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ConsolaAplicacion consola = new ConsolaAplicacion();
	}
}
