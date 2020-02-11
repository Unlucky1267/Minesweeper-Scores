import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Mines {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Pick Mode. 2D (2) or 3D (3): ");
		int mode = s.nextInt();
		System.out.println("How many games should be simulated?: ");
		int games = s.nextInt();
		System.out.println("Choose n: ");
		int gameSize = s.nextInt();
		System.out.println("Choose b: ");
		int bombs = s.nextInt();
		System.out.println("Score to beat: ");
		int sumAfter = s.nextInt();
		
		int maxScore = 0;
		if(mode == 2) {
			maxScore = (bombs * 20) + (gameSize*gameSize - bombs) + (bombs * 8) - 1;
		} else {
			maxScore = (bombs * 20) + (gameSize*gameSize*gameSize - bombs) + (bombs * 26) - 1;
		}
		
		if(!possible(mode, gameSize, bombs)) {
			System.out.println("Result will be invalid due to large number of Mines");
		}
		
		System.out.println("The maximum Score is: " + maxScore);
		
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		ArrayList<Scores> list = new ArrayList<Scores>();
		
		for(int g=0;g<games;g++ ) {
			Field f;
			
			if(mode == 2) {
				f = new Field2D(gameSize,bombs);
			} else {
				f = new Field3D(gameSize,bombs);
			}
			int score = f.score();
			
			//print(f.grid);
			
			if(map.containsKey(score)) {
				list.get(map.get(score)).add();
			} else {
				Scores newScore = new Scores(score);
				map.put(score,list.size());
				list.add(newScore);
			}
		}
		
		Collections.sort(list,new Comparator<Scores>() {
			@Override
			public int compare(Scores o1, Scores o2) {
				return Integer.compare(o1.score, o2.score);
			}
		});
		
		System.out.println(games + " games were analyzed:");
		double toBeat = 0;
		
		for(int i=0;i<list.size();i++) {
			Scores sc = list.get(i);
			double percent = ((double) sc.amount) / ((double) games);
			percent *= 100;
			if(sc.score - 1 > sumAfter) {
				toBeat += percent;
			}
			System.out.println("The score " + (sc.score-1) + " was found " + sc.amount + " times. Percentage: " + percent);
		}
		
		System.out.println("You have the Prob. " + toBeat + " % to beat the champion");
	}
	
	public static boolean possible(int mode,int n ,int b) {
		if(mode == 2) {
			return b <= ((n-1)/2) * ((n-1)/2);
		} else {
			return b <= ((n-1)/2) * ((n-1)/2) * ((n-1)/2);
		}
	}
	
	public static void print(int[][] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
