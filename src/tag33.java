import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class tag33 {

	public static int haeufigste_ziffer(List<String> zahlen, int stelle) {
		int anzahl = 0;
		
		for (int i = 0; i < zahlen.size(); i++) {
			if(zahlen.get(i).substring(stelle).startsWith("1")) {
				anzahl++;
			} else {
				anzahl--;
			}
		}
		
		return anzahl >= 0? 1:0;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag3.txt");
		int oxygen = 0, c02 = 0;
		int[] stellen = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		List<String> zahlen = new LinkedList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	zahlen.add(line);
		    }
		}
		List<String> zahlenBackup = new LinkedList<>();
		for (String zahl : zahlen) {
			zahlenBackup.add(zahl);
		}
		
		for(int i = 0; i < stellen.length; i++) {
			int zu_behalten = haeufigste_ziffer(zahlenBackup, i);
			for (int j = 0; j < zahlen.size(); j++) {
				if(zu_behalten == 1) {
					if(zahlen.get(j).substring(i).startsWith("0") && zahlenBackup.size() > 1) {
						zahlenBackup.remove(zahlen.get(j));
					}
				} else {
					if(zahlen.get(j).substring(i).startsWith("1") && zahlenBackup.size() > 1) {
						zahlenBackup.remove(zahlen.get(j));
					}
				}
			}
		}
		oxygen = Integer.parseInt(zahlenBackup.get(0), 2);
		
		zahlenBackup = new LinkedList<>(); //reset to clean list
		for (String zahl : zahlen) {
			zahlenBackup.add(zahl);
		} 
		
		for(int i = 0; i < stellen.length; i++) {
			int zu_behalten = haeufigste_ziffer(zahlenBackup, i);
			for (int j = 0; j < zahlen.size(); j++) {
				if(zu_behalten == 1) {
					if(zahlen.get(j).substring(i).startsWith("1") && zahlenBackup.size() > 1) {
						zahlenBackup.remove(zahlen.get(j));
					}
				} else {
					if(zahlen.get(j).substring(i).startsWith("0") && zahlenBackup.size() > 1) {
						zahlenBackup.remove(zahlen.get(j));
					}
				}
			}
		}
		c02 = Integer.parseInt(zahlenBackup.get(0), 2);
		
		System.out.println("Oxygen:\t" + oxygen);
		System.out.println(Integer.toBinaryString(oxygen));
		System.out.println("C02:\t" + c02);
		String co2String = Integer.toBinaryString(c02);
		System.out.println(co2String);
	
		System.out.println("Ergebnis: " + (oxygen * c02));
	}

}
