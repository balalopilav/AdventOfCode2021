import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class tag2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag2.txt");
		int horizontalPos = 0, depth = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] data = line.split(" ");
		    	if(data[0].equals("forward")) {
		    		horizontalPos += Integer.parseInt(data[1]);
		    	}else if(data[0].equals("up")) {
		    		depth -= Integer.parseInt(data[1]);
		    	}else if(data[0].equals("down")) {
		    		depth += Integer.parseInt(data[1]);
		    	}
		    	
		    }
		}
		System.out.println("Ergebnis: " + (horizontalPos * depth));
	}

}
