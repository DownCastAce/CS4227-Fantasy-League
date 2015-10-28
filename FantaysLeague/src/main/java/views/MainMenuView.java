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
	private JButton btnCreateTeam;
	private JButton btnChatRoom;
	
	public MainMenuView() {
		setBackground(Color.LIGHT_GRAY);
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 406);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCreateTeam = new JButton("Create Team");
		btnCreateTeam.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateTeam.setBounds(39, 35, 537, 51);
		contentPane.add(btnCreateTeam);
		
		btnViewTeam = new JButton("View Team/Transfers");
		btnViewTeam.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewTeam.setBounds(39, 123, 537, 51);
		contentPane.add(btnViewTeam);
		
		btnLeague = new JButton("View League");
		btnLeague.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLeague.setBounds(39, 217, 537, 51);
		contentPane.add(btnLeague);
		
		btnChatRoom = new JButton("Chat Room");
		btnChatRoom.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChatRoom.setBounds(39, 298, 537, 51);
		contentPane.add(btnChatRoom);
	}
	
	public void addCreateTeamListener(ActionListener createTeamListener)
	{
		btnCreateTeam.addActionListener(createTeamListener);
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
