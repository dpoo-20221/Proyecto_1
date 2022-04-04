package uniandes.dpoo.proyecto1.procesamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.proyecto1.modelo.Participante;
import uniandes.dpoo.proyecto1.modelo.Proyecto;

public class LoaderAplicacion 
{   
	Map<String, Participante> participantes = new HashMap<String, Participante>();
	ArrayList<Proyecto> proyectos = new ArrayList<>();
	
	public LoaderAplicacion()
	{
		cargarArchivo();
	}
	
	public void cargarArchivo() 
	{
		cargarParticipantes();
		cargarProyectos();
	}
	
	private void cargarProyectos() 
	{		
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/proyectos.txt"));
			String linea = br.readLine();
			while(linea != null) 
			{
				if(!linea.equals(""))
				{
					Boolean si = false;
					LocalDateTime fechaFinal = null;
					String nombre = linea;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					String linea1 = br.readLine();
					LocalDateTime fechaInicio = LocalDateTime.parse(linea1, formatter);
					if(br.readLine().equals("si"))
					{
						si = true;
						DateTimeFormatter formatterF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						fechaFinal = LocalDateTime.parse(br.readLine(), formatterF);
					}
					int numParticipantes = Integer.parseInt(br.readLine());
					ArrayList<Participante> participantesP = new ArrayList<Participante>();
					for(int i=0;i<numParticipantes;i++)
					{
						String nombreP = br.readLine();
						String correoP = br.readLine();
						int list = Integer.parseInt(br.readLine());
						Participante participante = new Participante(nombreP, correoP);
						for(int j=0;j<list;j++)
						{
							participante.agregarProyecto(Integer.parseInt(br.readLine()));
						}
						participantesP.add(participante);
					}
					String descripcion = br.readLine();
					int numTipos = Integer.parseInt(br.readLine());
					ArrayList<String> tiposDisponibles = new ArrayList<String>();
					for(int i=0;i<numTipos;i++)
					{
						tiposDisponibles.add(br.readLine());
					}
					if(si)
					{
						Proyecto proyecto = new Proyecto(nombre, fechaInicio, fechaFinal, participantesP.get(0), descripcion, tiposDisponibles);
						for(int i=0;i<numParticipantes;i++)
						{
							if(i!=0)
							{
								for(Participante p:participantesP)
								{
									proyecto.agregarParticipante(p);
								}
							}
						}
						proyectos.add(proyecto);
					}
					else
					{
						Proyecto proyecto = new Proyecto(nombre, fechaInicio, participantesP.get(0), descripcion, tiposDisponibles);
						for(int i=0;i<numParticipantes;i++)
						{
							if(i!=0)
							{
								for(Participante p:participantesP)
								{
									proyecto.agregarParticipante(p);
								}
							}
						}
						proyectos.add(proyecto);
					}
				}
				linea = br.readLine();
			}
			br.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}		
	
	private void cargarParticipantes() 
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/participantes.txt"));
			String linea = br.readLine();
			while(linea != null) 
			{
				if(!linea.equals(""))
				{
					String nombre = linea;
					String correo = br.readLine();
					int list = Integer.parseInt(br.readLine());
					Participante participante = new Participante(nombre, correo);
					for(int i=0;i<list;i++)
					{
						participante.agregarProyecto(Integer.parseInt(br.readLine()));
					}
					participantes.put(participante.getCorreo(), participante);
					linea = br.readLine();
				}
			}
			br.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * Guarda la informacion de los participantes y de los proyectos.
	 */
	static public void guardar(ArrayList<Proyecto> proyectos, Map<String, Participante> mapaUsuarios)
	{
		try {
            String ruta = "./data/participantes.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String textoParticipantes = "";
            
            for (Map.Entry<String, Participante> entry : mapaUsuarios.entrySet()) {
                textoParticipantes += entry.getValue().getNombre() + "\n";
                textoParticipantes += entry.getKey()+"\n";
                textoParticipantes += entry.getValue().getListaIdProyectos().size()+"\n";
                for(int i:entry.getValue().getListaIdProyectos())
                {
                	textoParticipantes += i + "\n";
                }
            }
            bw.write(textoParticipantes);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

		try {
            String ruta = "./data/proyectos.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String textoProyectos = "";
            for(Proyecto proyecto: proyectos)
            {
            	textoProyectos += proyecto.getNombre() + "\n";
            	String listFechaI[] = proyecto.getFechaInicio().toString().split("T");
            	String listaTiempoI[] = listFechaI[1].split(":");
            	textoProyectos += listFechaI[0]+" "+listaTiempoI[0]+":"+listaTiempoI[1]+"\n";
            	if(proyecto.getFechaFinal()!=null)
            	{
            		textoProyectos += "si"+"\n";
                	String listFechaF[] = proyecto.getFechaFinal().toString().split("T");
                	String listaTiempoF[] = listFechaF[1].split(":");
                	textoProyectos += listFechaI[0]+" "+listaTiempoF[0]+":"+listaTiempoF[1]+"\n";
            	}
            	textoProyectos += proyecto.getParticipantes().size()+"\n";
            	for(Participante p:proyecto.getParticipantes())
            	{
            		textoProyectos += p.getNombre() + "\n";
            		textoProyectos += p.getCorreo()+"\n";
            		textoProyectos += p.getListaIdProyectos().size()+"\n";
                    for(int i:p.getListaIdProyectos())
                    {
                    	textoProyectos += i + "\n";
                    }
            	}
            	textoProyectos += proyecto.getDescripcion()+"\n";
            	textoProyectos += proyecto.getTiposDisponibles().size()+"\n";
            	for(String t:proyecto.getTiposDisponibles())
            	{
            		textoProyectos += t + "\n";
            	}
            }
            bw.write(textoProyectos);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public Map<String, Participante> getParticipantes() {
		return participantes;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}
}
