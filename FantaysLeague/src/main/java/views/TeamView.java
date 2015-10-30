package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.Player;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;

public class TeamView extends JFrame {
	
	private JPanel contentPane;
	
	//Required JTables for players
	private JTable availablePlayers;
	private JTable selectedGks;
	private JTable selectedDs;
	private JTable selectedMs;
	private JTable selectedFs;
	
	//Required ScrollPanes for JTables
	private JScrollPane availablePlayersScrollPane;
	private JScrollPane goalkeeperScrollPane;
	private JScrollPane defenderScrollPane;
	private JScrollPane midfielderScrollPane;
	private JScrollPane forwardsScrollPane;
	
	//Required buttons + comboBox for view
	private JButton btnAddPlayer;
	private JButton btnReturn;
	private JButton btnRemovePlayer;
	private JButton btnFinaliseTeam;
	private JComboBox comboBox;
	
	//Budget Label
	private JLabel lblBudget;
	private JTextField textField;
	private String teamName;
	public TeamView(){
	//set up JFrame 
		setTitle("Pick Team");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 735);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//set up scrollpanes for player tables
		goalkeeperScrollPane = new JScrollPane();
		goalkeeperScrollPane.setBounds(28, 79, 251, 58);
		
		defenderScrollPane = new JScrollPane();
		defenderScrollPane.setBounds(28, 206, 251, 104);
		
		midfielderScrollPane = new JScrollPane();
		midfielderScrollPane.setBounds(28, 384, 251, 104);
		
		forwardsScrollPane = new JScrollPane();
		forwardsScrollPane.setBounds(28, 560, 251, 75);
		
		availablePlayersScrollPane = new JScrollPane();
		availablePlayersScrollPane.setBounds(354,79,323,500);
		
		//add scrollpanes
		contentPane.add(goalkeeperScrollPane);
		contentPane.add(defenderScrollPane);
		contentPane.add(midfielderScrollPane);
		contentPane.add(forwardsScrollPane);
		contentPane.add(availablePlayersScrollPane);
		
