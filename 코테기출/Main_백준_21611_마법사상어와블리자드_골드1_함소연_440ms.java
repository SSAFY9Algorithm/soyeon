package 코테기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_백준_21611_마법사상어와블리자드_골드1_함소연_440ms {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] magic;
	private static int sharkr;
	private static int sharkc;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int time;
	private static int[] boom;
	static ArrayList<Integer> list;
	static ArrayList<Integer> removeList;
	static Stack<Integer> stack;
	private static int res = 0;

	public static void main(String[] args) throws IOException {
		input();
		go();
		System.out.println(res);
	}

	private static void go() {
		time = 0;
		while (time++ < M) {
			// 블리자드 마법 수행
			blizard();
			// 1차원으로 맵 풀기
			makeList();
			// 구슬 폭발
			boom();
			// 구슬 변화
			changeMarble();
			// 2차원 맵 만들기
			makeMap();
		}
		res();
	}

	private static void res() {
		res += (boom[0]) + (boom[1] * 2) + (boom[2] * 3);
	}

	private static void makeMap() {
		while (stack.size() <= N * N - 1) {
			stack.push(0);
		}
		map = new int[N][N];
		int time = N * 2 - 1;
		int n = N - 1;
		int turn = 1;

		int r = 0;
		int c = N - 1;
		int nr = 0;
		int nc = 0;
		// 첫번째 줄
		for (int i = 0; i < N; i++) {
			map[0][i] = stack.pop();
		}
		// N * 2 - 1번
		while (time-- > 0) {
			// 세로
			for (int i = 0; i < n; i++) {
				r = r + turn;
				map[r][c] = stack.pop();

			}

			// 증가 <-> 감소
			turn = turn == 1 ? -1 : 1;

			// 가로
			for (int i = 0; i < n; i++) {
				c = c + turn;
				map[r][c] = stack.pop();
			}

			// 횟수
			n--;
		}
	}

	private static void changeMarble() {
		stack = new Stack<>();
		stack.push(0);
		for (int i = 0; i < list.size();) {
			if (stack.size() > N * N - 1)
				break;
			int cnt = 1;
			int marble = list.get(i);
			if (i < list.size() - 1 && list.get(i) == list.get(i + 1)) {
				for (int j = 1; j < 3; j++) {
					if (i + j >= list.size() || list.get(i + j) != marble) {
						break;
					}
					cnt++;
				}
				i += cnt;
			} else {
				i++;
			}
			stack.push(cnt);
			stack.push(marble);
		}
	}

	private static void boom() {
		while (!checkGroup()) {
			int cnt = 1;
			for (int i = 0; i < list.size() - 3; i++) {
				cnt = 1;
				if (list.size() == 0)
					break;
				if (list.get(i) != list.get(i + 1))
					continue;
				int marble = list.get(i);
				while (true) {
					if (list.size() <= i + cnt) {
						break;
					}
					if (marble == list.get(i + cnt)) {
						cnt++;
					} else
						break;
				}
				if (cnt >= 4) {
					for (int j = 0; j < cnt; j++) {
						boom[marble - 1]++;
						list.remove(i);
					}
				}
			}
		}
	}

	private static boolean checkGroup() {
		for (int i = 0; i < list.size() - 3; i++) {
			if (list.get(i) != list.get(i + 1))
				continue;
			int marble = list.get(i);
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				if (marble != list.get(i + j)) {
					// i += j;
					break;
				} else {
					cnt++;
				}
			}
			if (cnt == 4) {
				return false;
			}
		}
		return true;
	}

	private static void makeList() {
		list = new ArrayList<>();
		stack = new Stack<>();
		int time = N * 2 - 1;
		int n = N - 1;
		int turn = 1;

		int r = 0;
		int c = N - 1;
		int nr = 0;
		int nc = 0;
		// 첫번째 줄
		for (int i = 0; i < N; i++) {
			stack.push(map[0][i]);
		}
		// N * 2 - 1번
		while (time-- > 0) {
			// 세로
			for (int i = 0; i < n; i++) {
				r = r + turn;
				stack.push(map[r][c]);

			}

			// 증가 <-> 감소
			turn = turn == 1 ? -1 : 1;

			// 가로
			for (int i = 0; i < n; i++) {
				c = c + turn;
				stack.push(map[r][c]);
			}

			// 횟
			n--;
		}
		while (!stack.isEmpty()) {
			if (stack.peek() != 0) {
				list.add(stack.pop());
			} else {
				stack.pop();
			}
		}
	}

	private static void blizard() {
		int d = magic[time - 1][0];
		int nr = sharkr;
		int nc = sharkc;
		for (int i = 0; i < magic[time - 1][1]; i++) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				break;
			map[nr][nc] = 0;
		}

	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N]; // 맵
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		magic = new int[M][2]; // 마법
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			magic[i][0] = Integer.parseInt(st.nextToken()) - 1;
			magic[i][1] = Integer.parseInt(st.nextToken());
		}

		sharkr = (N + 1) / 2 - 1;
		sharkc = (N + 1) / 2 - 1;

		boom = new int[3];
	}

}
