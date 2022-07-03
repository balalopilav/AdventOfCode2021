import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class tag61 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag6.txt");
		String[] timers;
		long[] fishCounter = new long[9]; //How many fish with the same number
		
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    timers = br.readLine().split(",");
		}
	    
	    for(String timer : timers) {
	    	fishCounter[Integer.parseInt(timer)]++;
	    }
	    
	    
	    System.out.println("There are " + calculatePopulation(fishCounter, 80) + " lanterfish in Part 1!");
	    
	    System.out.println("There are " + calculatePopulation(fishCounter, 256) + " lanterfish in Part 2!");
	    
	}
	
	static long calculatePopulation(long[] fishCounter, int life) {
		for(int i = 0; i < life; i++) {
			long[] fishCounterNew = new long[9]; 
			fishCounterNew[8] = fishCounter[0];
			fishCounterNew[7] = fishCounter[8];
			fishCounterNew[6] = fishCounter[0] + fishCounter[7];  
			for(int j = 0; j < 6; j++) {
				fishCounterNew[j] = fishCounter[j+1];
			}
			fishCounter = fishCounterNew.clone();
		}
		return Arrays.stream(fishCounter).sum();
	}
}
