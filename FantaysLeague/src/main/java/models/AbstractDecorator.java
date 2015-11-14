package models;

public abstract class AbstractDecorator extends Team{

	protected Team tempTeam;
	
	public AbstractDecorator(Team newTeam) {
		this.tempTeam = newTeam;
	}
}
