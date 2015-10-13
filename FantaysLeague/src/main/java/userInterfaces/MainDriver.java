package userInterfaces;


public class MainDriver {
    public static void main(String [] args) {
//        MainMenuView view = new MainMenuView();
//        view.setVisible(true);
        LogInView view = new LogInView();
        UserController controller = new UserController(view);
        view.setVisible(true);
    }
}