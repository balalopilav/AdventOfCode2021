import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class tag11 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag1.txt");
		List<Integer> zahlen = new LinkedList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	zahlen.add(Integer.parseInt(line));
		    }
		}
		
		//Zahlen Addieren in neuer Liste
		List<Integer> zahlen1 = new LinkedList<>();
		
		for(int i = 0; i < zahlen.size() - 2; i ++) {
			zahlen1.add(zahlen.get(i) + zahlen.get(i+1) + zahlen.get(i+2));
		}
		
		int counter = 0;
		for (int i = 1; i < zahlen1.size(); i++) {
			if(zahlen1.get(i) > zahlen1.get(i-1)) {
				counter++;
			}
		}
		
		System.out.println("Ergebnis: " + (counter));
	}

}
