package 기출_문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1749_점수따먹기_골드4_함소연 {
	static int N, M, max = Integer.MIN_VALUE;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		input();
		go();
		System.out.println(max);
	}

	private static void go() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int r = i; r < N; r++) {
					for (int c = j; c < M; c++) {
						cnt = map[r][c];
						if(j-1 >= 0) cnt -= map[r][j-1];
						if(i-1 >= 0) cnt -= map[i-1][c];
						if(i-1 >=0 && j-1 >=0 ) cnt += map[i-1][j-1];
						max = Math.max(max, cnt);
					}
				}
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i - 1 >= 0) map[i][j] += map[i-1][j];
				if(j - 1 >= 0) map[i][j] += map[i][j-1];
				if(i - 1 >= 0 && j - 1>= 0) map[i][j] -= map[i-1][j-1];
			}
		}
	}
}
