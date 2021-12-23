import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class tag3 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag3.txt");
		int gamma = 0, epsilon = 0;
		int[] stellen = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] zahlen = line.split("");
		    	for (int i = 0; i < zahlen.length; i++) {
		    		if(zahlen[i].equals("0")) {
		    			stellen[i] -= 1;
		    		}else if(zahlen[i].equals("1")) {
		    			stellen[i] += 1;
		    		}
		    	}
		    }
		}
		
		int stellenwert = 1;
		for (int i = stellen.length-1; i >= 0; i--) {
			if(stellen[i] > 0) {
				gamma += stellenwert;
			}else {
				epsilon += stellenwert;
			}
			
			stellenwert *= 2;
		}
		
		
		System.out.println("Gamma:\t\t" + Integer.toBinaryString(gamma));
		System.out.println("Epsilon:\t" + Integer.toBinaryString(epsilon));
		
		System.out.println("Ergebnis: " + (gamma * epsilon));
	}

}
