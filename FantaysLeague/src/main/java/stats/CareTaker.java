package stats;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
	private List<StatMomento> momentoList = new ArrayList<StatMomento>();
	
	public void add(StatMomento newMomento){
		momentoList.add(newMomento);
	}
	
	public StatMomento get(int index){
		return momentoList.get(index);
	}
}
