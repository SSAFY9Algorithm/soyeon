package 최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1774_우주신과의교감_골드3_함소연_1652ms {
	static int[][] pos;
	static int[] parent;
	private static double[][] map;
	public static int[] numbers;
	static double total = 0;
	private static int N, idx = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		pos = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}

		numbers = new int[2];
		int size = ((N + 1) * N) / 2;
		map = new double[size][3];
		comb(0, 0);

		Arrays.sort(map, (o1, o2) -> Double.compare(o1[2], o2[2]));

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			union(a, b);
		}

		for (int i = 0; i < size; i++) {
			if (find((int) map[i][0]) != find((int) map[i][1])) {
				union((int) map[i][0], (int) map[i][1]);
				total += map[i][2];
			}
		}
		System.out.printf("%.2f", total);
	} // end of main

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	private static void union(int x, int y) {
		int px = find(x);
		int py = find(y);

		if (px > py) {
			parent[py] = px;
		} else {
			parent[px] = py;
		}
	}

	private static double getDist(int x1, int y1, int x2, int y2) {
		long x = Math.abs(x1 - x2);
		long y = Math.abs(y1 - y2);
		return Math.sqrt(x * x + y * y);
	}

	public static void comb(int cnt, int start) {
		if (cnt == 2) {
			double dist = getDist(pos[numbers[0]][0], pos[numbers[0]][1], pos[numbers[1]][0], pos[numbers[1]][1]);
			map[idx][0] = numbers[0];
			map[idx][1] = numbers[1];
			map[idx][2] = dist;
			idx++;
			return;
		}
		for (int i = start; i < N; i++) {
			numbers[cnt] = i;
			comb(cnt + 1, i);
		}
	}
} // end of class
