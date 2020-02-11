import java.util.Random;

public class Field3D extends Field{
	
	int n, bomb;
	int[][][] grid;
	
	public Field3D(int n, int bomb) {
		this.n = n;
		this.bomb = bomb;
		
		grid = new int[n][n][n];
		init(1);
		
		Random r = new Random();
		
		for(int i=0;i<bomb;i++) {
			boolean loop = true;
			int x = 0;
			int y = 0;
			int z = 0;
			
			while(loop) {
				x = r.nextInt(n);
				y = r.nextInt(n);
				z = r.nextInt(n);
				
				if(grid[x][y][z] != 20) {
					loop = false;
				}
			}
			
			grid[x][y][z] = 20;
			surround(x,y,z);
		}
	}
	
	void surround(int x, int y, int z) {
		for(int i=x-1;i<=x+1;i++) {
			for(int j=y-1;j<=y+1;j++) {
				for(int k=z-1;k<=z+1;k++) {
					if(inBound(grid.length-1,i,j,k) && !(x == i && y == j && z == k)) {
						grid[i][j][k]++;
					}
				}
			}
		}
	}

	@Override
	int score() {
		int score = 0;
		
		for(int x=0;x<n;x++) {
			for(int y=0;y<n;y++) {
				for(int z=0;z<n;z++) {
					score += grid[x][y][z];
				}
			}
		}
		
		return score;
	}

	boolean inBound(int bound, int x, int y, int z) {
		return x >= 0 && x <= bound && y >= 0 && y <= bound && z >= 0 && z <= bound;
	}

	@Override
	void init(int val) {
		for(int x=0;x<n;x++) {
			for(int y=0;y<n;y++) {
				for(int z=0;z<n;z++) {
					grid[x][y][z] = val;
				}
			}
		}
		
	}

}
