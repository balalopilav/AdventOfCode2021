import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class tag1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag1.txt");
		int counter = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    int zahl1 = 0, zahl2 = 0;
		    while ((line = br.readLine()) != null) {
		    	try {
		    		zahl2 = Integer.parseInt(line);
		    		if(zahl2 > zahl1) {
			    	    counter++;
			        }
		    	}catch(NumberFormatException e) {
		    		continue;
		    	}finally {
					zahl1 = zahl2;
				}
		    }
		}
		System.out.println("Ergebnis: " + (counter-1));
	}

}
