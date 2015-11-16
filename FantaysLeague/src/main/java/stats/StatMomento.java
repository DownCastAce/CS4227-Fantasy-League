package stats;

import java.io.Serializable;
import java.util.Map;

public class StatMomento implements Serializable {
	
	private Map<String, Integer> state;
	
	public StatMomento(Map<String, Integer> state){
		this.state = state;
	}
	
	public Map<String, Integer> getState(){
		return state;
	}
}
