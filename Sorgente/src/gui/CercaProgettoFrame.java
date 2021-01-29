package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entità.Dipendente;
import entità.Progetto;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

public class CercaProgettoFrame extends JFrame {

	private Controller controller;
	
	private JPanel contentPane;
	
	private JTable tableProgetti;
	private JPopupMenu popupMenuTable;
	private JComboBox comboBoxCercaProgetto;
	
	private JTextField textFieldCodiceProgetto;
	private JTextField textFieldTipologia;
	private JTextField textFieldAmbito;
	
	private JTextField textFieldCodiceFiscale;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldMinSalario;
	private JTextField textFieldMaxSalario;
	private JTextField textFieldMinValutazione;
	private JTextField textFieldMaxValutazione;
	
	private JButton buttonRicerca;
	private JButton buttonInserimento;
	private JButton buttonIndietro;
	

	/**
	 * Create the frame.
	 */
	public CercaProgettoFrame(Controller c) {
		setResizable(false);
		
		controller=c;
		
		setTitle("Cerca Progetti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 305, 424, 156);
		contentPane.add(scrollPane);
		
		tableProgetti = new JTable();
		tableProgetti.addMouseListener(new MouseAdapter() {
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
		tableProgetti.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice", "Tipologia"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(tableProgetti);
		
		buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ChiudiFrameCercaProgettoInMainMenu();
			}
		});
		buttonIndietro.setBounds(12, 12, 116, 23);
		contentPane.add(buttonIndietro);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(9, 44, 245, 216);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelAttributi = new JPanel();
		layeredPane.add(panelAttributi, "name_93493188555400");
		panelAttributi.setLayout(null);
		
		JPanel panelPartecipanti = new JPanel();
		layeredPane.add(panelPartecipanti, "name_5022918159200");
		panelPartecipanti.setLayout(null);
		
		comboBoxCercaProgetto = new JComboBox(new String[]{"Attributi", "Partecipanti"});
		comboBoxCercaProgetto.setBounds(266, 44, 146, 23);
		comboBoxCercaProgetto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

					switch (e.getItem().toString()) {
					case "Attributi":
						layeredPane.removeAll();
						layeredPane.add(panelAttributi);
						layeredPane.repaint();
						layeredPane.revalidate();
						break;
					case "Partecipanti":
						layeredPane.removeAll();
						layeredPane.add(panelPartecipanti);
						layeredPane.repaint();
						layeredPane.revalidate();
					}
			}
		});
		contentPane.add(comboBoxCercaProgetto);
		
		JLabel lblCodiceProgetto = new JLabel("Codice Progetto");
		lblCodiceProgetto.setBounds(10, 11, 118, 14);
		panelAttributi.add(lblCodiceProgetto);
		lblCodiceProgetto.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldCodiceProgetto = new JTextField();
		textFieldCodiceProgetto.setBounds(10, 25, 158, 20);
		textFieldCodiceProgetto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)))
					e.consume();
			}
		});
		panelAttributi.add(textFieldCodiceProgetto);
		textFieldCodiceProgetto.setColumns(10);
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setBounds(10, 49, 68, 14);
		panelAttributi.add(lblTipologia);
		lblTipologia.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldTipologia = new JTextField();
		textFieldTipologia.setBounds(10, 63, 158, 20);
		panelAttributi.add(textFieldTipologia);
		textFieldTipologia.setColumns(10);
		
		JLabel lblAmbito = new JLabel("Ambito");
		lblAmbito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmbito.setBounds(10, 86, 68, 14);
		panelAttributi.add(lblAmbito);
		
		textFieldAmbito = new JTextField();
		textFieldAmbito.setColumns(10);
		textFieldAmbito.setBounds(10, 100, 158, 20);
		panelAttributi.add(textFieldAmbito);
		
		JLabel labelCodiceFiscale = new JLabel("Codice Fiscale");
		labelCodiceFiscale.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelCodiceFiscale.setBounds(10, 11, 107, 14);
		panelPartecipanti.add(labelCodiceFiscale);
		
		textFieldCodiceFiscale = new JTextField();
		textFieldCodiceFiscale.setColumns(10);
		textFieldCodiceFiscale.setBounds(10, 25, 158, 20);
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
		panelPartecipanti.add(textFieldCodiceFiscale);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelNome.setBounds(10, 48, 46, 14);
		panelPartecipanti.add(labelNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(10, 62, 158, 20);
		panelPartecipanti.add(textFieldNome);
		
		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelCognome.setBounds(10, 86, 68, 14);
		panelPartecipanti.add(labelCognome);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(10, 100, 158, 20);
		panelPartecipanti.add(textFieldCognome);
		
		JLabel labelSalario = new JLabel("Salario");
		labelSalario.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSalario.setBounds(10, 124, 68, 14);
		panelPartecipanti.add(labelSalario);
		
		textFieldMinSalario = new JTextField();
		textFieldMinSalario.setColumns(10);
		textFieldMinSalario.setBounds(39, 138, 68, 20);
		textFieldMinSalario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c=='.'))
					e.consume();
			}
		});
		panelPartecipanti.add(textFieldMinSalario);
		
		JLabel labelMin = new JLabel("Min");
		labelMin.setHorizontalAlignment(SwingConstants.CENTER);
		labelMin.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMin.setBounds(0, 141, 32, 14);
		panelPartecipanti.add(labelMin);
		
		textFieldMaxSalario = new JTextField();
		textFieldMaxSalario.setColumns(10);
		textFieldMaxSalario.setBounds(129, 138, 68, 20);
		textFieldMaxSalario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c=='.'))
					e.consume();
			}
		});
		panelPartecipanti.add(textFieldMaxSalario);
		
		JLabel labelMax = new JLabel("Max");
		labelMax.setHorizontalAlignment(SwingConstants.CENTER);
		labelMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMax.setBounds(205, 141, 32, 14);
		panelPartecipanti.add(labelMax);
		
		JLabel labelValutazione = new JLabel("Valutazione");
		labelValutazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelValutazione.setBounds(10, 169, 68, 14);
		panelPartecipanti.add(labelValutazione);
		
		JLabel labelMin_1 = new JLabel("Min");
		labelMin_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelMin_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMin_1.setBounds(0, 188, 32, 14);
		panelPartecipanti.add(labelMin_1);
		
		textFieldMinValutazione = new JTextField();
		textFieldMinValutazione.setColumns(10);
		textFieldMinValutazione.setBounds(39, 185, 68, 20);
		panelPartecipanti.add(textFieldMinValutazione);
		
		textFieldMaxValutazione = new JTextField();
		textFieldMaxValutazione.setColumns(10);
		textFieldMaxValutazione.setBounds(129, 185, 68, 20);
		panelPartecipanti.add(textFieldMaxValutazione);
		
		JLabel labelMax_1 = new JLabel("Max");
		labelMax_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelMax_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMax_1.setBounds(205, 188, 32, 14);
		panelPartecipanti.add(labelMax_1);
		
		JLabel lblNewLabel = new JLabel("Cerca per:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(266, 30, 82, 14);
		contentPane.add(lblNewLabel);
		
		buttonInserimento = new JButton("Inserisci Progetto");
		buttonInserimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameInserisciProgettoInCercaProgetto();
			}
		});
		buttonInserimento.setBounds(12, 271, 146, 23);
		contentPane.add(buttonInserimento);
		
		buttonRicerca = new JButton("Cerca");
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					switch (comboBoxCercaProgetto.getSelectedItem().toString()) {
					case "Attributi":
						String codp = textFieldCodiceProgetto.getText();
						String tipologia = textFieldTipologia.getText();
						String ambito = textFieldAmbito.getText();
						
						PopolaTabella(controller.RicercaProgettoPerAttributi(codp, tipologia, ambito));
						break;
					case "Partecipanti":
						String codf = textFieldCodiceFiscale.getText();
						String nome = textFieldNome.getText();
						String cognome = textFieldCognome.getText();
						String minSalario = textFieldMinSalario.getText();
						String maxSalario = textFieldMaxSalario.getText();
						String minValutazione = textFieldMinValutazione.getText();
						String maxValutazione = textFieldMaxValutazione.getText();
						
						PopolaTabella(controller.RicercaProgettoPerPartecipanti(codf, nome, cognome, minSalario, maxSalario, minValutazione, maxValutazione));
					}
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		buttonRicerca.setBounds(291, 271, 104, 23);
		contentPane.add(buttonRicerca);
	}
	
	public void PopolaTabella(ArrayList<Progetto> lista) {
		
		DefaultTableModel model = (DefaultTableModel) tableProgetti.getModel();
		
		model.setRowCount(0);
		
		for (Progetto p : lista)
			model.addRow(new Object[] {p.getCodice(), p.getTipologia()});
	}
	
	public void ApriPopupMenu(MouseEvent e) {
		
		popupMenuTable = new JPopupMenu();
		
		JMenuItem itemModifica = new JMenuItem("Modifica progetto");
		itemModifica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String codp, tipologia;
				int i = tableProgetti.getSelectedRow();
				
				controller.ApriFrameModificaProgettoInCercaProgetto(controller.getProgettoSelezionato(i));
			}
		});
		
		JMenuItem itemElimina = new JMenuItem("Elimina progetti");
		itemElimina.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				switch (JOptionPane.showConfirmDialog(null, "Eliminare le righe selezionate?", "Cancella righe", JOptionPane.YES_NO_OPTION)) {
				case JOptionPane.YES_OPTION:
					try {
						ArrayList<Progetto> selectedRows = new ArrayList<Progetto>();
						
						for (int i : tableProgetti.getSelectedRows()) {
							selectedRows.add(controller.getProgettoSelezionato(i));
						}
						
						for (Progetto row : selectedRows)
							controller.CancellazioneProgetto(row);
					}
					catch(SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					break;
				case JOptionPane.NO_OPTION:
					
				}
			}
		});
		
		if(tableProgetti.getSelectedRowCount() == 1)
			popupMenuTable.add(itemModifica);
		if(tableProgetti.getSelectedRowCount() > 0)
			popupMenuTable.add(itemElimina);
		popupMenuTable.show(e.getComponent(), e.getX(), e.getY());
	}
}
