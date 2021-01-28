package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import entità.Dipendente;
import entità.Sala;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class CercaSalaMeetingFrame extends JFrame {

	private Controller controller;
	
	private JPanel contentPane;
	private JTable tableSale;
	
	private JTextField textFieldCittà;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldProvincia;
	private JTextField textFieldNumeroCivico;
	private JTextField textFieldMinNumeroPosti;
	private JTextField textFieldMaxNumeroPosti;
	
	private JButton buttonConferma;
	private JButton buttonRicerca;
	private JButton buttonIndietro;

	/**
	 * Create the frame.
	 */
	public CercaSalaMeetingFrame(Controller c) {
		setResizable(false);
		setTitle("Cerca Sale Riunioni");
		
		controller = c;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				controller.ChiudiFrameCercaSalaMeeting();
			}
		});
		setBounds(100, 100, 490, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		buttonIndietro = new JButton("Indietro");
		buttonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ChiudiFrameCercaSalaMeeting();
			}
		});
		buttonIndietro.setBounds(10, 11, 116, 23);
		contentPane.add(buttonIndietro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 206, 474, 156);
		contentPane.add(scrollPane);
		
		buttonConferma = new JButton("Conferma");
		buttonConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.SelezioneSalaMeeting(controller.getSalaSelezionata(tableSale.getSelectedRow()));
					
					controller.ChiudiFrameCercaSalaMeeting();
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		buttonConferma.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonConferma.setEnabled(false);
		buttonConferma.setBounds(108, 373, 242, 23);
		contentPane.add(buttonConferma);
		
		tableSale = new JTable();
		tableSale.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSale.setModel(new DefaultTableModel(
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
		tableSale.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				if(tableSale.getSelectedRowCount() > 0) {
					buttonConferma.setEnabled(true);
				}
			}
		});

		scrollPane.setViewportView(tableSale);
		
		buttonRicerca = new JButton("Cerca");
		buttonRicerca.setBounds(353, 172, 104, 23);
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String città = textFieldCittà.getText();
					String provincia = textFieldProvincia.getText();
					String indirizzo = textFieldIndirizzo.getText();
					String numeroCivico = textFieldNumeroCivico.getText();
					String minPosti = textFieldMinNumeroPosti.getText();
					String maxPosti = textFieldMaxNumeroPosti.getText();
					
					PopolaTabella(controller.RicercaSalaPerAttributi(città, provincia, indirizzo, numeroCivico, minPosti, maxPosti));
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		contentPane.add(buttonRicerca);
		
		JLabel labelCittà = new JLabel("Citt\u00E0");
		labelCittà.setBounds(22, 45, 107, 14);
		contentPane.add(labelCittà);
		labelCittà.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldCittà = new JTextField();
		textFieldCittà.setBounds(22, 59, 158, 20);
		contentPane.add(textFieldCittà);
		textFieldCittà.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Provincia");
		lblNewLabel.setBounds(192, 45, 70, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldProvincia = new JTextField();
		textFieldProvincia.setBounds(192, 59, 42, 20);
		contentPane.add(textFieldProvincia);
		textFieldProvincia.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Numero Civico");
		lblNewLabel_1.setBounds(192, 80, 82, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldNumeroCivico = new JTextField();
		textFieldNumeroCivico.setBounds(192, 94, 42, 20);
		contentPane.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setColumns(10);
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(22, 94, 158, 20);
		contentPane.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		
		JLabel labelIndirizzo = new JLabel("Indirizzo");
		labelIndirizzo.setBounds(22, 80, 107, 14);
		contentPane.add(labelIndirizzo);
		labelIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel labelNumeroPosti = new JLabel("Numero Posti");
		labelNumeroPosti.setBounds(22, 115, 107, 14);
		contentPane.add(labelNumeroPosti);
		labelNumeroPosti.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel labelMin = new JLabel("Min");
		labelMin.setBounds(10, 133, 32, 14);
		contentPane.add(labelMin);
		labelMin.setHorizontalAlignment(SwingConstants.CENTER);
		labelMin.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldMinNumeroPosti = new JTextField();
		textFieldMinNumeroPosti.setBounds(49, 130, 68, 20);
		contentPane.add(textFieldMinNumeroPosti);
		textFieldMinNumeroPosti.setColumns(10);
		
		textFieldMaxNumeroPosti = new JTextField();
		textFieldMaxNumeroPosti.setBounds(139, 130, 68, 20);
		contentPane.add(textFieldMaxNumeroPosti);
		textFieldMaxNumeroPosti.setColumns(10);
		
		JLabel labelMax = new JLabel("Max");
		labelMax.setBounds(216, 133, 32, 14);
		contentPane.add(labelMax);
		labelMax.setHorizontalAlignment(SwingConstants.CENTER);
		labelMax.setFont(new Font("Tahoma", Font.BOLD, 11));
	}
	
	public void PopolaTabella(ArrayList<Sala> lista) {
		
		DefaultTableModel model = (DefaultTableModel) tableSale.getModel();
		
		((DefaultTableModel) tableSale.getModel()).setRowCount(0);
		
		for (Sala s : lista)
			model.addRow(new Object[] {s.getCittà(), s.getProvincia(), s.getIndirizzo(), s.getNumeroCivico(), s.getNumeroPosti(), s.getCodice()});
	}
}
