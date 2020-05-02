package main;

import java.io.File;
import java.io.FileNotFoundException;

public class TrainSet extends FileSet{
	
	int[] ranges;
	
	public TrainSet(File file) throws FileNotFoundException {
		super(file);
		
		
		
		// Checks 
		for(int i = 0; i < Instances.size() - 1 ; i++) {
			for(int j = 0; j < nFeatures - 1 ; j++) {
				if(Instances.get(i).getValue(j) > ranges[j]) {
					ranges[j] = Instances.get(i).getValue(j);
				}
			}
		}	
		
	}
	
	/**
	 * Gets max range of a Feature
	 * @param i : Index of Feature
	 * @return Maximum value available in the file for that feature
	 */
	public int getRange(int i) {
		return ranges[i];
	}
	
	/**
	 * Gets max ranges of every Feature
	 * @return Array with ranges of every feature
	 */
	public int[] getRanges() {
		return ranges;
	}
}
