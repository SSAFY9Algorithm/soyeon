

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 플로이드 워셜 O(n^3) */
public class Main_백준_1389_케빈베이컨의6단계법칙_플로이드워셜 {
	static int[][] list;
	static int[][] bridge;
	static int N, M, ans, sum;
	static StringBuilder sb = new StringBuilder();
	static int min = Integer.MAX_VALUE;
	static int INF = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new int[N + 1][N + 1];
		bridge = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a][b] = 1; // 양방향
			list[b][a] = 1;
		}
		// 배열 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				else if (list[i][j] == 1)
					bridge[i][j] = 1;
				else
					bridge[i][j] = INF;
			}
		}
		FloydWarshall();
		// 케빈 베이컨의 수가 가장 작은 사람
		for (int i = 1; i <= N; i++) {
			sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += bridge[i][j];
			}
			if (min > sum) {
				min = sum;
				ans = i;
			}
		}
		System.out.println(ans);
	} // end of main

	/** 플로이드 워셜 */
	public static void FloydWarshall() {
		for (int k = 1; k <= N; k++) { // k : 중간 다리
			for (int i = 1; i <= N; i++) { // i부터 j까지 갈때 중간다리 k를 건널지 말지 (작으면 건넘)
				for (int j = 1; j <= N; j++) {
					bridge[i][j] = Math.min(bridge[i][j], bridge[i][k] + bridge[k][j]);
				}
			}
		}
	}

} // end of class