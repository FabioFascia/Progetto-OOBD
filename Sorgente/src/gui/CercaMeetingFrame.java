package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entità.Dipendente;
import entità.Meeting;
import entità.MeetingFisico;
import entità.MeetingTelematico;
import entità.Progetto;

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
import javax.swing.JMenuItem;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CercaMeetingFrame extends JFrame {


	private JPanel contentPane;
    private Controller controller;
    private JTable tableMeetingFisico;
    private JTable tableMeetingTelematico;
    private JTextField textCodiceMeetingFisico;
    private JPopupMenu popupMenuTable;
    private JComboBox comboBoxTipoMeeting;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField tfCodiceMeetingTelematico;
    private JTextField textPiattaforma;
    private JTextField textNumeroMassimo;
    private JTextField CodiceProgettoTelematico;
    private JTextField TipologiaProgettoTelematico;
    
   
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
		
		JPanel panelSala = new JPanel();
		layeredPaneFisici.add(panelSala, "name_7227523288000");
		panelSala.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 24, 130, 20);
		panelSala.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 62, 130, 20);
		panelSala.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 106, 130, 20);
		panelSala.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(162, 62, 43, 20);
		panelSala.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(162, 106, 43, 20);
		panelSala.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lbCodiceSala = new JLabel("Codice Sala");
		lbCodiceSala.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbCodiceSala.setBounds(10, 5, 75, 14);
		panelSala.add(lbCodiceSala);
		
		JLabel lblNewLabel_9 = new JLabel("Citt\u00E0");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(10, 46, 75, 14);
		panelSala.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Provincia");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setBounds(162, 46, 60, 14);
		panelSala.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Indirizzo");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setBounds(10, 91, 75, 14);
		panelSala.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Numero Civico");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_12.setBounds(162, 91, 86, 14);
		panelSala.add(lblNewLabel_12);
		
		textField_5 = new JTextField();
		textField_5.setBounds(162, 24, 43, 20);
		panelSala.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Numero Posti");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_13.setBounds(162, 5, 75, 14);
		panelSala.add(lblNewLabel_13);
		
		JPanel panelProgetto = new JPanel();
		layeredPaneFisici.add(panelProgetto, "name_8297324804800");
		panelProgetto.setLayout(null);
		
		textField_6 = new JTextField();
		textField_6.setBounds(10, 42, 127, 20);
		panelProgetto.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(10, 85, 127, 20);
		panelProgetto.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Codice Progetto");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_14.setBounds(10, 21, 107, 14);
		panelProgetto.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Tipologia");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_15.setBounds(10, 66, 79, 14);
		panelProgetto.add(lblNewLabel_15);
		
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
		tableMeetingFisico.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.isPopupTrigger()) {
					ShowPopupMenu(e);
				}
			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()) {
					ShowPopupMenu(e);
				}
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
		
		JComboBox comboBoxMeetingFisico = new JComboBox(new String[] {"Attributi", "Per Sale", "Per Progetti"});
		comboBoxMeetingFisico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(e.getItem().toString()) {
				
				case "Attributi":
					layeredPaneFisici.removeAll();
					layeredPaneFisici.add(panelAttributi);
					layeredPaneFisici.repaint();
					layeredPaneFisici.revalidate();
					break;
				case "Per Sale":
					layeredPaneFisici.removeAll();
					layeredPaneFisici.add(panelSala);
					layeredPaneFisici.repaint();
					layeredPaneFisici.revalidate();
					break;
				case "Per Progetti":
					layeredPaneFisici.removeAll();
					layeredPaneFisici.add(panelProgetto);
					layeredPaneFisici.repaint();
					layeredPaneFisici.revalidate();
					break;
			}
		}
	});
		
		
		
		comboBoxMeetingFisico.setBounds(325, 28, 150, 22);
		panelFisico.add(comboBoxMeetingFisico);
		
		JPanel panelTelematico = new JPanel();
		layeredPane.add(panelTelematico, "name_19511124629900");
		panelTelematico.setLayout(null);
		
		JLayeredPane layeredPaneTelematico = new JLayeredPane();
		layeredPaneTelematico.setBounds(0, 0, 315, 159);
		panelTelematico.add(layeredPaneTelematico);
		layeredPaneTelematico.setLayout(new CardLayout(0, 0));
		
		JPanel panelAttributoTelematico = new JPanel();
		layeredPaneTelematico.add(panelAttributoTelematico, "name_9443958210100");
		panelAttributoTelematico.setLayout(null);
		
		tfCodiceMeetingTelematico = new JTextField();
		tfCodiceMeetingTelematico.setColumns(10);
		tfCodiceMeetingTelematico.setBounds(10, 21, 150, 20);
		panelAttributoTelematico.add(tfCodiceMeetingTelematico);
		
		JLabel lblNewLabel_8 = new JLabel("Codice Meeting");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(10, 7, 101, 14);
		panelAttributoTelematico.add(lblNewLabel_8);
		
		JSpinner spinnerData = new JSpinner();
		spinnerData.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerData.getEditor()).getTextField().setEditable(false);
		spinnerData.setBounds(10, 55, 150, 20);
		panelAttributoTelematico.add(spinnerData);
		
		JLabel lblNewLabel_7 = new JLabel("Data");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(10, 42, 85, 14);
		panelAttributoTelematico.add(lblNewLabel_7);
		
		JSpinner spinnerOraInizio = new JSpinner();
		spinnerOraInizio.setModel(new SpinnerDateModel(new Date(1610924400000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizio.setEditor(new JSpinner.DateEditor(spinnerOraInizio, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizio.getEditor()).getTextField().setEditable(false);
		spinnerOraInizio.setBounds(10, 89, 65, 20);
		panelAttributoTelematico.add(spinnerOraInizio);
		
		JLabel lblNewLabel_5 = new JLabel("Ora Inizio");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(10, 76, 65, 14);
		panelAttributoTelematico.add(lblNewLabel_5);
		
		JSpinner spinnerOraFine = new JSpinner();
		spinnerOraFine.setModel(new SpinnerDateModel(new Date(1611010740000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFine.setEditor(new JSpinner.DateEditor(spinnerOraFine, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraFine.getEditor()).getTextField().setEditable(false);
		spinnerOraFine.setBounds(98, 89, 62, 20);
		panelAttributoTelematico.add(spinnerOraFine);
		
		JLabel lblNewLabel_6 = new JLabel("Ora Fine");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(98, 76, 62, 14);
		panelAttributoTelematico.add(lblNewLabel_6);
		
		textPiattaforma = new JTextField();
		textPiattaforma.setColumns(10);
		textPiattaforma.setBounds(10, 128, 150, 20);
		panelAttributoTelematico.add(textPiattaforma);
		
		JLabel lblNewLabel_3 = new JLabel("Piattaforma");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 113, 85, 14);
		panelAttributoTelematico.add(lblNewLabel_3);
		
		textNumeroMassimo = new JTextField();
		textNumeroMassimo.setColumns(10);
		textNumeroMassimo.setBounds(184, 128, 65, 20);
		panelAttributoTelematico.add(textNumeroMassimo);
		
		JLabel lblNewLabel_4 = new JLabel("Limite Partecipanti");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(181, 113, 124, 14);
		panelAttributoTelematico.add(lblNewLabel_4);
		
		JPanel panelProgettoTelematico = new JPanel();
		layeredPaneTelematico.add(panelProgettoTelematico, "name_11952619611800");
		panelProgettoTelematico.setLayout(null);
		
		CodiceProgettoTelematico = new JTextField();
		CodiceProgettoTelematico.setBounds(10, 37, 132, 20);
		panelProgettoTelematico.add(CodiceProgettoTelematico);
		CodiceProgettoTelematico.setColumns(10);
		
		TipologiaProgettoTelematico = new JTextField();
		TipologiaProgettoTelematico.setBounds(10, 82, 132, 20);
		panelProgettoTelematico.add(TipologiaProgettoTelematico);
		TipologiaProgettoTelematico.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Codice Progetto");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_16.setBounds(10, 21, 115, 14);
		panelProgettoTelematico.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Tipologia");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_17.setBounds(10, 67, 115, 14);
		panelProgettoTelematico.add(lblNewLabel_17);
		
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
		tableMeetingTelematico.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.isPopupTrigger()) {
					ShowPopupMenu(e);
				}
			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()) {
					ShowPopupMenu(e);
				}
			}
		});
		scrollPane_1.setViewportView(tableMeetingTelematico);
		
		JButton btnInserisciMeetingTelematico = new JButton("Inserisci Meeting");
		btnInserisciMeetingTelematico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameInserisciMeetingInCercaMeeting();
			}
		});
		btnInserisciMeetingTelematico.setBounds(9, 170, 170, 23);
		panelTelematico.add(btnInserisciMeetingTelematico);
		
		JComboBox comboBoxMeetingTelematico = new JComboBox(new String[] {"Attributi", "Per Progetti"});
		comboBoxMeetingTelematico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(e.getItem().toString()) {
				case "Attributi":
					layeredPaneTelematico.removeAll();
					layeredPaneTelematico.add(panelAttributoTelematico);
					layeredPaneTelematico.repaint();
					layeredPaneTelematico.revalidate();
				    break;
				case "Per Progetti":
					layeredPaneTelematico.removeAll();
					layeredPaneTelematico.add(panelProgettoTelematico);
					layeredPaneTelematico.repaint();
					layeredPaneTelematico.revalidate();
				    break;
			}
		}
	});
		comboBoxMeetingTelematico.setModel(new DefaultComboBoxModel(new String[] {"Attributi", "Per Progetti"}));
		comboBoxMeetingTelematico.setBounds(325, 28, 150, 22);
		panelTelematico.add(comboBoxMeetingTelematico);
		
		JLabel labelRicerca_1 = new JLabel("Cerca per:");
		labelRicerca_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca_1.setBounds(325, 11, 89, 14);
		panelTelematico.add(labelRicerca_1);
		
		
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
		
		comboBoxTipoMeeting = new JComboBox(new String[] {"Fisico", "Telematico"});
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
    
    public void ShowPopupMenu(MouseEvent e) {
    	
    	popupMenuTable = new JPopupMenu();
		JMenuItem itemModifica = new JMenuItem("Modifica meeting");
		JMenuItem itemElimina = new JMenuItem("Elimina meeting");
		
    	switch(comboBoxTipoMeeting.getSelectedItem().toString()) {
    	
    	case "Fisico":
    		
   			itemModifica.addActionListener(new ActionListener() {
   				
   				public void actionPerformed(ActionEvent e) {
   					
   					controller.ApriFrameModificaMeetingFisicoInCercaMeeting(controller.getMeetingFisicoSelezionato(tableMeetingFisico.getSelectedRow()));
   				}
   			});
   			
   			itemElimina.addActionListener(new ActionListener() {
   				
   				public void actionPerformed(ActionEvent e) {
   					
   					switch (JOptionPane.showConfirmDialog(null, "Eliminare le righe selezionate?", "Cancella righe", JOptionPane.YES_NO_OPTION)) {
   					case JOptionPane.YES_OPTION:
    					try {
    						ArrayList<MeetingFisico> selectedRows = new ArrayList<MeetingFisico>();
    						
    						for (int i :tableMeetingFisico.getSelectedRows()) {
    							selectedRows.add(controller.getMeetingFisicoSelezionato(i));
    						}
    						
    						for (MeetingFisico row : selectedRows)
    							controller.CancellazioneMeetingFisico(row);
    					}
    					catch(SQLException ex) {
    						JOptionPane.showMessageDialog(null, ex.getMessage());
    					}
    					break;
    				case JOptionPane.NO_OPTION:
    					
    				}
    			}
    		});
    		
   			if(tableMeetingFisico.getSelectedRowCount() == 1)
    			popupMenuTable.add(itemModifica);
    		if(tableMeetingFisico.getSelectedRowCount() > 0)
    			popupMenuTable.add(itemElimina);
    		popupMenuTable.show(e.getComponent(), e.getX(), e.getY());
    		break;
    		
    	case "Telematico":
    		
    		itemModifica.addActionListener(new ActionListener() {
    			
    			public void actionPerformed(ActionEvent e) {
    				
    				controller.ApriFrameModificaMeetingTelematicoInCercaMeeting(controller.getMeetingTelematicoSelezionato(tableMeetingTelematico.getSelectedRow()));
    			}
    		});
    		
    		itemElimina.addActionListener(new ActionListener() {
    			
    			public void actionPerformed(ActionEvent e) {
    				
    				switch (JOptionPane.showConfirmDialog(null, "Eliminare le righe selezionate?", "Cancella righe", JOptionPane.YES_NO_OPTION)) {
    				case JOptionPane.YES_OPTION:
//   						try {
//   							ArrayList<MeetingTelematico> selectedRows = new ArrayList<MeetingTelematico>();
//   							
//  							for (int i :tableMeetingTelematico.getSelectedRows()) {
//   								selectedRows.add(controller.getMeetingTelematicoSelezionato(i));
//   						}
   							
//   							for (MeetingTelematico row : selectedRows)
//   								controller.CancellazioneMeetingTelematico(row);
//   						catch(SQLException ex) {
//   							JOptionPane.showMessageDialog(null, ex.getMessage());
//    						}
    					break;	
    				case JOptionPane.NO_OPTION:
    					
    				}
    			}
    		});
    		
    		if(tableMeetingTelematico.getSelectedRowCount() == 1)
    			popupMenuTable.add(itemModifica);
    		if(tableMeetingTelematico.getSelectedRowCount() > 0)
    			popupMenuTable.add(itemElimina);
    		popupMenuTable.show(e.getComponent(), e.getX(), e.getY());
    	}
    }
    
    
	public void PopolaTabellaFisico(ArrayList<MeetingFisico> lista) {
    		
    	DefaultTableModel model = (DefaultTableModel) tableMeetingFisico.getModel();
    		
    	model.setRowCount(0);
    		
    	for (MeetingFisico mf : lista)
    		model.addRow(new Object[] {mf.getCodice(), mf.getData(), mf.getOraInizio(), mf.getOraFine(), mf.getSalaRiunioni().getCittà()});

    }
	public void PopolaTabellaTelematico(ArrayList<MeetingTelematico> lista) {
		
		DefaultTableModel model = (DefaultTableModel) tableMeetingTelematico.getModel();
		
		model.setRowCount(0);
		
		for (MeetingTelematico m : lista)
			model.addRow(new Object[] {m.getCodice(), m.getData(), m.getOraInizio(), m.getOraFine(), m.getPiattaforma(), m.getNumeroLimite()});
	}
	
}
