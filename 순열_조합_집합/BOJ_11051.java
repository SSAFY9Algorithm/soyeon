package 순열_조합_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * 이항계수2 : 이항계수 : ( n k ) = (n! / (k! * (n-k)!)) == 파스칼의 정리
 *
 */
public class BOJ_11051 {
	static int[][] pascal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		pascal = new int[N + 1][N + 1];
		
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i ; j++) {
				if( j == 0 || i == j) pascal[i][j] = 1;
				else pascal[i][j] = (pascal[i-1][j-1] + pascal[i-1][j]) % 10007;
			}
		}
		System.out.println(pascal[N][K]);
		
	}
}
