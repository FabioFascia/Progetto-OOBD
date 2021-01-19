package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import entità.Dipendente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.ListSelectionModel;


public class CercaPartecipanteMeetingFrame extends JFrame {

	private Controller controller;
	private JPanel contentPane;
	private JTable tableDipendenti;
	private JTextField textFieldMaxSalario;
	private JTextField textFieldCodiceFiscale;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldMinSalario;
	private JTextField textFieldCodiceProgetto;
	private JTextField textFieldTipologia;
	private JTextField textFieldAmbito;
	private JTextField textFieldRuolo;
	private JTextField textFieldMinNumeroProgetti;
	private JTextField textFieldMaxNumeroProgetti;
	
	/**
	 * Create the frame.
	 */
	public CercaPartecipanteMeetingFrame(Controller c) {
		
		controller = c;

		setResizable(false);
		setTitle("Cerca Partecipante Meeting");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameCercaPartecipanteMeeting();
			}
		});
		setBounds(100, 100, 480, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 32, 257, 199);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelAttributi = new JPanel();
		layeredPane.add(panelAttributi, "name_144397810216100");
		panelAttributi.setLayout(null);
		
		textFieldCodiceFiscale = new JTextField();
		textFieldCodiceFiscale.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldCodiceFiscale.getText().length() >= 16)
		            e.consume();
				
				char keyChar = e.getKeyChar();
				e.setKeyChar(Character.toUpperCase(keyChar));
			}
		});
		textFieldCodiceFiscale.setColumns(10);
		textFieldCodiceFiscale.setBounds(49, 25, 158, 20);
		panelAttributi.add(textFieldCodiceFiscale);
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(49, 62, 158, 20);
		panelAttributi.add(textFieldNome);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(49, 100, 158, 20);
		panelAttributi.add(textFieldCognome);
		
		textFieldMinSalario = new JTextField();
		textFieldMinSalario.setColumns(10);
		textFieldMinSalario.setBounds(49, 137, 68, 20);
		panelAttributi.add(textFieldMinSalario);
		
		textFieldMaxSalario = new JTextField();
		textFieldMaxSalario.setBounds(139, 137, 68, 20);
		panelAttributi.add(textFieldMaxSalario);
		textFieldMaxSalario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c=='.'))
					e.consume();
			}
		});
		textFieldMaxSalario.setColumns(10);
		
		JLabel labelMin = new JLabel("Min");
		labelMin.setBounds(10, 140, 32, 14);
		panelAttributi.add(labelMin);
		labelMin.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMin.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel labelMax = new JLabel("Max");
		labelMax.setBounds(215, 140, 32, 14);
		panelAttributi.add(labelMax);
		labelMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMax.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel labelSalario = new JLabel("Salario");
		labelSalario.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSalario.setBounds(49, 123, 68, 14);
		panelAttributi.add(labelSalario);
		
		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelCognome.setBounds(49, 86, 68, 14);
		panelAttributi.add(labelCognome);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelNome.setBounds(49, 48, 46, 14);
		panelAttributi.add(labelNome);
		
		JLabel labelCodiceFiscale = new JLabel("Codice Fiscale");
		labelCodiceFiscale.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelCodiceFiscale.setBounds(49, 11, 107, 14);
		panelAttributi.add(labelCodiceFiscale);
		
		JPanel panelProgetti = new JPanel();
		layeredPane.add(panelProgetti, "name_146017521321800");
		panelProgetti.setLayout(null);
		
		textFieldCodiceProgetto = new JTextField();
		textFieldCodiceProgetto.setBounds(49, 25, 158, 20);
		panelProgetti.add(textFieldCodiceProgetto);
		textFieldCodiceProgetto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Codice Progetto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(49, 11, 97, 14);
		panelProgetti.add(lblNewLabel);
		
		textFieldTipologia = new JTextField();
		textFieldTipologia.setBounds(49, 62, 158, 20);
		panelProgetti.add(textFieldTipologia);
		textFieldTipologia.setColumns(10);
		
		JLabel labelTipologia = new JLabel("Tipologia");
		labelTipologia.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelTipologia.setBounds(49, 48, 97, 14);
		panelProgetti.add(labelTipologia);
		
		textFieldAmbito = new JTextField();
		textFieldAmbito.setBounds(49, 100, 158, 20);
		panelProgetti.add(textFieldAmbito);
		textFieldAmbito.setColumns(10);
		
		JLabel labelAmbito = new JLabel("Ambito");
		labelAmbito.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelAmbito.setBounds(49, 86, 68, 14);
		panelProgetti.add(labelAmbito);
		
		textFieldRuolo = new JTextField();
		textFieldRuolo.setBounds(49, 137, 158, 20);
		panelProgetti.add(textFieldRuolo);
		textFieldRuolo.setColumns(10);
		
		JLabel labelRuolo = new JLabel("Ruolo nel progetto");
		labelRuolo.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRuolo.setBounds(49, 123, 105, 14);
		panelProgetti.add(labelRuolo);
		
		textFieldMinNumeroProgetti = new JTextField();
		textFieldMinNumeroProgetti.setBounds(49, 174, 68, 20);
		panelProgetti.add(textFieldMinNumeroProgetti);
		textFieldMinNumeroProgetti.setColumns(10);
		
		textFieldMaxNumeroProgetti = new JTextField();
		textFieldMaxNumeroProgetti.setColumns(10);
		textFieldMaxNumeroProgetti.setBounds(139, 174, 68, 20);
		panelProgetti.add(textFieldMaxNumeroProgetti);
		
		JLabel labelNumeroProgetti = new JLabel("Numero di progetti a cui partecipa");
		labelNumeroProgetti.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelNumeroProgetti.setBounds(49, 160, 198, 14);
		panelProgetti.add(labelNumeroProgetti);
		
		JLabel labelMinProgetti = new JLabel("Min");
		labelMinProgetti.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMinProgetti.setHorizontalAlignment(SwingConstants.CENTER);
		labelMinProgetti.setBounds(10, 177, 32, 14);
		panelProgetti.add(labelMinProgetti);
		
		JLabel labelMaxProgetti = new JLabel("Max");
		labelMaxProgetti.setHorizontalAlignment(SwingConstants.CENTER);
		labelMaxProgetti.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMaxProgetti.setBounds(215, 177, 32, 14);
		panelProgetti.add(labelMaxProgetti);
		
		JPanel panelMeeting = new JPanel();
		layeredPane.add(panelMeeting, "name_88616854995700");
		panelMeeting.setLayout(null);
		
		JComboBox comboBox = new JComboBox(new String[] {"Fisico", "Telematico", "Qualsiasi"});
		comboBox.setBounds(124, 12, 121, 18);
		panelMeeting.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 273, 464, 156);
		contentPane.add(scrollPane);
		
		JButton buttonConferma = new JButton("Conferma");
		buttonConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					controller.SelezionePartecipanteMeeting(controller.getDipendenteSelezionato(tableDipendenti.getSelectedRow()));
					
					controller.ChiudiFrameCercaPartecipanteMeeting();
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		buttonConferma.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonConferma.setBounds(106, 440, 242, 23);
		contentPane.add(buttonConferma);
		buttonConferma.setEnabled(false);
		
		tableDipendenti = new JTable();
		tableDipendenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDipendenti.setModel(new DefaultTableModel(
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
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tableDipendenti.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				if(tableDipendenti.getSelectedRowCount() > 0) {
					buttonConferma.setEnabled(true);
				}
			}
		});
		scrollPane.setViewportView(tableDipendenti);
		
		JButton buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ChiudiFrameCercaPartecipanteMeeting();
			}
		});
		buttonIndietro.setBounds(10, 5, 116, 23);
		contentPane.add(buttonIndietro);
		
		
		JComboBox comboBoxCercaDipendente = new JComboBox(new String[] {"Attributi", "Progetti a cui partecipa", "Meeting a cui partecipa"});
		comboBoxCercaDipendente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

					switch (e.getItem().toString()) {
					case "Attributi":
						layeredPane.removeAll();
						layeredPane.add(panelAttributi);
						layeredPane.repaint();
						layeredPane.revalidate();
						break;
					case "Progetti a cui partecipa":
						layeredPane.removeAll();
						layeredPane.add(panelProgetti);
						layeredPane.repaint();
						layeredPane.revalidate();
						break;
					case "Meeting a cui partecipa":
						layeredPane.removeAll();
						layeredPane.add(panelMeeting);
						layeredPane.repaint();
						layeredPane.revalidate();
					}
			}
		});
		comboBoxCercaDipendente.setBounds(281, 57, 168, 23);
		contentPane.add(comboBoxCercaDipendente);
		
		JLabel labelRicerca = new JLabel("Cerca per:");
		labelRicerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca.setBounds(281, 43, 89, 14);
		contentPane.add(labelRicerca);
		
		JButton buttonRicerca = new JButton("Cerca");
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					switch(comboBoxCercaDipendente.getSelectedItem().toString()) {
					case "Attributi":
						String codf = textFieldCodiceFiscale.getText();
						String nome = textFieldNome.getText();
						String cognome = textFieldCognome.getText();
						String minSalario = textFieldMinSalario.getText();
						String maxSalario = textFieldMaxSalario.getText();
						
						PopolaTabella(controller.RicercaDipendentePerAttributi(codf, nome, cognome, minSalario, maxSalario));
						break;
					case "Progetti a cui partecipa":
						String codp = textFieldCodiceProgetto.getText();
						String tipologia = textFieldTipologia.getText();
						String ambito = textFieldAmbito.getText();
						String ruolo = textFieldRuolo.getText();
						String minProgetti = textFieldMinNumeroProgetti.getText();
						String maxProgetti = textFieldMaxNumeroProgetti.getText();
						
						PopolaTabella(controller.RicercaDipendentePerProgetti(codp, tipologia, ambito, ruolo, minProgetti, maxProgetti));
						break;
					}
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		buttonRicerca.setBounds(335, 243, 104, 23);
		contentPane.add(buttonRicerca);
		
		
	}
	
	public void PopolaTabella(ArrayList<Dipendente> lista) {
		
		DefaultTableModel model = (DefaultTableModel) tableDipendenti.getModel();
		
		model.setRowCount(0);
		
		for (Dipendente d : lista)
			model.addRow(new Object[] {d.getCodF(), d.getNome(), d.getCognome(), d.getSalario()});
	}
}
