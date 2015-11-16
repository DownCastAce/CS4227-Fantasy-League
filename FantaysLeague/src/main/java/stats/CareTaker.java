package stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CareTaker implements Serializable{
	private List<StatMomento> momentoList = new ArrayList<StatMomento>();
	
	public void add(StatMomento newMomento){
		momentoList.add(newMomento);
	}
	
	public StatMomento get(int index){
		return momentoList.get(index);
	}
	
	public int size(){
		return momentoList.size();
	}
}
