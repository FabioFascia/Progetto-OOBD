package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entità.Dipendente;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ModificaDipendenteFrame extends JFrame {

	private Controller controller;
	
	private JPanel contentPane;
	
	private JTextField textFieldCodiceFiscale;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldSalario;
	
	private JButton buttonAnnulla;
	private JButton buttonModifica;

	/**
	 * Create the frame.
	 */
	public ModificaDipendenteFrame(Controller c, Dipendente oldDip) {
		
		controller = c;
		
		setTitle("Modifica Dipendente");
		setResizable(false);
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameModificaDipendenteInCercaDipendente();
			}
		});
		setBounds(100, 100, 203, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ChiudiFrameInserisciDipendenteInCercaDipendente();
			}
		});
		buttonAnnulla.setBounds(10, 11, 89, 23);
		contentPane.add(buttonAnnulla);
		
		textFieldCodiceFiscale = new JTextField();
		textFieldCodiceFiscale.setBounds(10, 70, 171, 20);
		contentPane.add(textFieldCodiceFiscale);
		textFieldCodiceFiscale.setColumns(10);
		textFieldCodiceFiscale.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldCodiceFiscale.getText().length() >= 16)
		            e.consume();
				
				char keyChar = e.getKeyChar();
				
				if(!Character.isAlphabetic(keyChar) && !Character.isDigit(keyChar))
					e.consume();
				
				e.setKeyChar(Character.toUpperCase(keyChar));
			}
		});
		textFieldCodiceFiscale.setText(oldDip.getCodF());
		
		textFieldNome = new JTextField(oldDip.getNome());
		textFieldNome.setBounds(10, 126, 171, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCognome = new JTextField(oldDip.getCognome());
		textFieldCognome.setBounds(10, 181, 171, 20);
		contentPane.add(textFieldCognome);
		textFieldCognome.setColumns(10);
		
		textFieldSalario = new JTextField(String.valueOf(oldDip.getSalario()));
		textFieldSalario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c=='.'))
					e.consume();
			}
		});
		textFieldSalario.setBounds(10, 237, 171, 20);
		contentPane.add(textFieldSalario);
		textFieldSalario.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Codice Fiscale");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 45, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 101, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cognome");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 156, 102, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Salario");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 212, 89, 14);
		contentPane.add(lblNewLabel_3);
		
		buttonModifica = new JButton("Modifica");
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codf = textFieldCodiceFiscale.getText();
				String nome = textFieldNome.getText();
				String cognome = textFieldCognome.getText();
				float salario = Float.parseFloat(textFieldSalario.getText());
				
				Dipendente d = new Dipendente(codf, nome, cognome, salario);
				
				try {
					controller.ModificaDipendente(d);
					
					JOptionPane.showMessageDialog(null, "Modifica riuscita!");
					controller.ChiudiFrameModificaDipendenteInCercaDipendente();
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Inserire il salario.");
				}
			}
		});
		buttonModifica.setBounds(10, 275, 171, 23);
		contentPane.add(buttonModifica);
	}
}
