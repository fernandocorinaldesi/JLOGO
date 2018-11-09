package unpaz.tallerDeProgramacion.JLogoAPI;

import java.util.List;

import unpaz.tallerDeProgramacion.JLogoData.Comando;
import unpaz.tallerDeProgramacion.JLogoData.Imagen;
import unpaz.tallerDeProgramacion.JLogoData.Punto;

/**
* La interfaz ILogo contiene los métodos que deben ser implementados para el funcionamiento de la aplicación según lo requerido en el trabajo práctico
* 
*
* @author  Docentes@taller.de.programacion.unpaz
* @version 1.0
* @since   2017-08-31 
*/
public interface ILogo 
{
	/**
	* Procesa el comando ingresado. Resuelve lo necesario según el comando recibido por parámetro
	* @param comando String con el texto del comando ingresado por el usuario
	* @return boolean Indica si el comando es correcto
	*/
	public boolean procesarComando(String comando);
	
	/**
	* Retorna el dibujo actual en el formato de una imagen
	* @return Imagen Imagen resultado luedo del procesamiento del último comando
	* @see Imagen
	*/
	public Imagen getDibujo();	
	
	/**
	* Retorna la posición actual de la tortuga
	* @return Punto Coordenada actual de la tortuga en el espacio de dibujo
	* @see Punto
	*/
	public Punto getPosicionTortuga();

	/**
	* Retorna el ángulo actual de la tortuga (en grados) respecto del eje X positivo (eje horizontal con crecimiento hacia la derecha)
	* @return double valor de la orientación actual de la tortuga (en grados)
	*/
	public double getAnguloTortuga();
		
	/**
	* Retorna el historial de comandos ejecutados hasta el momento
	* @return List lista con los comandos históricos ejecutados
	* @see Comando
	*/
	public List<Comando> getHistorial();	

}
