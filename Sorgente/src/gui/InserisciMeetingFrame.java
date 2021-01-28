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
import entit�.Sala;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class InserisciMeetingFrame extends JFrame {

	private Controller controller;
	
	private JPanel contentPane;
	private JPopupMenu popupMenuTable;
	private JComboBox comboBoxTipoMeeting;
	
	private JSpinner spinnerData;
	private JSpinner spinnerOraInizio;
	private JSpinner spinnerOraFine;
	private JTable tableProgetto;
	private JTable tablePartecipanti;
	
	private JTable tableSala;
	
	private JTextField textFieldPiattaforma;
	private JTextField textFieldLimitePartecipanti;
	
	private JButton buttonSelezionaSala;
	private JButton buttonSelezionaProgetto;
	private JButton buttonSelezionaPartecipante;
	private JButton btnInserisciMeeting;
	private JButton buttonAnnulla;

	/**
	 * Create the frame.
	 */
	public InserisciMeetingFrame(Controller c) {
		setResizable(false);
		controller = c;
		setTitle("Inserisci Meeting");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameInserisciMeetingInCercaMeeting();
			}
		});
		setBounds(100, 100, 440, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ChiudiFrameInserisciMeetingInCercaMeeting();
			}
		});
		buttonAnnulla.setBounds(10, 11, 89, 23);
		contentPane.add(buttonAnnulla);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(103, 45, 319, 114);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelFisico = new JPanel();
		layeredPane.add(panelFisico, "name_98679284745000");
		panelFisico.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 43, 309, 41);
		panelFisico.add(scrollPane);
		
		tableSala = new JTable();
		tableSala.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Citt\u00E0", "Provincia", "Indirizzo", "Numero Civico", "Numero Posti", "Codice"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tableSala);
		
		buttonSelezionaSala = new JButton("Seleziona sala...");
		buttonSelezionaSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ApriFrameCercaSalaMeeting();
			}
		});
		buttonSelezionaSala.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonSelezionaSala.setBounds(0, 11, 146, 23);
		panelFisico.add(buttonSelezionaSala);
		
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
				"Codice Fiscale", "Nome", "Cognome", "Salario", "Valutazione"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Float.class, Integer.class
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
		tablePartecipanti.getColumnModel().getColumn(0).setPreferredWidth(103);
		scrollPane_1.setViewportView(tablePartecipanti);
		
		buttonSelezionaPartecipante = new JButton("Seleziona partecipanti...");
		buttonSelezionaPartecipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ApriFrameCercaPartecipanteMeeting();
			}
		});
		buttonSelezionaPartecipante.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonSelezionaPartecipante.setBounds(10, 247, 183, 23);
		contentPane.add(buttonSelezionaPartecipante);
		
		comboBoxTipoMeeting = new JComboBox(new String[] {"Fisico", "Telematico"});
		comboBoxTipoMeeting.setBounds(259, 12, 155, 22);
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
					AttivaButtonInserimento();
			}
		});
		contentPane.add(comboBoxTipoMeeting);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo Meeting:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(169, 16, 85, 14);
		contentPane.add(lblNewLabel_3);
		
		spinnerData = new JSpinner();
		spinnerData.setBounds(10, 59, 80, 20);
		spinnerData.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerData.getEditor()).getTextField().setEditable(false);
		contentPane.add(spinnerData);
		
		spinnerOraInizio = new JSpinner();
		spinnerOraInizio.setBounds(10, 94, 63, 20);
		spinnerOraInizio.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizio.setEditor(new JSpinner.DateEditor(spinnerOraInizio, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizio.getEditor()).getTextField().setEditable(false);
		contentPane.add(spinnerOraInizio);
		
		spinnerOraFine = new JSpinner();
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
		
		buttonSelezionaProgetto = new JButton("Seleziona progetto...");
		buttonSelezionaProgetto.setBounds(10, 162, 183, 21);
		contentPane.add(buttonSelezionaProgetto);
		buttonSelezionaProgetto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonSelezionaProgetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ApriFrameCercaProgettoMeeting();
			}
		});
		
		btnInserisciMeeting = new JButton("Inserisci Meeting");
		btnInserisciMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MeetingFisico mf;
				MeetingTelematico mt;
				Progetto p;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat of = new SimpleDateFormat("HH:mm:ss");
				switch(comboBoxTipoMeeting.getSelectedItem().toString()) {
				case "Fisico" :
					
					mf = new MeetingFisico();
					Sala s = new Sala(Integer.parseInt(tableSala.getModel().getValueAt(0, 5).toString()));
					p = new Progetto(Integer.parseInt(tableProgetto.getModel().getValueAt(0, 0).toString()));
					mf.setData(java.sql.Date.valueOf(df.format((java.util.Date)spinnerData.getValue())));
					mf.setOraInizio(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraInizio.getValue())));
					mf.setOraFine(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraFine.getValue())));
					mf.setSalaRiunioni(s);
					mf.setProgettoMeeting(p);
					
					for(int i : tablePartecipanti.getSelectedRows()) {
						Dipendente d = new Dipendente(tablePartecipanti.getModel().getValueAt(i, 0).toString());
						mf.addPartecipante(d);
					}
					try {
						controller.InserimentoMeetingFisico(mf);
						controller.ChiudiFrameInserisciMeetingInCercaMeeting();
					}
					catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					
					break;
				case "Telematico" :
					
					mt = new MeetingTelematico();
					p = new Progetto(Integer.parseInt(tableProgetto.getModel().getValueAt(0, 0).toString()));
					mt.setData(java.sql.Date.valueOf(df.format((java.util.Date)spinnerData.getValue())));
					mt.setOraInizio(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraInizio.getValue())));
					mt.setOraFine(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraFine.getValue())));
					mt.setPiattaforma(textFieldPiattaforma.getText());
					if(textFieldLimitePartecipanti.getText().isBlank())
						mt.setNumeroLimite(-1);
					else
						mt.setNumeroLimite(Integer.parseInt(textFieldLimitePartecipanti.getText()));
					mt.setProgettoMeeting(p);
					
					for(int i : tablePartecipanti.getSelectedRows()) {
						Dipendente d = new Dipendente(tablePartecipanti.getModel().getValueAt(i, 0).toString());
						mt.addPartecipante(d);
					}
					try {
						controller.InserimentoMeetingTelematico(mt);
						controller.ChiudiFrameInserisciMeetingInCercaMeeting();
					}
					catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					break;
				}
			}
		});
		btnInserisciMeeting.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInserisciMeeting.setEnabled(false);
		btnInserisciMeeting.setBounds(81, 437, 259, 23);
		contentPane.add(btnInserisciMeeting);
	}
	
	public void ApriPopupMenu(MouseEvent e) {
		
		popupMenuTable = new JPopupMenu();
				
		JMenuItem itemElimina = new JMenuItem("Elimina righe");
		
		itemElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch (JOptionPane.showConfirmDialog(null, "Eliminare le righe selezionate?", "Cancella righe", JOptionPane.YES_NO_OPTION)) {
				case JOptionPane.YES_OPTION:
					while (tablePartecipanti.getSelectedRowCount() > 0)
						((DefaultTableModel)tablePartecipanti.getModel()).removeRow(tablePartecipanti.getSelectedRow());
					break;
				case JOptionPane.NO_OPTION:
					break;
				}
			}
		});
		if(tablePartecipanti.getSelectedRowCount() > 0)
			popupMenuTable.add(itemElimina);
		
		popupMenuTable.show(e.getComponent(), e.getX(), e.getY());
	}
	
	public void addPartecipante(Dipendente d) {
		
		((DefaultTableModel) tablePartecipanti.getModel()).addRow(new Object[] {d.getCodF(), d.getNome(), d.getCognome(), d.getSalario(), d.getValutazione()});
		
		AttivaButtonInserimento();
	}
	public void setProgetto(Progetto p) {
		
		DefaultTableModel model = (DefaultTableModel) tableProgetto.getModel();
		
		model.setRowCount(0);
		model.addRow(new Object[] {p.getCodice(), p.getTipologia()});
		
		AttivaButtonInserimento();
	}
	public void setSala(Sala s) {
		
		DefaultTableModel model = (DefaultTableModel) tableSala.getModel();
		
		model.setRowCount(0);
		model.addRow(new Object[] {s.getCitt�(), s.getProvincia(), s.getIndirizzo(), s.getNumeroCivico(), s.getNumeroPosti(), s.getCodice()});
		
		AttivaButtonInserimento();
	}
	public void AttivaButtonInserimento() {
		
		boolean ret = true;
		
		switch(comboBoxTipoMeeting.getSelectedItem().toString()) {
		
		case "Fisico":
			if(tableSala.getModel().getRowCount() == 0)
				ret = false;
			else if(tablePartecipanti.getModel().getRowCount() == 0)
				ret = false;
			else if(tableProgetto.getModel().getRowCount() == 0)
				ret = false;
			btnInserisciMeeting.setEnabled(ret);
			break;
			
		case "Telematico":
			if(textFieldPiattaforma.getText().isBlank())
				ret = false;
			else if(tablePartecipanti.getModel().getRowCount() == 0)
				ret = false;
			else if(tableProgetto.getModel().getRowCount() == 0)
				ret = false;
			btnInserisciMeeting.setEnabled(ret);
		}
	}
}
