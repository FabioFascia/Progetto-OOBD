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
import entità.Sala;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificaMeetingFrame extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JTable tableSala;
	private JTable tablePartecipanti;
	private JTextField textFieldPiattaforma;
	private JTextField textFieldLimitePartecipanti;
	private JButton buttonModificaMeeting;
	private JTable tableProgetto;
	private Meeting oldMeeting;
    private JPopupMenu popupMenuTable;
	/**
	 * Create the frame.
	 */
	public ModificaMeetingFrame(Controller c, Meeting m) {
		controller = c;
		oldMeeting = m;
		setTitle("Inserisci Meeting");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameModificaMeetingInCercaMeeting();
			}
		});
		setBounds(100, 100, 440, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Annulla");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ChiudiFrameModificaMeetingInCercaMeeting();
			}
		});
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
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(tableSala);
		
		JButton btnNewButton_1 = new JButton("Seleziona sala...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameCercaSalaMeeting();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(0, 11, 146, 23);
		panelFisico.add(btnNewButton_1);
		
		JPanel panelTelematico = new JPanel();
		layeredPane.add(panelTelematico, "name_106301275497300");
		panelTelematico.setLayout(null);
		
		textFieldPiattaforma = new JTextField();
		textFieldPiattaforma.setBounds(10, 47, 142, 20);
		panelTelematico.add(textFieldPiattaforma);
		textFieldPiattaforma.setColumns(10);
		
		textFieldLimitePartecipanti = new JTextField();
		textFieldLimitePartecipanti.setBounds(175, 47, 61, 20);
		panelTelematico.add(textFieldLimitePartecipanti);
		textFieldLimitePartecipanti.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Piattaforma");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 33, 96, 14);
		panelTelematico.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Limite Partecipanti");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(175, 33, 114, 14);
		panelTelematico.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 276, 414, 150);
		contentPane.add(scrollPane_1);
		
		tablePartecipanti = new JTable();
		tablePartecipanti.setModel(new DefaultTableModel(
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
		tablePartecipanti.getColumnModel().getColumn(0).setPreferredWidth(103);
		scrollPane_1.setViewportView(tablePartecipanti);
		
		JButton btnNewButton_2 = new JButton("Seleziona partecipanti...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ApriFrameCercaPartecipanteMeeting();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_2.setBounds(10, 247, 183, 23);
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
						break;
					}
			}
		});
		contentPane.add(comboBoxTipoMeeting);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo Meeting:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(172, 15, 85, 14);
		contentPane.add(lblNewLabel_3);
		
		JSpinner spinnerData = new JSpinner();
		spinnerData.setBounds(10, 59, 80, 20);
		spinnerData.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerData.getEditor()).getTextField().setEditable(false);
		contentPane.add(spinnerData);
		
		JSpinner spinnerOraInizio = new JSpinner();
		spinnerOraInizio.setBounds(10, 94, 63, 20);
		spinnerOraInizio.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizio.setEditor(new JSpinner.DateEditor(spinnerOraInizio, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizio.getEditor()).getTextField().setEditable(false);
		contentPane.add(spinnerOraInizio);
		
		JSpinner spinnerOraFine = new JSpinner();
		spinnerOraFine.setBounds(10, 131, 63, 20);
		spinnerOraFine.setModel(new SpinnerDateModel(new Date(946767540000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFine.setEditor(new JSpinner.DateEditor(spinnerOraFine, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraFine.getEditor()).getTextField().setEditable(false);
		contentPane.add(spinnerOraFine);
		
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
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 192, 414, 44);
		contentPane.add(scrollPane_2);
		
		tableProgetto = new JTable();
		tableProgetto.addMouseListener(new MouseAdapter() {
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
		tableProgetto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice Progetto", "Tipologia"
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
		scrollPane_2.setViewportView(tableProgetto);
		
		JButton btnNewButton_3 = new JButton("Seleziona progetto...");
		btnNewButton_3.setBounds(10, 162, 183, 21);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ApriFrameCercaProgettoMeeting();
			}
		});
		
		buttonModificaMeeting = new JButton("Modifica Meeting");
		buttonModificaMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MeetingFisico mf;
				MeetingTelematico mt;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat of = new SimpleDateFormat("HH:mm:ss");
				switch(comboBoxTipoMeeting.getSelectedItem().toString()) {
				case "Fisico" :
//					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//					DateFormat of = new SimpleDateFormat("HH:mm:ss");
//					
//					mf = new MeetingFisico();
//					Sala s = new Sala();
//					mf.setData(java.sql.Date.valueOf(df.format((java.util.Date)spinnerData.getValue())));
//					mf.setOraI(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraInizio.getValue())));
//					mf.setOraF(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraFine.getValue())));
//					mf.setSalaRiunioni(s);
//					try {
//						controller.InserimentoMeetingFisico(mf);
//						controller.ChiudiFrameInserisciMeetingInCercaMeeting();
//					} catch (SQLException e1) {
//						
//						JOptionPane.showMessageDialog(null, e1.getMessage());
//						
//					}
					
					break;
				case "Telematico" :
					
//					mt= new MeetingTelematico();
//					mt.setData(java.sql.Date.valueOf(df.format((java.util.Date)spinnerData.getValue())));
//					mt.setOraI(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraInizio.getValue())));
//					mt.setOraF(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraFine.getValue())));
//					mt.setPiattaforma(textFieldPiattaforma.getText());
//					mt.setNumeroLimite(Integer.parseInt(textFieldLimitePartecipanti.getText()));
//					try {
//						controller.InserimentoMeetingTelematico(mt);
//						controller.ChiudiFrameInserisciMeetingInCercaMeeting();
//					} catch (SQLException e2) {
//						
//						JOptionPane.showMessageDialog(null, e2.getMessage());
//					}
					break;
				}
			}
		});
		buttonModificaMeeting.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonModificaMeeting.setEnabled(false);
		buttonModificaMeeting.setBounds(81, 437, 259, 23);
		contentPane.add(buttonModificaMeeting);
	}
	
	public void addPartecipante(Dipendente d) {
		
		((DefaultTableModel) tablePartecipanti.getModel()).addRow(new Object[] {d.getCodF(), d.getNome(), d.getCognome(), d.getSalario()});
		
		ToggleInsertButton();
	}
	public void setProgetto(Progetto p) {
		
		DefaultTableModel model = (DefaultTableModel) tableProgetto.getModel();
		
		model.setRowCount(0);
		model.addRow(new Object[] {p.getCodice(), p.getTipologia()});
		
		ToggleInsertButton();
	}
	public void setSala(Sala s) {
		
		DefaultTableModel model = (DefaultTableModel) tableSala.getModel();
		
		model.setRowCount(0);
		model.addRow(new Object[] {s.getCittà(), s.getProvincia(), s.getIndirizzo(), s.getNumeroCivico(), s.getNumeroPosti()});
		
		ToggleInsertButton();
	}
	public void ToggleInsertButton() {
		
		boolean ret = true;
		
		if(tableSala.getModel().getRowCount() == 0)
			ret = false;
		else if(tablePartecipanti.getModel().getRowCount() == 0)
			ret = false;
		else if(tableProgetto.getModel().getRowCount() == 0)
			ret = false;
		
		buttonModificaMeeting.setEnabled(ret);
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
		else if (e.getSource() == tableProgetto) {
			
			itemElimina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					switch (JOptionPane.showConfirmDialog(null, "Eliminare le righe selezionate?", "Cancella righe", JOptionPane.YES_NO_OPTION)) {
					case JOptionPane.YES_OPTION:
						while (tableProgetto.getSelectedRowCount() > 0) {
							int i = tableProgetto.getSelectedRow();
							((DefaultTableModel)tableProgetto.getModel()).removeRow(i);
						}
						break;
					case JOptionPane.NO_OPTION:
						break;
					}
				}
			});
			if(tableProgetto.getSelectedRowCount() > 0)
				popupMenuTable.add(itemElimina);
		}
		
		popupMenuTable.show(e.getComponent(), e.getX(), e.getY());
	}
	public void deletePartecipante(int indice) throws SQLException {
		
		((DefaultTableModel) tablePartecipanti.getModel()).removeRow(indice);
		
		controller.CancellazionePartecipanteMeeting(oldMeeting, oldMeeting.getPartecipanti().get(indice));
	}
}
