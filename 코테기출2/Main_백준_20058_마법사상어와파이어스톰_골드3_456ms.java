package 코테기출2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_20058_마법사상어와파이어스톰_골드3_456ms{
	private static int N;
	private static int Q;
	private static int[][] map;
	private static int[][] temp;
	private static int[] arr;
	private static int idx = 0;
	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { 1, -1, 0, 0 };
	private static int total;
	private static boolean[][] isVisited;
	private static int group;
	private static int MAX = 0;

	public static void main(String[] args) throws IOException {
		input();
		go();
		System.out.println(total);
		System.out.println(MAX);
	}

	private static void go() {
		int time = Q;
		while (time-- > 0) {
			turn();
			temp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp[i][j] = map[i][j];
				}
			}
			removeIce();
			map=temp;
		}
		getCount();
		getGroup();
	}

	// 가장 큰 덩어리의 개수 구하기 dfs
	private static void getGroup() {
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] >= 1) {
					group = 1;
					isVisited[i][j] = true;
					dfs(i, j);
					MAX  = Math.max(group, MAX);
				}
			}
		}
	}

	private static void dfs(int r, int c) {
		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N ) continue;
			if(!isVisited[nr][nc] && map[nr][nc] >= 1) {
				isVisited[nr][nc] = true;
				group++;
				dfs(nr, nc);
			}
		}
	}

	// 남아있는 얼음 합
	private static void getCount() {
		total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 1) {
					total += map[i][j];
				}
			}
		}
	}

	// 3이상과 인접해있지 않은 칸은 1감소
	private static void removeIce() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] <= 0) {
						cnt++;
					}
				}
				if (cnt >= 2) {
					temp[i][j] -= 1;
				}
			}
		}
	}

	// 시계방향 회전
	private static void turn() {
		int L = arr[idx++];
		if(L == 0) return;
		int size = (int) Math.pow(2, L);
		for (int i = 0; i < N; i += size) {
			for (int j = 0; j < N; j += size) {
				int cnt = 0;
				int r = i;
				int c = j;
				while (cnt < size / 2) {
					int len = size - cnt * 2;
					int[] temp = new int[len];
					for (int k = 0; k < len; k++) {
						temp[k] = map[r][c + k];
					}
					for (int k = 0; k < len; k++) {
						map[r][c + k] = map[r + len - 1 - k][c];
					}
					for (int k = 0; k < len; k++) {
						map[r + k][c] = map[r + len - 1][c + k];
					}
					for (int k = 0; k < len; k++) {
						map[r + len - 1][c + k] = map[r + len - 1 - k][c + len - 1];
					}
					for (int k = 0; k < len; k++) {
						map[r + k][c + len - 1] = temp[k];
					}
					r += 1;
					c += 1;
					cnt++;
				}
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N);
		N = size;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		arr = new int[Q];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
}
