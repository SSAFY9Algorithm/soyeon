package 분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_17829_222풀링 {
	static int[][] pool;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pool = new int[N][N];
		StringTokenizer st; 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				pool[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(Pooling(N, 0, 0));
<<<<<<< HEAD
	} // end of main
	
=======
		
	} // end of main
>>>>>>> 284d4774554f0459225244fbcaa9364bb33bb571
	public static int Pooling(int N, int x, int y) {
		if(N == 2) {
			return FindSecond(N, x, y);
		}else {
			int[] arr = new int[4];
			int N2 = N/2;
			arr[0] = Pooling(N2, x, y);
			arr[1] = Pooling(N2, x + N2, y);
			arr[2] = Pooling(N2, x, y + N2);
			arr[3] = Pooling(N2, x + N2, y + N2);
			Arrays.sort(arr);
			return arr[2]; // 두 번째로 큰 수
		}
<<<<<<< HEAD
	} // end of Pooling
	
=======
	}
>>>>>>> 284d4774554f0459225244fbcaa9364bb33bb571
	public static int FindSecond(int N, int x, int y) {
		int[] arr = new int[4];	
		int idx = 0;
		for (int i = x; i < x + 2; i++) {
			for (int j = y; j < y + 2; j++) {
				arr[idx++] = pool[i][j];
			}
		}
		Arrays.sort(arr);
		return arr[2]; // 두 번째로 큰 수
<<<<<<< HEAD
	} // end of FindSecond
} // end of class
=======
	}
}
>>>>>>> 284d4774554f0459225244fbcaa9364bb33bb571
