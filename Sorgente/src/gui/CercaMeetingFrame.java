package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entit�.Dipendente;
import entit�.Meeting;
import entit�.MeetingFisico;
import entit�.MeetingTelematico;
import entit�.Progetto;

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
    private JTextField textFieldCodiceMeetingFisico;
    private JPopupMenu popupMenuTable;
    private JComboBox comboBoxTipoMeeting;
    private JTextField textFieldCitt�;
    private JTextField textFieldIndirizzo;
    private JTextField textFieldProvincia;
    private JTextField textFieldNumeroCivico;
    private JTextField textFieldCodiceProgettoFisico;
    private JTextField textFieldTipologiaFisico;
    private JTextField tfCodiceMeetingTelematico;
    private JTextField textPiattaforma;
    private JTextField textNumeroMassimo;
    private JTextField textFieldCodiceProgettoTelematico;
    private JTextField textFieldTipologiaTelematico;
    private JTextField textFieldAmbitoFisico;
    private JTextField textFieldAmbitoTelematico;
    private JTextField textFieldMinPosti;
    private JTextField textFieldMaxPosti;
    
   
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
		layeredPaneFisici.setBounds(0, 0, 285, 165);
		panelFisico.add(layeredPaneFisici);
		layeredPaneFisici.setLayout(new CardLayout(0, 0));
		
		JPanel panelAttributi = new JPanel();
		layeredPaneFisici.add(panelAttributi, "name_61921273834600");
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
		
		textFieldCodiceMeetingFisico = new JTextField();
		textFieldCodiceMeetingFisico.setColumns(10);
		textFieldCodiceMeetingFisico.setBounds(24, 25, 150, 20);
		panelAttributi.add(textFieldCodiceMeetingFisico);
		
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
		layeredPaneFisici.add(panelSala, "name_61921294364700");
		panelSala.setLayout(null);
		
		textFieldCitt� = new JTextField();
		textFieldCitt�.setBounds(10, 25, 130, 20);
		panelSala.add(textFieldCitt�);
		textFieldCitt�.setColumns(10);
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(10, 65, 130, 20);
		panelSala.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		
		textFieldProvincia = new JTextField();
		textFieldProvincia.setBounds(162, 25, 43, 20);
		panelSala.add(textFieldProvincia);
		textFieldProvincia.setColumns(10);
		
		textFieldNumeroCivico = new JTextField();
		textFieldNumeroCivico.setBounds(162, 65, 43, 20);
		panelSala.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Citt\u00E0");
		lblNewLabel_9.setBounds(10, 11, 75, 14);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSala.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Provincia");
		lblNewLabel_10.setBounds(162, 11, 60, 14);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSala.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Indirizzo");
		lblNewLabel_11.setBounds(10, 51, 75, 14);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSala.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Numero Civico");
		lblNewLabel_12.setBounds(162, 51, 86, 14);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSala.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Numero Posti");
		lblNewLabel_13.setBounds(10, 91, 75, 14);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSala.add(lblNewLabel_13);
		
		JLabel labelMin = new JLabel("Min");
		labelMin.setHorizontalAlignment(SwingConstants.CENTER);
		labelMin.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMin.setBounds(10, 112, 32, 14);
		panelSala.add(labelMin);
		
		textFieldMinPosti = new JTextField();
		textFieldMinPosti.setColumns(10);
		textFieldMinPosti.setBounds(49, 109, 68, 20);
		panelSala.add(textFieldMinPosti);
		
		textFieldMaxPosti = new JTextField();
		textFieldMaxPosti.setColumns(10);
		textFieldMaxPosti.setBounds(139, 109, 68, 20);
		panelSala.add(textFieldMaxPosti);
		
		JLabel labelMax = new JLabel("Max");
		labelMax.setHorizontalAlignment(SwingConstants.CENTER);
		labelMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMax.setBounds(216, 112, 32, 14);
		panelSala.add(labelMax);
		
		JPanel panelProgetto = new JPanel();
		layeredPaneFisici.add(panelProgetto, "name_61921314940500");
		panelProgetto.setLayout(null);
		
		textFieldCodiceProgettoFisico = new JTextField();
		textFieldCodiceProgettoFisico.setBounds(10, 25, 127, 20);
		panelProgetto.add(textFieldCodiceProgettoFisico);
		textFieldCodiceProgettoFisico.setColumns(10);
		
		textFieldTipologiaFisico = new JTextField();
		textFieldTipologiaFisico.setBounds(10, 68, 127, 20);
		panelProgetto.add(textFieldTipologiaFisico);
		textFieldTipologiaFisico.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Codice Progetto");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_14.setBounds(10, 11, 107, 14);
		panelProgetto.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Tipologia");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_15.setBounds(10, 54, 79, 14);
		panelProgetto.add(lblNewLabel_15);
		
		textFieldAmbitoFisico = new JTextField();
		textFieldAmbitoFisico.setColumns(10);
		textFieldAmbitoFisico.setBounds(10, 113, 127, 20);
		panelProgetto.add(textFieldAmbitoFisico);
		
		JLabel labelAmbito = new JLabel("Ambito");
		labelAmbito.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelAmbito.setBounds(10, 99, 79, 14);
		panelProgetto.add(labelAmbito);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 210, 492, 176);
		panelFisico.add(scrollPane);
		
		tableMeetingFisico = new JTable();
		tableMeetingFisico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {

				"Codice Meeting", "Data", "Ora Inizio", "Ora Fine", "Citt�"
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
					ApriPopupMenu(e);
				}
			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()) {
					ApriPopupMenu(e);
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
		
		JComboBox comboBoxMeetingFisico = new JComboBox(new String[] {"Attributi", "Progetto associato", "Sala riunioni"});
		comboBoxMeetingFisico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(e.getItem().toString()) {
				
				case "Attributi":
					layeredPaneFisici.removeAll();
					layeredPaneFisici.add(panelAttributi);
					layeredPaneFisici.repaint();
					layeredPaneFisici.revalidate();
					break;
				case "Progetto associato":
					layeredPaneFisici.removeAll();
					layeredPaneFisici.add(panelProgetto);
					layeredPaneFisici.repaint();
					layeredPaneFisici.revalidate();
					break;
				case "Sala riunioni":
					layeredPaneFisici.removeAll();
					layeredPaneFisici.add(panelSala);
					layeredPaneFisici.repaint();
					layeredPaneFisici.revalidate();
					break;
				}
			}
		});
		comboBoxMeetingFisico.setBounds(325, 28, 150, 22);
		panelFisico.add(comboBoxMeetingFisico);
		
		JButton btnCercaFisico = new JButton("Cerca");
		btnCercaFisico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					switch(comboBoxMeetingFisico.getSelectedItem().toString()) {
					case "Attributi":
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						DateFormat of = new SimpleDateFormat("HH:mm:ss");
						
						String codiceMeeting = textFieldCodiceMeetingFisico.getText();
						String data = df.format((java.util.Date)spinnerDataFisico.getValue());
						String oraInizio = of.format((java.util.Date)spinnerOraInizioFisico.getValue());
						String oraFine = of.format((java.util.Date)spinnerOraFineFisico.getValue());
						
						PopolaTabellaFisico(controller.RicercaMeetingFisicoPerAttributi(codiceMeeting, data, oraInizio, oraFine));
						break;
					case "Progetto associato":
						String codp = textFieldCodiceProgettoFisico.getText();
						String tipologia = textFieldTipologiaFisico.getText();
						String ambito = textFieldAmbitoFisico.getText();
						
						PopolaTabellaFisico(controller.RicercaMeetingFisicoPerProgetti(codp, tipologia, ambito));
						break;
					case "Sala riunioni":
						String citt� = textFieldCitt�.getText();
						String provincia = textFieldProvincia.getText();
						String indirizzo = textFieldIndirizzo.getText();
						String numCivico = textFieldNumeroCivico.getText();
						String minPosti = textFieldMinPosti.getText();
						String maxPosti = textFieldMaxPosti.getText();
						
						PopolaTabellaFisico(controller.RicercaMeetingFisicoPerSala(citt�, provincia, indirizzo, numCivico, minPosti, maxPosti));
					}
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
     		}
		});
		btnCercaFisico.setBounds(349, 176, 115, 23);
		panelFisico.add(btnCercaFisico);
		
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
		
		textFieldCodiceProgettoTelematico = new JTextField();
		textFieldCodiceProgettoTelematico.setBounds(10, 37, 132, 20);
		panelProgettoTelematico.add(textFieldCodiceProgettoTelematico);
		textFieldCodiceProgettoTelematico.setColumns(10);
		
		textFieldTipologiaTelematico = new JTextField();
		textFieldTipologiaTelematico.setBounds(10, 82, 132, 20);
		panelProgettoTelematico.add(textFieldTipologiaTelematico);
		textFieldTipologiaTelematico.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Codice Progetto");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_16.setBounds(10, 21, 115, 14);
		panelProgettoTelematico.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Tipologia");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_17.setBounds(10, 67, 115, 14);
		panelProgettoTelematico.add(lblNewLabel_17);
		
		JLabel labelAmbito_1 = new JLabel("Ambito");
		labelAmbito_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelAmbito_1.setBounds(10, 113, 79, 14);
		panelProgettoTelematico.add(labelAmbito_1);
		
		textFieldAmbitoTelematico = new JTextField();
		textFieldAmbitoTelematico.setColumns(10);
		textFieldAmbitoTelematico.setBounds(10, 127, 127, 20);
		panelProgettoTelematico.add(textFieldAmbitoTelematico);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 204, 492, 182);
		panelTelematico.add(scrollPane_1);
		
		tableMeetingTelematico = new JTable();
		tableMeetingTelematico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice Meeting", "Data", "Ora Inizio", "Ora Fine", "Piattaforma", "Numero limite"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableMeetingTelematico.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.isPopupTrigger()) {
					ApriPopupMenu(e);
				}
			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()) {
					ApriPopupMenu(e);
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
		
		JComboBox comboBoxMeetingTelematico = new JComboBox(new String[] {"Attributi", "Progetto associato"});
		comboBoxMeetingTelematico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(e.getItem().toString()) {
				case "Attributi":
					layeredPaneTelematico.removeAll();
					layeredPaneTelematico.add(panelAttributoTelematico);
					layeredPaneTelematico.repaint();
					layeredPaneTelematico.revalidate();
				    break;
				case "Progetto associato":
					layeredPaneTelematico.removeAll();
					layeredPaneTelematico.add(panelProgettoTelematico);
					layeredPaneTelematico.repaint();
					layeredPaneTelematico.revalidate();
				    break;
				}
			}
		});
		comboBoxMeetingTelematico.setBounds(325, 28, 150, 22);
		panelTelematico.add(comboBoxMeetingTelematico);
		
		JLabel labelRicerca_1 = new JLabel("Cerca per:");
		labelRicerca_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca_1.setBounds(325, 11, 89, 14);
		panelTelematico.add(labelRicerca_1);
		
		
		JButton btnCercaTelematico = new JButton("Cerca");
		btnCercaTelematico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					switch(comboBoxMeetingTelematico.getSelectedItem().toString()) {
					case "Attributi":
						String codmt = tfCodiceMeetingTelematico.getText();
						String piattaforma = textPiattaforma.getText();
						String numMassimo = textNumeroMassimo.getText();
						
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						DateFormat of = new SimpleDateFormat("HH:mm:ss");
						String data = df.format((java.util.Date)spinnerData.getValue());
						String oraInizio = of.format((java.util.Date)spinnerOraInizio.getValue());
						String oraFine = of.format((java.util.Date)spinnerOraFine.getValue());
						
						PopolaTabellaTelematico(controller.RicercaMeetingTelematicoPerAttributi(codmt, data, oraInizio, oraFine, piattaforma, numMassimo));
					break;
					
					case "Progetto associato":
						String codp = textFieldCodiceProgettoTelematico.getText();
						String tipologia = textFieldTipologiaTelematico.getText();
						String ambito = textFieldAmbitoTelematico.getText();
						
						PopolaTabellaTelematico(controller.RicercaMeetingTelematicoPerProgetti(codp, tipologia, ambito));
					}
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
    
    public void ApriPopupMenu(MouseEvent e) {
    	
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
   						try {
   							ArrayList<MeetingTelematico> selectedRows = new ArrayList<MeetingTelematico>();
   							
  							for (int i :tableMeetingTelematico.getSelectedRows()) {
   								selectedRows.add(controller.getMeetingTelematicoSelezionato(i));
  							}
   							
   							for (MeetingTelematico row : selectedRows)
   								controller.CancellazioneMeetingTelematico(row);
   						}
   						catch(SQLException ex) {
   							JOptionPane.showMessageDialog(null, ex.getMessage());
    					}
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
    		model.addRow(new Object[] {mf.getCodice(), mf.getData(), mf.getOraInizio(), mf.getOraFine(), mf.getSalaRiunioni().getCitt�()});

    }
	public void PopolaTabellaTelematico(ArrayList<MeetingTelematico> lista) {
		
		DefaultTableModel model = (DefaultTableModel) tableMeetingTelematico.getModel();
		
		model.setRowCount(0);
		
		for (MeetingTelematico m : lista)
			model.addRow(new Object[] {m.getCodice(), m.getData(), m.getOraInizio(), m.getOraFine(), m.getPiattaforma(), m.getNumeroLimite()});
	}
}
