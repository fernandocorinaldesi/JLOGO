package unpaz.tallerDeProgramacion.Main;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import unpaz.tallerDeProgramacion.JLogoData.Imagen;
import unpaz.tallerDeProgramacion.JLogoData.Pixel;

public class LectorBmp
{
	public static Imagen cargarFondo(String direccion)//recibo la direccion donde se va guardar la imagen
	{
		
		Imagen imgLeida=new Imagen(512,512);
		try
        {	FileInputStream archivo = new FileInputStream(direccion);//aqui uso la clase fileinput que es necesaria
                                                                    //para leer un archivo binario, y le paso el string
                                                                    //con la direccion
        	ImageInputStream imageInput=ImageIO.createImageInputStream(archivo);//abro el flujo hacia esa direccion
                                                               	//que sera de donde voy a leer la imagen 
        	BufferedImage image = ImageIO.read(imageInput); //creo el buffer donde voy a escribir la imagen
        	int altImagen=image.getHeight();
        	int anchImagen=image.getWidth();
        	if(altImagen<512||anchImagen<512)
        	{
        		int i=0;
            	int j=0;
            	for(int y=altImagen-1;y>=0;y--)
            	{
            		j=0;
                   	for(int x=0;x<anchImagen;x++)
                   	{
               			int pixel=image.getRGB(x,y);
               			Color color=new Color(pixel);
               			int r=color.getRed();
               			int g=color.getGreen();
                   		int b=color.getBlue();
                   		Pixel pixelin=new Pixel(r,g,b);
                   		imgLeida.set(i, j, pixelin);
                   		j++;
                   	}
                   	i++;
                }
        	}
            else if(altImagen>=512||anchImagen>=512)
            {
            	int i=0;
               	int j=0;
               	for(int y=512-1;y>=0;y--)
               	{
               		j=0;
                   	for(int x=0;x<512;x++)
                   	{
                   		int pixel=image.getRGB(x,y);
                   		Color color=new Color(pixel);
                  		int r=color.getRed();
               			int g=color.getGreen();
                   		int b=color.getBlue();
                   		Pixel pixelin=new Pixel(r,g,b);
                   		imgLeida.set(i, j, pixelin);
                   		j++;                    }
                   	i++;
            	}
        	}
        }
        catch(Exception e)
        {
        	
        	System.out.println("Error  "+e);
        	return null;
        	
        }
		return imgLeida;
	}   
}
