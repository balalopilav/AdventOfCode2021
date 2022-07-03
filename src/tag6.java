import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tag6 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag6.txt");
		String[] timers;
		List<lanternfish> lanternfish = new ArrayList<>();
		
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    timers = br.readLine().split(",");
		}
	    
	    for(String timer : timers) {
	    	lanternfish.add(new lanternfish(Integer.parseInt(timer)));
	    }
	    
	    for(int i = 0; i < 80; i++) {
	    	int size = lanternfish.size();
	    	List<lanternfish> lanternfishNew = new ArrayList<>();
	    	for(int j = 0; j < size; j++) {
				if(lanternfish.get(j).decreaseTimer()) {
					lanternfishNew.add(new lanternfish(8));
				}
			}
	    	
	    	for(lanternfish fish : lanternfishNew) {
	    		lanternfish.add(fish);
	    	}
	    }
	    
	    System.out.println("There are " + lanternfish.size() + " lanterfish!");
	    
	    
	    
	}
}

class lanternfish{
	int timer;
	
	public lanternfish(int timer) {
		this.timer = timer;
	}
	
	public boolean decreaseTimer() {
		if(timer > 0) {
			timer--;
			return false;
		} else {
			timer = 6;
			return true;
		}
	}
}
