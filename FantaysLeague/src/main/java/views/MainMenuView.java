package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class MainMenuView extends JFrame {

	private JPanel contentPane;
	private JButton btnLeague;
	private JButton btnViewTeam;
	private JButton btnChatRoom;
	
	public MainMenuView() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 354);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnViewTeam = new JButton("View Team/Transfers");
		btnViewTeam.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewTeam.setBounds(39, 42, 537, 51);
		contentPane.add(btnViewTeam);
		
		btnLeague = new JButton("View League");
		btnLeague.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLeague.setBounds(39, 133, 537, 51);
		contentPane.add(btnLeague);
		
		btnChatRoom = new JButton("Chat Room");
		btnChatRoom.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChatRoom.setBounds(39, 232, 537, 51);
		contentPane.add(btnChatRoom);
	}
	
	public void addViewTeamListener(ActionListener viewTeamListener)
	{
		btnViewTeam.addActionListener(viewTeamListener);
	}
	
	public void addViewLeagueListener(ActionListener viewLeagueListener)
	{
		btnLeague.addActionListener(viewLeagueListener);
	}
	
	public void addChatRoomListener(ActionListener chatRoomListener)
	{
		btnChatRoom.addActionListener(chatRoomListener);
	}
}
