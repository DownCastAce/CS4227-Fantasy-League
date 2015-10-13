package userInterfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TeamView extends JFrame {

    TeamView(String col[], String data[][]) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setTitle("Team View");

        JPanel jp = new JPanel();
        JTable jt = new JTable(data, col);

        JScrollPane scroll = new JScrollPane(jt);

        this.add(scroll);
    }

}