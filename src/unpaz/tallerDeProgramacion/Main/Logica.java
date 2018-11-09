package unpaz.tallerDeProgramacion.Main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import unpaz.tallerDeProgramacion.JLogoAPI.ILogo;
import unpaz.tallerDeProgramacion.JLogoData.Comando;
import unpaz.tallerDeProgramacion.JLogoData.Imagen;
import unpaz.tallerDeProgramacion.JLogoData.Pixel;
import unpaz.tallerDeProgramacion.JLogoData.Punto;

public class Logica implements ILogo {

	private Punto posicion = new Punto(255, 255);
	private List<Comando> historial = new ArrayList<Comando>();
	private double angulo;
	private Pixel color = new Pixel(0, 0, 0);
	private Imagen im = new Imagen(512, 512);
	private Imagen im2 = new Imagen(512, 512);
	private int size=1;

	
	static {
		JOptionPane.showMessageDialog(null,"BIENVENIDO A ILOGO", null, JOptionPane.INFORMATION_MESSAGE);
	}
	@Override
	public boolean procesarComando(String comando) {
		int[] ubi = new int[2];
		int[] rgb = new int[3];

		if (comando.startsWith("ubicar ")) {//valido que ponga ubicar y un espacio con el comando
			comando = comando.replaceFirst("ubicar ", "");//borro ubicar y el espacio
			String[] coordenadas = comando.split(",");//creo un vector con el split por , para obtene run vector con las coord x/y
			if (coordenadas.length == 2) {
				if (esEntero(coordenadas[0]) && esEntero(coordenadas[1])) {
					ubi[0] = Integer.parseInt(coordenadas[0]);
					ubi[1] = Integer.parseInt(coordenadas[1]);
				}
				if (im.validarRango(ubi[0]) && im.validarRango(ubi[1])) {
					posicion.setX(ubi[0]);
					posicion.setY(ubi[1]);
					this.historial.add(new Comando("ubicar", coordenadas));
				} else {
					JOptionPane.showMessageDialog(null, "coordenadas invalidas: \n" + "ERROR!", "error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "cantidad de coordenadas invalidas: \n" + "ERROR!", "error",
						JOptionPane.ERROR_MESSAGE);

			}
			return true;
		}
		if (comando.startsWith("girar")) {
			comando = comando.replaceFirst("girar","");
			comando = comando.trim();
			if (comando.equals("r")) {//si el angulo es r, se  resetea el angulo
					this.angulo = 0;
					this.historial.add(new Comando("girar",new String[] {comando}));
					return true;
				}
				if (esEntero(comando)) {//validacion para que el angulo sea un numero
					double nuevoAngulo = angulo + Double.parseDouble(comando);
			          if (nuevoAngulo >= 0 && nuevoAngulo < 360) {
			            angulo = nuevoAngulo;
			          } else if (nuevoAngulo < 0) {
			            nuevoAngulo += 360;
			            angulo = nuevoAngulo;
			          } else if (nuevoAngulo >= 360) {
			            nuevoAngulo -= 360;
			            angulo = nuevoAngulo;
			          }
			          
						this.historial.add(new Comando("girar",new String[] {comando}));
						return true;
				} else {
					JOptionPane.showMessageDialog(null, "parametro incorrecto \n" + "ERROR!", "error",
							JOptionPane.ERROR_MESSAGE);
				}
	     	return true;
		}
		if (comando.startsWith("color ")) {//valido que ponga ubicar y un espacio con el comando
			comando = comando.replaceFirst("color ", "");//parto por , y obtengo los 3 parametros
			String[] coordenadas = comando.split(",");
		
			if (coordenadas.length == 3) {//valido que el vector que partio el split  sea de 3 posiciones

				if (esEntero(coordenadas[0]) && esEntero(coordenadas[1]) && esEntero(coordenadas[2])) {
					rgb[0] = Integer.parseInt(coordenadas[0]);//valido q sean numeros enteros
					rgb[1] = Integer.parseInt(coordenadas[1]);
					rgb[2] = Integer.parseInt(coordenadas[2]);
					if (im.validarRangoPixel(rgb[0]) && im.validarRangoPixel(rgb[1]) && im.validarRangoPixel(rgb[2])) {
						this.color.setR(rgb[0]);//valido que esos numeros esten dentro del rango rgb
						this.color.setG(rgb[1]);
						this.color.setB(rgb[2]);
						this.historial.add(new Comando("color", coordenadas));
						return true;
					}

				} else {
					JOptionPane.showMessageDialog(null, "hubo un error en el comando indicado: \n" + "ERROR!", "error",
							JOptionPane.ERROR_MESSAGE);
					return true;
				}

			} else {
				JOptionPane.showMessageDialog(null, "cantidad de coordenadas invalidas: \n" + "ERROR!", "error",
						JOptionPane.ERROR_MESSAGE);
				return true;
			}

		}

		if (comando.equals("nuevo")) {//valido que sea solo "nuevo" el comando
			this.angulo = 0;
			this.posicion.setY(255);
			this.posicion.setX(255);
			this.color.setR(0);
			this.color.setG(0);
			this.color.setB(0);
			this.historial = new ArrayList<Comando>();
			this.im = new Imagen(512, 512);
			this.size=1;
			return true;
		}
		if (comando.startsWith("nuevo")) {
			comando = comando.replaceFirst("nuevo", "");
			comando = comando.trim();
			im = LectorBmp.cargarFondo(comando);
				if (im != null) {
					this.historial.add(new Comando("nuevo",new String[] {comando}));
					this.angulo = 0;
					this.posicion.setX(255);
					this.posicion.setY(255);
					this.color.setR(0);
					this.color.setG(0);
					this.color.setB(0);
					this.size=1;
					return true;
				} else {
					return false;
				}
			}

		if (comando.equals("ayuda"))

		{
			JOptionPane.showMessageDialog(null, "LISTA DE COMANDOS \n" + "girar : gira la orientaciï¿½n de la tortuga "
					+ "a los grados indicados. \n"
					+ "ubicar : posiciona a la tortuga en la posiciï¿½n <x,y> indicada.\n\n "
					+ "color : indica el color del pincel en formato RGB. \n\n"
					+ "nuevo : genera un dibujo nuevo con fondo en blanco. \n\n"
					+ "nuevo imagen.bmp: genera un nuevo fondo con la imagen bmp elegida. \n\n"
					+ "ayuda : muestra la ventana de ayuda con  la lista de comandos y una breve explicacion. \n\n"
					+ "avanzar : avanza hacia la posicion indicada dibujando. \n\n"
					+ "borrar : avanza hacia la posicion indicada borrando. \n\n"
					+ "dibujar : guarda el dibujo hecho en formato bmp. \n\n"
					+ "abrir : guarda el dibujo hecho en formato bmp. \n\n"
					+ "pincel : es para darle un parametro de grosor a la linea, puede ser entre 1 y 49. \n\n"
					+ "pintar :consiste en pintar con el color actual del pincel todos aquellos pixeles \n vecinos," + 
					"utilizando vecindad 4 que tienen el mismo color que el píxel \n donde está posicionada "+" la tortuga \n\n" 			
					+ "guardar : Crea un dibujo nuevo y lee el archivo de texto de formato\r\n" + 
					"XML y ejecuta los comandos para producir el dibujo realizado en una sesión anterior.\n\n"
					+ "abrir : Lee el archivo de texto de formato XML y ejecuta\r\n" + 
					"los comandos en el dibujo actual. Opcionalmente, si existen los parámetros <x,y>,\r\n" + 
					"posiciona la tortuga allí y luego ejecuta los comandos. \n\n"
					+ "insertar : Lee el archivo de texto de formato XML y ejecuta\r\n" + 
					"los comandos en el dibujo actual. Opcionalmente, si existen los parámetros <x,y>,\r\n" + 
					"posiciona la tortuga allí y luego ejecuta los comandos. \n\n"
					, "VENTANA DE AYUDA", JOptionPane.INFORMATION_MESSAGE);
			return true;

		}
		if (comando.startsWith("fondo")) {
			comando = comando.replaceFirst("fondo", "");
			comando = comando.trim();
			im = LectorBmp.cargarFondo(comando);
			if (im != null) {
					this.historial.add(new Comando("fondo",new String [] {comando}));
					im2 = new Imagen(im);
					return true;
				} else {

					return false;
				}
			}
		if (comando.startsWith("dibujar")) {
			comando = comando.replaceFirst("dibujar", "");
			comando = comando.trim();
			if (EscritorBmp.guardarImagen(im,comando)) {
					this.historial.add(new Comando("dibujar",new String[] {comando}));
					return true;
				}
				else {
					return false;
     			}
			}
	  if (comando.startsWith("avanzar")) {
			comando = comando.replaceFirst("avanzar", "");
			comando = comando.trim();
			if (esEntero(comando)&& mayoracero(comando)) {
				int pasos = Integer.valueOf(comando);
				Punto objetivo = null;
				int x0 = posicion.getX();//obtengo la posicion actual de x
				int y0 = posicion.getY();//obtengo la posicion actual de y
				int y1 = (int) ((Math.sin(Math.toRadians(angulo))) * pasos) + y0;//este calculo se aplica
				int x1 = (int) ((Math.cos(Math.toRadians(angulo))) * pasos) + x0;//a al parametro ingresado
                                                                         //para  obtener la posicion          
				if (x1 >= 20 && x1 <= 480 && y1 >= 20 && y1 <= 480) {   //x,y destino
					objetivo = new Punto(x1, y1);//creo un objeto punto con la posicion x,y ya obtenidas
					int difX = x1 - x0;          //que es hasta donde se desplaza la tortuga
					int difY = y1 - y0;          //se obtiene la diferencia de restando los ejes objetivo-posicion
					float error = 0;

					if (angulo >= 0 && angulo <= 44) {//validacion para saber a q octante se encuentra apuntando
                                                      //la tortuga						
						float pendiente = ((float) difY) / ((float) difX);//dependiendo que octante sea
						int y = y0;                                     //se dividen las diferencias, para obtener
						for (int x = x0; x <= x1; x++) {             //la pendiente, si la pendiente da 0,en cada
							
							                                      //iteracion del for la linea avanzar un punto
							im.pincel(y, x, color,size);           //en linea recta  
							error += pendiente;                          
							if (error >= 0.5) {// en caso que no sea un angulo recto, con este if se ira regulando la pendiente
								y++;
								error -= 1;
							}
						}

					}
					if (angulo >= 45 && angulo <= 89) {
						float pendiente = ((float) difX) / ((float) difY);
						int x = x0;
						for (int y = y0; y <= y1; y++) {
							im.pincel(y, x, color,size);
							error += pendiente;
							if (error >= 0.5) {
								x++;
								error -= 1;
							}
						}

					}
					if (angulo >= 90 && angulo <= 134) {
						float pendiente = ((float) difX) / ((float) difY);
						int x = x0;
						for (int y = y0; y <= y1; y++) {
							im.pincel(y, x, color,size);
							error += pendiente;
							if (error <= (-0.5)) {
								x--;
								error += 1;
							}
						}

					}
					if (angulo >= 135 && angulo <= 179) {
						float pendiente = ((float) difX) / ((float) difY);
						int x;
						int y = y0;
						for (x = x0; x > x1; x--) {
							im.pincel(y, x, color,size);
							error += pendiente;
							if (error <= (-0.5)) {
								y++;
								error += 1;
							}
						}

					}
					if (angulo >= 180 && angulo <= 224) {
						float pendiente = ((float) difY) / ((float) difX);
						int y = y0;
						for (int x = x0; x >= x1; x--) {
							im.pincel(y, x, color,size);
							error += pendiente;
							if (error >= 0.5) {
								y--;
								error -= 1;
							}
						}

					}
					if (angulo >= 225 && angulo <= 269) {
						float pendiente = ((float) difX) / ((float) difY);
						int x = x0;
						for (int y = y0; y >= y1; y--) {
							im.pincel(y, x, color,size);
							error += pendiente;
							if (error >= 0.5) {
								x--;
								error -= 1;
							}
						}

					}
					if (angulo >= 270 && angulo <= 314) {
						float pendiente = ((float) difX) / ((float) difY);
						int x = x0;
						for (int y = y0; y > y1; y--) {
							im.pincel(y, x, color,size);
							error += pendiente;
							if (error <= (-0.5)) {
								x++;
								error += 1;
							}
						}

					}
					if (angulo >= 315 && angulo <= 359) {
						float pendiente = ((float) difY) / ((float) difX);
						int y = y0;
						for (int x = x0; x < x1; x++) {
							im.pincel(y, x, color,size);
							error += pendiente;
							if (error <= (-0.5)) {
								y--;
								error += 1;
							}
						}

					}

					posicion = objetivo;
					this.historial.add(new Comando("avanzar",new String[] {comando}));
					return true;
				}

			}
		}
		if (comando.startsWith("borrar")) {
			comando = comando.replaceFirst("borrar", "");
			comando = comando.trim();
			if (esEntero(comando)&& mayoracero(comando)) {
				int pasos = Integer.valueOf(comando);
				
				Punto objetivo = null;
				int x0 = posicion.getX();
				int y0 = posicion.getY();
				int y1 = (int) ((Math.sin(Math.toRadians(angulo))) * pasos) + y0;
				int x1 = (int) ((Math.cos(Math.toRadians(angulo))) * pasos) + x0;

				objetivo = new Punto(x1, y1);
				int difX = x1 - x0;
				int difY = y1 - y0;
				float error = 0;

				if (angulo >= 0 && angulo <= 44) {
					float pendiente = ((float) difY) / ((float) difX);
					int y = y0;
					for (int x = x0; x <= x1; x++) {
						im.pincel(y, x, im2.get(y,x),size);
						error += pendiente;
						if (error >= 0.5) {
							y++;
							error -= 1;
						}
					}

				}
				if (angulo >= 45 && angulo <= 89) {
					float pendiente = ((float) difX) / ((float) difY);
					int x = x0;
					for (int y = y0; y <= y1; y++) {
						im.pincel(y, x, im2.get(y,x),size);
						error += pendiente;
						if (error >= 0.5) {
							x++;
							error -= 1;
						}
					}

				}
				if (angulo >= 90 && angulo <= 134) {
					float pendiente = ((float) difX) / ((float) difY);
					int x = x0;
					for (int y = y0; y <= y1; y++) {
						im.pincel(y, x, im2.get(y,x),size);
						error += pendiente;
						if (error <= (-0.5)) {
							x--;
							error += 1;
						}
					}

				}
				if (angulo >= 135 && angulo <= 179) {
					float pendiente = ((float) difX) / ((float) difY);
					int y = y0;
					for (int x = x0; x > x1; x--) {
						im.pincel(y, x, im2.get(y,x),size);
						error += pendiente;
						if (error <= (-0.5)) {
							y++;
							error += 1;
						}
					}

				}
				if (angulo >= 180 && angulo <= 224) {
					float pendiente = ((float) difY) / ((float) difX);
					int y = y0;
					for (int x = x0; x >= x1; x--) {
						im.pincel(y, x, im2.get(y,x),size);
						error += pendiente;
						if (error >= 0.5) {
							y--;
							error -= 1;
						}
					}

				}
				if (angulo >= 225 && angulo <= 269) {
					float pendiente = ((float) difX) / ((float) difY);
					int x = x0;
					for (int y = y0; y >= y1; y--) {
						im.pincel(y, x, im2.get(y,x),size);
						error += pendiente;
						if (error >= 0.5) {
							x--;
							error -= 1;
						}
					}

				}
				if (angulo >= 270 && angulo <= 314) {
					float pendiente = ((float) difX) / ((float) difY);
					int x = x0;
					for (int y = y0; y > y1; y--) {
						im.pincel(y, x, im2.get(y,x),size);
						error += pendiente;
						if (error <= (-0.5)) {
							x++;
							error += 1;
						}
					}

				}
				if (angulo >= 315 && angulo <= 359) {
					float pendiente = ((float) difY) / ((float) difX);
					int y = y0;
					for (int x = x0; x < x1; x++) {
						im.pincel(y, x, im2.get(y,x),size);
						error += pendiente;
						if (error <= (-0.5)) {
							y--;
							error += 1;
						}
					}

				}
				posicion = objetivo;
				this.historial.add(new Comando("borrar",new String[] {comando}));
				return true;
			}
		}
		
		if(comando.startsWith("pincel")) {
			comando = comando.replaceFirst("pincel", "");
			comando = comando.trim();
			if (esEntero(comando)) {
				int tamaño = Integer.parseInt(comando);
				if(tamaño>1 && tamaño<50) {
					size=tamaño;
					this.historial.add(new Comando("pincel",new String[] {comando}));
					return true;
				}
						
		     }
			
		}
		  if (comando.startsWith("pintar")) {
		      int x = posicion.getX();
		      int y = posicion.getY();
		      Pixel viejoColor = im.get(y, x);
		      Pixel nuevoColor = color;
		      if (!viejoColor.equals(nuevoColor)) 
		      {
		        int[] dx = {1, -1, 0, 0};
		        int[] dy = {0, 0, 1, -1};
		        ArrayDeque<Punto> pila = new ArrayDeque<Punto>();
		        pila.push(posicion);
		        while (!pila.isEmpty()) {
		          Punto actualPunto = pila.pop();
		          im.set(actualPunto.getX(), actualPunto.getY() , nuevoColor);
		          for (int i = 0; i < 4; ++i) {
		            int nx = actualPunto.getX() + dx[i];
		            int ny = actualPunto.getY() + dy[i];
		            if (nx >= 0 && nx < 512 && ny >= 0 && ny < 512
		                && im.get(nx, ny).equals(viejoColor)) {
		              pila.push(new Punto(nx, ny));
		            }                                                                                                          
		          }
		        }
		      }
		      historial.add(new Comando("pintar", new String[]{}));
		      return true;
		    }
		  if (comando.startsWith("abrir")) {
				comando = comando.replaceFirst("abrir", "");
				comando = comando.trim();
				Document docEntrada=Xml.cargar(comando);
				String comandoEntradaArchivo=new String("");
				if(docEntrada!=null)
            	{
            		//Obtener elemento raiz y sus hijos
                	Element root_element = docEntrada.getDocumentElement();
                    NodeList comandos_list = root_element.getChildNodes();
                    //Obtener uno a uno cada nodo y, si es un elemento, obtener su nombre y atributo
                    for (int i=0;i<comandos_list.getLength();i++)
                    {
                    	Node nodo = comandos_list.item(i);
                        if (nodo.getNodeType() == Node.ELEMENT_NODE) 
                        {
                            Element comandoNodo = (Element)nodo;
                            String nombreComando = comandoNodo.getNodeName();
                            comandoEntradaArchivo+=nombreComando;
                            NodeList listaParametros=comandoNodo.getChildNodes();
                            if(listaParametros.getLength()>0)
                            {
                                for (int j=0;j<listaParametros.getLength();j++)
                                {
                                	Node parametro = listaParametros.item(j);
                                	NamedNodeMap atributos=parametro.getAttributes();
                                	for(int k=0;k<atributos.getLength();k++)
                                	{
                                		if(k==0)
                                		{
                                			comandoEntradaArchivo+=" ";
                                		}
                                		comandoEntradaArchivo+=(atributos.item(k).getTextContent());
                                		if(k<atributos.getLength()-1)
                                		{
                                			comandoEntradaArchivo+=",";
                                		}
                                	}
                                }
                            }
                            comandoEntradaArchivo+="\n";
                        }
                    }
                    
                    String comandos[]=comandoEntradaArchivo.split("\n");
                    procesarComando("nuevo");
                    for(int i=0;i<comandos.length;i++)
                    {
                    	procesarComando(comandos[i]);
                    }
                    return true;
            	}else
            	{
            		System.out.println("Error de parametros");
            		return false;
            	}
		  }
		  if (comando.startsWith("guardar")) {
				comando = comando.replaceFirst("guardar", "");
				comando = comando.trim();
				Document doc=null;
            	try 
                {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    doc = dBuilder.newDocument();
                } catch (Exception e) 
                {
                	System.out.println("No se pudo crear el Documento");
                    e.printStackTrace();
                    return false;
                }
            	Element root_element = doc.createElement("ListaDeComandos");
            	doc.appendChild(root_element);

            	try
            	{
            		for(int i=0;i<historial.size();i++) 
                	{
                		Xml.agregarComando(historial.get(i),doc,doc.getDocumentElement());
                	}
            		System.out.println("guardando archivo XML...");
                	Xml.guardar(comando,doc);
                	System.out.println("guardado con exito");
                	return true;
            	}catch(Exception e)
            	{
            		System.out.println("Error: "+e);
            		return false;
            	}	
					
		  }
		  if(comando.startsWith("insertar ")) {
				comando = comando.replaceFirst("insertar ","");
				String[] coordenadas = comando.split(",");
				boolean coordenadas1 =false;
				boolean coordenadas3 =false;
				Document docEntrada=null;
				String comandoEntradaArchivo=new String("");
				if(coordenadas.length == 1) {
					coordenadas1=true;
				}
			   if (coordenadas.length == 3 ) {
					if (esEntero(coordenadas[1]) && esEntero(coordenadas[2])) {
						ubi[0] = Integer.parseInt(coordenadas[1]);
						ubi[1] = Integer.parseInt(coordenadas[2]);
						if(im.validarRango(ubi[0]) && im.validarRango(ubi[1])) {
							coordenadas3=true;
						}
						
					}
					
				}
				
				docEntrada=Xml.cargar(coordenadas[0]);
				if(docEntrada!=null && coordenadas1 || coordenadas3)
			 	{
            		//Obtener elemento raiz y sus hijos
                	Element root_element = docEntrada.getDocumentElement();
                    NodeList comandos_list = root_element.getChildNodes();
                    //Obtener uno a uno cada nodo y, si es un elemento, obtener su nombre y atributo
                    for (int i=0;i<comandos_list.getLength();i++)
                    {
                    	Node nodo = comandos_list.item(i);
                        if (nodo.getNodeType() == Node.ELEMENT_NODE) 
                        {
                            Element comandoNodo = (Element)nodo;
                            String nombreComando = comandoNodo.getNodeName();
                            comandoEntradaArchivo+=nombreComando;
                            NodeList listaParametros=comandoNodo.getChildNodes();
                            if(listaParametros.getLength()>0)
                            {
                                for (int j=0;j<listaParametros.getLength();j++)
                                {
                                	Node parametro = listaParametros.item(j);
                                	NamedNodeMap atributos=parametro.getAttributes();
                                	for(int k=0;k<atributos.getLength();k++)
                                	{
                                		if(k==0)
                                		{
                                			comandoEntradaArchivo+=" ";
                                		}
                                		comandoEntradaArchivo+=(atributos.item(k).getTextContent());
                                		if(k<atributos.getLength()-1)
                                		{
                                			comandoEntradaArchivo+=",";
                                		}
                                	}
                                }
                            }
                            comandoEntradaArchivo+="\n";
                        }
                    }
                    if(coordenadas3)
                    {
                    	this.posicion.setX(ubi[0]);
    					this.posicion.setY(ubi[1]);
                    }
                    String comandos[]=comandoEntradaArchivo.split("\n");
                    for(int i=0;i<comandos.length;i++)
                    {
                    	this.procesarComando(comandos[i]);
                    }
                    return true;
            	}
				
		  }
		return false;
	}

	private boolean mayoracero(String comando) {
		int n=Integer.parseInt(comando);
		return n>0;
	}

	@Override
	public Imagen getDibujo() {
		return this.im;
	}

	@Override
	public Punto getPosicionTortuga() {
		// TODO Auto-generated method stub
		return this.posicion;
	}

	public double getAnguloTortuga() {
		// TODO Auto-generated method stub
		return this.angulo;
	}

	@Override
	public List<Comando> getHistorial() {

		return this.historial;
	}

	public boolean esEntero(String s) {
		try {
			Integer.parseInt(s);
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
}
