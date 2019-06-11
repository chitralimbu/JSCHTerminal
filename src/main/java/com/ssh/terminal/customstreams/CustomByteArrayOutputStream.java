package com.ssh.terminal.customstreams;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import javax.swing.JTextArea;

public class CustomByteArrayOutputStream extends ByteArrayOutputStream{

	private JTextArea textArea;

	public CustomByteArrayOutputStream(JTextArea textArea) {
		this.textArea = Objects.requireNonNull(textArea, "Require textArea to output");
	}

	@Override
	public void write(byte[] b, int off, int len) {
		super.write(b, off, len);
		String output = new String(b, off, len);
		byte [] outputBytes = output.getBytes();
		for(int i =0; i < outputBytes.length; i++) {
			if(outputBytes[i] == 27) {
				while(outputBytes[i] != 109) {
					outputBytes[i] = (byte) 0;
					if(outputBytes[i + 1] == 109) {
						outputBytes[i + 1] = (byte) 0;
						break;
					}
					i++;
				}
			}
		}
		String removeANSI = new String(outputBytes);
		textArea.append(removeANSI);
	}
}
