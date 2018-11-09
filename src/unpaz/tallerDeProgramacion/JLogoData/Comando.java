package unpaz.tallerDeProgramacion.JLogoData;

/**
* Modela un comando separando su nombre de los distintos par�metros 
*
* @author  Docentes@taller.de.programacion.unpaz
* @version 1.0
* @since   2017-08-31 
*/

public class Comando 
{
	private String nombre;
	private String[] parametros;
	
	/**
	 * Inicializa un objeto comando con su nombre y sus par�metros
	 * @param nombre nombre del comando
	 * @param parametros arreglo de par�metros si los tiene
	 */
	public Comando(String nombre, String[] parametros) {
		super();
		this.nombre = nombre;
		this.parametros = parametros;
	}
	
	/**
	 * Retorna el nombre del comando
	 * @return String el nombre del comando
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna los par�metros del comando
	 * @return String[] arreglo con los par�metros ingresados
	 */
	public String[] getParametros() {
		return parametros;
	}
	
	/** 
	 * Convierte el comando en un String con el nombre concatenado con los par�metros
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s =  "" + nombre;
		if (this.parametros!=null)
		{
			for(String p: this.parametros)
			{
				s += " " + p;
			}
		}
		return s;
	}	

}