		//set up JTables
		selectedGks = new JTable();
		selectedGks.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name", "Value", "ID"
			}
		));
		selectedGks.getColumnModel().getColumn(2).setMinWidth(0);
		
		selectedDs = new JTable();
		selectedDs.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name","Value", "ID"
			}
		));
		selectedDs.getColumnModel().getColumn(2).setMinWidth(0);
		
		selectedMs = new JTable();
		selectedMs.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name","Value", "ID"
			}
		));
		selectedMs.getColumnModel().getColumn(2).setMinWidth(0);
		
		selectedFs = new JTable();
		selectedFs.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name","Value", "ID"
			}
		));
		selectedFs.getColumnModel().getColumn(2).setMinWidth(0);
		
		availablePlayers = new JTable();
		availablePlayers.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Position", "Value"
			}
		));
		
		//add JTables to ScrollPanes.
		goalkeeperScrollPane.setViewportView(selectedGks);
		defenderScrollPane.setViewportView(selectedDs);
		midfielderScrollPane.setViewportView(selectedMs);
		forwardsScrollPane.setViewportView(selectedFs);
		availablePlayersScrollPane.setViewportView(availablePlayers);
		
		//initialise buttons + comboBox
		comboBox = new JComboBox(); 
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Goalkeepers", "Defenders", "Midfielders", "Forwards"}));
		comboBox.setBounds(436, 40, 212, 22);
		contentPane.add(comboBox);
		comboBox.setSelectedIndex(0);
		
		btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddPlayer.setBounds(354, 610, 120, 23);
		contentPane.add(btnAddPlayer);
		
		btnFinaliseTeam = new JButton("Finalise");
		btnFinaliseTeam.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFinaliseTeam.setBounds(557, 610, 120, 23);
		contentPane.add(btnFinaliseTeam);
		
		btnRemovePlayer = new JButton("Remove");
		btnRemovePlayer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemovePlayer.setBounds(28, 656, 91, 23);
		contentPane.add(btnRemovePlayer);
		
		lblBudget= new JLabel("Budget:");
		lblBudget.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBudget.setBounds(28, 14, 46, 14);
		contentPane.add(lblBudget);
		
		textField = new JTextField();
		textField.setText("100.0");
		textField.setBounds(84, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		btnReturn = new JButton("Return To Main Menu");
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReturn.setBounds(513, 644, 164, 42);
		contentPane.add(btnReturn);

	}
	
	public void updateGoalKeeperTable(ArrayList<Player> arrayList)
	{
		ArrayList<Player>goalkeepers = new ArrayList<Player>();
		
		for(int i = 0; i < arrayList.size();i++)
			if((arrayList.get(i).getPosition()).equals("G"))
				goalkeepers.add(arrayList.get(i));
		
		String data [][] = new String[2][3];
		String col [] = {"Name", "Value","ID"};

		for(int z = 0; z<goalkeepers.size();z++)
		{
				data[z][0] = goalkeepers.get(z).getName();
				data[z][1] = Double.toString(goalkeepers.get(z).getValue());
				data[z][2] = goalkeepers.get(z).getID();
		}
		
		selectedGks = new JTable(data,col);
		selectedGks.getColumnModel().getColumn(2).setMinWidth(0);
		goalkeeperScrollPane.setViewportView(selectedGks);
	}
	
	public void updateDefenderTable(ArrayList<Player> players)
	{
		ArrayList<Player>defenders = new ArrayList<Player>();
		
		for(int i = 0; i < players.size();i++)
			if((players.get(i).getPosition()).equals("D"))
				defenders.add(players.get(i));
		
		String data [][] = new String[5][3];
		String col [] = {"Name","Value","ID"};

		for(int z = 0; z<defenders.size();z++)
		{
				data[z][0] = defenders.get(z).getName();
				data[z][1] = Double.toString(defenders.get(z).getValue());	
				data[z][2] = defenders.get(z).getID();
		}
		
		selectedDs = new JTable(data,col);
		selectedDs.getColumnModel().getColumn(2).setMinWidth(0);
		defenderScrollPane.setViewportView(selectedDs);
	}
	
	public void updateMidfielderTable(ArrayList<Player> players)
	{
		ArrayList<Player>midfielders = new ArrayList<Player>();
		
		for(int i = 0; i < players.size();i++)
			if((players.get(i).getPosition()).equals("M"))
				midfielders.add(players.get(i));
		
		String data [][] = new String[5][3];
		String col [] = {"Name","Value","ID"};

		for(int z = 0; z<midfielders.size();z++)
		{
				data[z][0] = midfielders.get(z).getName();
				data[z][1] = Double.toString(midfielders.get(z).getValue());
				data[z][2] = midfielders.get(z).getID();
		}
		
		selectedMs = new JTable(data,col);
		selectedMs.getColumnModel().getColumn(2).setMinWidth(0);
		midfielderScrollPane.setViewportView(selectedMs);
	}
	
	public void updateForwardsTable(ArrayList<Player> players)
	{
		ArrayList<Player>forwards = new ArrayList<Player>();
		
		for(int i = 0; i < players.size();i++)
			if((players.get(i).getPosition()).equals("F"))
				forwards.add(players.get(i));
		
		String data [][] = new String[3][3];
		String col [] = {"Name","Value","ID"};

		for(int z = 0; z<forwards.size();z++)
		{
				data[z][0] = forwards.get(z).getName();
				data[z][1] = Double.toString(forwards.get(z).getValue());
				data[z][2] = forwards.get(z).getID();
		}
		
		selectedFs = new JTable(data,col);
		selectedFs.getColumnModel().getColumn(2).setMinWidth(0);
		forwardsScrollPane.setViewportView(selectedFs);
	}
}
