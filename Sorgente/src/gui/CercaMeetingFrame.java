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
    private JTextField textCodiceSala;
    
   
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
		
		JLayeredPane layeredPaneFisici = new JLayeredPane();
		layeredPaneFisici.setBounds(0, 0, 285, 126);
		panelMeetingFisico.add(layeredPaneFisici);
		layeredPaneFisici.setLayout(new CardLayout(0, 0));
		
		JPanel panelAttributi = new JPanel();
		layeredPaneFisici.add(panelAttributi, "name_11114369615200");
		panelAttributi.setLayout(null);
		
		JSpinner spinnerOraInizioFisico = new JSpinner();
		spinnerOraInizioFisico.setModel(new SpinnerDateModel(new Date(1610492400000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizioFisico.setEditor(new JSpinner.DateEditor(spinnerOraInizioFisico, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizioFisico.getEditor()).getTextField().setEditable(false);
		spinnerOraInizioFisico.setBounds(27, 69, 63, 20);
		panelAttributi.add(spinnerOraInizioFisico);
		
		JSpinner spinnerOraFineFisico = new JSpinner();
		spinnerOraFineFisico.setModel(new SpinnerDateModel(new Date(1610578740000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFineFisico.setEditor(new JSpinner.DateEditor(spinnerOraFineFisico, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraFineFisico.getEditor()).getTextField().setEditable(false);
		spinnerOraFineFisico.setBounds(114, 69, 63, 20);
		panelAttributi.add(spinnerOraFineFisico);
		
		JSpinner spinnerDataFisico = new JSpinner();
		spinnerDataFisico.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerDataFisico.setEditor(new JSpinner.DateEditor(spinnerDataFisico, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerDataFisico.getEditor()).getTextField().setEditable(false);
		spinnerDataFisico.setBounds(27, 38, 150, 20);
		panelAttributi.add(spinnerDataFisico);
		
		textCodiceMeetingFisico = new JTextField();
		textCodiceMeetingFisico.setColumns(10);
		textCodiceMeetingFisico.setBounds(27, 7, 150, 20);
		panelAttributi.add(textCodiceMeetingFisico);
		
		textCodiceSala = new JTextField();
		textCodiceSala.setColumns(10);
		textCodiceSala.setBounds(27, 95, 150, 20);
		panelAttributi.add(textCodiceSala);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 155, 571, 182);
		panelMeetingFisico.add(scrollPane);
		
		tableMeetingFisico = new JTable();
		tableMeetingFisico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice Meeting", "Data", "Ora Inizio", "Ora Fine", "Codice Sala"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tableMeetingFisico);
		
		JButton btnInserisciMeeting = new JButton("Inserisci Meeting");
		btnInserisciMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInserisciMeeting.setBounds(10, 127, 170, 23);
		panelMeetingFisico.add(btnInserisciMeeting);
		
		JLabel labelRicerca = new JLabel("Cerca per:");
		labelRicerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca.setBounds(400, 11, 89, 14);
		panelMeetingFisico.add(labelRicerca);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat of = new SimpleDateFormat("HH:mm:ss");
				
				String codiceMeeting = textCodiceMeetingFisico.getText();
				String codieSala = textCodiceSala.getText();
				String data = df.format((java.util.Date)spinnerDataFisico.getValue());
				String oraInizio = of.format((java.util.Date)spinnerOraInizioFisico.getValue());
				String oraFine = of.format((java.util.Date)spinnerOraFineFisico.getValue());
				
				try {
					controller.RicercaMeetingFisicoPerAttributi(codiceMeeting, data, oraInizio, oraFine, codieSala);
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
     		}
		});
		btnCerca.setBounds(428, 127, 115, 23);
		panelMeetingFisico.add(btnCerca);
		
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
		
		
		
		comboBoxMeetingFisico.setBounds(400, 28, 150, 22);
		panelMeetingFisico.add(comboBoxMeetingFisico);
		
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
		
		JComboBox comboBoxMeetingTelematico = new JComboBox(new String[] {"Codice Meeting", "Data e ora", "Piattaforma", "Numero limite"});
		comboBoxMeetingTelematico.setModel(new DefaultComboBoxModel(new String[] {"Attributi", "Per Dipendenti", "Per Progetti"}));
		comboBoxMeetingTelematico.setBounds(400, 28, 150, 22);
		panelMeetingTelematico.add(comboBoxMeetingTelematico);
		
		JLabel labelRicerca_1 = new JLabel("Cerca per:");
		labelRicerca_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca_1.setBounds(400, 11, 89, 14);
		panelMeetingTelematico.add(labelRicerca_1);
		
		JSpinner spinnerData = new JSpinner();
		spinnerData.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerData.getEditor()).getTextField().setEditable(false);
		spinnerData.setBounds(49, 38, 150, 20);
		panelMeetingTelematico.add(spinnerData);
		
		JSpinner spinnerOraInizio = new JSpinner();
		spinnerOraInizio.setModel(new SpinnerDateModel(new Date(1610233200000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizio.setEditor(new JSpinner.DateEditor(spinnerOraInizio, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizio.getEditor()).getTextField().setEditable(false);
		spinnerOraInizio.setBounds(49, 69, 65, 20);
		panelMeetingTelematico.add(spinnerOraInizio);
		
		JSpinner spinnerOraFine = new JSpinner();
		spinnerOraFine.setModel(new SpinnerDateModel(new Date(1610319540000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFine.setEditor(new JSpinner.DateEditor(spinnerOraFine, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraFine.getEditor()).getTextField().setEditable(false);
		spinnerOraFine.setBounds(137, 69, 62, 20);
		panelMeetingTelematico.add(spinnerOraFine);
		
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
					controller.RicercaMeetingTelematicoPerAttributi(codmt, data, oraInizio, oraFine, piattaforma, numMassimo);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				}
		});
		btnCercaTelematico.setBounds(428, 127, 115, 23);
		panelMeetingTelematico.add(btnCercaTelematico);
		
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
    	
	
		public void PopolaTabella(ArrayList<MeetingTelematico> lista) {
    		
    		DefaultTableModel model = (DefaultTableModel) tableMeetingTelematico.getModel();
    		
    		model.setRowCount(0);
    		
    		for (MeetingTelematico m : lista)
    			model.addRow(new Object[] {m.getCodMeeting(), m.getData(), m.getOraI(), m.getOraF(), m.getPiattaforma(), m.getNumeroLimite()});
    	}
public void PopolaTabellaFisico(ArrayList<MeetingFisico> lista) {
    		
    		DefaultTableModel model = (DefaultTableModel) tableMeetingFisico.getModel();
    		
    		model.setRowCount(0);
    		
    		for (MeetingFisico mf : lista)
    			model.addRow(new Object[] {mf.getCodMeeting(), mf.getData(), mf.getOraI(), mf.getOraF(), mf.getSalaRiunioni().getCodSala()});
    	}
}
