package models;

public class SuperSundayTeam extends AbstractDecorator {

	public SuperSundayTeam(Team newTeam) {
		super(newTeam);
	}

	@Override
	public boolean save() {
		return tempTeam.save();
	}

	@Override
	public String getTeamName() {
		return tempTeam.getTeamName();
	}

	@Override
	public User getOwner() {
		return tempTeam.getOwner();
	}

	@Override
	public double getBudget() {
		return tempTeam.getBudget();
	}

	@Override
	public int getAmountOfPlayersAllowed() {
		return tempTeam.getAmountOfPlayersAllowed();
	}

	@Override
	public int getTotalPoints() {
		return tempTeam.getTotalPoints()*2;
	}

	@Override
	public void update() {
		tempTeam.update();
	}
}