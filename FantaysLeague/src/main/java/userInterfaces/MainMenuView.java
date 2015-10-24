package userInterfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import sports.AbstractLeague;
import sports.LeagueFactory;
import sports.AbstractTeam;
import sports.SoccerRoster;
import sports.SoccerTeam;
import sports.TeamFactory;
import sports.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

public class MainMenuView extends JFrame {

    private JPanel contentPane;
    private AbstractLeague currentLeague = LeagueFactory.makeLeague("soccer", "globalLeague");
    private SoccerRoster rost = new SoccerRoster("resources/players.soccer");

    /**
     * Create the frame.
     */
    public MainMenuView(final User loggedInUser) {
//        User user = new User("testUser", "1", "test.user@email.com", "0871234567", "password", "testTeamUnited");
//        User user2 = new User("Rodrigo", "2", "rodrigo.user@email.com", "0871234567", "password", "rodrigoUnited");
        setTitle("Main Menu (LoggedIn as:" + loggedInUser.getUserName() + ")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 621, 505);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        /*
         * JButton btnLogIn = new JButton("Log In");
         * btnLogIn.addActionListener(new ActionListener() { public void
         * actionPerformed(ActionEvent e) { LogInView view = new LogInView();
         * User user = new User(); UserController controller = new
         * UserController(user,view); user = controller.getUser();
         * System.out.println(user.getUserName()); view.setVisible(true);
         * MainMenuView.this.dispose();
         * 
         * } });
         */

        // btnLogIn.setBounds(70, 116, 448, 50);
        // contentPane.add(btnLogIn);

        JButton btnCreateTeam = new JButton("Create Team");
        btnCreateTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((new File("resources/teams/" + loggedInUser.getTeamName())).exists()) {
                    JOptionPane.showMessageDialog(null, "Team Already Created.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                else {
                    AbstractTeam team = TeamFactory.makeEmptyTeam("Soccer", loggedInUser.getTeamName(), loggedInUser);
                    currentLeague.addTeamToLeague(team);
                    PickTeamView view = new PickTeamView();
                    TeamController controller = new TeamController(view, team, rost, MainMenuView.this);
                }
            }
        });

        btnCreateTeam.setBounds(70, 109, 448, 50);
        contentPane.add(btnCreateTeam);

        JButton btnViewTeam = new JButton("View Team/Transfers");
        btnViewTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((new File("resources/teams/" + loggedInUser.getTeamName())).exists()) {
                    AbstractTeam team = TeamFactory.makeTeam("Soccer", loggedInUser.getTeamName(), loggedInUser);
                    PickTeamView view = new PickTeamView();
                    TeamController controller = new TeamController(view, team, rost, MainMenuView.this);
                }

                else {
                    JOptionPane.showMessageDialog(null, "Must Select A User.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnViewTeam.setBounds(70, 210, 448, 50);
        contentPane.add(btnViewTeam);

        JButton leagueBtn = new JButton("View League");
        leagueBtn.setBounds(70, 307, 448, 50);
        leagueBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                currentLeague.updateTeamPoints();
                LeagueView view = new LeagueView(currentLeague);
                LeagueController controller = new LeagueController(view, currentLeague, MainMenuView.this);
            }
        });
        contentPane.add(leagueBtn);
    }
}