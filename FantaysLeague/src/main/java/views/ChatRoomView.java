package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.UIManager;

public class ChatRoomView extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JTextField tf;
	private JButton btnLeave;
	private JTextArea ta;
	private boolean connected;

	
	public ChatRoomView() 
	{
		setTitle("Chat Room");
			
		JPanel northPanel = new JPanel(new GridLayout(3,1));
		northPanel.setBackground(Color.WHITE);
		tf = new JTextField("");
		northPanel.add(tf);
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().setBackground(SystemColor.desktop);
		// The CenterPanel which is the chat room
		ta = new JTextArea("Welcome to Fantasy League Chatroom\n For A List Of Participants Type '-people'\n", 80, 80);
		ta.setBackground(Color.LIGHT_GRAY);
		JPanel centerPanel = new JPanel(new GridLayout(1,1));
		centerPanel.add(new JScrollPane(ta));
		ta.setEditable(false);
		getContentPane().add(centerPanel, BorderLayout.CENTER);

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setPreferredSize(new Dimension(100,600));
		getContentPane().add(leftPanel, BorderLayout.WEST);
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		btnLeave = new JButton("Back To Main Menu");// you have to login before being able to logout
		btnLeave.setFont(new Font("Tahoma", Font.BOLD, 11));
		southPanel.add(btnLeave);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);

	}
	public void setConnected(boolean b){
		connected = b;
	}
	public void append(String str){
		ta.append(str);
		ta.setCaretPosition(ta.getText().length() - 1);
	}
	
	public String getMessage(){
		return tf.getText();
	}
	
	public void setMessage(String s){
		tf.setText(s);
	}
	public void addNewMessageListener(ActionListener newMessageListener)
	{
		tf.addActionListener(newMessageListener);
	}
	
	public void addBackListener(ActionListener backListener){
		btnLeave.addActionListener(backListener);
	}

}
