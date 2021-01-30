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
import entità.Sala;

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

public class ModificaSalaFrame extends JFrame {

	private Controller controller;
	
	private JPanel contentPane;
	
	private JTextField textFieldCittà;
	private JTextField textFieldProvincia;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldNumeroCivico;
	private JTextField textFieldNumeroPosti;
	
	private JButton buttonModifica;
	private JButton buttonAnnulla;

	/**
	 * Create the frame.
	 */
	public ModificaSalaFrame(Controller c, Sala oldSala) {
		
		setTitle("Modifica Sala");
		setResizable(false);
		
		controller = c;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameModificaSalaInCercaSala();
			}
		});
		setBounds(100, 100, 282, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ChiudiFrameModificaSalaInCercaSala();
			}
		});
		buttonAnnulla.setBounds(10, 11, 89, 23);
		contentPane.add(buttonAnnulla);
		
		buttonModifica = new JButton("Modifica Sala");
		buttonModifica.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					String città = textFieldCittà.getText();
					String provincia = textFieldProvincia.getText();
					String indirizzo = textFieldIndirizzo.getText();
					int civico = Integer.parseInt(textFieldNumeroCivico.getText());
					int numPosti = Integer.parseInt(textFieldNumeroPosti.getText());
					
					Sala s = new Sala(città, provincia, indirizzo, civico, numPosti);
					
					controller.ModificaSala(s);
					JOptionPane.showMessageDialog(null, "Inserimento riuscito!");
					
					controller.ChiudiFrameModificaSalaInCercaSala();
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Inserire numero civico e numero di posti.");
				}
			}
		});
		buttonModifica.setBounds(51, 207, 171, 23);
		contentPane.add(buttonModifica);
		
		JLabel labelCittà = new JLabel("Citt\u00E0");
		labelCittà.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelCittà.setBounds(10, 45, 107, 14);
		contentPane.add(labelCittà);
		
		JLabel lblNewLabel = new JLabel("Provincia");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(180, 45, 70, 14);
		contentPane.add(lblNewLabel);
		
		textFieldCittà = new JTextField();
		textFieldCittà.setColumns(10);
		textFieldCittà.setBounds(10, 59, 158, 20);
		contentPane.add(textFieldCittà);
		textFieldCittà.setText(oldSala.getCittà());
		
		textFieldProvincia = new JTextField();
		textFieldProvincia.setColumns(10);
		textFieldProvincia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldProvincia.getText().length() >= 2)
		            e.consume();
				
				char keyChar = e.getKeyChar();
				
				if(!Character.isAlphabetic(keyChar))
					e.consume();
				
				e.setKeyChar(Character.toUpperCase(keyChar));
			}
		});
		textFieldProvincia.setBounds(180, 59, 42, 20);
		contentPane.add(textFieldProvincia);
		textFieldProvincia.setText(oldSala.getProvincia());
		
		JLabel labelIndirizzo = new JLabel("Indirizzo");
		labelIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelIndirizzo.setBounds(10, 90, 107, 14);
		contentPane.add(labelIndirizzo);
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setColumns(10);
		textFieldIndirizzo.setBounds(10, 104, 158, 20);
		contentPane.add(textFieldIndirizzo);
		textFieldIndirizzo.setText(oldSala.getIndirizzo());
		
		JLabel lblNewLabel_1 = new JLabel("Numero Civico");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(180, 90, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldNumeroCivico = new JTextField();
		textFieldNumeroCivico.setColumns(10);
		textFieldNumeroCivico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				
				if(!Character.isDigit(keyChar))
					e.consume();
			}
		});
		textFieldNumeroCivico.setBounds(180, 104, 42, 20);
		contentPane.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setText(String.valueOf(oldSala.getNumeroCivico()));
		
		JLabel labelNumeroPosti = new JLabel("Numero Posti");
		labelNumeroPosti.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelNumeroPosti.setBounds(10, 136, 107, 14);
		contentPane.add(labelNumeroPosti);
		
		textFieldNumeroPosti = new JTextField();
		textFieldNumeroPosti.setColumns(10);
		textFieldNumeroPosti.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char keyChar = e.getKeyChar();
				
				if(!Character.isDigit(keyChar))
					e.consume();
			}
		});
		textFieldNumeroPosti.setBounds(10, 153, 89, 20);
		contentPane.add(textFieldNumeroPosti);
		textFieldNumeroPosti.setText(String.valueOf(oldSala.getNumeroPosti()));
	}
}
