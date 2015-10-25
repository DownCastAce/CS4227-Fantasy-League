package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

public class InitialMenuView extends JFrame {

	private JPanel contentPane;
	private JButton btnLogin;
	private JButton btnRegister;

	public InitialMenuView() 
	{
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 166);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to SinMacroSystems");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(21, 29, 395, 39);
		contentPane.add(lblNewLabel);
		
		btnLogin = new JButton("Log In");
		btnLogin.setBounds(49, 82, 91, 23);
		contentPane.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(256, 82, 91, 23);
		contentPane.add(btnRegister);

	}
	
	public void addLoginListener(ActionListener listenForLogin)
	{
		btnLogin.addActionListener(listenForLogin);
	}
	
	public void addRegisterListener(ActionListener listenForRegister)
	{
		btnRegister.addActionListener(listenForRegister);
	}
}
