package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import javax.swing.JComboBox;

public class InserisciMeetingFrame extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JTable tableSala;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public InserisciMeetingFrame(Controller c) {
		controller = c;
		setTitle("Inserisci Meeting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Annulla");
		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(103, 45, 319, 114);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelFisico = new JPanel();
		layeredPane.add(panelFisico, "name_98679284745000");
		panelFisico.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 43, 302, 41);
		panelFisico.add(scrollPane);
		
		tableSala = new JTable();
		tableSala.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Citt\u00E0", "Provincia", "Indirizzo", "Numero Civico", "Numero Posti"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tableSala);
		
		JButton btnNewButton_1 = new JButton("Seleziona sala...");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(0, 11, 146, 23);
		panelFisico.add(btnNewButton_1);
		
		JPanel panelTelematico = new JPanel();
		layeredPane.add(panelTelematico, "name_106301275497300");
		panelTelematico.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 47, 142, 20);
		panelTelematico.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 47, 61, 20);
		panelTelematico.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Piattaforma");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 33, 96, 14);
		panelTelematico.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Limite Partecipanti");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(175, 33, 114, 14);
		panelTelematico.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 210, 412, 150);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice Fiscale", "Nome", "Cognome", "Salario"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(103);
		scrollPane_1.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Seleziona partecipanti...");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_2.setBounds(10, 181, 183, 23);
		contentPane.add(btnNewButton_2);
		
		JComboBox comboBoxTipoMeeting = new JComboBox(new String[] {"Fisico", "Telematico"});
		comboBoxTipoMeeting.setBounds(267, 11, 155, 22);
		comboBoxTipoMeeting.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

					switch (e.getItem().toString()) {
					case "Fisico":
						layeredPane.removeAll();
						layeredPane.add(panelFisico);
						layeredPane.repaint();
						layeredPane.revalidate();
						break;
					case "Telematico":
						layeredPane.removeAll();
						layeredPane.add(panelTelematico);
						layeredPane.repaint();
						layeredPane.revalidate();
					}
			}
		});
		contentPane.add(comboBoxTipoMeeting);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo Meeting:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(172, 15, 85, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnInserisciMeeting = new JButton("Inserisci Meeting");
		btnInserisciMeeting.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInserisciMeeting.setEnabled(false);
		btnInserisciMeeting.setBounds(81, 371, 259, 23);
		contentPane.add(btnInserisciMeeting);
		
		JSpinner spinnerData = new JSpinner();
		spinnerData.setBounds(10, 59, 80, 20);
		contentPane.add(spinnerData);
		spinnerData.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
		
		JSpinner spinnerOraInizio = new JSpinner();
		spinnerOraInizio.setBounds(10, 94, 63, 20);
		contentPane.add(spinnerOraInizio);
		spinnerOraInizio.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizio.setEditor(new JSpinner.DateEditor(spinnerOraInizio, "HH:mm"));
		
		JSpinner spinnerOraFine = new JSpinner();
		spinnerOraFine.setBounds(10, 131, 63, 20);
		contentPane.add(spinnerOraFine);
		spinnerOraFine.setModel(new SpinnerDateModel(new Date(946767540000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFine.setEditor(new JSpinner.DateEditor(spinnerOraFine, "HH:mm"));
		
		JLabel labelData = new JLabel("Data");
		labelData.setBounds(10, 45, 46, 14);
		contentPane.add(labelData);
		labelData.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel labelOraInizio = new JLabel("Ora Inizio");
		labelOraInizio.setBounds(10, 80, 80, 14);
		contentPane.add(labelOraInizio);
		labelOraInizio.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel labelOraFine = new JLabel("Ora Fine");
		labelOraFine.setBounds(10, 117, 63, 14);
		contentPane.add(labelOraFine);
		labelOraFine.setFont(new Font("Tahoma", Font.BOLD, 11));
		((JSpinner.DefaultEditor) spinnerOraFine.getEditor()).getTextField().setEditable(false);
		((JSpinner.DefaultEditor) spinnerOraInizio.getEditor()).getTextField().setEditable(false);
		((JSpinner.DefaultEditor) spinnerData.getEditor()).getTextField().setEditable(false);
	}
}
