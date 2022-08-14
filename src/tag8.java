import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class tag8 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag8.txt");
		List<String> input = new ArrayList<>();
		List<String> output = new ArrayList<>();
		
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    	String line;
		    while ((line = br.readLine()) != null) {
	    		 String[] lineArray = line.split("\\|");
	    		 
	    		 String[] inputArray = lineArray[0].split(" ");
	    		 for(String inTemp : inputArray)
	    			 input.add(inTemp);
	    		 
	    		 String[] outputArray = lineArray[1].split(" ");
	    		 for(String outTemp : outputArray)
	    			 output.add(outTemp);
	    		 
	    	}
		}
	    
	    List<Integer> digits = getDigits(output);
	    
	    int simpledigits = Collections.frequency(digits, 1) 
	    		+ Collections.frequency(digits, 7) 
	    		+ Collections.frequency(digits, 4) 
	    		+ Collections.frequency(digits, 8);
	    
	    System.out.println("There are " + simpledigits + " digits in Part 1!");
	    
	    //Part 2
	    digits = getDigits(output);
	    List<Integer> numbers = new ArrayList<>();
	    for(int i = 0; i < digits.size()-3; i+=4) {
	    	numbers.add(digits.get(i)*1000
	    			+ digits.get(i+1)*100 
	    			+ digits.get(i+2)*10 
	    			+ digits.get(i+3));
	    }
	    
	    int sum = numbers.stream().mapToInt(Integer::intValue).sum();
	    
	    System.out.println("The sum for Part2 is: " + sum);
	    
	}
	
	static List<Integer> getDigits(List<String> input){
		List<Integer> digits = new ArrayList<>();
		
		//This was used in part 1
		for(String in : input) {
			switch(in.length()) {
			case 2:
				digits.add(1);
				break;
			case 3:
				digits.add(7);
				break;
			case 4:
				digits.add(4);
				break;
			case 7:
				digits.add(8);
				break;
			}
		}
		
		return digits;
	}
	
	static List<String> decode(List<String> input){
		List<String> digits = new ArrayList<>();
		int counter = 0;
		String[] positions = new String[7]; //up left right middle and so on
		String[] numbers = new String[10];
		
		
		firstCheck:
		for(String in : input) {
			switch(in.length()) {
			case 2:
				if(counter == 0) {
					positions[0] = in; // set right and down-right
					counter++;
				}
				numbers[1] = in;
				break;
			case 3:
				if(counter == 1) {
					for(String c : in.split("")) {
						if(Arrays.stream(positions[0].split("")).noneMatch(c::equals)) {
							positions[1] = c; // set up^
						}
					}
					counter++;
				}
				numbers[7] = in;
				break;
			case 4:
				if(counter == 2) {
					for(String c : in.split("")) {
						if(Arrays.stream(positions[0].split("")).noneMatch(c::equals)) {
							positions[2] += c; // set left and middle
						}
					}
					counter++;
				}
				numbers[4] = in;
				break;
			case 7:
				if(counter == 3) {
					for(String c : in.split("")) {
						// I hate myself for this
						if(Arrays.stream(positions[0].split("")).noneMatch(c::equals) && Arrays.stream(positions[1].split("")).noneMatch(c::equals) && Arrays.stream(positions[2].split("")).noneMatch(c::equals)) {
							positions[3] += c; // set down and down-left
						}
					}
					numbers[8] = in;
					break firstCheck;
				}
				break;
			}
		}
		//Now we have letters for 1,7,4,8
		counter = 0;
		
		for(String in : input) {
			switch(in.length()) {
			case 5:
				if(counter == 0) {
					if(containsAllCharacters(in, positions[0]) && containsAllCharacters(in, positions[1])) {
						//Its a 3!
						numbers[3] = in;
					}
					counter++;
				} else if(counter == 1 && containsAllCharacters(in, positions[3])) {
					//Its a 2!
					numbers[2] = in;
					counter++;
				} else if(counter == 2) {
					//Its a 5!
					numbers[5] = in;
					counter++;
				}
				break;
			case 6:
				if(counter == 3) {
					
					counter++;
				}
				break;
			}
		}
		
		return digits;
	}
	
	static boolean containsAllCharacters(String input, String match) {
		for(String c : input.split("")) {
			if(Arrays.stream(match.split("")).noneMatch(c::equals)){
				return false;
			}
		}
		return true;
	}
}
