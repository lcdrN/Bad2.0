package IHM;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controleur.ControleurAjouterJoueur;


public class AjouterJoueur extends JFrame {
	
	protected JLabel message;
	private MainWin frame;
	
	public AjouterJoueur(MainWin frame){
		super("Ajouter un Joueur");
		this.frame = frame;
		
		
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResource("UsersAdd32.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String[] values={"H", "F"};
		String[] anciennete={"Nouveau", "Ancien"};
		String[] niveaubox = {"D�butant", "Interm�diaire" ,"Expert"};
		
		//JTextField
		JTextField nom = new JTextField("Nom");
		JTextField prenom = new JTextField("Pr�nom");
		JTextField age = new JTextField("00");
		JComboBox sexe = new JComboBox(values);
		JComboBox anciente = new JComboBox(anciennete);
		JComboBox niveau = new JComboBox(niveaubox);
		//
		
		//JLabel
		JLabel txtnom = new JLabel("Nom :");
		JLabel txtprenom = new JLabel("Pr�nom :");
		JLabel txtage = new JLabel("Age :");
		JLabel txtsexe = new JLabel("Sexe");
		JLabel txtanciente = new JLabel("Anciennet� :");
		JLabel txtniveau = new JLabel("Niveau :");
		JLabel message = new JLabel("\n \n \n");
		//
		
		//JButton
		JButton ajouter = new JButton("Ajouter", new ImageIcon("png/Ok32.png"));
		ajouter.setToolTipText("Ajoute le joueur au tournois");
		//JButton annuler = new JButton("Annuler", new ImageIcon("png/Minus32.png"));
		//
		
		//Controleur
		ControleurAjouterJoueur control = new ControleurAjouterJoueur(message, nom, prenom, age, sexe, anciente, this.frame);
		ajouter.addActionListener(control);
		//
		
		
		JPanel principal = new JPanel();
		
		
		//JPanel nord 
		JPanel nord = new JPanel();
		nord.setBorder(BorderFactory. createTitledBorder("Joueur"));
		nord.setLayout(new GridLayout(6, 2));
		
		//ajouter au panel nord
		nord.add(txtnom);
		nord.add(nom);
		nord.add(txtprenom);
		nord.add(prenom);
		nord.add(txtage);
		nord.add(age);
		nord.add(txtsexe);
		nord.add(sexe);
		nord.add(txtanciente);
		nord.add(anciente);
		nord.add(txtniveau);
		nord.add(niveau);
		//
		
	//
		
		//Panel sud
		JPanel sud = new JPanel();
		sud.setLayout(new GridLayout(1, 2));
		sud.add(ajouter);
		//sud.add(annuler);
		//
		
		
		principal.setLayout(new BorderLayout());
		principal.add(nord, BorderLayout.NORTH);
		principal.add(message, BorderLayout.CENTER);
		principal.add(sud, BorderLayout.SOUTH);
		
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	
	
	
}
	
	

}	
	


