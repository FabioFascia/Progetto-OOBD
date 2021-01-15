package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entità.Dipendente;
import entità.Meeting;
import entità.MeetingFisico;
import entità.MeetingTelematico;

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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class CercaMeetingFrame extends JFrame {


	private JPanel contentPane;
    private Controller controller;
    private JTable tableMeetingFisico;
    private JTable tableMeetingTelematico;
    private JTextField tfCodiceMeetingTelematico;
    private JTextField textPiattaforma;
    private JTextField textNumeroMassimo;
    private JTextField textCodiceMeetingFisico;
    
   
	/**
	 * Create the frame.
	 */

    	public CercaMeetingFrame(Controller c) {
		setResizable(false);
    	controller=c;
		setTitle("Meeting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 45, 502, 386);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelFisico = new JPanel();
		layeredPane.add(panelFisico, "name_19490538713400");
		panelFisico.setLayout(null);
		
		JLayeredPane layeredPaneFisici = new JLayeredPane();
		layeredPaneFisici.setBounds(0, 0, 285, 152);
		panelFisico.add(layeredPaneFisici);
		layeredPaneFisici.setLayout(new CardLayout(0, 0));
		
		JPanel panelAttributi = new JPanel();
		layeredPaneFisici.add(panelAttributi, "name_11114369615200");
		panelAttributi.setLayout(null);
		
		JSpinner spinnerOraInizioFisico = new JSpinner();
		spinnerOraInizioFisico.setModel(new SpinnerDateModel(new Date(1610492400000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizioFisico.setEditor(new JSpinner.DateEditor(spinnerOraInizioFisico, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizioFisico.getEditor()).getTextField().setEditable(false);
		spinnerOraInizioFisico.setBounds(24, 98, 63, 20);
		panelAttributi.add(spinnerOraInizioFisico);
		
		JSpinner spinnerOraFineFisico = new JSpinner();
		spinnerOraFineFisico.setModel(new SpinnerDateModel(new Date(1610578740000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFineFisico.setEditor(new JSpinner.DateEditor(spinnerOraFineFisico, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraFineFisico.getEditor()).getTextField().setEditable(false);
		spinnerOraFineFisico.setBounds(111, 98, 63, 20);
		panelAttributi.add(spinnerOraFineFisico);
		
		JSpinner spinnerDataFisico = new JSpinner();
		spinnerDataFisico.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerDataFisico.setEditor(new JSpinner.DateEditor(spinnerDataFisico, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerDataFisico.getEditor()).getTextField().setEditable(false);
		spinnerDataFisico.setBounds(24, 62, 150, 20);
		panelAttributi.add(spinnerDataFisico);
		
		textCodiceMeetingFisico = new JTextField();
		textCodiceMeetingFisico.setColumns(10);
		textCodiceMeetingFisico.setBounds(24, 25, 150, 20);
		panelAttributi.add(textCodiceMeetingFisico);
		
		JLabel lblNewLabel = new JLabel("Ora Inizio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(24, 84, 63, 14);
		panelAttributi.add(lblNewLabel);
		
		JLabel lblOraFine = new JLabel("Ora Fine");
		lblOraFine.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOraFine.setBounds(111, 84, 63, 14);
		panelAttributi.add(lblOraFine);
		
		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(24, 48, 46, 14);
		panelAttributi.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Codice Meeting");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(24, 11, 104, 14);
		panelAttributi.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 210, 492, 176);
		panelFisico.add(scrollPane);
		
		tableMeetingFisico = new JTable();
		tableMeetingFisico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {

				"Codice Meeting", "Data", "Ora Inizio", "Ora Fine", "Città"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class

			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tableMeetingFisico);
		
		JButton btnInserisciMeeting = new JButton("Inserisci Meeting");
		btnInserisciMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameInserisciMeetingInCercaMeeting();
			}
		});
		btnInserisciMeeting.setBounds(10, 176, 170, 23);
		panelFisico.add(btnInserisciMeeting);
		
		JLabel labelRicerca = new JLabel("Cerca per:");
		labelRicerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca.setBounds(325, 11, 89, 14);
		panelFisico.add(labelRicerca);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat of = new SimpleDateFormat("HH:mm:ss");
				
				String codiceMeeting = textCodiceMeetingFisico.getText();
				String data = df.format((java.util.Date)spinnerDataFisico.getValue());
				String oraInizio = of.format((java.util.Date)spinnerOraInizioFisico.getValue());
				String oraFine = of.format((java.util.Date)spinnerOraFineFisico.getValue());
				
				try {
					PopolaTabellaFisico(controller.RicercaMeetingFisicoPerAttributi(codiceMeeting, data, oraInizio, oraFine));
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
     		}
		});
		btnCerca.setBounds(349, 176, 115, 23);
		panelFisico.add(btnCerca);
		
		JComboBox comboBoxMeetingFisico = new JComboBox(new String[] {"Attributi", "Per Dipendenti", "Per Progetti"});
		comboBoxMeetingFisico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(e.getItem().toString()) {
				
				case "Per Dipendenti":
					layeredPaneFisici.removeAll();
					layeredPaneFisici.add(panelAttributi);
					layeredPaneFisici.repaint();
					layeredPaneFisici.revalidate();
				}
			}
		});
		
		
		
		comboBoxMeetingFisico.setBounds(325, 28, 150, 22);
		panelFisico.add(comboBoxMeetingFisico);
		
		JPanel panelTelematico = new JPanel();
		layeredPane.add(panelTelematico, "name_19511124629900");
		panelTelematico.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 204, 492, 182);
		panelTelematico.add(scrollPane_1);
		
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
		tfCodiceMeetingTelematico.setBounds(29, 29, 150, 20);
		panelTelematico.add(tfCodiceMeetingTelematico);
		tfCodiceMeetingTelematico.setColumns(10);
		
		textPiattaforma = new JTextField();
		textPiattaforma.setBounds(29, 139, 150, 20);
		panelTelematico.add(textPiattaforma);
		textPiattaforma.setColumns(10);
		
		textNumeroMassimo = new JTextField();
		textNumeroMassimo.setBounds(189, 139, 65, 20);
		panelTelematico.add(textNumeroMassimo);
		textNumeroMassimo.setColumns(10);
		
		JButton btnInserisciMeetingTelematico = new JButton("Inserisci Meeting");
		btnInserisciMeetingTelematico.setBounds(9, 170, 170, 23);
		panelTelematico.add(btnInserisciMeetingTelematico);
		
		JComboBox comboBoxMeetingTelematico = new JComboBox(new String[] {"Codice Meeting", "Data e ora", "Piattaforma", "Numero limite"});
		comboBoxMeetingTelematico.setModel(new DefaultComboBoxModel(new String[] {"Attributi", "Per Dipendenti", "Per Progetti"}));
		comboBoxMeetingTelematico.setBounds(325, 28, 150, 22);
		panelTelematico.add(comboBoxMeetingTelematico);
		
		JLabel labelRicerca_1 = new JLabel("Cerca per:");
		labelRicerca_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca_1.setBounds(325, 11, 89, 14);
		panelTelematico.add(labelRicerca_1);
		
		JSpinner spinnerData = new JSpinner();
		spinnerData.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerData.getEditor()).getTextField().setEditable(false);
		spinnerData.setBounds(29, 64, 150, 20);
		panelTelematico.add(spinnerData);
		
		JSpinner spinnerOraInizio = new JSpinner();
		spinnerOraInizio.setModel(new SpinnerDateModel(new Date(1610233200000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizio.setEditor(new JSpinner.DateEditor(spinnerOraInizio, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizio.getEditor()).getTextField().setEditable(false);
		spinnerOraInizio.setBounds(29, 100, 65, 20);
		panelTelematico.add(spinnerOraInizio);
		
		JSpinner spinnerOraFine = new JSpinner();
		spinnerOraFine.setModel(new SpinnerDateModel(new Date(1610319540000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFine.setEditor(new JSpinner.DateEditor(spinnerOraFine, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraFine.getEditor()).getTextField().setEditable(false);
		spinnerOraFine.setBounds(117, 100, 62, 20);
		panelTelematico.add(spinnerOraFine);
		
		JButton btnCercaTelematico = new JButton("Cerca");
		btnCercaTelematico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codmt = tfCodiceMeetingTelematico.getText();
                String piattaforma = textPiattaforma.getText();
                String numMassimo = textNumeroMassimo.getText();
                
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat of = new SimpleDateFormat("HH:mm:ss");
				String data = df.format((java.util.Date)spinnerData.getValue());
				String oraInizio = of.format((java.util.Date)spinnerOraInizio.getValue());
				String oraFine = of.format((java.util.Date)spinnerOraFine.getValue());
               
				try {
					PopolaTabellaTelematico(controller.RicercaMeetingTelematicoPerAttributi(codmt, data, oraInizio, oraFine, piattaforma, numMassimo));
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnCercaTelematico.setBounds(347, 170, 115, 23);
		panelTelematico.add(btnCercaTelematico);
		
		JLabel lblNewLabel_3 = new JLabel("Piattaforma");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(29, 125, 85, 14);
		panelTelematico.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Limite Partecipanti");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(189, 125, 124, 14);
		panelTelematico.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ora Inizio");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(29, 86, 65, 14);
		panelTelematico.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Ora Fine");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(117, 86, 62, 14);
		panelTelematico.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Data");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(29, 51, 85, 14);
		panelTelematico.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Codice Meeting");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(30, 15, 101, 14);
		panelTelematico.add(lblNewLabel_8);
		
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
		lblTipologiaMeeting.setBounds(181, 11, 133, 17);
		contentPane.add(lblTipologiaMeeting);
		
		JComboBox comboBoxTipoMeeting = new JComboBox(new String[] {"Fisico", "Telematico"});
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
		comboBoxTipoMeeting.setBounds(324, 11, 150, 22);
		contentPane.add(comboBoxTipoMeeting);
		
	
	}
    	
	
	public void PopolaTabellaTelematico(ArrayList<MeetingTelematico> lista) {
    		
    	DefaultTableModel model = (DefaultTableModel) tableMeetingTelematico.getModel();
    		
    	model.setRowCount(0);
    		
    	for (MeetingTelematico m : lista)
    		model.addRow(new Object[] {m.getCodice(), m.getData(), m.getOraI(), m.getOraF(), m.getPiattaforma(), m.getNumeroLimite()});
    }
	
	public void PopolaTabellaFisico(ArrayList<MeetingFisico> lista) {
    		
    	DefaultTableModel model = (DefaultTableModel) tableMeetingFisico.getModel();
    		
    	model.setRowCount(0);
    		
    	for (MeetingFisico mf : lista)
    		model.addRow(new Object[] {mf.getCodice(), mf.getData(), mf.getOraI(), mf.getOraF(), mf.getSalaRiunioni().getCittà()});

    }
}
