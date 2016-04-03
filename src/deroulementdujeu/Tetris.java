package deroulementdujeu;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//import client.Client;
import serveur.Serveur;

@SuppressWarnings("serial")
public class Tetris extends JFrame {

	//Statusbar indique ce qu'il se passe (Partie terminée, pause, en attente... etc)
    JLabel statusbar;


    public Tetris() {
    	//Panel regroupant la GlobalView et le 'rectangle' du joueur jouant coté serveur.
    	//JPanel panel0= new JPanel();
    	JPanel panel1= new JPanel();
		
    	//Bar de menu
    	JMenuBar menu_bar1 = new JMenuBar();
		
    	//différents menus
    	JMenu menu1 = new JMenu("Fichier");
    	JMenu menu2 = new JMenu("Edition");
    	JMenu menu3 = new JMenu("Aide");
    	JMenu menu4 = new JMenu("?");
		
    	//differents choix de chaque menu
    	JMenuItem jouer = new JMenuItem("Jouer");
    	JMenuItem pause = new JMenuItem("Pause (P)");
    	JMenuItem online = new JMenuItem("Jouer en réseau");
    	JMenuItem annuler = new JMenuItem("Annuler");
    	JMenuItem copier = new JMenuItem("Copier");
    	JMenuItem coller = new JMenuItem("Coller");
    	
		//Ajouter les choix au menu
		menu1.add(jouer);
		menu1.add(online);
		menu1.add(pause);
		menu2.add(annuler);
		menu2.add(copier);
		menu2.add(coller);
		
		//Ajouter les menu sur la bar de menu
		menu_bar1.add(menu1);
		menu_bar1.add(menu2);
		menu_bar1.add(menu3);
		menu_bar1.add(menu4);
		//Ajouter la bar du menu à la frame
		setJMenuBar(menu_bar1);
    	
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.NORTH);
        
        Terrain Terrain = new Terrain(this);
        add(Terrain);
        Terrain.start();
        Terrain.pause();
        this.setSize(200, 440);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

    public JLabel getStatusBar() {
    	return statusbar;
    }
   
    public static void main(String[] args) {
    	Serveur.main(args);
        Tetris game = new Tetris();
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    } 
}