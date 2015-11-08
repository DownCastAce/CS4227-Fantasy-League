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
import java.awt.SystemColor;
import java.awt.Font;

public class RegisterView extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtConfPassword;
	private JButton btnConfirm;
	private JButton btnCancel;

	public RegisterView() {
		setTitle("Register User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 158);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterAUsername = new JLabel("Enter A Username:");
		lblEnterAUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterAUsername.setBounds(10, 11, 109, 14);
		contentPane.add(lblEnterAUsername);
		
		JLabel lblEnterAPassword = new JLabel("Enter A Password:");
		lblEnterAPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterAPassword.setBounds(10, 36, 109, 14);
		contentPane.add(lblEnterAPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConfirmPassword.setBounds(10, 61, 109, 14);
		contentPane.add(lblConfirmPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(147, 8, 226, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(147, 33, 226, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtConfPassword = new JTextField();
		txtConfPassword.setBounds(147, 58, 226, 20);
		contentPane.add(txtConfPassword);
		txtConfPassword.setColumns(10);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfirm.setBounds(195, 92, 91, 23);
		contentPane.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(296, 92, 91, 23);
		contentPane.add(btnCancel);
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
	
	public void addConfirmListener(ActionListener listenForConfirm)
	{
		btnConfirm.addActionListener(listenForConfirm);
	}
	
	public void addCancelListener(ActionListener listenForCancel)
	{
		btnCancel.addActionListener(listenForCancel);
	}
}
