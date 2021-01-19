package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import entit�.Dipendente;
import entit�.Partecipante;
import entit�.Progetto;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class ModificaProgettoFrame extends JFrame {

	private Controller controller;
	private JPanel contentPane;
	private JTextField textFieldTipologia;
	private JTextField textFieldAmbito;
	private JTable tableAmbiti;
	private JTable tableProjectManager;
	private JTable tablePartecipanti;
	private JButton buttonModificaProgetto;
	private JPopupMenu popupMenuTable;
	private Progetto oldProgetto;

	/**
	 * Create the frame.
	 */
	public ModificaProgettoFrame(Controller c, Progetto p) {
		
		setTitle("Modifica Progetto");
		controller = c;
		oldProgetto = p;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameModificaProgettoInCercaProgetto();
			}
		});
		setBounds(100, 100, 580, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Annulla");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ChiudiFrameModificaProgettoInCercaProgetto();
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel labelTipologia = new JLabel("Tipologia");
		labelTipologia.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelTipologia.setBounds(10, 48, 97, 14);
		contentPane.add(labelTipologia);
		
		JLabel lblAmbito = new JLabel("Ambito");
		lblAmbito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmbito.setBounds(10, 93, 97, 14);
		contentPane.add(lblAmbito);
		
		JButton buttonInserisciAmbito = new JButton("Ok");
		buttonInserisciAmbito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addAmbito(textFieldAmbito.getText());
					textFieldAmbito.setText("");
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		buttonInserisciAmbito.setBounds(32, 134, 119, 23);
		contentPane.add(buttonInserisciAmbito);
		buttonInserisciAmbito.setEnabled(false);
		
		textFieldAmbito = new JTextField();
		textFieldAmbito.getDocument().addDocumentListener(new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) {
				updateButton();
			}
			
			public void removeUpdate(DocumentEvent e) {
				updateButton();
			}
			
			public void insertUpdate(DocumentEvent e) {
				updateButton();
			}
			
			public void updateButton() {
				if(textFieldAmbito.getText().isBlank())
					buttonInserisciAmbito.setEnabled(false);
				else
					buttonInserisciAmbito.setEnabled(true);
			}
		});
		textFieldAmbito.setColumns(10);
		textFieldAmbito.setBounds(10, 107, 158, 20);
		contentPane.add(textFieldAmbito);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 158, 128);
		contentPane.add(scrollPane);
		
		tableAmbiti = new JTable();
		tableAmbiti.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ambito"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tableAmbiti.addMouseListener(new MouseAdapter() {
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
		scrollPane.setViewportView(tableAmbiti);
		for(String a : p.getAmbiti()) {
			((DefaultTableModel) tableAmbiti.getModel()).addRow(new Object[] {a});
		}
		
		JLabel lblNewLabel = new JLabel("Project Manager");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(203, 48, 119, 14);
		contentPane.add(lblNewLabel);
		
		JButton buttonProjectManager = new JButton("Cerca Dipendente...");
		buttonProjectManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameCercaProjectManager();
			}
		});
		buttonProjectManager.setBounds(203, 61, 158, 23);
		contentPane.add(buttonProjectManager);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(203, 93, 338, 38);
		contentPane.add(scrollPane_1);
		
		
		
		tableProjectManager = new JTable();
		tableProjectManager.setRowSelectionAllowed(false);
		scrollPane_1.setViewportView(tableProjectManager);
		tableProjectManager.setModel(new DefaultTableModel(
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
		((DefaultTableModel) tableProjectManager.getModel()).addRow(new Object[] {p.getProjectManager().getCodF(), p.getProjectManager().getNome(), p.getProjectManager().getCognome(), p.getProjectManager().getSalario()});
		
		JLabel lblPartecipanti = new JLabel("Partecipanti");
		lblPartecipanti.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPartecipanti.setBounds(203, 138, 119, 14);
		contentPane.add(lblPartecipanti);
		
		JButton buttonPartecipanti = new JButton("Cerca Dipendenti...");
		buttonPartecipanti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameCercaPartecipante();
			}
		});
		buttonPartecipanti.setBounds(203, 151, 158, 23);
		contentPane.add(buttonPartecipanti);
		
		JTextArea textAreaDescrizione = new JTextArea();
		textAreaDescrizione.setWrapStyleWord(true);
		textAreaDescrizione.setLineWrap(true);
		textAreaDescrizione.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textAreaDescrizione.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textAreaDescrizione.getText().length() >= 200)
		            e.consume();
			}
		});
		textAreaDescrizione.setBounds(10, 317, 531, 63);
		textAreaDescrizione.setText(p.getDescrizione());
		contentPane.add(textAreaDescrizione);
		
		buttonModificaProgetto = new JButton("Modifica Progetto");
		buttonModificaProgetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					oldProgetto.setTipologia(textFieldTipologia.getText());
					oldProgetto.setDescrizione(textAreaDescrizione.getText());
					
					controller.ModificaProjectManager(oldProgetto, oldProgetto.getProjectManager());
					controller.ModificaProgetto(oldProgetto);
					
					controller.ChiudiFrameModificaProgettoInCercaProgetto();
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		buttonModificaProgetto.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonModificaProgetto.setBounds(148, 391, 259, 23);
		contentPane.add(buttonModificaProgetto);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(203, 185, 338, 111);
		contentPane.add(scrollPane_1_1);
		
		tablePartecipanti = new JTable();
		tablePartecipanti.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice Fiscale", "Nome", "Cognome", "Salario", "Ruolo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Float.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablePartecipanti.addMouseListener(new MouseAdapter() {
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
		scrollPane_1_1.setViewportView(tablePartecipanti);
		for(Partecipante par : p.getPartecipanti()) {
			((DefaultTableModel) tablePartecipanti.getModel()).addRow(new Object[] {par.getDipendente().getCodF(), par.getDipendente().getNome(), par.getDipendente().getCognome(), par.getDipendente().getSalario(), par.getRuolo()});
		}
		
		textFieldTipologia = new JTextField();
		textFieldTipologia.setColumns(10);
		textFieldTipologia.setBounds(10, 62, 158, 20);
		textFieldTipologia.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				ToggleUpdateButton();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				ToggleUpdateButton();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				ToggleUpdateButton();
			}
			
		});
		textFieldTipologia.setText(oldProgetto.getTipologia());
		contentPane.add(textFieldTipologia);
		
		JLabel lblNewLabel_1 = new JLabel("Descrizione");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 302, 89, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	public void ShowPopupMenu(MouseEvent e) {
		
		popupMenuTable = new JPopupMenu();
				
		JMenuItem itemElimina = new JMenuItem("Elimina righe");
		if (e.getSource() == tablePartecipanti) {
			
			itemElimina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					switch (JOptionPane.showConfirmDialog(null, "Eliminare le righe selezionate?", "Cancella righe", JOptionPane.YES_NO_OPTION)) {
					case JOptionPane.YES_OPTION:
						while (tablePartecipanti.getSelectedRowCount() > 0) {
							try {
								deletePartecipante(tablePartecipanti.getSelectedRow());
							}
							catch (SQLException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage());
							}
						}
						break;
					case JOptionPane.NO_OPTION:
						break;
					}
				}
			});
			if(tablePartecipanti.getSelectedRowCount() > 0)
				popupMenuTable.add(itemElimina);
		}
		else if (e.getSource() == tableAmbiti) {
			
			itemElimina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					switch (JOptionPane.showConfirmDialog(null, "Eliminare le righe selezionate?", "Cancella righe", JOptionPane.YES_NO_OPTION)) {
					case JOptionPane.YES_OPTION:
						while (tableAmbiti.getSelectedRowCount() > 0) {
							int i = tableAmbiti.getSelectedRow();
							((DefaultTableModel)tableAmbiti.getModel()).removeRow(i);
						}
						break;
					case JOptionPane.NO_OPTION:
						break;
					}
				}
			});
			if(tableAmbiti.getSelectedRowCount() > 0)
				popupMenuTable.add(itemElimina);
		}
		
		popupMenuTable.show(e.getComponent(), e.getX(), e.getY());
	}
	
	public void ToggleUpdateButton() {
		boolean ret = true;
		
		if(textFieldTipologia.getText().isBlank())
			ret = false;
		else if(tableProjectManager.getModel().getRowCount() == 0)
			ret = false;
		else {			
			for(int i = 0 ; i < tablePartecipanti.getModel().getRowCount() ; i++) {
				if(tablePartecipanti.getModel().getValueAt(i, 4).toString().isBlank())
					ret = false;
			}
		}
		
		buttonModificaProgetto.setEnabled(ret);
	}
	
	public void setProjectManager(Dipendente pm) throws SQLException {
		
		controller.ModificaProjectManager(oldProgetto, pm);
		
		DefaultTableModel model = (DefaultTableModel) tableProjectManager.getModel();
		
		model.setRowCount(0);
		
		model.addRow(new Object[] {pm.getCodF(), pm.getNome(), pm.getCognome(), pm.getSalario()});
	}
	
	public void addAmbito(String ambito) throws SQLException {
		
		DefaultTableModel model = (DefaultTableModel) tableAmbiti.getModel();
		
		model.addRow(new Object[] {ambito});
		
		controller.InserimentoAmbito(oldProgetto, ambito);
	}
	
	public void deleteAmbito(int indice) throws SQLException {
		
		((DefaultTableModel) tableAmbiti.getModel()).removeRow(indice);
		
		controller.CancellazioneAmbito(oldProgetto, oldProgetto.getAmbiti().get(indice));
	}
	
	public void addPartecipante(Dipendente d, String ruolo) throws SQLException {
		
		DefaultTableModel model = (DefaultTableModel) tablePartecipanti.getModel();
		
		model.addRow(new Object[] {d.getCodF(), d.getNome(), d.getCognome(), d.getSalario(), ruolo});
		
		controller.InserimentoPartecipante(d, oldProgetto, ruolo);
	}
	
	public void deletePartecipante(int indice) throws SQLException {
		
		((DefaultTableModel) tablePartecipanti.getModel()).removeRow(indice);
		
		controller.CancellazionePartecipante(oldProgetto, oldProgetto.getPartecipanti().get(indice));
	}
}
