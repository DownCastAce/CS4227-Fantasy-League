package userInterfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import sports.SoccerPlayer;
import sports.SoccerRoster;

public class PickTeam extends JFrame {

    private JPanel contentPane;
    private JTable selectedGks;
    private JTable selectedDs;
    private JTable selectedMs;
    private JTable selectedFs;
    private JTable availablePlayers;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane_3;
    private JScrollPane scrollPane_4;
    private ArrayList<SoccerPlayer> players;
    private int countGs;
    private int countDs;
    private int countMs;
    private int countFs;
    private JButton btnRemovePlayer;
    private double bank;

    /**
     * Create the frame.
     */
    public PickTeam(final SoccerRoster rost) {

        countGs = 0;
        countDs = 0;
        countMs = 0;
        countFs = 0;
        bank = 100.0;

        setTitle("Pick Team");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 695, 735);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        players = new ArrayList<SoccerPlayer>();
        scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 79, 251, 58);
        contentPane.add(scrollPane);

        selectedGks = new JTable();
        selectedGks.setModel(new DefaultTableModel(new Object[][] {
                { null, null, null }, { null, null, null }, },
                new String[] { "Name", "Age", "Value" }));
        scrollPane.setViewportView(selectedGks);

        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(28, 206, 251, 108);
        contentPane.add(scrollPane_1);

        selectedDs = new JTable();
        selectedDs.setModel(new DefaultTableModel(new Object[][] {
                { null, null, null }, { null, null, null },
                { null, null, null }, { null, null, null },
                { null, null, null }, },
                new String[] { "Name", "Age", "Value" }));
        scrollPane_1.setViewportView(selectedDs);

        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(28, 384, 251, 108);
        contentPane.add(scrollPane_2);

        selectedMs = new JTable();
        selectedMs.setModel(new DefaultTableModel(new Object[][] {
                { null, null, null }, { null, null, null },
                { null, null, null }, { null, null, null },
                { null, null, null }, },
                new String[] { "Name", "Age", "Value" }));
        scrollPane_2.setViewportView(selectedMs);

        scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(28, 560, 251, 71);
        contentPane.add(scrollPane_3);

        selectedFs = new JTable();
        scrollPane_3.setViewportView(selectedFs);
        selectedFs.setModel(new DefaultTableModel(new Object[][] {
                { null, null, null }, { null, null, null },
                { null, null, null }, },
                new String[] { "Name", "Age", "Value" }));

        JLabel lblNewLabel = new JLabel("Forwards");
        lblNewLabel.setBounds(28, 516, 200, 33);
        contentPane.add(lblNewLabel);

        JLabel lblMids = new JLabel("Midfielders");
        lblMids.setLabelFor(selectedMs);
        lblMids.setBounds(28, 340, 200, 33);
        contentPane.add(lblMids);

        JLabel lblDefenders = new JLabel("Defenders");
        lblDefenders.setBounds(28, 162, 200, 33);
        contentPane.add(lblDefenders);

        JLabel lblGoalkeepers = new JLabel("Goalkeepers");
        lblGoalkeepers.setBounds(28, 35, 200, 33);
        contentPane.add(lblGoalkeepers);

        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "Goalkeepers", "Defenders", "Midfielders", "Forwards" }));
        comboBox.setBounds(436, 40, 212, 22);
        contentPane.add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                ArrayList<SoccerPlayer> selectedTypePlayers;
                if ((comboBox.getSelectedItem().toString()).equals("Goalkeepers")) {
                    selectedTypePlayers = rost.getListOfGks();
                    doSomething(selectedTypePlayers);
                }

                else if ((comboBox.getSelectedItem().toString()).equals("Defenders")) {
                    selectedTypePlayers = rost.getListOfDs();
                    doSomething(selectedTypePlayers);
                }

                else if ((comboBox.getSelectedItem().toString()).equals("Midfielders")) {
                    selectedTypePlayers = rost.getListOfMs();
                    doSomething(selectedTypePlayers);
                }

                else if ((comboBox.getSelectedItem().toString()).equals("Forwards")) {
                    selectedTypePlayers = rost.getListOfFs();
                    doSomething(selectedTypePlayers);
                }

                else {
                    selectedTypePlayers = rost.getAllPlayers();
                    doSomething(selectedTypePlayers);
                }

            }
        });

        JButton btnAddPlayer = new JButton("Add Player");
        btnAddPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<SoccerPlayer> selectedTypePlayers;
                if ((comboBox.getSelectedItem().toString()).equals("Goalkeepers")) {
                    selectedTypePlayers = rost.getListOfGks();
                    boolean allow = allowPlayerAddition(selectedTypePlayers.get(availablePlayers.getSelectedRow()));

                    if (allow == true && countGs < 2 && (bank - selectedTypePlayers.get(availablePlayers.getSelectedRow()).getValue() >= 0)) {
                        players.add(selectedTypePlayers.get(availablePlayers.getSelectedRow()));
                        updateGoalKeeperTable();
                        countGs++;
                        bank = bank - selectedTypePlayers.get(availablePlayers.getSelectedRow()).getValue();
                        System.out.println("Bank: £" + bank);
                    }
                }

                else if ((comboBox.getSelectedItem().toString()).equals("Defenders")) {
                    selectedTypePlayers = rost.getListOfDs();
                    boolean allow = allowPlayerAddition(selectedTypePlayers.get(availablePlayers.getSelectedRow()));

                    if (allow == true && countDs < 5 && (bank - selectedTypePlayers.get(availablePlayers.getSelectedRow()).getValue() >= 0)) {
                        players.add(selectedTypePlayers.get(availablePlayers.getSelectedRow()));
                        updateDefenderTable();
                        bank = bank - selectedTypePlayers.get(availablePlayers.getSelectedRow()).getValue();
                        countDs++;
                        System.out.println("Bank: £" + bank);
                    }
                }

                else if ((comboBox.getSelectedItem().toString()).equals("Midfielders")) {
                    selectedTypePlayers = rost.getListOfMs();
                    boolean allow = allowPlayerAddition(selectedTypePlayers.get(availablePlayers.getSelectedRow()));

                    if (allow == true
                            && countMs < 5 && (bank - selectedTypePlayers.get(availablePlayers.getSelectedRow()).getValue() >= 0)) {
                        players.add(selectedTypePlayers.get(availablePlayers.getSelectedRow()));
                        updateMidsTable();
                        bank = bank - selectedTypePlayers.get(availablePlayers.getSelectedRow()).getValue();
                        countMs++;
                        System.out.println("Bank: £" + bank);
                    }
                }

                else if ((comboBox.getSelectedItem().toString()).equals("Forwards")) {
                    selectedTypePlayers = rost.getListOfFs();
                    boolean allow = allowPlayerAddition(selectedTypePlayers.get(availablePlayers.getSelectedRow()));

                    if (allow == true
                            && countDs < 3 && (bank - selectedTypePlayers.get(availablePlayers.getSelectedRow()).getValue() >= 0)) {
                        players.add(selectedTypePlayers.get(availablePlayers.getSelectedRow()));
                        updateForwardsTable();
                        bank = bank - selectedTypePlayers.get(availablePlayers.getSelectedRow()).getValue();
                        countFs++;
                        System.out.println("Bank: £" + bank);
                    }
                }
            }
        });

        btnAddPlayer.setBounds(354, 610, 120, 23);
        contentPane.add(btnAddPlayer);

        scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(354, 79, 323, 500);
        contentPane.add(scrollPane_4);

        availablePlayers = new JTable();
        availablePlayers.setModel(new DefaultTableModel(new Object[][] {},
                new String[] { "Name", "Value", "Position", "Value" }));
        scrollPane_4.setViewportView(availablePlayers);

        JButton btnFinalise = new JButton("Finalise");
        btnFinalise.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                createTeam();
            }
        });
        btnFinalise.setBounds(557, 610, 120, 23);
        contentPane.add(btnFinalise);

        btnRemovePlayer = new JButton("Remove");
        btnRemovePlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String name;
                // http://stackoverflow.com/questions/13973572/hide-a-column-in-jtable
                if (selectedGks.getSelectedRow() != -1) {
                    name = (String) selectedGks.getValueAt(
                            selectedGks.getSelectedRow(), 0);
                    for (int i = 0; i < players.size(); i++)
                        if ((players.get(i).getName().equals(name))) {
                            bank = bank + players.get(i).getValue();
                            players.remove(i);
                            countGs--;
                            System.out.println("Bank: £" + bank);
                        }
                    updateGoalKeeperTable();
                }

                else if (selectedDs.getSelectedRow() != -1) {
                    name = (String) selectedDs.getValueAt(
                            selectedDs.getSelectedRow(), 0);
                    for (int i = 0; i < players.size(); i++)
                        if ((players.get(i).getName().equals(name))) {
                            bank = bank + players.get(i).getValue();
                            players.remove(i);
                            countDs--;
                            System.out.println("Bank: £" + bank);
                        }
                    updateDefenderTable();
                }

                else if (selectedMs.getSelectedRow() != -1) {
                    name = (String) selectedMs.getValueAt(
                            selectedMs.getSelectedRow(), 0);
                    for (int i = 0; i < players.size(); i++)
                        if ((players.get(i).getName().equals(name))) {
                            bank = bank + players.get(i).getValue();
                            players.remove(i);
                            countMs--;
                            System.out.println("Bank: £" + bank);
                        }
                    updateMidsTable();
                }

                else if (selectedFs.getSelectedRow() != -1) {
                    name = (String) selectedFs.getValueAt(
                            selectedFs.getSelectedRow(), 0);
                    for (int i = 0; i < players.size(); i++)
                        if ((players.get(i).getName().equals(name))) {
                            bank = bank + players.get(i).getValue();
                            players.remove(i);
                            countFs--;
                            System.out.println("Bank: £" + bank);
                        }
                    updateForwardsTable();
                }
            }
        });
        btnRemovePlayer.setBounds(28, 656, 91, 23);
        contentPane.add(btnRemovePlayer);
    }

    protected void doSomething(ArrayList<SoccerPlayer> list) {

        String data[][] = new String[list.size()][5];
        String col[] = { "Name", "Position", "Age", "Team", "Value" };

        for (int z = 0; z < list.size(); z++) {
            data[z][0] = list.get(z).getName();
            data[z][1] = list.get(z).getPosition();
            data[z][2] = Integer.toString(list.get(z).getAge());
            data[z][3] = list.get(z).getRealTeam();
            data[z][4] = Double.toString(list.get(z).getValue());
        }

        availablePlayers = new JTable(data, col);
        scrollPane_4.setViewportView(availablePlayers);

    }

    public boolean allowPlayerAddition(SoccerPlayer p) {
        boolean allow = true;

        for (int i = 0; i < players.size(); i++) {
            if (!p.getName().equals(players.get(i).getName()))
                allow = true;

            else
                allow = false;
        }

        return allow;
    }

    public void updateTables(ArrayList<SoccerPlayer> players) {
        ArrayList<SoccerPlayer> p = new ArrayList<SoccerPlayer>();

        for (int i = 0; i < players.size(); i++)
            if ((players.get(i).getPosition()).equals("G"))
                p.add(players.get(i));

    }

    public void updateGoalKeeperTable() {
        ArrayList<SoccerPlayer> goalkeepers = new ArrayList<SoccerPlayer>();

        for (int i = 0; i < players.size(); i++)
            if ((players.get(i).getPosition()).equals("G"))
                goalkeepers.add(players.get(i));

        String data[][] = new String[2][3];
        String col[] = { "Name", "Age", "Value" };

        for (int z = 0; z < goalkeepers.size(); z++) {
            data[z][0] = goalkeepers.get(z).getName();
            data[z][1] = Integer.toString(goalkeepers.get(z).getAge());
            data[z][2] = Double.toString(goalkeepers.get(z).getValue());
        }

        selectedGks = new JTable(data, col);
        scrollPane.setViewportView(selectedGks);
    }

    public void updateDefenderTable() {
        ArrayList<SoccerPlayer> def = new ArrayList<SoccerPlayer>();
        for (int i = 0; i < players.size(); i++)
            if ((players.get(i).getPosition()).equals("D"))
                def.add(players.get(i));

        String data[][] = new String[5][3];
        String col[] = { "Name", "Age", "Value" };

        for (int z = 0; z < def.size(); z++) {
            data[z][0] = def.get(z).getName();
            data[z][1] = Integer.toString(def.get(z).getAge());
            data[z][2] = Double.toString(def.get(z).getValue());
        }

        selectedDs = new JTable(data, col);
        scrollPane_1.setViewportView(selectedDs);
    }

    public void updateMidsTable() {
        ArrayList<SoccerPlayer> mids = new ArrayList<SoccerPlayer>();
        for (int i = 0; i < players.size(); i++)
            if ((players.get(i).getPosition()).equals("M"))
                mids.add(players.get(i));

        String data[][] = new String[5][3];
        String col[] = { "Name", "Age", "Value" };

        for (int z = 0; z < mids.size(); z++) {
            data[z][0] = mids.get(z).getName();
            data[z][1] = Integer.toString(mids.get(z).getAge());
            data[z][2] = Double.toString(mids.get(z).getValue());

        }

        selectedMs = new JTable(data, col);
        scrollPane_2.setViewportView(selectedMs);
    }

    public void updateForwardsTable() {
        ArrayList<SoccerPlayer> forwards = new ArrayList<SoccerPlayer>();
        for (int i = 0; i < players.size(); i++)
            if ((players.get(i).getPosition()).equals("F"))
                forwards.add(players.get(i));

        String data[][] = new String[3][3];
        String col[] = { "Name", "Age", "Value" };

        for (int z = 0; z < forwards.size(); z++) {
            data[z][0] = forwards.get(z).getName();
            data[z][1] = Integer.toString(forwards.get(z).getAge());
            data[z][2] = Double.toString(forwards.get(z).getValue());
        }

        selectedFs = new JTable(data, col);
        scrollPane_3.setViewportView(selectedFs);
    }

    public void createTeam() {
        String teamName = "Chestervilla";

        if (players.size() == 15) {
            try {
                File f = new File(teamName + ".txt");
                BufferedWriter out = new BufferedWriter(new FileWriter(f));
                out.write(teamName + ",");
                for (int i = 0; i < players.size(); i++) {
                    if (i != 14)
                        out.write(players.get(i).getName() + ",");

                    else
                        out.write(players.get(i).getName());
                }
                out.write("\n");
                out.close();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            this.dispose();
        }
    }
}