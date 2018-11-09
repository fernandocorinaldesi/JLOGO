package unpaz.tallerDeProgramacion.JLogoData;

/**
* Modela un pixel con su color, los valores en rojo (Red), verde (Green) y azul (Blue).
* Los valores válidos para cada canal de color es entre 0 y 255 
*
* @author  Docentes@taller.de.programacion.unpaz
* @version 1.0
* @since   2017-08-31 
*/
public class Pixel {
	
	private int r;
	private int g;
	private int b;
	
	/**
	 * Constructor por copia. Crea e inicializa un nuevo pixel copiando los valores de color del recibido por parámetro
	 * @param p pixel del cual copiar los valores de color	  
	 */
	public Pixel(Pixel p)
	{
		this.r = p.getR();
		this.g = p.getG();
		this.b = p.getB();
	}
	
	/**
	 * Crea e inicializa un nuevo pixel a partir de los valores de color recibidos por parámetro
	 * @param r valor del canal rojo
	 * @param g valor del canal verde
	 * @param b valor del canal azul
	 * @throws RuntimeException en caso de que los valores estén fuera del rango válido
	 */
	public Pixel(int r, int g, int b)
	{
		if (!validate(r) || !validate(g) || !validate(b) )
			throw new RuntimeException("Los valores admitidos para cada canal de color es un entero positivo entre 0 y 255 inclusives");
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * Retorna el valor del canal rojo
	 * @return int valor del canal rojo
	 */	
	public int getR() 
	{
		return r;
	}

	/**
	 * Modifica el valor del canal rojo
	 * @param r valor del canal rojo
	 * @throws RuntimeException en caso de que los valores estén fuera del rango válido
	 */	
	public void setR(int r) 
	{
		if (!validate(r))
			throw new RuntimeException("Los valores admitidos para cada canal de color es un entero positivo entre 0 y 255 inclusives");
		this.r = r;
	}

	/**
	 * Retorna el valor del canal verde
	 * @return int valor del canal verde
	 */
	public int getG() 
	{
		return g;
	}

	/**
	 * Modifica el valor del canal verde
	 * @param g valor del canal verde
	 * @throws RuntimeException en caso de que los valores estén fuera del rango válido
	 */	
	public void setG(int g) {
		if (!validate(g))
			throw new RuntimeException("Los valores admitidos para cada canal de color es un entero positivo entre 0 y 255 inclusives");
		this.g = g;
	}

	/**
	 * Retorna el valor del canal azul
	 * @return int valor del canal azul
	 */
	public int getB() {
		return b;
	}

	/**
	 * Modifica el valor del canal azul
	 * @param b valor del canal azul
	 * @throws RuntimeException en caso de que los valores estén fuera del rango válido
	 */	
	public void setB(int b) {
		if (!validate(b))
			throw new RuntimeException("Los valores admitidos para cada canal de color es un entero positivo entre 0 y 255 inclusives");
		this.b = b;
	}
	
	private boolean validate(int v)
	{
		if(0<= v && v < 256)
			return true;
		return false;
	}
	
	/**
	 * Compara si los pixeles son iguales
	 * @param o pixel con el cual comparar
	 * @throws RuntimeException en caso de que el parámetro no sea un pixel
	 */
	public boolean equals(Object o)
	{
		if ( !(o instanceof Pixel)) 
		{
			throw new RuntimeException("comparando pixel con manzanas!");
		}
		Pixel otro = (Pixel)o;
		return (this.r == otro.getR() && this.g==otro.getG() && this.b==otro.getB() );
	}

}
