import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class tag7 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag7.txt");
		int[] positions;
		
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    positions = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray(); 
		}
	    
	    //Use median for part 1
	    Arrays.sort(positions);
	    int median = (positions[positions.length/2] + positions[(positions.length-1)/2]) / 2;	    
	    int fuel = 0;
	    
	    for(int i = 0; i < positions.length; i++) {
	    	int distance = Math.abs(median - positions[i]);
	    	fuel += Math.abs(distance);
	    }
	    	    
	    System.out.println(fuel + " fuel used for Part 1!");
	    
	    //Use arithmetic mean for part 2
	    
	    int mean = Arrays.stream(positions).sum() / positions.length;
	    
	    fuel = 0;
	    
	    for(int i = 0; i < positions.length; i++) {
	    	
	    	int distance = Math.abs(mean - positions[i]);
	    	for(int j = 0; j < distance; j++) {
	    		fuel += 1 + j;
	    	}
	    }
	    
	    System.out.println(fuel + " fuel used for Part 2!");
	    
	}
	
}
