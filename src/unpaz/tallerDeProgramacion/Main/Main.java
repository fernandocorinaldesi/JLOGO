package unpaz.tallerDeProgramacion.Main;

import unpaz.tallerDeProgramacion.JLogoAPI.ILogo;
import unpaz.tallerDeProgramacion.JLogoGUI.LogoMainFrame;

public class Main {

  public static void main(String[] args) {
    LogoMainFrame frame = new LogoMainFrame();
    frame.setTitle("JLogo XXI");
    ILogo ilogo = new Logica();
    frame.initializes(ilogo);
    frame.setVisible(true);

  }

}
