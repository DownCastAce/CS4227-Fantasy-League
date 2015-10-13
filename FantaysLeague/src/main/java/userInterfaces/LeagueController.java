package userInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sports.AbstractLeague;

public class LeagueController {
    private LeagueView view;
    private AbstractLeague league;
    private MainMenuView mainView;
    
    public LeagueController(LeagueView view, AbstractLeague league, MainMenuView mainView) {
        this.view = view;
        this.league = league;
        this.mainView = mainView;
        this.view.setVisible(true);
        this.mainView.setVisible(false);
        this.view.addReturnListener(new ReturnListener());
    }
    
    class ReturnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            league.saveLeague();
            view.dispose();
            mainView.setVisible(true);
        }
    }
}