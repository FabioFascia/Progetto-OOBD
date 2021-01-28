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

public class ModificaMeetingTelematicoFrame extends JFrame {

	private Controller controller;
	private MeetingTelematico oldMeeting;
	
	private JPanel contentPane;
	private JPopupMenu popupMenuTable;
	
	private JSpinner spinnerData;
	private JSpinner spinnerOraInizio;
	private JSpinner spinnerOraFine;
	private JTextField textFieldPiattaforma;
	private JTextField textFieldLimitePartecipanti;
	private JTable tableProgetto;
	private JTable tablePartecipanti;
	
	private JButton buttonModificaMeeting;
	private JButton buttonSelezionaProgetto;
	private JButton buttonSelezionaPartecipante;
	private JButton buttonAnnulla;
	
	/**
	 * Create the frame.
	 */
	public ModificaMeetingTelematicoFrame(Controller c, MeetingTelematico mt) {
		setResizable(false);
		
		controller = c;
		oldMeeting = mt;
		
		setTitle("Modifica Meeting");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameModificaMeetingTelematicoInCercaMeeting();
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
				controller.ChiudiFrameModificaMeetingTelematicoInCercaMeeting();
			}
		});
		buttonAnnulla.setBounds(10, 11, 89, 23);
		contentPane.add(buttonAnnulla);
		
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
		for(Dipendente d : oldMeeting.getPartecipanti()) {
			((DefaultTableModel) tablePartecipanti.getModel()).addRow(new Object[] {d.getCodF(), d.getNome(), d.getCognome(), d.getSalario(), d.getValutazione()});
		}
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
		
		spinnerData = new JSpinner();
		spinnerData.setBounds(10, 59, 80, 20);
		spinnerData.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerData.getEditor()).getTextField().setEditable(false);
		spinnerData.getModel().setValue(oldMeeting.getData());
		contentPane.add(spinnerData);
		
		spinnerOraInizio = new JSpinner();
		spinnerOraInizio.setBounds(10, 94, 63, 20);
		spinnerOraInizio.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizio.setEditor(new JSpinner.DateEditor(spinnerOraInizio, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizio.getEditor()).getTextField().setEditable(false);
		spinnerOraInizio.getModel().setValue(oldMeeting.getOraInizio());
		contentPane.add(spinnerOraInizio);
		
		spinnerOraFine = new JSpinner();
		spinnerOraFine.setBounds(10, 131, 63, 20);
		spinnerOraFine.setModel(new SpinnerDateModel(new Date(946767540000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFine.setEditor(new JSpinner.DateEditor(spinnerOraFine, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraFine.getEditor()).getTextField().setEditable(false);
		spinnerOraFine.getModel().setValue(oldMeeting.getOraFine());
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
		((DefaultTableModel) tableProgetto.getModel()).addRow(new Object[] {oldMeeting.getProgettoMeeting().getCodice(), oldMeeting.getProgettoMeeting().getTipologia()});
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
		
		buttonModificaMeeting = new JButton("Modifica Meeting");
		buttonModificaMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MeetingTelematico mt;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat of = new SimpleDateFormat("HH:mm:ss");
				
				oldMeeting.setData(java.sql.Date.valueOf(df.format((java.util.Date)spinnerData.getValue())));
				oldMeeting.setOraInizio(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraInizio.getValue())));
				oldMeeting.setOraFine(java.sql.Time.valueOf(of.format((java.util.Date)spinnerOraFine.getValue())));
				oldMeeting.setPiattaforma(textFieldPiattaforma.getText());
				oldMeeting.setNumeroLimite(Integer.parseInt(textFieldLimitePartecipanti.getText()));
				try {
					controller.ModificaMeetingTelematico(oldMeeting);
					controller.ChiudiFrameModificaMeetingTelematicoInCercaMeeting();
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		buttonModificaMeeting.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonModificaMeeting.setBounds(81, 437, 259, 23);
		contentPane.add(buttonModificaMeeting);
		
		JLabel lblNewLabel = new JLabel("Piattaforma");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(117, 80, 96, 14);
		contentPane.add(lblNewLabel);
		
		textFieldPiattaforma = new JTextField();
		textFieldPiattaforma.setColumns(10);
		textFieldPiattaforma.setBounds(117, 94, 142, 20);
		textFieldPiattaforma.setText(oldMeeting.getPiattaforma());
		contentPane.add(textFieldPiattaforma);
		
		textFieldLimitePartecipanti = new JTextField();
		textFieldLimitePartecipanti.setColumns(10);
		textFieldLimitePartecipanti.setBounds(282, 94, 61, 20);
		textFieldLimitePartecipanti.setText(String.valueOf(oldMeeting.getNumeroLimite()));
		contentPane.add(textFieldLimitePartecipanti);
		
		JLabel lblNewLabel_1 = new JLabel("Limite Partecipanti");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(282, 80, 114, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	public void addPartecipante(Dipendente d) throws SQLException {
		
		controller.InserimentoPartecipanteMeetingTelematico(oldMeeting, d);
		
		((DefaultTableModel) tablePartecipanti.getModel()).addRow(new Object[] {d.getCodF(), d.getNome(), d.getCognome(), d.getSalario()});
		
		AttivaButtonModifica();
	}
	public void deletePartecipante(int indice) throws SQLException {
		
		controller.CancellazionePartecipanteMeetingTelematico(oldMeeting, oldMeeting.getPartecipanti().get(indice));
		
		((DefaultTableModel) tablePartecipanti.getModel()).removeRow(indice);
		
		AttivaButtonModifica();
	}
	public void setProgetto(Progetto p) throws SQLException {
		
		DefaultTableModel model = (DefaultTableModel) tableProgetto.getModel();
		
		model.setRowCount(0);
		model.addRow(new Object[] {p.getCodice(), p.getTipologia()});
		oldMeeting.setProgettoMeeting(p);
		
		AttivaButtonModifica();
	}
	public void AttivaButtonModifica() {
		
		boolean ret = true;
		
		if(textFieldPiattaforma.getText().isBlank())
			ret = false;
		else if(tablePartecipanti.getModel().getRowCount() == 0)
			ret = false;
		else if(tableProgetto.getModel().getRowCount() == 0)
			ret = false;
		
		buttonModificaMeeting.setEnabled(ret);
	}
	public void ApriPopupMenu(MouseEvent e) {
		
		popupMenuTable = new JPopupMenu();
				
		JMenuItem itemElimina = new JMenuItem("Elimina righe");
			
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
		
		popupMenuTable.show(e.getComponent(), e.getX(), e.getY());
	}
}
