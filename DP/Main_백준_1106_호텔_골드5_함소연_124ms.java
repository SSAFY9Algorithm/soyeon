package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** knapsack */
public class Main_백준_1106_호텔_골드5_함소연_124ms {
	private static int[][] arr;
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int C = Integer.parseInt(st.nextToken()); // 늘려야 하는 최소 고객
		int N = Integer.parseInt(st.nextToken()); // 홍보 가능한 도시 수
		
		arr = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[100001];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 100000; j++) {
				if(j - arr[i][0] >= 0) // 범위
				dp[j] = Math.max(dp[j], dp[j - arr[i][0]] + arr[i][1]);
			}
		}
		
		System.out.println(Arrays.toString(dp));
		for (int i = 1; i <= 100001; i++) {
			if(dp[i] >= C) { // 적어도 C명 이상 고객 유치
				System.out.println(i);
				break;
			}
		}
		
	} // end of main
} // end of class
