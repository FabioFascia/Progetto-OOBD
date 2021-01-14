package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class CercaMeetingFrame extends JFrame {


	private JPanel contentPane;
    private Controller controller;
    private JTable tableMeetingFisico;
    private JTable tableMeetingTelematico;
    private JTextField tfCodiceMeetingFisico;
    private JTextField tfData;
    private JTextField tfCodiceMeetingTelematico;
    private JTextField textData;
    private JTextField textOraInizio;
    private JTextField textOraFine;
    private JTextField textPiattaforma;
    private JTextField textNumeroMassimo;
    
   
	/**
	 * Create the frame.
	 */

    	public CercaMeetingFrame(Controller c) {
		setResizable(false);
    	controller=c;
		setTitle("Meeting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 45, 571, 337);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelMeetingFisico = new JPanel();
		layeredPane.add(panelMeetingFisico, "name_19490538713400");
		panelMeetingFisico.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 155, 571, 182);
		panelMeetingFisico.add(scrollPane);
		
		tableMeetingFisico = new JTable();
		tableMeetingFisico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice Meeting Fisico", "Data", "Ora Inizio", "Ora Fine", "Codice Sala"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tableMeetingFisico);
		
		tfCodiceMeetingFisico = new JTextField();
		tfCodiceMeetingFisico.setBounds(49, 11, 150, 20);
		panelMeetingFisico.add(tfCodiceMeetingFisico);
		tfCodiceMeetingFisico.setColumns(10);
		
		tfData = new JTextField();
		tfData.setBounds(49, 42, 150, 20);
		panelMeetingFisico.add(tfData);
		tfData.setColumns(10);
		
		JButton btnInserisciMeeting = new JButton("Inserisci Meeting");
		btnInserisciMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInserisciMeeting.setBounds(10, 127, 170, 23);
		panelMeetingFisico.add(btnInserisciMeeting);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setBounds(428, 127, 115, 23);
		panelMeetingFisico.add(btnCerca);
		
		JComboBox comboBoxMeetingFisico = new JComboBox(new String[] {"Codice Meeting", "Data e ora", "Sala"});
		comboBoxMeetingFisico.setBounds(400, 28, 150, 22);
		panelMeetingFisico.add(comboBoxMeetingFisico);
		
		JLabel labelRicerca = new JLabel("Cerca per:");
		labelRicerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca.setBounds(400, 11, 89, 14);
		panelMeetingFisico.add(labelRicerca);
		
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1610060400000L), null, null, Calendar.HOUR_OF_DAY));
		//JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm");
		spinner.setEditor(new JSpinner.DateEditor(spinner, "HH:mm"));
		((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
		spinner.setBounds(49, 73, 63, 20);
		panelMeetingFisico.add(spinner);
		
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerDateModel(new Date(1610060400000L), null, null, Calendar.HOUR_OF_DAY));
		spinner_2.setEditor(new JSpinner.DateEditor(spinner_2, "HH:mm"));
		((JSpinner.DefaultEditor) spinner_2.getEditor()).getTextField().setEditable(false);
		spinner_2.setBounds(136, 73, 63, 20);
		panelMeetingFisico.add(spinner_2);
		
		JPanel panelMeetingTelematico = new JPanel();
		layeredPane.add(panelMeetingTelematico, "name_19511124629900");
		panelMeetingTelematico.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 155, 571, 182);
		panelMeetingTelematico.add(scrollPane_1);
		
		tableMeetingTelematico = new JTable();
		tableMeetingTelematico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice Meeting Telematico", "Data", "Ora Inizio", "Ora Fine", "Piattaforma", "Numero limite"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(tableMeetingTelematico);
		
		tfCodiceMeetingTelematico = new JTextField();
		tfCodiceMeetingTelematico.setBounds(49, 11, 150, 20);
		panelMeetingTelematico.add(tfCodiceMeetingTelematico);
		tfCodiceMeetingTelematico.setColumns(10);
		
		textData = new JTextField();
		textData.setBounds(49, 42, 150, 20);
		panelMeetingTelematico.add(textData);
		textData.setColumns(10);
		
		textOraInizio = new JTextField();
		textOraInizio.setBounds(49, 69, 65, 20);
		panelMeetingTelematico.add(textOraInizio);
		textOraInizio.setColumns(10);
		
		textOraFine = new JTextField();
		textOraFine.setBounds(134, 69, 65, 20);
		panelMeetingTelematico.add(textOraFine);
		textOraFine.setColumns(10);
		
		textPiattaforma = new JTextField();
		textPiattaforma.setBounds(49, 100, 150, 20);
		panelMeetingTelematico.add(textPiattaforma);
		textPiattaforma.setColumns(10);
		
		textNumeroMassimo = new JTextField();
		textNumeroMassimo.setBounds(209, 100, 65, 20);
		panelMeetingTelematico.add(textNumeroMassimo);
		textNumeroMassimo.setColumns(10);
		
		JButton btnInserisciMeetingTelematico = new JButton("Inserisci Meeting");
		btnInserisciMeetingTelematico.setBounds(10, 127, 170, 23);
		panelMeetingTelematico.add(btnInserisciMeetingTelematico);
		
		JButton btnCercaTelematico = new JButton("Cerca");
		btnCercaTelematico.setBounds(428, 127, 115, 23);
		panelMeetingTelematico.add(btnCercaTelematico);
		
		JComboBox comboBoxMeetingTelematico = new JComboBox(new String[] {"Codice Meeting", "Data e ora", "Piattaforma", "Numero limite"});
		comboBoxMeetingTelematico.setBounds(400, 28, 150, 22);
		panelMeetingTelematico.add(comboBoxMeetingTelematico);
		
		JLabel labelRicerca_1 = new JLabel("Cerca per:");
		labelRicerca_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca_1.setBounds(400, 11, 89, 14);
		panelMeetingTelematico.add(labelRicerca_1);
		
		JButton btnMeetingFisico = new JButton("Meeting Fisico");
		btnMeetingFisico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panelMeetingFisico);
				layeredPane.repaint();
				layeredPane.revalidate();
				
			
			}
		});
		btnMeetingFisico.setBounds(254, 11, 150, 23);
		contentPane.add(btnMeetingFisico);
		
		JButton btnMeetingTelematico = new JButton("Meeting Telematico");
		btnMeetingTelematico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panelMeetingTelematico);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnMeetingTelematico.setBounds(414, 11, 150, 23);
		contentPane.add(btnMeetingTelematico);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrameCercaMeetingInMainMenu();
			}
		});
		btnIndietro.setBounds(10, 11, 99, 23);
		contentPane.add(btnIndietro);
		
		JLabel lblTipologiaMeeting = new JLabel("Tipologia Meeting:");
		lblTipologiaMeeting.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipologiaMeeting.setBounds(119, 12, 145, 17);
		contentPane.add(lblTipologiaMeeting);
		
	
	}
}
