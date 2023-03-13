package A형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17070_파이프옮기기1_골드5_함소연_252ms {
	static int[] position = { 0, 1, 2 }; // 0 : 가로, 1 : 세로, 2 : 대각선
	private static int N;
	private static int total;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(0, 1, 0);
		System.out.println(total);
	}

	private static void move(int x, int y, int pos) {
		if (x == N - 1 && y == N - 1) {
			total++;
			return;
		}
		switch (pos) {
		case 0:
			// 가로
			horizontal(x, y);
			// 대각선
			diagonal(x, y);
			break;
		case 1:
			// 세로
			vertical(x, y);
			// 대각선
			diagonal(x, y);
			break;
		case 2:
			// 가로
			horizontal(x, y);
			// 세로
			vertical(x, y);
			// 대각선
			diagonal(x, y);
			break;
		default:
			break;
		}
	}

	// 범위 체크
	public static boolean checkMove(int x, int y, int pos) {
		if (x < 0 || x >= N || y < 1 || y >= N || map[x][y] == 1) return false;
		// 대각선 체크
		if(pos == 2) {
			if(map[x][y-1] == 1 || map[x-1][y] == 1) return false;
		}
		return true;
	}
	// 가로
	public static void horizontal(int x, int y) {
		int nx = x;
		int ny = y + 1;
		if (checkMove(nx, ny, 0)) {
			move(nx, ny, 0);
		}
	}
	// 세로
	public static void vertical(int x, int y) {
		int nx = x + 1;
		int ny = y;
		if (checkMove(nx, ny, 1)) {
			move(nx, ny, 1);
		}
	}
	// 대각선
	public static void diagonal(int x, int y) {
		int nx = x + 1;
		int ny = y + 1;
		if (checkMove(nx, ny, 2)) {
			move(nx, ny, 2);
		}
	}

}
