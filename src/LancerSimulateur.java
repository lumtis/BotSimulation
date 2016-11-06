import java.awt.Color;

import gui.GUISimulator;
import simulation.Simulateur;

public class LancerSimulateur {

	public static void main(String[] args)
    {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        Simulateur sim = new Simulateur(gui, args[0]);
    }
}
