package controllers;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;

public class InformacionDeCargaCSV extends JInternalFrame {
	

	public JTextArea textAreaDatos = new JTextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformacionDeCargaCSV frame = new InformacionDeCargaCSV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
public void agregar(String dato){
		
		
	
	textAreaDatos.setText(dato);

	}
	
	
	
	
	public InformacionDeCargaCSV() {
		setTitle("Cargando Datos");
		setBackground(Color.WHITE);
		getContentPane().setBackground(SystemColor.text);
		
		JTextArea textAreaDatos = new JTextArea();
		getContentPane().add(textAreaDatos, BorderLayout.CENTER);
		setBounds(100, 100, 710, 789);

	}
	
	
	
	
	

}
