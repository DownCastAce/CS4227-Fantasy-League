package stats;

import java.util.ArrayList;
import java.util.List;

public class CareTaker extends Observer{
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
