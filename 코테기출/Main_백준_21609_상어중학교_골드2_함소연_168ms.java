package 코테기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_백준_21609_상어중학교_골드2_함소연_168ms {
	static class Pos implements Comparable<Pos> {
		int r;
		int c;
		int cnt;
		int rainbow;

		public Pos(int r, int c, int cnt, int rainbow) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.rainbow = rainbow;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.cnt == o.cnt) {
				if (this.rainbow == o.rainbow) {
					if (this.r == o.r) {
						return o.c - this.c;
					} else {
						return o.r - this.r;
					}
				} else {
					return o.rainbow - this.rainbow;
				}
			} else {
				return o.cnt - this.cnt;
			}
		}
	}

	private static int N;
	private static int M;
	private static int[][] map;
	private static int score = 0;
	private static boolean[][] isVisited;
	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		input();
		go();
		System.out.println(score);
	}

	private static void go() {
		while (!checkBlock()) { //
			Pos p = findBlock();
			if(p.cnt== 0) break;
			remove(p);
			gravity();
			turn();
			gravity();
		}
	}

	private static void turn() {
		int[][] temp = new int[N][N];

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				temp[N - i - 1][j] = map[j][i];
			}
		}

		map = temp;
	}

	private static void gravity() {
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = N-1; j >= 0; j--) {
				if(map[j][i] == -2) {
					cnt++;
				}else {
					if(map[j][i] != -1 && cnt > 0) {
						map[j + cnt][i] = map[j][i];
						map[j][i] = -2;
						j = j + cnt;
						cnt = 0;
					}else {
						cnt = 0;
					}
				}
			}
		}
	}

	private static void remove(Pos b) {
		Deque<Pos> queue = new ArrayDeque<Pos>();
		isVisited = new boolean[N][N];

		isVisited[b.r][b.c] = true;

		queue.offer(new Pos(b.r, b.c, 0, 0));
		int color = map[b.r][b.c];

		while (queue.size() > 0) {
			Pos p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				if (map[nr][nc] == color && !isVisited[nr][nc]) { // 색이 같고 방문한 적이 없음
					isVisited[nr][nc] = true;
					map[nr][nc] = -2; // 쓰레기 값
					queue.offer(new Pos(nr, nc, 0, 0));
				} else if (map[nr][nc] == 0 && !isVisited[nr][nc]) { // 무지개고, 방문한 적이 없음
					isVisited[nr][nc] = true;
					map[nr][nc] = -2; // 쓰레기 값
					queue.offer(new Pos(nr, nc, 0, 0));
				}
			}

		} // end of while
		map[b.r][b.c] = -2; // 쓰레기 값
		score += b.cnt * b.cnt;
	}

	private static Pos findBlock() {
		Deque<Pos> queue = new ArrayDeque<Pos>();
		isVisited = new boolean[N][N];
		ArrayList<Pos> list = new ArrayList<Pos>();
		int color = 0;
		int r = 0;
		int c = 0;
		int cnt = 0;
		int rainbow = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 방문했었거나, 검은블록이거나, 무지개블록, 빈칸이면 continue
				if (map[i][j] == 0 || map[i][j] == -1 || isVisited[i][j] || map[i][j] == -2)
					continue;

				color = map[i][j];
				r = i;
				c = j;
				cnt = 1; // 개수
				rainbow = 0;
				isVisited[i][j] = true;
				queue.offer(new Pos(r, c, 0, 0));
				// BFS 시작
				while (queue.size() > 0) {
					Pos p = queue.poll();
					for (int d = 0; d < 4; d++) {
						int nr = p.r + dr[d];
						int nc = p.c + dc[d];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;

						if (map[nr][nc] == color && !isVisited[nr][nc]) { // 색이 같고 방문한 적이 없음
							isVisited[nr][nc] = true;
							queue.offer(new Pos(nr, nc, cnt++, rainbow));
						} else if (map[nr][nc] == 0 && !isVisited[nr][nc]) { // 무지개고, 방문한 적이 없음
							isVisited[nr][nc] = true;
							queue.offer(new Pos(nr, nc, cnt++, rainbow++));
						}
					}

				} // end of while
					// list에 저장
				if(cnt != 1)
				list.add(new Pos(r, c, cnt, rainbow));

				// 무지개 방문 초기화
				for (int x = 0; x < N; x++) {
					for (int y = 0; y < N; y++) {
						if (map[x][y] == 0 && isVisited[x][y]) {
							isVisited[x][y] = false;
						}
					}
				}
			}
		} // end of for
		Collections.sort(list);
		if(list.size() == 0) {
			return new Pos(0, 0, 0, 0);
		}
		Pos result = list.get(0);
		return result;

	}

	private static boolean checkBlock() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 1 && map[i][j] <= M)
					return false;
			}
		}
		return true;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == -2) {
					System.out.printf("%2s", "■");
				} else
					System.out.printf("%2d", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
