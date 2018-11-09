package unpaz.tallerDeProgramacion.Main;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import unpaz.tallerDeProgramacion.JLogoData.Imagen;

public class EscritorBmp
{
	public static boolean guardarImagen(Imagen imagenAGuardar, String nombre)
	{
	try
        {	File archivo = new File(nombre);//utilizo la clase file para representar un archivo o directorio
                                            //le paso el string con la direccion
        	ImageOutputStream imageOutput=ImageIO.createImageOutputStream(archivo);//con imageoutput abro un flujo
        	                                                            //de datos hacia la direcion destino
        	                                                            //para poder guardar la imagen
        	BufferedImage image =new BufferedImage(512,512,BufferedImage.TYPE_3BYTE_BGR);
        	//uso la clse bufferimage para crear un buffer donde se escribira en memoria la imagen a guardar
        	int i=0;
            int j=0;
             for(int y=512-1;y>=0;y--)
            {
            	j=0;
                for(int x=0;x<512;x++){ //en este for hago la conversion de mi objeto tipo imagen al tipo de imagen que usa bufferimage
               		int r=imagenAGuardar.get(y,x).getR();//obtengo el color de un pixel de esa posicion de mi
               		int g=imagenAGuardar.get(y,x).getG();// objeto de tipo imageny lo guardo en un entero, para 
              		int b=imagenAGuardar.get(y,x).getB();//pasarselo luego como parametro a la clase color
               		 Color color= new Color(r, g, b); 
               		image.setRGB(j,i,color.getRGB());//mando los parametros a mi objeto de tipo bufferimage
                j++;                             //tanto la posicion de pixel y el color de esa posicion
                }                                   //para java la columna es la x, y la fila la y
                 i++;
            }
      
            ImageIO.write(image, "BMP", imageOutput);//grabo la imagen que escribi en el buffer
            imageOutput.close();
            return true;
        }
		catch (IOException x) {
			System.err.format("IOException %s%n",x);
		}
        catch(Exception e)
        {
        	System.out.println("Error  "+e);
        }
		return false;
	}   
}

