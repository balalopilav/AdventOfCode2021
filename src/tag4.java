import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class tag4 {
	
	static int[] lineToIntArray(String line, int anzahlZahlen) {
		int[] zahlen = new int[anzahlZahlen];
		StringBuilder sb = new StringBuilder();
		int erfassteZahlen = 0;
		
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if(Character.isDigit(c)) {
				sb.append(c);
			} else if(sb.length() != 0){
				zahlen[erfassteZahlen] = Integer.parseInt(sb.toString());
				sb = new StringBuilder();
				erfassteZahlen++;
			}
		}
		
		if(sb.length() != 0)	//Letzter Eintrag
			zahlen[erfassteZahlen] = Integer.parseInt(sb.toString());
		return zahlen;
	}
	
	static boolean contains(int[] array, int nummer) {
		for(int zahl : array) {
			if (zahl == nummer) {
				return true;
			}
		}
		return false;
	}
	
	static boolean isBoardBingo(int[][] board, int[] bingoZahlen) {
		int[] spalteBingo = new int[board.length]; //Merke dir in einem Array wie viele Zahlen an welcher Stelle Bingo sind
		int zeileBingo = 0;
		boolean[][] boardBool = new boolean[5][5];
		
		
		for (int zeile = 0; zeile < board.length; zeile++) {
			for (int spalte = 0; spalte < board[zeile].length; spalte++) {
				if(contains(bingoZahlen, board[zeile][spalte])) {
					spalteBingo[spalte]++;
					zeileBingo++;
					boardBool[zeile][spalte] = true;
				}
				if(zeileBingo == 5) { //Y überprüfen ist ganz einfach
					return true;
				}
			}
			zeileBingo = 0;
		}
		
		if(contains(spalteBingo, 5)) { //X überprüfen nicht so
			return true;
		}

		return false;
	}
	
	static int summeUnmarkierterZahlen(int[][] board, int[] bingoZahlen) {
		int summe = 0;
		
		for (int x = 0; x < board.length; x++) {
			for(int y = 0; y < board.length; y++) {
				if(!contains(bingoZahlen, board[x][y])) {
					summe += board[x][y];
				}
			}
		}
		
		return summe;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag4.txt");
		List<int[][]> boards = new LinkedList<>();
		int[] bingoZahlen = new int[100];
		
	    boolean bingoZeile = true;
	    int boardZeile = 0;
	    int[][] board = new int[5][5];
	    
	    //Bingozahlen und boards einlesen
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if(line.equals(""))
		    		continue;
		    	if (bingoZeile) {
		    		bingoZahlen= lineToIntArray(line, 100);
		    		bingoZeile = false;
		    		continue;
		    	}
		    	board[boardZeile] = lineToIntArray(line, 5);
		    	boardZeile++;
		    	if (boardZeile == 5) {
		    		boards.add(board);
		    		board = new int[5][5];
		    		boardZeile = 0;
		    	}
		    }
		}
	    
	    //Geh für alle boards durch wann Bingo ist und dann rechne erst!
	    //Bingozahlen einfügen und probieren
	    int[][] anzahlVersuche = new int[boards.size()][3];

	    for(int i = 0; i < boards.size(); i++) {
	    	outerloop:
	    	for (int j = 0; j < bingoZahlen.length; j++) {
	    		int[] subBingoZahlen = new int[j];
		    	for (int z = 0; z < j; z++) {
		    		subBingoZahlen[z] = bingoZahlen[z];
		    	}
		    	if(isBoardBingo(boards.get(i), subBingoZahlen)) {
		    		if (i == 75) {
		    			System.out.println("");
		    		}
		    		anzahlVersuche[i][0] = summeUnmarkierterZahlen(boards.get(i), subBingoZahlen); //Summe unmarkiertr Zahlen
			    	anzahlVersuche[i][1] = subBingoZahlen[subBingoZahlen.length-1]; //Zahl mit der Bingo erreicht wurde
			    	anzahlVersuche[i][2] = subBingoZahlen.length; //Wie viele Versuche gebraucht wurden
			    	break outerloop;
		    	}
	    	}
	    }
	    
	    //Finde Board mit wenigsten Versuchen
	    int wenigsteVersuche = anzahlVersuche[0][2];
	    int indexMinVersuche = 0;
	    
	    for (int i = 0; i < anzahlVersuche.length; i++) {
	    	if(anzahlVersuche[i][2] < wenigsteVersuche) {
	    		wenigsteVersuche = anzahlVersuche[i][2];
	    		indexMinVersuche = i;
	    	}
	    }
	    
	    for (int i = 0; i < anzahlVersuche.length; i++) {
	    	System.out.println("Versuche: " + anzahlVersuche[i][2] + "\tergebnis: " + (anzahlVersuche[i][0] * anzahlVersuche[i][1]));
	    }
	    
	    System.out.println("Summe unmarkierter min:" + anzahlVersuche[indexMinVersuche][0]);
	    System.out.println("Bingozahl min: " + anzahlVersuche[indexMinVersuche][1]);
		System.out.println("Ergebnis min: " + (anzahlVersuche[indexMinVersuche][0] * anzahlVersuche[indexMinVersuche][1]));
	    
		
		//Finde Board mit meisten Versuchen
	    int meisteVersuche = anzahlVersuche[0][2];
	    int indexMaxVersuche = 0;
	    
	    for (int i = 0; i < anzahlVersuche.length; i++) {
	    	if(anzahlVersuche[i][2] > meisteVersuche) {
	    		meisteVersuche = anzahlVersuche[i][2];
	    		indexMaxVersuche = i;
	    	}
	    }
	    
	    System.out.println("\nSumme unmarkierter max:" + anzahlVersuche[indexMaxVersuche][0]);
	    System.out.println("Bingozahl max: " + anzahlVersuche[indexMaxVersuche][1]);
		System.out.println("Ergebnis max: " + (anzahlVersuche[indexMaxVersuche][0] * anzahlVersuche[indexMaxVersuche][1]));
	    
	}
	
}
