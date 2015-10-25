package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	
	public LoginView() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 182);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(44, 40, 79, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(44, 81, 79, 14);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(111, 37, 188, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(111, 78, 188, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		btnLogin = new JButton("Log In");
		btnLogin.setBounds(180, 121, 91, 23);
		contentPane.add(btnLogin);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(293, 121, 91, 23);
		contentPane.add(btnCancel);
	}
	
	public String getUsername()
	{
		return txtUsername.getText();
	}
	
	public String getPassword()
	{
		return txtPassword.getText();
	}
	
	public void addConfirmListener(ActionListener listenForConfirm)
	{
		btnLogin.addActionListener(listenForConfirm);
	}
	
	public void addCancelListener(ActionListener listenForCancel)
	{
		btnCancel.addActionListener(listenForCancel);
	}

}
