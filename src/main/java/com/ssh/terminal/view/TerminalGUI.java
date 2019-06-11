package com.ssh.terminal.view;

import java.awt.Font;
import java.io.PrintStream;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import com.ssh.terminal.customstreams.CustomByteArrayOutputStream;
import com.ssh.terminal.handlers.EnterPressHandler;
import com.ssh.terminal.model.SSHConnector;

public class TerminalGUI {
	public static void main(String[] args) {
		SSHConnector sshConnect = new SSHConnector("", "".getBytes(), "");
		JTextArea textArea = new JTextArea(300, 300);
		PrintStream printStream = new PrintStream(new CustomByteArrayOutputStream(textArea));
		sshConnect.connectChannel(printStream);
		JFrame frame = new JFrame(sshConnect.getUserName() + "@" + sshConnect.getHostName());
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JTextField textField = new JTextField();
		
		JScrollPane scroll = new JScrollPane(textArea);
		textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		textArea.setLineWrap(true);
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		panel.add(scroll);
		panel.add(textField);

		textField.addKeyListener(new EnterPressHandler(sshConnect.getOut(), textField));

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setVisible(true);
	}
}
