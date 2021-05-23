import java.io.*;
import java.util.*;

public class RoomAllocation {
	static class Cust implements Comparable<Cust> {
		int a, d, idx;
		Cust (int a, int d, int idx) { this.a = a; this.d = d; this.idx = idx; }
		public int compareTo (Cust c) { 
			if (a == c.a) return Integer.compare(idx, c.idx);
			return Integer.compare(a, c.a); 
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n = in.iscan(), ans = 0;
		Cust[] arr = new Cust[n];
		for (int i = 0; i < n; i ++) arr[i] = new Cust(in.iscan(), in.iscan(), i);
		Arrays.sort(arr);
		TreeSet<Cust> rooms = new TreeSet<Cust>();
		Stack<Integer> empty = new Stack<Integer>();
		
		int[] res = new int[n];
		for (int i = 0; i < n; i ++) {
			while (!rooms.isEmpty() && arr[i].a > rooms.first().a) empty.push(res[rooms.pollFirst().idx]);
				
			if (empty.isEmpty()) { 
				ans ++; 
				res[arr[i].idx] = ans; 
				rooms.add(new Cust(arr[i].d, arr[i].a, arr[i].idx));
			} else {
				res[arr[i].idx] = empty.pop();
				rooms.add(new Cust(arr[i].d, arr[i].a, arr[i].idx));
			}
		}
		out.println(ans);
		for (int i = 0; i < n; i ++) out.print(res[i] + " ");
		out.close();
	}

	static INPUT in = new INPUT(System.in);
	static PrintWriter out = new PrintWriter(System.out);
	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		@SuppressWarnings("unused")
		public INPUT (String file) throws IOException {
			this.stream = new FileInputStream (file);
		}

		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
			}
			
			if (numChars == -1)
				return numChars;

			return buf[curChar++];
		}

		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;
			
			while (space (c))
				c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			int res = 0;

			do {
				res = (res << 1) + (res << 3);
				res += c - '0';
				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		@SuppressWarnings("unused")
		public String sscan () throws IOException {
			int c = cscan ();
			
			while (space (c))
				c = cscan ();

			StringBuilder res = new StringBuilder ();

			do {
				res.appendCodePoint (c);
				c = cscan ();
			}
			while (!space (c));

			return res.toString ();
		}

		@SuppressWarnings("unused")
		public double dscan () throws IOException {
			int c = cscan (), sgn = 1;
			
			while (space (c))
				c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			double res = 0;

			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * UTILITIES.fast_pow (10, iscan ());
				
				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E')
						return res * UTILITIES.fast_pow (10, iscan ());

					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}

			return res * sgn;
		}

		@SuppressWarnings("unused")
		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;
			
			while (space (c))
				c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			long res = 0;

			do {
				res = (res << 1) + (res << 3);
				res += c - '0';
				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}

	public static class UTILITIES {

		static final double EPS = 10e-6;

		public static int lower_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] >= x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int upper_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] > x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int gcd (int a, int b) {
			return b == 0 ? a : gcd (b, a % b);
		}

		public static int lcm (int a, int b) {
			return a * b / gcd (a, b);
		}

		public static long fast_pow_mod (long b, long x, int mod) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;

			return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
		}

		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);

			return b * fast_pow (b * b, x / 2);
		}

		public static long choose (long n, long k) {
			k = Math.min (k, n - k);
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = val * (n - i) / (i + 1);

			return val;
		}

		public static long permute (int n, int k) {
			if (n < k) return 0;
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = (val * (n - i));

			return val;
		}
	}
}
