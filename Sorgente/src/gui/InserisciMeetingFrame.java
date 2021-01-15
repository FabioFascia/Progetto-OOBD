package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class InserisciMeetingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Controller controller;

	/**
	 * Create the frame.
	 */
	public InserisciMeetingFrame(Controller c) {
		controller = c;
		setTitle("Inserisci Meeting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Annulla");
		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 64, 150, 20);
		contentPane.add(textField);
		
		JSpinner spinnerDataFisico = new JSpinner();
		spinnerDataFisico.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinnerDataFisico.setEditor(new JSpinner.DateEditor(spinnerDataFisico, "dd/MM/yyyy"));
		((JSpinner.DefaultEditor) spinnerDataFisico.getEditor()).getTextField().setEditable(false);
		spinnerDataFisico.setBounds(10, 95, 150, 20);
		contentPane.add(spinnerDataFisico);
		
		JSpinner spinnerOraInizioFisico = new JSpinner();
		spinnerOraInizioFisico.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraInizioFisico.setEditor(new JSpinner.DateEditor(spinnerOraInizioFisico, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraInizioFisico.getEditor()).getTextField().setEditable(false);
		spinnerOraInizioFisico.setBounds(10, 126, 63, 20);
		contentPane.add(spinnerOraInizioFisico);
		
		JSpinner spinnerOraFineFisico = new JSpinner();
		spinnerOraFineFisico.setModel(new SpinnerDateModel(new Date(946767540000L), null, null, Calendar.HOUR_OF_DAY));
		spinnerOraFineFisico.setEditor(new JSpinner.DateEditor(spinnerOraFineFisico, "HH:mm"));
		((JSpinner.DefaultEditor) spinnerOraFineFisico.getEditor()).getTextField().setEditable(false);
		spinnerOraFineFisico.setBounds(97, 126, 63, 20);
		contentPane.add(spinnerOraFineFisico);
	}
}
