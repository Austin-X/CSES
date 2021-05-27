import java.io.*;
import java.util.*;

public class ChessboardAndQueens {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PrintWriter pw = new PrintWriter(System.out);
	static int ans = 0;
	static char[][] grid;
	
	public static void main(String[] args) throws IOException {
		grid = new char[8][8];
		for (int i = 0; i < 8; i ++) grid[i] = readLine().toCharArray();
		solve(0, new HashMap<Integer, Integer>());
		
		pw.println(ans);
		pw.close();
	}
	
	static void solve(int col, HashMap<Integer, Integer> map) {
		if (col == 8) { ans ++; return; }
		for (int i = 0; i < 8; i ++) {
			boolean flag = true;
			for (int x: map.keySet()) if (Math.abs(i - x) ==  Math.abs(col - map.get(x))) { flag = false; break; }
			if (flag && !map.containsKey(i) && grid[i][col] != '*') {
				map.put(i, col);
				solve(col + 1, map);
				map.remove(i);
			}
		}
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) 
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong() throws IOException {
		return Long.parseLong(next());
	}
	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter() throws IOException {
		return next().charAt(0);
	}
	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}
