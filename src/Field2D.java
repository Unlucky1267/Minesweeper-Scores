import java.util.Random;

public class Field2D extends Field{
	int n, bomb;
	int[][] grid;
	
	public Field2D(int n, int bomb) {
		this.n = n;
		this.bomb = bomb;
		
		grid = new int[n][n];
		init(1);
		
		Random r = new Random();
		
		for(int i=0;i<bomb;i++) {
			boolean loop = true;
			int x = 0;
			int y = 0;
			
			while(loop) {
				x = r.nextInt(n);
				y = r.nextInt(n);
				
				if(grid[x][y] != 20) {
					loop = false;
				}
			}
			
			grid[x][y] = 20;
			surround(x,y);
		}
	}
	
	public int score() {
		int score = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				score += grid[i][j];
			}
		}
		return score;
	}
	
	public void surround(int x, int y) {
		for(int i=x-1;i<=x+1;i++) {
			for(int j=y-1;j<=y+1;j++) {
				if(inBound(grid.length-1,i,j) && !(x == i && y == j)) {
					grid[i][j]++;
				}
			}
		}
	}
	
	public boolean inBound(int bound , int x, int y) {
		return x >= 0 && x <= bound && y >= 0 && y <= bound;
	}
	
	public void init(int value) {
		int n = grid.length;
		
		for(int i=0;i<n;i++) {
			for(int k=0;k<n;k++) {
				grid[i][k] = value;
			}
		}
	}

}
