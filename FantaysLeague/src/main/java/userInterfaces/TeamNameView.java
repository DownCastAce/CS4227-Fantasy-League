package userInterfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TeamNameView extends JFrame {

	private JPanel contentPane;
	private JTextField teamName;
	private JButton btnSubmit;

	/**
	 * Create the frame.
	 */
	public TeamNameView() {
		setTitle("Team Name");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeamName = new JLabel("Team Name:");
		lblTeamName.setBounds(10, 67, 72, 14);
		contentPane.add(lblTeamName);
		
		teamName = new JTextField();
		teamName.setBounds(92, 64, 194, 20);
		contentPane.add(teamName);
		teamName.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(195, 135, 91, 23);
		contentPane.add(btnSubmit);
	}
	
	public String getTeamName()
	{
		return teamName.getText();
	}
	
	public void addSubmitListenerEdit(ActionListener listenForSubmitButton) {
        btnSubmit.addActionListener(listenForSubmitButton);
    }
	
	public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}