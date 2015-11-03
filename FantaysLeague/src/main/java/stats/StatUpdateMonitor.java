package stats;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class StatUpdateMonitor extends Subject implements FileAlterationListener{

	private List<Stat> stats = new ArrayList<Stat>();
	
	@Override
	public void onFileChange(File arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onFileCreate(File statFile) {
		// Proposed file format
		//first line "ID,stat1,stat2,stat3,stat4"
		//all following lines "1,4,3.3,2,10"
		notifyAllObservers();
	}

	@Override
	public void onStart(FileAlterationObserver arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onStop(FileAlterationObserver arg0) {
		// TODO Auto-generated method stub		
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
