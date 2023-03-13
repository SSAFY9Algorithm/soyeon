package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_12026_BOJ거리_실버1_함소연_92ms {
	private static int[] dp;
	private static char[] block;
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 보도 블럭 수
		block = br.readLine().toCharArray();
		dp = new int[N];
		Arrays.fill(dp, 987654321);
		jump();
		if(dp[N-1] == 987654321) {
			System.out.println(-1);
		}else {
			System.out.println(dp[N - 1]);
		}
	} // end of main

	private static void jump() {
		dp[0] = 0;
		for (int i = 0; i < N - 1; i++) {
			char now = block[i];
			switch (now) {
			case 'B':
				findNext(i, 'O');
				break;
			case 'O':
				findNext(i, 'J');
				break;
			case 'J':
				findNext(i, 'B');
				break;
			default:
				break;
			}
		}
	}
	private static void findNext(int i, char ch) {
		for (int j = i+1; j < N; j++) {
			char next = block[j];
			if(next == ch) {
				dp[j] = Math.min(dp[j], dp[i] + (j - i) * (j - i));
			}
		}
	}
} // end of class
