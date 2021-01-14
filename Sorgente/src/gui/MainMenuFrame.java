package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MainMenuFrame extends JFrame {

	private JPanel contentPane;
	private Controller controller;

	/**
	 * Create the frame.
	 */
	public MainMenuFrame(Controller c) {
		setTitle("Database Progetti Aziendali");
		setResizable(false);
		controller = c;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><center>Universit\u00E0 degli Studi di Napoli \"Federico II\"<br/>Corso di Laurea Triennale in Informatica<center/><html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 424, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Progetto per i corsi di Basi di Dati e Object Orientation<html/>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 166, 424, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<html>di Fascia Fabio (N86003288) e Conte Salvatore (N86003295)<html/>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 416, 424, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Traccia 2: Sistema di planning per gestione di progetti");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 200, 424, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		
		ImageIcon imageIconLogoUniversità = new ImageIcon(MainMenuFrame.class.getResource("/img/LogoFedericoII.png"));
		Image image = imageIconLogoUniversità.getImage();
		Image newimg = image.getScaledInstance(110, 110,  java.awt.Image.SCALE_SMOOTH);
		imageIconLogoUniversità = new ImageIcon(newimg);
		
		lblNewLabel_4.setIcon(imageIconLogoUniversità);
		lblNewLabel_4.setBounds(166, 58, 110, 110);
		contentPane.add(lblNewLabel_4);
		
		JButton buttonDipendenti = new JButton("Dipendenti");
		buttonDipendenti.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonDipendenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.CambiaFrameMainMenuInCercaDipendente();
			}
		});
		buttonDipendenti.setBounds(129, 225, 181, 36);
		contentPane.add(buttonDipendenti);
		
		JButton buttonProgetti = new JButton("Progetti");
		buttonProgetti.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonProgetti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.CambiaFrameMainMenuInCercaProgetto();
			}
		});
		buttonProgetti.setBounds(129, 272, 181, 36);
		contentPane.add(buttonProgetti);
		
		JButton buttonMeeting = new JButton("Meeting");
		buttonMeeting.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrameMainMenuInCercaMeeting();
			}
		});
		buttonMeeting.setBounds(129, 319, 181, 36);
		contentPane.add(buttonMeeting);
		
		
		JButton buttonSale = new JButton("Sale Riunioni");
		buttonSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrameMainMenuInCercaSala();
			}
		});
		buttonSale.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonSale.setBounds(129, 366, 181, 36);
		contentPane.add(buttonSale);
	}
}
