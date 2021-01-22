package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entit�.Dipendente;
import entit�.Sala;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CercaSalaFrame extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JPopupMenu popupMenuTable;
	private JTextField textFieldCitt�;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldProvincia;
	private JTextField textFieldNumeroCivico;
	private JTextField textFieldMinNumeroPosti;
	private JTextField textFieldMaxNumeroPosti;
	private JTable tableSale;

	/**
	 * Create the frame.
	 */
	public CercaSalaFrame(Controller c) {
		setTitle("Cerca Sale Riunioni");
		
		controller = c;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		setBounds(100, 100, 500, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrameCercaSalaInMainMenu();
			}
		});
		buttonIndietro.setBounds(10, 11, 116, 23);
		contentPane.add(buttonIndietro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 245, 484, 156);
		contentPane.add(scrollPane);
		
		tableSale = new JTable();
		tableSale.setModel(new DefaultTableModel(
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
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tableSale.addMouseListener(new MouseAdapter() {
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
		scrollPane.setViewportView(tableSale);
		
		JLabel labelRicerca = new JLabel("Cerca per:");
		labelRicerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelRicerca.setBounds(285, 11, 89, 14);
		contentPane.add(labelRicerca);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 45, 271, 155);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelAttributi = new JPanel();
		layeredPane.add(panelAttributi, "name_14079923771700");
		panelAttributi.setLayout(null);
		
		JComboBox comboBoxCercaSala = new JComboBox(new Object[]{"Attributi", "Meeting Fisici tenutivisi"});
		comboBoxCercaSala.setBounds(285, 25, 168, 23);
		comboBoxCercaSala.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

					switch (e.getItem().toString()) {
					case "Attributi":
						layeredPane.removeAll();
						layeredPane.add(panelAttributi);
						layeredPane.repaint();
						layeredPane.revalidate();
						break;
					}
			}
		});
		contentPane.add(comboBoxCercaSala);
		
		JLabel labelCitt� = new JLabel("Citt\u00E0");
		labelCitt�.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelCitt�.setBounds(12, 12, 107, 14);
		panelAttributi.add(labelCitt�);
		
		textFieldCitt� = new JTextField();
		textFieldCitt�.setColumns(10);
		textFieldCitt�.setBounds(12, 26, 158, 20);
		panelAttributi.add(textFieldCitt�);
		
		JLabel labelIndirizzo = new JLabel("Indirizzo");
		labelIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelIndirizzo.setBounds(12, 47, 107, 14);
		panelAttributi.add(labelIndirizzo);
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setColumns(10);
		textFieldIndirizzo.setBounds(12, 61, 158, 20);
		panelAttributi.add(textFieldIndirizzo);
		
		JLabel labelNumeroPosti = new JLabel("Numero Posti");
		labelNumeroPosti.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelNumeroPosti.setBounds(12, 82, 107, 14);
		panelAttributi.add(labelNumeroPosti);
		
		textFieldProvincia = new JTextField();
		textFieldProvincia.setBounds(182, 26, 42, 20);
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
		panelAttributi.add(textFieldProvincia);
		textFieldProvincia.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Provincia");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(182, 12, 70, 14);
		panelAttributi.add(lblNewLabel);
		
		textFieldNumeroCivico = new JTextField();
		textFieldNumeroCivico.setBounds(182, 61, 42, 20);
		textFieldNumeroCivico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				
				if(!Character.isDigit(keyChar))
					e.consume();
			}
		});
		panelAttributi.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Numero Civico");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(182, 47, 82, 14);
		panelAttributi.add(lblNewLabel_1);
		
		textFieldMinNumeroPosti = new JTextField();
		textFieldMinNumeroPosti.setColumns(10);
		textFieldMinNumeroPosti.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				
				if(!Character.isDigit(keyChar))
					e.consume();
			}
		});
		textFieldMinNumeroPosti.setBounds(39, 99, 68, 20);
		panelAttributi.add(textFieldMinNumeroPosti);
		
		JLabel labelMin = new JLabel("Min");
		labelMin.setHorizontalAlignment(SwingConstants.CENTER);
		labelMin.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMin.setBounds(0, 102, 32, 14);
		panelAttributi.add(labelMin);
		
		textFieldMaxNumeroPosti = new JTextField();
		textFieldMaxNumeroPosti.setColumns(10);
		textFieldMinNumeroPosti.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				
				if(!Character.isDigit(keyChar))
					e.consume();
			}
		});
		textFieldMaxNumeroPosti.setBounds(129, 99, 68, 20);
		panelAttributi.add(textFieldMaxNumeroPosti);
		
		JLabel labelMax = new JLabel("Max");
		labelMax.setHorizontalAlignment(SwingConstants.CENTER);
		labelMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMax.setBounds(206, 102, 32, 14);
		panelAttributi.add(labelMax);
		
		JButton buttonRicerca = new JButton("Cerca");
		buttonRicerca.setBounds(349, 211, 104, 23);
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					switch(comboBoxCercaSala.getSelectedItem().toString()) {
					case "Attributi":
						String citt� = textFieldCitt�.getText();
						String provincia = textFieldProvincia.getText();
						String indirizzo = textFieldIndirizzo.getText();
						String numeroCivico = textFieldNumeroCivico.getText();
						String minPosti = textFieldMinNumeroPosti.getText();
						String maxPosti = textFieldMaxNumeroPosti.getText();
						
						PopolaTabella(controller.RicercaSalaPerAttributi(citt�, provincia, indirizzo, numeroCivico, minPosti, maxPosti));
//						break;
//					case "Progetti a cui partecipa":
//						String codp = textFieldCodiceProgetto.getText();
//						String tipologia = textFieldTipologia.getText();
//						String ambito = textFieldAmbito.getText();
//						String ruolo = textFieldRuolo.getText();
//						String minProgetti = textFieldMinNumeroProgetti.getText();
//						String maxProgetti = textFieldMaxNumeroProgetti.getText();
//						
//						PopolaTabella(controller.RicercaDipendentePerProgetti(codp, tipologia, ambito, ruolo, minProgetti, maxProgetti));
//						break;
					}
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		contentPane.add(buttonRicerca);
		
		JButton buttonInserisciSala = new JButton("Inserisci Sala");
		buttonInserisciSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameInserisciSalaInCercaSala();
			}
		});
		buttonInserisciSala.setBounds(10, 211, 170, 23);
		contentPane.add(buttonInserisciSala);
	}
	
	public void PopolaTabella(ArrayList<Sala> lista) {
		
		DefaultTableModel model = (DefaultTableModel) tableSale.getModel();
		
		((DefaultTableModel) tableSale.getModel()).setRowCount(0);
		
		for (Sala s : lista)
			model.addRow(new Object[] {s.getCitt�(), s.getProvincia(), s.getIndirizzo(), s.getNumeroCivico(), s.getNumeroPosti()});
	}
	
	public void ShowPopupMenu(MouseEvent e) {
		
		popupMenuTable = new JPopupMenu();
		
		JMenuItem itemModifica = new JMenuItem("Modifica riga");
		itemModifica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int i = tableSale.getSelectedRow();
				
				controller.ApriFrameModificaSalaInCercaSala(controller.getSalaSelezionata(i));
			}
		});
		
		JMenuItem itemElimina = new JMenuItem("Elimina righe");
		itemElimina.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				switch (JOptionPane.showConfirmDialog(null, "Eliminare le righe selezionate?", "Cancella righe", JOptionPane.YES_NO_OPTION)) {
				case JOptionPane.YES_OPTION:
					try {
						ArrayList<Sala> selectedRows = new ArrayList<Sala>();
						
						for (int i : tableSale.getSelectedRows()) {
							selectedRows.add(controller.getSalaSelezionata(i));
						}
						
						for (Sala row : selectedRows)
							controller.CancellazioneSala(row);
					}
					catch(SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					break;
				case JOptionPane.NO_OPTION:
					
				}
			}
		});
		
		if(tableSale.getSelectedRowCount() == 1)
			popupMenuTable.add(itemModifica);
		if(tableSale.getSelectedRowCount() > 0)
			popupMenuTable.add(itemElimina);
		popupMenuTable.show(e.getComponent(), e.getX(), e.getY());
	}
}
