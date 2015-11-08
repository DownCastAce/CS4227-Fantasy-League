package stats;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	protected List<Observer> observers = new ArrayList<Observer>();
	
	public void notifyAllObservers(){
		for(Observer o: observers){
			o.update();
		}
	}
	
	public void attach(Observer o){
		observers.add(o);
	}
	
	public void dettach(Observer o){
		observers.remove(o);
	}
	
	public abstract Object getState();
}
