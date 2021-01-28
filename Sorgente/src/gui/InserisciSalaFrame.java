package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entit�.Dipendente;
import entit�.Sala;

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

public class InserisciSalaFrame extends JFrame {

	private Controller controller;
	
	private JPanel contentPane;
	
	private JTextField textFieldCitt�;
	private JTextField textFieldProvincia;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldNumeroCivico;
	private JTextField textFieldNumeroPosti;
	
	private JButton buttonInserimento;
	private JButton buttonAnnulla;

	/**
	 * Create the frame.
	 */
	public InserisciSalaFrame(Controller c) {
		
		setTitle("Inserimento Sala");
		setResizable(false);
		
		controller = c;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameInserisciSalaInCercaSala();
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
				
				controller.ChiudiFrameInserisciSalaInCercaSala();
			}
		});
		buttonAnnulla.setBounds(10, 11, 89, 23);
		contentPane.add(buttonAnnulla);
		
		buttonInserimento = new JButton("Inserisci Sala");
		buttonInserimento.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonInserimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Sala s = new Sala();
					
					s.setCitt�(textFieldCitt�.getText());
					s.setProvincia(textFieldProvincia.getText());
					s.setIndirizzo(textFieldIndirizzo.getText());
					s.setNumeroCivico(Integer.parseInt(textFieldNumeroCivico.getText()));
					s.setNumeroPosti(Integer.parseInt(textFieldNumeroPosti.getText()));
					
					controller.InserimentoSala(s);
					JOptionPane.showMessageDialog(null, "Inserimento riuscito!");
					
					controller.ChiudiFrameInserisciSalaInCercaSala();
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Inserire numero civico e numero di posti.");
				}
			}
		});
		buttonInserimento.setBounds(51, 207, 171, 23);
		contentPane.add(buttonInserimento);
		
		JLabel labelCitt� = new JLabel("Citt\u00E0");
		labelCitt�.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelCitt�.setBounds(10, 45, 107, 14);
		contentPane.add(labelCitt�);
		
		JLabel lblNewLabel = new JLabel("Provincia");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(180, 45, 70, 14);
		contentPane.add(lblNewLabel);
		
		textFieldCitt� = new JTextField();
		textFieldCitt�.setColumns(10);
		textFieldCitt�.setBounds(10, 59, 158, 20);
		contentPane.add(textFieldCitt�);
		
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
		
		JLabel labelIndirizzo = new JLabel("Indirizzo");
		labelIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelIndirizzo.setBounds(10, 90, 107, 14);
		contentPane.add(labelIndirizzo);
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setColumns(10);
		textFieldIndirizzo.setBounds(10, 104, 158, 20);
		contentPane.add(textFieldIndirizzo);
		
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
	}
}
