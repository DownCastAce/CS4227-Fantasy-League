package sports;

import java.io.IOException;

/**
 * @author Brian
 **/

public interface IUser {
    String getUserName();
    String getEmail();
    String getPhoneNumber();
    String getUserId();
    String getPassword();
    
    //This doesn't make sense to me - Lorcan
    User readAccountDetails(String userName) throws IOException;
    boolean isValidPhoneNumber(String phoneNumber);

    void setUserName(String userName);
    void setEmail(String email);
    void setUserId(String id);
    void setPassword(String password);
    void setPhoneNumber(String phoneNumber);
    void saveNewAccountDetails() throws IOException;
}