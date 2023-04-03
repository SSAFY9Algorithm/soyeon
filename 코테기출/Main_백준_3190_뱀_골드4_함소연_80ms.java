package 코테기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_백준_3190_뱀_골드4_함소연_80ms {
	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static Deque<Pos> queue = new ArrayDeque<Pos>();
	private static ArrayList<Pos> list = new ArrayList<Pos>();
	private static int N;
	private static int L;
	private static int K;
	private static int[][] map;
	private static int[][] turn;
	private static int time = 0;
	private static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	private static int[] dc = { 1, 0, -1, 0 };
	private static int d = 0;
	private static int idx = 0;
	private static int[][] temp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		go();
		System.out.println(sb.toString());
	}

	private static void go() {
		int r = 0;
		int c = 0;
		queue.offer(new Pos(0, 0));
		map[0][0] = 1;
		while (true) {
			// 직진
			int nr = r + dr[d]; // 한칸 이동
			int nc = c + dc[d];

			// 시간 증가
			time++;

			// 움직일 수 있는지 확인
			// 없으면 break;
			if (!isPossible(nr, nc))
				break;

			// 머리에 사과 확인
			if (!isApple(nr, nc)) { // 사과가 없으면, queue.poll
				Pos p = queue.poll();
				map[p.r][p.c] = 0; // 꼬리 삭제
			}
			
			// 머리를 다음칸에 위치 queue.offer()
			queue.offer(new Pos(nr, nc));
			map[nr][nc] = 1; // 머리

			// 방향확인
			checkDirection();

			// head 재지정
			r = nr;
			c = nc;
		}
		sb.append(time);
	}

	private static void checkDirection() {
		int key = 0;

		if (idx < L && turn[idx][0] == time) {
			key = turn[idx][1];
			idx++;
		}
		if (key == 'L') { // 왼쪽
			d--;
			if (d < 0)
				d = 3; // 범위 재지정
		} else if (key == 'D') { // 오른쪽
			d++;
			if (d > 3)
				d = 0; // 범위 재지정
		}
	}

	private static boolean isPossible(int r, int c) {
		// 벽
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}
		// 자기 몸
		if (map[r][c] == 1)
			return false;
		return true;
	}

	private static boolean isApple(int r, int c) {
		if (map[r][c] == 2) {
			return true;
		} else
			return false;
	}

	private static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1] = 2; // apple
		}

		L = Integer.parseInt(br.readLine());
		turn = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int c = st.nextToken().charAt(0);
			turn[i][0] = x;
			turn[i][1] = c;
		}
	}
}
