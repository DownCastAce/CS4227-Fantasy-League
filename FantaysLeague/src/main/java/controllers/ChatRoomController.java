package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import models.IUser;
import views.ChatRoomView;

public class ChatRoomController {
	
	private ChatRoomView view;
	private IUser user;
	
	private ObjectInputStream sInput;		// to read from the socket
	private ObjectOutputStream sOutput;		// to write on the socket
	private Socket socket;
	
	private String server;
	private int port;
	private boolean connected;
	
	private String msg;

	
	public ChatRoomController(IUser user, ChatRoomView view)
	{
		this.user = user;
		this.view = view;
		this.view.addNewMessageListener(new NewMessageListener());
		this.view.setVisible(true);
		this.view.addBackListener(new BackListener());
		server = "localhost";
		port = 2222;
		
	}
	
	public boolean start() {
		// try to connect to the server
		try 
		{
			socket = new Socket(server, port);
		} 
		// if it failed not much I can so
		catch(Exception ec) {
			view.append("Error connectiong to server:" + ec);
			return false;
		}
		
		msg = "\nConnection accepted " + socket.getInetAddress() + ":" + socket.getPort() + "\n";
		view.append(msg);
	
		/* Creating both Data Stream */
		try
		{
			sInput  = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			view.append("Exception creating new Input/output Streams: " + eIO);
			return false;
		}
				
		try
		{
			sOutput.writeObject(user.getUserName());
		}
		catch (IOException eIO) {
			display("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		
		new ListenFromServer().start();
		
		connected = true;
		return true;
	}
	
	private void display(String msg) {
		view.append(msg + "\n");		// append to the ClientGUI JTextArea (or whatever)
	}
	
	public void sendMessage(String msg) {
		try {
			sOutput.writeObject(msg);
		}
		catch(IOException e) {
		}
	}
	
	private void disconnect() {
		try { 
			if(sInput != null) sInput.close();
		}
		catch(Exception e) {} // not much else I can do
		try {
			if(sOutput != null) sOutput.close();
		}
		catch(Exception e) {} // not much else I can do
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} // not much else I can do
		
		connectionFailed();
			
	}
	
	public void connectionFailed(){
		
	}
	
	public void Logout(){
		try {
			sOutput.writeObject("LOGOUT");
		}
		catch(IOException e) {
			
		}
	}
	
	class NewMessageListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			sendMessage(view.getMessage().trim());
			view.setMessage(" ");
		}
		
	}
	
	class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Logout();
			GoBackToMainMenuCommand com = new GoBackToMainMenuCommand(user);
			view.dispose();
			com.execute();
		}
		
	}
		
	class ListenFromServer extends Thread {

		public void run() {
			while(true) {
				try{
					String msg = (String) sInput.readObject();
					// if console mode print the message and add back the prompt				
					view.append(msg);		
				}
				
				catch(IOException e) {
					display("Server has close the connection: " + e);
					connectionFailed();
					
					break;
				}
				// can't happen with a String object but need the catch anyhow
				catch(ClassNotFoundException e2) {
				}
			}
		}
	}
}
