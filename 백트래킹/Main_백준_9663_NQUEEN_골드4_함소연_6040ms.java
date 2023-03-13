package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_9663_NQUEEN_골드4_함소연_6040ms {
	static int N, res = 0;
	static int[] board;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N];
		Queen(0);
		System.out.println(res);
	} // end of main

	public static void Queen(int n) {
		if (n == N) {
			res++;
			return;
		}
		for (int i = 0; i < board.length; i++) {
			board[n] = i;
			if (promising(n)) {
				Queen(n + 1);
			}
		}
	} // end of queen

	public static boolean promising(int n) {
		for (int i = 0; i < n; i++) {
			// 대각선 (행의 차 == 열의 차)일 경우, 행과 열이 같을 경우
			if(Math.abs(n - i) == Math.abs(board[n] - board[i])|| board[n] == board[i]) return false; 
		}
		return true;
	} // end of promising
} // end of class
