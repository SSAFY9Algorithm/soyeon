package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18111 {
	static int[][] map;
	static int N;
	static int M;
	static int B;
	static int time;
	static int Block;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int min = Integer.MAX_VALUE;
		int h = 0;
		for (int i = 0; i < 257; i++) {
			time = 0;
			Block = B;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[j][k] == i)
						continue;
					else if (map[j][k] > i) {
						minus(j, k, i);
					} else {
						plus(j, k, i);
					}
				}
			}

			if (min >= time && Block >= 0) {
				min = time;
				h = i;
			}
		}
		System.out.print(min + " " + h);
	}

	public static void plus(int j, int k, int i) {
		Block -= i - map[j][k];
		time += 1 * (i - map[j][k]);
	}

	public static void minus(int j, int k, int i) {
		Block += map[j][k] - i;
		time += 2 * (map[j][k] - i);
	}
}
