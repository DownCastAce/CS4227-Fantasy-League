package userInterfaces;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import sports.User;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class UserView extends JFrame {

    /**
	 * 	
	 */
    private static final long serialVersionUID = 1424280359020465477L;
    private JPanel contentPane;
    private JTextField txtUserName;
    private JTextField txtEmail;
    private JTextField txtPhoneNumber;
    private JButton btnSubmit;
    private JPasswordField txtNewPassWord;
    private JPasswordField txtVerifyPassWord;
    private JPasswordField confirmDialogPasswordField;
    private JButton btnReturn;
    private JTextField textTeamName;

    /**
     * Create the frame.
     */
    public UserView() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUserName = new JLabel("UserName*");
        lblUserName.setBounds(40, 40, 190, 14);
        contentPane.add(lblUserName);

        JLabel lblEmail = new JLabel("Email*");
        lblEmail.setBounds(40, 59, 190, 23);
        contentPane.add(lblEmail);

        JLabel lblPhoneNumber = new JLabel("Phone Number*");
        lblPhoneNumber.setBounds(40, 81, 190, 23);
        contentPane.add(lblPhoneNumber);

        txtUserName = new JTextField();
        txtUserName.setBounds(266, 37, 122, 20);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setBounds(266, 60, 122, 20);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(266, 82, 122, 20);
        contentPane.add(txtPhoneNumber);
        txtPhoneNumber.setColumns(10);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(299, 243, 89, 23);
        contentPane.add(btnSubmit);

        JLabel lblNewPassWord = new JLabel("New Password");
        lblNewPassWord.setBounds(40, 160, 190, 14);
        contentPane.add(lblNewPassWord);

        txtNewPassWord = new JPasswordField();
        txtNewPassWord.setBounds(266, 157, 122, 20);
        contentPane.add(txtNewPassWord);

        JLabel lblVerifyNewPassword = new JLabel("Verify New Password");
        lblVerifyNewPassword.setBounds(40, 185, 190, 14);
        contentPane.add(lblVerifyNewPassword);

        txtVerifyPassWord = new JPasswordField();
        txtVerifyPassWord.setBounds(266, 182, 122, 20);
        contentPane.add(txtVerifyPassWord);

        JLabel lblSetNewPassword = new JLabel("Set New Password");
        lblSetNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSetNewPassword.setBounds(40, 129, 139, 14);
        contentPane.add(lblSetNewPassword);

        JLabel lblReqField = new JLabel("* denotes a mandatory field");
        lblReqField.setForeground(Color.RED);
        lblReqField.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblReqField.setBounds(10, 286, 139, 14);
        contentPane.add(lblReqField);
        
        btnReturn = new JButton("Return to main menu");
        btnReturn.setBounds(242, 277, 170, 23);
        contentPane.add(btnReturn);
        
        JLabel lblTeamname = new JLabel("TeamName*");
        lblTeamname.setBounds(40, 104, 190, 14);
        contentPane.add(lblTeamname);
        
        textTeamName = new JTextField();
        textTeamName.setBounds(266, 101, 122, 20);
        contentPane.add(textTeamName);
        textTeamName.setColumns(10);
    }

    public String getTeamName() {
        return textTeamName.getText();
    }
    
    public String getPasswordConfirm() {
        return new String(txtNewPassWord.getPassword());
    }

    public String getUserNameEdit() {
        return txtUserName.getText();
    }

    public void setUserName(String userName) {
        txtUserName.setText(userName);
    }

    public String getEmailEdit() {
        return txtEmail.getText();
    }

    public void setEmailEdit(String email) {
        txtEmail.setText(email);
    }

    public String getPhoneNumberEdit() {
        return txtPhoneNumber.getText();
    }

    public void setPhoneNumberEdit(String phoneNumber) {
        txtPhoneNumber.setText(phoneNumber);
    }

    /* Start button listeners */
    public void addSubmitListenerEdit(ActionListener listenForSubmitButton) {
         btnSubmit.addActionListener(listenForSubmitButton);
    }
    
    public void addReturnListener(ActionListener listenForReturn) {
        btnReturn.addActionListener(listenForReturn);
    }
    
    // public void addPassWordListenerConfirm(ActionListener
    // listenForConfirmPasswordButton) {
    // btnConfirm.addActionListener(listenForConfirmPasswordButton);
    // }

    /* End button listeners */

    // Error handling
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}