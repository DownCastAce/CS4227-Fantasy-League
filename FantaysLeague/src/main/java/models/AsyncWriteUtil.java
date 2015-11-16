package models;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.LockableFileWriter;


public class AsyncWriteUtil implements Runnable{

	List<String> output;
	LockableFileWriter outfileWriter;
	File outfile;
	
	public AsyncWriteUtil(List<String> output, File outfile) throws IOException{
		this.output = output;
		this.outfile = outfile;
		this.outfileWriter = new LockableFileWriter(outfile); 
	}
	
	@Override
	public void run() {
		try{
			for(String line : output){
				outfileWriter.write(line + "\n");
			}
			outfileWriter.close();
		}catch(IOException e){	}	
	}
}
