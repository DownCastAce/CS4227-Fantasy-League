package userInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import sports.User;

public class UserController {

    private User userModel;
    private LogInView viewLogIn;
    private UserView userView;
    private MainMenuView mainView;

    public UserController(User userModel, UserView userView, MainMenuView mainView) {
        this.userModel = userModel;
        this.userView = userView;
        this.mainView = mainView;
        this.mainView.setVisible(false);
        this.userView.setVisible(true);
        this.userView.addReturnListener(new ListenForReturn());

        this.userView.addSubmitListenerEdit(new SubmitButtonListener());
        //this._userView.addPassWordListenerConfirm(new ConfirmPassWordListener());
    }
    
    public UserController(User userModel, UserView userView, LogInView view) {
        this.userModel = userModel;
        this.userView = userView;
        this.viewLogIn = view;
        this.viewLogIn.setVisible(false);
        this.userView.setVisible(true);
        this.userView.addReturnListener(new ListenForReturn2());

        this.userView.addSubmitListenerEdit(new SubmitButtonListener2());
        //this._userView.addPassWordListenerConfirm(new ConfirmPassWordListener());
    }
    
    public UserController(LogInView viewLogIn) {
        this.viewLogIn = viewLogIn;
        this.viewLogIn.setVisible(true);
        this.viewLogIn.addLogInListener(new LogInListener());
    }
    
    public User getUser()
    {
    	return userModel;
    }
    
    class ConfirmPassWordListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            // If password in confirmBox is entered correctly - allow
            // data to be registered!

        }

    }
    
    class SubmitButtonListener2 implements ActionListener {
        // Action performed when submit button is pressed.
        @Override
        public void actionPerformed(ActionEvent arg0) {
            // Call pwdConfirm Dialog here
            //userView.ConfirmPassword();
            // TODO Save updated details
            System.out.println("Button Pressed");
            if (StringUtils.isNotBlank(userView.getTeamName()) && StringUtils.isNotBlank(userView.getUserNameEdit()) && StringUtils.isNotBlank(userView.getEmailEdit()) && StringUtils.isNotBlank(userView.getPhoneNumberEdit())) {
                userModel.setUserName(userView.getUserNameEdit()); //= new User(userView.getUserNameEdit());
                userModel.setEmail(userView.getEmailEdit());
                userModel.setPassword(userView.getPasswordConfirm());
                userModel.setPhoneNumber(userView.getPhoneNumberEdit());
                userModel.setTeamName(userView.getTeamName());
                try {
                    userModel.saveNewAccountDetails();
                    userView.dispose();
                    //userView.setVisible(false);
                    viewLogIn.setVisible(true);
                } catch (IOException ioex) {
                    userView.displayErrorMessage("Problem Reading from file: \n" + ioex.getMessage());
                }
            } else
                userView.displayErrorMessage("Please ensure all required fields are filled in!");
        }
    }
 
    private class ListenForReturn2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            userView.dispose();
            viewLogIn.setVisible(true);
        }
    }
    
    private class ListenForReturn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            userView.dispose();
            mainView.setVisible(true);
        }
    }
    
    class SubmitButtonListener implements ActionListener {
        // Action performed when submit button is pressed.
        @Override
        public void actionPerformed(ActionEvent arg0) {
            // Call pwdConfirm Dialog here
            //userView.ConfirmPassword();
            // TODO Save updated details
            System.out.println("Button Pressed");
            if (StringUtils.isNotBlank(userView.getTeamName()) && StringUtils.isNotBlank(userView.getUserNameEdit()) && StringUtils.isNotBlank(userView.getEmailEdit()) && StringUtils.isNotBlank(userView.getPhoneNumberEdit())) {
                userModel.setUserName(userView.getUserNameEdit()); //= new User(userView.getUserNameEdit());
                userModel.setEmail(userView.getEmailEdit());
                userModel.setPassword(userView.getPasswordConfirm());
                userModel.setPhoneNumber(userView.getPhoneNumberEdit());
                userModel.setTeamName(userView.getTeamName());
                try {
                    userModel.saveNewAccountDetails();
                    userView.dispose();
                    //userView.setVisible(false);
                    mainView.setVisible(true);
                } catch (IOException ioex) {
                    userView.displayErrorMessage("Problem Reading from file: \n" + ioex.getMessage());
                }
            } else
                userView.displayErrorMessage("Please ensure all required fields are filled in!");
        }
    }
    
    private class LogInListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("2222222");
            String userName = viewLogIn.getUsername();
            String password = viewLogIn.getPassword();
            if(new File("resources/users/" + userName).exists()) {
                try {
                    //Name,Email,password,number
                    String[] userDetails = FileUtils.readFileToString(new File("resources/users/" + userName)).split(",");
                    if(userDetails[0].equals(userName) && userDetails[2].equals(password)) {
                        viewLogIn.setVisible(false);
                        MainMenuView mainMenu = new MainMenuView(new User(userName, null, userDetails[1], userDetails[3], userDetails[2], userDetails[4]));
                        mainMenu.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "User or password incorrect!");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("Error reading user file\n" + e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "User or password incorrect!");
            }
        }
        
    }
}