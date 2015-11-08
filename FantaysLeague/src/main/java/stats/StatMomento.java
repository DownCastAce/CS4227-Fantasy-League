package stats;

import java.util.Map;

public class StatMomento {
	
	private Map<Integer, Stat> state;
	
	public StatMomento(Map<Integer, Stat> state){
		this.state = state;
	}
	
	public Map<Integer, Stat> getState(){
		return state;
	}
}
