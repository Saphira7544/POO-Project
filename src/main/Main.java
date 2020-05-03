package main;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		// Exits if arguments are not correctly input
		if(args.length != 3) { 
			System.err.println("Necessary arguments: TrainFile TestFile Score(LL or MDL)");
			System.exit(-1);
		}
		
		File TrainFile = new File(args[0]);
			
		try {	
			@SuppressWarnings("unused")
			TrainSet traindata = new TrainSet(TrainFile);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

	

}
