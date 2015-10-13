package userInterfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import sports.User;

import java.awt.event.ActionEvent;

public class LogInView extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogIn;
    private JButton btnRegister;

    public LogInView() {
        setTitle("Log In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsernameemail = new JLabel("Username/Email:");
        lblUsernameemail.setBounds(10, 49, 102, 24);
        contentPane.add(lblUsernameemail);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(10, 106, 83, 14);
        contentPane.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setBounds(108, 51, 148, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(108, 103, 148, 20);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

        btnLogIn = new JButton("Log In");
        btnLogIn.setBounds(138, 155, 91, 23);
        contentPane.add(btnLogIn);
        
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserView view = new UserView();
                User model = new User();
                UserController controller = new UserController(model, view, LogInView.this);
            }
        });
        btnRegister.setBounds(321, 50, 89, 23);
        contentPane.add(btnRegister);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void addRegisterListener(ActionListener listenForRegisterButton) {
        btnRegister.addActionListener(listenForRegisterButton);
    }
    
    public void addLogInListener(ActionListener listenForSubmitButton) {
        btnLogIn.addActionListener(listenForSubmitButton);
    }

}