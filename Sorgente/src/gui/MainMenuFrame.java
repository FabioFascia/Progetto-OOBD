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

	private Controller controller;
	
	private JPanel contentPane;
	
	private JButton buttonDipendenti;
	private JButton buttonProgetti;
	private JButton buttonMeeting;
	private JButton buttonSale;

	/**
	 * Create the frame.
	 */
	public MainMenuFrame(Controller c) {
		setTitle("Sistema Aziendale di Planning");
		setResizable(false);
		controller = c;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Benvenuto!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(114, 11, 210, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<html>di Fascia Fabio (N86003288) e Conte Salvatore (N86003295)<html/>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 297, 424, 14);
		contentPane.add(lblNewLabel_2);
		
		buttonDipendenti = new JButton("Dipendenti");
		buttonDipendenti.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonDipendenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ApriFrameCercaDipendenteInMainMenu();
			}
		});
		buttonDipendenti.setBounds(129, 106, 181, 36);
		contentPane.add(buttonDipendenti);
		
		buttonProgetti = new JButton("Progetti");
		buttonProgetti.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonProgetti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.ApriFrameCercaProgettoInMainMenu();
			}
		});
		buttonProgetti.setBounds(129, 153, 181, 36);
		contentPane.add(buttonProgetti);
		
		buttonMeeting = new JButton("Meeting");
		buttonMeeting.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameCercaMeetingInMainMenu();
			}
		});
		buttonMeeting.setBounds(129, 200, 181, 36);
		contentPane.add(buttonMeeting);
		
		
		buttonSale = new JButton("Sale Riunioni");
		buttonSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameCercaSalaInMainMenu();
			}
		});
		buttonSale.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonSale.setBounds(129, 247, 181, 36);
		contentPane.add(buttonSale);
		
		JLabel lblNewLabel = new JLabel("Scegli una sezione per iniziare...");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 49, 343, 30);
		contentPane.add(lblNewLabel);
	}
}
