package com.ssh.terminal.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextField;

public class FlushHandler implements ActionListener{

	private OutputStream out;
	private JTextField textField;
	public FlushHandler(OutputStream out, JTextField textField) {
		this.out = out;
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			out.write((textField.getText() + "\n").getBytes()); 
			out.flush();
			textField.setText(""); 
		} catch (IOException ex) {
			ex.printStackTrace(); 
		} 


	}

}
