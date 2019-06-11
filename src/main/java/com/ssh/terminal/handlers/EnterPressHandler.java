package com.ssh.terminal.handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextField;

public class EnterPressHandler extends KeyAdapter{
	
	private OutputStream out;
	private JTextField textField;
	
	public EnterPressHandler(OutputStream out, JTextField textField) {
		this.out = out;
		this.textField = textField;
	}
	
	@Override 
	public void keyPressed(KeyEvent e) { 
		try {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				out.write((textField.getText() + "\n").getBytes()); 
				out.flush();
				textField.setText(""); 
		    }
		} catch (IOException ex) {
			ex.printStackTrace(); 
		} 
	}
}
