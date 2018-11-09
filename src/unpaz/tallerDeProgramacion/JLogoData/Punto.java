package unpaz.tallerDeProgramacion.JLogoData;

/**
* Modela una coordenada de dos dimensiones en el espacio de coordenadas del dibujo. 
* El origen de coordenadas se considera el vértice inferior izquierdo. 
* El eje X horizontal crece hacia la derecha.
* El eje Y vertical crece hacia arriba.
*
* @author  Docentes@taller.de.programacion.unpaz
* @version 1.0
* @since   2017-08-31 
*/
public class Punto implements Comparable<Punto>
{
	private int x;
	private int y;
	
	/**
	 * Crea e inicializa un punto a partir de las coodenadas indicadas
	 * @param x valor en el eje X
	 * @param y valor en el eje Y
	 */
	public Punto(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Retorna el valor de la coordenada en el eje X
	 * @return int el valor de la coordenada en el eje X
	 */
	public int getX() 
	{
		return x;
	}
	
	/**
	 * Retorna el valor de la coordenada en el eje Y
	 * @return int el valor de la coordenada en el eje Y
	 */	
	public int getY() 
	{
		return y;
	}
	
	/**
	 * Modifica el valor de la coordenada en el eje X
	 * @param x el nuevo valor para la coordenada en el eje X
	 */
	public void setX(int x) 
	{
		this.x = x;
	}
	
	/**
	 * Modifica el valor de la coordenada en el eje Y
	 * @param y el nuevo valor para la coordenada en el eje Y
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
	
	/**
	 * Compara dos puntos para dar una idea de orden e igualdad
	 * El criterio compara primero los valores de las coordenadas del eje X y luego los del eje Y 
	 * @param otro el otro punto con el cual comparar
	 */
	@Override
	public int compareTo(Punto otro) 
	{
		if (otro.getX() > this.getX())
		{
			return 1;
		}
		else if (otro.getX() < this.getX())
		{
			return -1;
		}
		else if (otro.getY() > this.getY())
		{
			return 1;
		}
		else if (otro.getY() < this.getY())
		{
			return -1;
		}
		else
		{
			return 0;
		}		
	}
	
	/**
	 * Traslada el punto sumando los valores indicados
	 * @param dx valor a sumar en la coordenada del eje X
	 * @param dy valor a sumar en la coordenada del eje Y
	 */
	public void mover(int dx, int dy)
	{
		this.x += dx;
		this.y += dy;
	}
	

}
