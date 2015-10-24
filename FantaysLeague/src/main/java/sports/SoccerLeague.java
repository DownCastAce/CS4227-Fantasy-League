package sports;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;

public class SoccerLeague extends AbstractLeague {

    SoccerLeague(String leagueName, AbstractTeam leagueCreatorTeam, User leagueCreator) {
        this.leagueId = "League123456789";
        this.leagueName = leagueName;
        this.leagueTeams.add(leagueCreatorTeam);
        this.ownerId = leagueCreator.getId();
        
        leagueTeamPoints.put(leagueCreatorTeam.getTeamName(), 0);
    }
    
    public SoccerLeague(String leagueId) {
        try {
            this.leagueId = leagueId;
            List<String> fileLines = FileUtils.readLines(new File(FILEPATH + this.leagueId));
            String[] firstLine = fileLines.remove(0).split(",");
            this.leagueName = firstLine[0];
            this.ownerId = firstLine[1];
            this.lastUpdate = DateUtils.parseDate(firstLine[2], "dd/MM/yyyy");
            for(String line : fileLines) {
                //TeamName,UserId,UserName,TotalPoints
                String[] currentLine = line.split(",");
                leagueTeamPoints.put(currentLine[0], Integer.parseInt(currentLine[3]));
                leagueTeams.add(TeamFactory.makeTeam("soccer", currentLine[0], new User(currentLine[2],currentLine[1])));
            }
            rankOrderOfTeams();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file : " + e);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void rankOrderOfTeams() {
        Collections.sort(leagueTeams, new Comparator<AbstractTeam>() {
            @Override
            public int compare(AbstractTeam  firstTeam, AbstractTeam  secondTeam)
            {
                return  leagueTeamPoints.get(secondTeam.getTeamName()).compareTo(leagueTeamPoints.get(firstTeam.getTeamName()));
            }
        });
    }
    
    @Override
    public void saveLeague() {
        try {
            GregorianCalendar todaysDate = new GregorianCalendar();
            String date = todaysDate.get(Calendar.DAY_OF_MONTH) + "/" + (todaysDate.get(Calendar.MONTH)+1) + "/" + todaysDate.get(Calendar.YEAR);
            String leagueInfo = leagueName + "," + ownerId + "," + date + "\n";
            try {
                lastUpdate = DateUtils.parseDate(date, "dd/MM/yyy");
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Could not update lastUpdate\n" + e);
            }
            rankOrderOfTeams();
            for(AbstractTeam team : leagueTeams) {
                leagueInfo += team.getTeamName() + "," + team.getOwner().getId() + "," + team.getOwner().getUserName() + "," + leagueTeamPoints.get(team.getTeamName()) + "\n";
            }
            FileUtils.writeStringToFile(new File(FILEPATH + leagueId), leagueInfo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("League " + leagueName + " could not be saved" + e);
        }
    }

    @Override
    public void updateTeamPoints(){
        Date fileLasteModified = new Date(new File(FILEPATH + "stats/stats.soccer").lastModified());
        fileLasteModified = DateUtils.round(fileLasteModified, Calendar.DAY_OF_MONTH);
        
        if(fileLasteModified.compareTo(lastUpdate) > 0) {
            for(AbstractTeam currentTeam : leagueTeams) {
                leagueTeamPoints.put(currentTeam.getTeamName(), leagueTeamPoints.get(currentTeam.getTeamName())+currentTeam.generateTeamPoints());
            }
        }
        rankOrderOfTeams();
        saveLeague();
    }
}