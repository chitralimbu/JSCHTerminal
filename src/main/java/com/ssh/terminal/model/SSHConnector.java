package com.ssh.terminal.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnector {
	private String userName;
	private byte [] password;
	private String hostName;
	private int port = 22;
	private OutputStream out;
	private Session session;
	
	public SSHConnector(String userName, byte [] password, String hostName) {
		this.userName = userName;
		this.password = password;
		this.hostName = hostName;
	}
	
	public Session connectSession() {
		session = null;
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(userName, hostName, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setConfig("PreferredAuthentications", "password");
			session.connect();
		} catch (JSchException e) {
			e.printStackTrace();
			//LOG HERE
		}
		return session;
	}
	
	public void connectChannel(PrintStream printStream) {
		try {
			session = connectSession();
			Channel channel = session.openChannel("shell");
			((ChannelShell)channel).setPtyType("dumb");
			channel.setOutputStream(printStream);
			channel.setXForwarding(true);
			out = channel.getOutputStream();
			channel.connect();
		} catch (JSchException | IOException e) {
			e.printStackTrace();
			//LOG HERE
		}
		
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public OutputStream getOut() {
		return out;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
