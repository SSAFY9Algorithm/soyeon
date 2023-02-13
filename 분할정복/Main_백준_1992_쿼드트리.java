package 분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1992_쿼드트리 {
	static int[][] arr;
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		QuadTree(N, 0, 0);
		System.out.println(sb.toString());
	} // end of main
	
	public static void QuadTree(int N, int x, int y) {
		if(N < 1) return; // 더이상 쪼갤 수 없으면 return	
		if(check(N, x, y)) { // 모두 1이거나 0이면 만족
			if(arr[x][y] == 1) {
				sb.append(1);
			}else
				sb.append(0);
		}else {
			sb.append("(");
			int N2 = N/2;
			QuadTree(N2, x, 		y); 		// (0, 0)
			QuadTree(N2, x, 		y + N2); 	// (0, 1)
			QuadTree(N2, x + N2, 	y); 		// (1, 0)
			QuadTree(N2, x + N2,	y + N2); 	// (1, 1)
			sb.append(")");
		}
		
	} // end of QuadTree
	public static boolean check(int N, int x, int y) {
		int color = arr[x][y]; // 맨 처음 색상
		for (int i = x; i < x+N ; i++) {
			for (int j = y; j < y+N; j++) {
				if(arr[i][j] != color)
					return false;
			}
		}
		return true;
	} // end of check
} // end of class
