package views;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class RegisterView extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtConfPassword;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JTextField txtTeamName;

	public RegisterView() {
		setTitle("Register User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 183);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterAUsername = new JLabel("Enter A Username:");
		lblEnterAUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterAUsername.setBounds(10, 14, 109, 14);
		contentPane.add(lblEnterAUsername);
		
		JLabel lblEnterAPassword = new JLabel("Enter A Password:");
		lblEnterAPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterAPassword.setBounds(10, 68, 109, 14);
		contentPane.add(lblEnterAPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConfirmPassword.setBounds(10, 96, 109, 14);
		contentPane.add(lblConfirmPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(147, 8, 226, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(147, 65, 226, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtConfPassword = new JTextField();
		txtConfPassword.setBounds(147, 90, 226, 20);
		contentPane.add(txtConfPassword);
		txtConfPassword.setColumns(10);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfirm.setBounds(195, 122, 91, 23);
		contentPane.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(299, 122, 91, 23);
		contentPane.add(btnCancel);
		
		JLabel lblEnterATeam = new JLabel("Enter A Team Name:");
		lblEnterATeam.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterATeam.setBounds(10, 43, 127, 14);
		contentPane.add(lblEnterATeam);
		
		txtTeamName = new JTextField();
		txtTeamName.setBounds(147, 39, 226, 20);
		contentPane.add(txtTeamName);
		txtTeamName.setColumns(10);
	}
	
	//getters
	public String getUsername()
	{
		return txtUsername.getText();
	}
	
	public String getPassword()
	{
		return txtPassword.getText();
	}
	
	public String getTeamName(){
		return txtTeamName.getText();
	}
	
	public void addConfirmListener(ActionListener listenForConfirm)
	{
		btnConfirm.addActionListener(listenForConfirm);
	}
	
	public void addCancelListener(ActionListener listenForCancel)
	{
		btnCancel.addActionListener(listenForCancel);
	}
}
