package stats;

import java.util.List;
import java.util.Map;

public class StatMomento {
	
	private Map<Integer, List<Stat>> state;
	
	public StatMomento(Map<Integer, List<Stat>> state){
		this.state = state;
	}
	
	public Map<Integer, List<Stat>> getState(){
		return state;
	}
}
