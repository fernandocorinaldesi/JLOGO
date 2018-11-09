package unpaz.tallerDeProgramacion.JLogoData;

/**
* Modela una imagen. Un arreglo bidimensional de pixeles con un alto y ancho dado. 
*
* @author  Docentes@taller.de.programacion.unpaz
* @version 1.0
* @since   2017-08-31 
* @see Pixel
*/
public class Imagen 
{
	private Pixel[][] pixeles;
	private int ancho;
	private int alto;
	
	
	
	/**
	 * Crea e inicializa una imagen en blanco
	 * @param ancho ancho en pixeles de la imagen
	 * @param alto alto en pixeles de la imagen
	 */
	public Imagen(int ancho, int alto)
	{
		this.ancho = ancho;
		this.alto = alto;
		this.pixeles = new Pixel[this.alto][this.ancho];
		for (int f = 0; f < alto; f++)
		{
			for (int c = 0; c < ancho; c++)
			{
				this.pixeles[f][c] = new Pixel(255,255,255);
			}
		}
	}
	public Imagen(Imagen im)
	{
		this.ancho = im.getAncho();
		this.alto = im.getAlto();
		this.pixeles = new Pixel[this.alto][this.ancho];
		for (int f = 0; f < alto; f++)
		{
			for (int c = 0; c < ancho; c++)
			{
				Pixel aux= im.get(f, c);
				this.pixeles[f][c] = new Pixel(aux);
			}
		}
	}
	
	/**
	 * Crea e inicializa una imagen en con los valores del arreglo recibido. 
	 * El primer índice corresponde a la fila y el segundo a la columna. 
	 * Guarda una referencia al arreglo bidimensional recibido, no realiza una copia del mismo 
	 * @param ancho ancho en pixeles de la imagen
	 * @param alto alto en pixeles de la imagen
	 * @param pixeles arreglo bidimensional de pixeles con el contenido de la imagen a crear (toma los pixeles como propios, sin copiarlos)
	 * @see Pixel
	 */
	public Imagen(int ancho, int alto, Pixel[][] pixeles)
	{
		this.ancho = ancho;
		this.alto = alto;
		this.pixeles = pixeles;
	}
	
	/**
	 * Setea el color de un pixel de la imagen
	 * @param fila fila donde ubicar el pixel a pintar
	 * @param columna columna donde ubicar el pixel a pintar
	 * @param color pixel con el color a copiar al pixel de la imagen
	 */
	public void set(int fila, int columna, Pixel color)
	{
		this.pixeles[fila][columna].setR(color.getR());
		this.pixeles[fila][columna].setG(color.getG());
		this.pixeles[fila][columna].setB(color.getB());
	}
	
	/**
	 * Retorna el color de un pixel de la imagen
	 * @param fila fila donde ubicar el pixel a pintar
	 * @param columna columna donde ubicar el pixel a pintar
	 * @return Pixel una copia con el color del pixel indicado
	 * @see Pixel
	 */
	public Pixel get(int fila, int columna)
	{
		return new Pixel(this.pixeles[fila][columna]);
	}
	
	/**
	 * Retorna el ancho de la imagen
	 * @return int el valor del ancho de la imagen
	 */
	public int getAncho() 
	{
		return ancho;
	}

	/**
	 * Retorna el alto de la imagen
	 * @return int el valor del alto de la imagen
	 */
	public int getAlto() 
	{
		return alto;
	}

	public boolean validarRango(int rango) {

		if (rango >= 20 && rango <= 480) {
			return true;
		} else {
			return false;
		}

	}
	public boolean validarRangoPixel(int rango) {

		if (rango >= 0 && rango <= 255) {
			return true;
		} else {
			return false;
		}

	}
	public void pincel(int y,int x,Pixel color,int size) {
			
		int xI=x; int yI=y; int xF=x; int yF=y;
		if(size>1) {
		for(int i=1;i<size;i++)
		{
			xI--;yI++;xF++;yF--;
		}
		for(int i=yI;i>=yF;i--)
		{
			for(int j=xI;j<=xF;j++)
			{   
				
			try {
				this.set(i,j,color);
			} catch (Exception e) {
				System.out.println("error fuera de rango");
			}
				
			}
		}
		}
		else {
			this.set(y,x,color);
		}
	                                                                                    	
	}
	

}
	
