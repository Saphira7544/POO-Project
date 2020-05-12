package files;

import java.io.File;
import java.io.FileNotFoundException;

public class TestSet extends FileSet{
	
	int[] ranges;
	int[] classification;
	
	public TestSet(File file) throws FileNotFoundException {
			
		super(file);
		classification = new int [Instances.size()];
		for(int i = 0; i < Instances.size(); i++) {
			classification[i] = Instances.get(i).getClassVariable();		
		}
		
	}

	public int[] getClassification() {
		return classification;
	}
}
