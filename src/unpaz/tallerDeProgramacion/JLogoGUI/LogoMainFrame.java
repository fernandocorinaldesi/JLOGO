package unpaz.tallerDeProgramacion.JLogoGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import unpaz.tallerDeProgramacion.JLogoAPI.ILogo;
import unpaz.tallerDeProgramacion.JLogoData.Comando;
import unpaz.tallerDeProgramacion.JLogoData.Imagen;
import unpaz.tallerDeProgramacion.JLogoData.Punto;

/**
* Frame que contiene la interfaz gráfica de usuario para el programa JLogoXXI.
* 
* Resuelve la interacción con el usuario. Captura el ingreso de los comandos y muestra los resultados de lo realizado.
* <p>
* Modo de uso:
* 
* <pre>
*  {@code
* 	
*   public static void main(String[] args)
*   {
*       LogoMainFrame frame = new LogoMainFrame();
*       ILogo ilogo = new <su clase que implemente ILogo>();
*       frame.initializes(ilogo);
*       frame.setVisible(true);
*   } 
* }
* </pre>
*
* @author  Docentes@taller.de.programacion.unpaz
* @version 1.0
* @since   2017-08-31 
* 

* 
*/
public class LogoMainFrame extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLayeredPane panel_center;
	private JPanel panel_sur;
	private JScrollPane scroll_pane_historial;
	private JTextField text_field_comandos;
	private JButton btn_ejecutar;
	private JPanel panel_west;
	private JLabel label_dibujo;
	private JLabelTortuga label_tortuga;
	
	private ILogo logo;
	
	/**
	 * Inicializa la ventana a partir de obtener el objeto que implementa los métodos de la interfaz ILogo
	 * @param logo implementación de la interfaz ILogo 
	 */	
	public void initializes(ILogo logo)
	{
		this.logo = logo;
		
		this.label_tortuga.setText("");
		URL url = this.getClass().getResource("resources/tortuga.png");
        if (null != url)
        {
        	this.label_tortuga.setIcon(new ImageIcon(url));
        	this.label_tortuga.setText("");
        	this.label_tortuga.setSize(40, 53);
        	
        }
        else
        {
        	System.out.println("No se pudo cargar la imagen, no se encontra el path a la tortuga " );
        }        
		
		this.updateDibujo();
	}
	
	private void updateDibujo()
	{
		Imagen imagen = logo.getDibujo();
		BufferedImage buff_image = this.transformToBufferedImage(imagen);
		ImageIcon image_icon = new ImageIcon(buff_image);
		this.label_dibujo.setText("");
		this.label_dibujo.setIcon(image_icon);
		
		Punto pos_tortuga = this.logo.getPosicionTortuga();
		this.label_tortuga.setLocation(pos_tortuga.getX() - this.label_tortuga.getWidth()/2, imagen.getAlto() - pos_tortuga.getY() - this.label_tortuga.getHeight() / 2);
		String caption_tortuga = "Posición ("+pos_tortuga.getX()+","+pos_tortuga.getY()+") \n Ángulo "+this.logo.getAnguloTortuga()+" grados";
		this.label_tortuga.setToolTipText(caption_tortuga);
		this.label_tortuga.setAngulo(this.logo.getAnguloTortuga());
		
		this.panel_west.removeAll();
		for(Comando c:this.logo.getHistorial())
		{
			panel_west.add(new JLabel(c.toString()));
		}

		JScrollBar vertical = this.scroll_pane_historial.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
		
		this.pack();
		this.repaint();
		
	}
	
	private void updateLabelTortuga(Punto posicion, double angulo)
	{
		
	}
	
	
	private BufferedImage transformToBufferedImage(Imagen imagen)
	{
		BufferedImage image = new BufferedImage(imagen.getAncho(), imagen.getAlto(), BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < imagen.getAlto(); i++) 
        {
            for (int j = 0; j < imagen.getAncho(); j++) 
            {
                Color c = new Color(imagen.get(i,j).getR(), imagen.get(i,j).getG(), imagen.get(i,j).getB());
                image.setRGB(j, imagen.getAlto()-i-1, c.getRGB());
            }
        }
        return image;
	}


	/**
	 * Crea el frame o ventana con el contenido de la interfaz gráfica.
	 */
	public LogoMainFrame() {
		setSize(new Dimension(650, 610));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setPreferredSize(new Dimension(650, 610));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_center = new JLayeredPane();
		panel_center.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(null);
		
		label_dibujo = new JLabel("Dibujo");
		label_dibujo.setLocation(0, 0);
		label_dibujo.setSize(new Dimension(512, 512));
		label_dibujo.setPreferredSize(new Dimension(512, 512));
		panel_center.add(label_dibujo);
		
		label_tortuga = new JLabelTortuga();
		label_tortuga.setText("Tortuga");
		label_tortuga.setToolTipText("Esta es la tortuga");
		panel_center.setLayer(label_tortuga, 1);
		label_tortuga.setBounds(236, 249, 46, 14);
		panel_center.add(label_tortuga);
		
		panel_sur = new JPanel();
		contentPane.add(panel_sur, BorderLayout.SOUTH);
		panel_sur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		text_field_comandos = new JTextField();
		text_field_comandos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exitoso = logo.procesarComando(text_field_comandos.getText());
				if (exitoso)
				{
					text_field_comandos.setText("");
					updateDibujo();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "hubo un error en el comando indicado: \n" + text_field_comandos.getText(), "ERROR!", JOptionPane.ERROR_MESSAGE);					
				}				
				
			}
		});
		text_field_comandos.setSize(new Dimension(1024, 20));
		text_field_comandos.setPreferredSize(new Dimension(1024, 20));
		text_field_comandos.setMinimumSize(new Dimension(1024, 20));
		panel_sur.add(text_field_comandos);
		text_field_comandos.setColumns(48);
		
		btn_ejecutar = new JButton("Ejecutar");
		btn_ejecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean exitoso = logo.procesarComando(text_field_comandos.getText());
				if (exitoso)
				{
					text_field_comandos.setText("");
					updateDibujo();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "hubo un error en el comando indicado: \n" + text_field_comandos.getText(), "ERROR!", JOptionPane.ERROR_MESSAGE);					
				}	
			}
		});
		panel_sur.add(btn_ejecutar);
		
		panel_west = new JPanel();
		panel_west.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		scroll_pane_historial = new JScrollPane(panel_west);
		panel_west.setLayout(new BoxLayout(panel_west, BoxLayout.Y_AXIS));
		scroll_pane_historial.setSize(new Dimension(100, 520));
		scroll_pane_historial.setToolTipText("historial");
		scroll_pane_historial.setPreferredSize(new Dimension(100, 520));
		scroll_pane_historial.setAutoscrolls(true);
		// panel_west.add(scroll_pane_historial);
		contentPane.add(scroll_pane_historial, BorderLayout.WEST);
		scroll_pane_historial.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
}
