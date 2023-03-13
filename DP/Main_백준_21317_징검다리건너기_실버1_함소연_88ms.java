package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_21317_징검다리건너기_실버1_함소연_88ms {
	private static int[][] arr;
	private static int min = 987654321;
	private static int N;
	private static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N+1][2];
		for (int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()); // 바위 번호
			int l = Integer.parseInt(st.nextToken()); // 에너지 크기
			arr[i][0] = s;
			arr[i][1] = l;
		}

		K = Integer.parseInt(br.readLine());
		
		dfs(1, false, 0);
		System.out.println(min);
	}
	private static void dfs(int cnt, boolean bigJump, int E) {
		if(cnt == N) {
			min = Math.min(min, E);
			return;
		}
		if(cnt > N) return;
		// 작은 점프
		dfs(cnt+1, bigJump, E + arr[cnt][0]);
		// 큰 점프
		dfs(cnt+2, bigJump, E + arr[cnt][1]);
		// 완전 큰 점프
		if(!bigJump) dfs(cnt+3, true, E + K);
	}
}