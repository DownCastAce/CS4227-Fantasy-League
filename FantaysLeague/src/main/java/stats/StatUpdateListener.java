package stats;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class StatUpdateListener extends Subject implements FileAlterationListener{

	private Map<Integer, List<Stat>> currentData = new HashMap<Integer, List<Stat>>();
	private CareTaker caretaker = new CareTaker();
	
	@Override
	public void onFileChange(File file) {
		try{
			System.out.println("File changed: " + file.getCanonicalPath());
		}catch(IOException e){
			System.out.println("IOException in onFileChange");
		}
	}
	
	public Map<Integer, List<Stat>> getState(){
		return currentData;		
	}
	public StatMomento saveState(){
		return new StatMomento(currentData);
	}
	
	public void setStateFromMomento(StatMomento momento){
		currentData = momento.getState();
	}

	@Override
	public void onFileCreate(File file) {
		// Proposed file format
		//first line "ID,stat1,stat2,stat3,stat4"
		//all following lines "1,4,3.3,2,10		
		try{
			System.out.println("File created: " + file.getCanonicalPath());
			if(!file.getName().endsWith(".stat")){
				System.out.println("Not of correct type, ignoring");
				return;
			}
			caretaker.add(saveState());
			List<String> data = FileUtils.readLines(file);
			String statNames[] = data.remove(0).split(",");		
			for(String line : data){
				ArrayList<Stat> temp = new ArrayList<Stat>();
				String stat[] =  line.split(",");
				for(int i = 1; i < stat.length;i++)
					temp.add(new Stat(statNames[i], 1, Double.parseDouble(stat[i])));
				currentData.put(Integer.parseInt(stat[0]), temp);
			}

		}catch(IOException e){
			System.out.println("IOException in onFileChange");
		}

		notifyAllObservers();
	}
	
	public CareTaker getPreviousStates(){
		return caretaker;
	}

	@Override
	public void onStart(FileAlterationObserver observer) {
		System.out.println("Listening on folder: " + observer.getDirectory());
	}

	@Override
	public void onStop(FileAlterationObserver observer) {
		System.out.println("No longer listening on folder: " + observer.getDirectory());		
	}
	
	@Override
	public void onDirectoryChange(File arg0) {/*Do nothing*/}
	@Override
	public void onDirectoryCreate(File arg0) {/*Do nothing*/}
	@Override
	public void onDirectoryDelete(File arg0) {/*Do nothing*/}
	@Override
	public void onFileDelete(File arg0) {/*Do nothing*/}

}
