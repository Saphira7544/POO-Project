package files;

import java.io.File;
import java.io.FileNotFoundException;

public class TestSet extends FileSet{
	
	double[] countClass;
	int[] classification;
	
	public TestSet(File file) throws FileNotFoundException {
			
		super(file);
		
		countClass = new double [getClassRange()+1];
		classification = new int [Instances.size()];
		
		for(int i = 0; i < Instances.size(); i++) {
			int c = Instances.get(i).getClassVariable();
			classification[i] = c;	
			countClass[c]++;
		}
		
	}

	public int[] getClassification() {
		return classification;
	}
	public double[] getCountClass() {
		return countClass;
	}
}
