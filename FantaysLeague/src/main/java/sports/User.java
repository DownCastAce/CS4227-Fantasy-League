package sports;

/***Class import for file management***/
/**************************************/
import java.io.BufferedReader;/********/
import java.io.BufferedWriter;/********/
import java.io.File;/******************/
import java.io.FileReader;/************/
import java.io.FileWriter;/************/
import java.io.IOException;/***********/

import javax.swing.JOptionPane;


/*******/
/**************************************/

public class User implements IUser {
    private static final String FILEPATH = "resources/users/";
    private String userName, userId, email, phoneNumber,password, teamName;

    /**
     * 
     * @param userName
     *            name of End User used to instantiate the User class
     * @param null default constructor for User Class
     * 
     * */
    public User() {
    }
    
    public User(String userName, String id, String email, String phoneNumber, String password, String teamName)
    {
        this.userName = userName;
        this.userId = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.setTeamName(teamName);
    }

    public User(String userName) {
        setUserName(userName);
    }
    
    public User(String userName, String userId) {
        setUserName(userName);
        setUserId(userId);
    }

    // Seemingly useless attribute, maybe a refactor is needed?
    public String getId() {
        return userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;

    }

    @Override
    public void setEmail(String email) {
        this.email = email;

    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    // TODO: Refactor as saving to ArrayList of information
    // TODO: Create Dump2File method which writes all updated information to
    // file.
    @Override
    public void saveNewAccountDetails() throws IOException {
        File aFile = new File(FILEPATH + getUserName());
        FileWriter fWriter = new FileWriter(aFile);
        BufferedWriter writer = new BufferedWriter(fWriter);

        if (aFile.exists()) {
            // TODO: Write user information to the file using BufferedWriter.
            writer.append(getUserName() + "," + getEmail() + "," + getPassword() + "," + getPhoneNumber() + "," + getTeamName());
            writer.close();
        } else {
            // TODO: Create the file if !exists & then write to it.
            aFile.createNewFile();
            writer.append("Name, Email, Password, Phone Number");
            saveNewAccountDetails();
        }

    }

    @Override
    public User readAccountDetails(String userName) throws IOException {
        File aFile = new File(FILEPATH + getUserName());
        FileReader fReader = new FileReader(aFile);
        BufferedReader rdr = new BufferedReader(fReader);

        if (aFile.exists()) {
            // TODO: Read from file using BufferedReader
            String line;
            while ((line = rdr.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(userName)) {
                    this.setUserName(userName);
                    this.setEmail(values[1]);
                    this.setPassword(values[2]);
                    this.setPhoneNumber(values[3]);
                    this.setTeamName(values[4]);
                }
            }
        } else {
            Object menuOptions[] = { "Yes", "No", "Quit" };
            JOptionPane.showMessageDialog(null, "No User data found!", "NO_DATA_FOUND", JOptionPane.WARNING_MESSAGE);
            switch (JOptionPane.showOptionDialog(null, "Would you like to create a new profile?", "CREATE_NEW_PROFILE?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuOptions, menuOptions[2])) {
            case 1:
                saveNewAccountDetails();
            case 2:
                break;
            case 3:
                System.exit(0);
            }
        }
        rdr.close();
        return this;

    }

    @Override
    public boolean isValidPhoneNumber(String phoneNumber) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getUserId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setUserId(String id) {
        userId = id;
    }

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}