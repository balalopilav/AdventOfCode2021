import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class tag5 {
	
	static point[] lineToPoints(String line) {
		int[] zahlen = new int[4];
		point p1 = new point();
		point p2 = new point();
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
		
		p1.x = zahlen[0];
		p1.y = zahlen[1];
		p2.x = zahlen[2];
		p2.y = zahlen[3];
		point[] points = {p1, p2};
		return points;
	}
	
	static boolean contains(int[] array, int nummer) {
		for(int zahl : array) {
			if (zahl == nummer) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("./files/tag5.txt");
		int[][] map = new int[1000][1000];
		int[][] map1 = new int[1000][1000];
				
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	point[] readPoints = lineToPoints(line);
		    	map = addPointsToMap(map, readPoints, false); //Two calls for parts 1 and 2
		    	map1 = addPointsToMap(map1, readPoints, true);
		    }
		}
	    
	    
	    System.out.println(getOverlapping(map) + " lines are overlapping");
	    System.out.println(getOverlapping(map1) + " lines are overlapping including diagonal");
	}

	private static int getOverlapping(int[][] map) {
		int counter = 0;
		
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y] > 1) {
					counter++;
				}
			}
		}
		return counter;
	}

	private static int[][] addPointsToMap(int[][] map, point[] points, boolean useDiagonal) {
		//Vertical
		if (points[0].x == points[1].x) {
			int distance = points[0].dinstanceToPoint(points[1]);
			int smallerY = points[0].y < points[1].y ? points[0].y : points[1].y;
			for (int i = 0; i < distance; i++) {
				map[points[0].x][smallerY + i]++;
			}
		}
		
		//Horizontal
		else if (points[0].y == points[1].y) {
			int distance = points[0].dinstanceToPoint(points[1]);
			int smallerX = points[0].x < points[1].x ? points[0].x : points[1].x;
			for (int i = 0; i < distance; i++) {
				map[smallerX + i][points[0].y]++;
			}
		}
		
		//Diagonal
		else if (useDiagonal && points[0].is45DegreesToPoint(points[1])){
			int smallerY = 0, smallerX = 0;
			int distance = Math.abs(points[0].x - points[1].x) + 1 ;
		
			if (points[0].x > points[1].x) 
				smallerX = 1;
			if (points[0].y > points[1].y)
				smallerY = 1;
			
			for (int i = 0; i < distance; i++) {
				if (smallerX == 0 && smallerY == 0)
					map[points[0].x + i][points[0].y + i]++;
				else if (smallerX == 0 && smallerY == 1)
					map[points[0].x + i][points[0].y - i]++;
				else if (smallerX == 1 && smallerY == 0)
					map[points[0].x - i][points[0].y + i]++;
				else if (smallerX == 1 && smallerY == 1)
					map[points[0].x - i][points[0].y - i]++;
			}
		}
		
		return map;
	}
	
}

class point {
	int x, y;
	
	public point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public point() {
		this.x = 0;
		this.y = 0;
	}
	
	public int dinstanceToPoint(point p){
		return (int) (Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2))) +1;	
	}
	
	public boolean is45DegreesToPoint(point p) {
		int X = Math.abs(p.x - this.x);
		int Y = Math.abs(p.y - this.y);
		
		if(X == Y) {
			return true;
		}
		return false;
		
//		int angle = (int) Math.toDegrees(Math.atan2(p.y - this.y, p.x - this.x));
//
//	    if(angle < 0){
//	        angle += 360;
//	    }
//
//		return angle == 45? true : false;
	}
}
