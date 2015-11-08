package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SelectSportView extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnNext;
	private JButton btnCancel;
	private JLabel lblTeamName;
	private JTextField txtTeamName;
	
	public SelectSportView() {
		setTitle("Pick Sport");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 220);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Soccer"}));
		comboBox.setBounds(20, 111, 285, 22);
		contentPane.add(comboBox);
		
		JLabel lblChoice = new JLabel("What sport would you like to create a team for?");
		lblChoice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChoice.setBounds(20, 86, 285, 14);
		contentPane.add(lblChoice);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(67, 159, 91, 23);
		contentPane.add(btnNext);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 159, 91, 23);
		contentPane.add(btnCancel);
		
		lblTeamName = new JLabel("Team Name:");
		lblTeamName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTeamName.setBounds(20, 11, 138, 14);
		contentPane.add(lblTeamName);
		
		txtTeamName = new JTextField();
		txtTeamName.setBounds(20, 36, 169, 20);
		contentPane.add(txtTeamName);
		txtTeamName.setColumns(10);
	}
	
	public String getComboBoxSelection()
	{
		return comboBox.getSelectedItem().toString();
	}
	
	public String getTeamName(){
		return txtTeamName.getText();
	}
	
	public void addNextListener(ActionListener nextListener){
		btnNext.addActionListener(nextListener);
	}
	
	public void addCancelListener(ActionListener nextListener){
		btnCancel.addActionListener(nextListener);
	}
}
