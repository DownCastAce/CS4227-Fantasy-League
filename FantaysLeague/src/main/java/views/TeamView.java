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
import models.SoccerPlayer;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
	private JTextField txtBudget;
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
		selectedGks.getColumnModel().getColumn(2).setPreferredWidth(0);
		selectedGks.getColumnModel().getColumn(2).setMinWidth(0);
		selectedGks.getColumnModel().getColumn(2).setMaxWidth(0);
		
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
				"Name", "Value", "ID"
			}
		));
		selectedDs.getColumnModel().getColumn(2).setPreferredWidth(0);
		selectedDs.getColumnModel().getColumn(2).setMinWidth(0);
		selectedDs.getColumnModel().getColumn(2).setMaxWidth(0);
		
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
				"Name", "Value", "ID"
			}
		));
		selectedMs.getColumnModel().getColumn(2).setPreferredWidth(0);
		selectedMs.getColumnModel().getColumn(2).setMinWidth(0);
		selectedMs.getColumnModel().getColumn(2).setMaxWidth(0);
		
		selectedFs = new JTable();
		selectedFs.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name", "Value", "ID"
			}
		));
		selectedFs.getColumnModel().getColumn(2).setPreferredWidth(0);
		selectedFs.getColumnModel().getColumn(2).setMinWidth(0);
		selectedFs.getColumnModel().getColumn(2).setMaxWidth(0);
		
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
		
		txtBudget = new JTextField();
		txtBudget.setText("100.0");
		txtBudget.setBounds(84, 11, 86, 20);
		contentPane.add(txtBudget);
		txtBudget.setColumns(10);
		txtBudget.setEditable(false);
		
		btnReturn = new JButton("Return To Main Menu");
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReturn.setBounds(513, 644, 164, 42);
		contentPane.add(btnReturn);

	}
	
	public void addPlayerAdditionListener(ActionListener addPlayerListener)
	{
		btnAddPlayer.addActionListener(addPlayerListener);
	}
	
	public void addFilterListener(ActionListener listenForFilter)
	{
		comboBox.addActionListener(listenForFilter);
	}
	
	public void addRemovePlayerListener(ActionListener removePlayerListener)
	{
		btnRemovePlayer.addActionListener(removePlayerListener);
	}
	
	public void finaliseButtonListener(ActionListener finaliseTeamListener)
	{
		btnFinaliseTeam.addActionListener(finaliseTeamListener);
	}
	
	public String getComboBoxSelection()
	{
		return comboBox.getSelectedItem().toString();
	}
	
	public void setBudgetTextField(double a)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		String text = df.format(a);
		txtBudget.setText(text);
	}
	
	public int getGksSelection()
	{
		return selectedGks.getSelectedRow();
	}
	
	public int getDsSelection()
	{
		return selectedDs.getSelectedRow();
	}
	
	public int getMsSelection()
	{
		return selectedMs.getSelectedRow();
	}
	
	public int getFsSelection()
	{
		return selectedFs.getSelectedRow();
	}
	
	public String gkSelectionString()
	{
		return (String) selectedGks.getValueAt(selectedGks.getSelectedRow(), 2);
	}
	
	public String dSelectionString()
	{
		return (String) selectedDs.getValueAt(selectedDs.getSelectedRow(), 2);
	}
	
	public String mSelectionString()
	{
		return (String) selectedMs.getValueAt(selectedMs.getSelectedRow(), 2);
	}
	
	public String fSelectionString()
	{
		return (String) selectedFs.getValueAt(selectedFs.getSelectedRow(), 2);
	}
	
	public int getAvailablePlayersSelection()
	{
		return availablePlayers.getSelectedRow();
	}
	
	public void updateGoalKeeperTable(ArrayList<SoccerPlayer> arrayList)
	{
		ArrayList<SoccerPlayer>goalkeepers = new ArrayList<SoccerPlayer>();
		
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
	
	public void updateDefenderTable(ArrayList<SoccerPlayer> players)
	{
		ArrayList<SoccerPlayer>defenders = new ArrayList<SoccerPlayer>();
		
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
	
	public void updateMidfielderTable(ArrayList<SoccerPlayer> players)
	{
		ArrayList<SoccerPlayer>midfielders = new ArrayList<SoccerPlayer>();
		
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
	
	public void updateForwardsTable(ArrayList<SoccerPlayer> players)
	{
		ArrayList<SoccerPlayer>forwards = new ArrayList<SoccerPlayer>();
		
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
	
	public void updateAvailablePlayersPane(ArrayList<SoccerPlayer> list) 
	{
		String data [][] = new String[list.size()][3];
		String col [] = {"Name","Position","Value"};
			
		for(int z = 0; z<list.size();z++)
		{
			data[z][0] = list.get(z).getName();
			data[z][1] = list.get(z).getPosition();
			data[z][2] = Double.toString(list.get(z).getValue());
		}
		
		availablePlayers = new JTable(data,col);
		availablePlayersScrollPane.setViewportView(availablePlayers);			
	}
}
