package stats;

import java.util.List;
import java.util.Map;

public class StatMomento {
	
	private Map<String, Integer> state;
	
	public StatMomento(Map<String, Integer> state){
		this.state = state;
	}
	
	public Map<String, Integer> getState(){
		return state;
	}
}
