import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class tag33 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag3.txt");
		int oxygen = 0, c02 = 0;
		int[] stellen = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		List<String> zahlen = new LinkedList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] ziffern = line.split("");
		    	for (int i = 0; i < ziffern.length; i++) {
		    		if(ziffern[i].equals("0")) {
		    			stellen[i] -= 1;
		    		}else if(ziffern[i].equals("1")) {
		    			stellen[i] += 1;
		    		}
		    	}
		    	zahlen.add(line);
		    }
		}
		List<String> zahlen1 = zahlen;
		
		for(int i = 0; i < stellen.length-1; i++) {
			if(stellen[i] >= 0) {
				for (int j = 0; j < zahlen.size(); j++) {
					if(zahlen.get(j).substring(i).startsWith("0")) {
						zahlen.remove(j);
						j--;
					}
				}
			} else {
				for (int j = 0; j < zahlen.size(); j++) {
					if(zahlen.get(j).substring(i).startsWith("1")) {
						zahlen.remove(j);
						j--;
					}
				}
			}
		}
		
		oxygen = Integer.parseInt(zahlen.get(0), 2);
		
		for(int i = 0; i < stellen.length-1; i++) {
			if(stellen[i] < 0) {
				for (int j = 0; j < zahlen1.size(); j++) {
					if(zahlen1.get(j).substring(i).startsWith("0")) {
						zahlen1.remove(j);
						j--;
					}
				}
			} else {
				for (int j = 0; j < zahlen1.size(); j++) {
					if(zahlen1.get(j).substring(i).startsWith("1")) {
						zahlen1.remove(j);
						j--;
					}
				}
			}
		}
		
		c02 = Integer.parseInt(zahlen1.get(0), 2);
		
		
		System.out.println("Oxygen:\t" + oxygen);
		System.out.println(Integer.toBinaryString(oxygen));
		System.out.println("C02:\t" + c02);
		String co2String = Integer.toBinaryString(~oxygen);
		System.out.println(co2String.substring(co2String.length()-12, co2String.length()));
	
//		System.out.println("Ergebnis: " + (gamma * epsilon));
	}

}
