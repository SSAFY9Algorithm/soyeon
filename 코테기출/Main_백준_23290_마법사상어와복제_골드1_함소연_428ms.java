package 코테기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_백준_23290_마법사상어와복제_골드1_함소연_428ms{
	static class Pos implements Comparable<Pos> {
		int cnt;
		int p1;
		int p2;
		int p3;

		public Pos(int cnt, int p1, int p2, int p3) {
			super();
			this.cnt = cnt;
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.cnt == o.cnt) {
				if (this.p1 == o.p1) {
					if (this.p2 == o.p2) {
						return this.p3 - o.p3;
					} else
						return this.p2 - o.p2;
				} else
					return this.p1 - o.p1;
			} else
				return o.cnt - this.cnt;
		}
	}

	private static int[] sr = { -1, 0, 1, 0 }; // 상 좌 하 우
	private static int[] sc = { 0, -1, 0, 1 };
	private static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 물고기 방향
	private static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int S;
	private static int M;
	private static ArrayList[][] map;
	private static ArrayList[][] temp;
	private static ArrayList[][] copy;
	private static ArrayList<Pos> list;
	private static int[][] smell;
	private static boolean[][] isVisited;
	private static int[] move;
	private static int sharkr;
	private static int sharkc;
	private static int maxCnt = -1;

	public static void main(String[] args) throws IOException {
		input();
		go();
		int res = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!map[i][j].isEmpty()) {
					for (int k = 0; k < map[i][j].size(); k++) {
						res++;
					}
				}
			}
		}
		System.out.println(res);
	}

	private static void go() {
		while (S-- > 0) {
			// 물고기 복제
			copyOfFish();
			// 물고기 한칸 이동
			moveFish();
			// 상어 3칸이동
			moveShark();
			// 2턴 전 물고기 냄새 삭제
			removeSmell();
			// 복제 마법 완료
			completeCopy();
			map = temp;
		}
	}

	private static void removeSmell() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(smell[i][j] < -2) {
					smell[i][j] = 0;
				}
			}
		}
	}

	private static void completeCopy() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!copy[i][j].isEmpty()) {
					for (int k = 0; k < copy[i][j].size(); k++) {
						temp[i][j].add(copy[i][j].get(k));
					}
				}
			}
		}

	}

	private static void moveShark() {
		move = new int[3];
		list = new ArrayList<>();
		maxCnt = -1;
		// 중복 순열
		
		perm(0);

		Collections.sort(list);
		Pos p = list.get(0); // 최대 개수 구하기
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(smell[i][j] != 0) {
					smell[i][j]--;
				}
			}
		}
		// 물고기 지우기
		int r = sharkr;
		int c = sharkc;
		int[] pos = { p.p1, p.p2, p.p3 };
		for (int i = 0; i < 3; i++) {
			int nr = r + sr[pos[i]];
			int nc = c + sc[pos[i]];
			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4)
				continue;
			if (!temp[nr][nc].isEmpty()) {
				temp[nr][nc].clear();
				smell[nr][nc] = -1;
			}
			r = nr;
			c = nc;
		}
		sharkr = r;
		sharkc = c;
	}

	private static void perm(int start) {
		if (start == 3) {
			checkCnt(move);
			return;
		}
		for (int i = 0; i < 4; i++) {
			move[start] = i;
			perm(start + 1);
		}
	}

	private static void checkCnt(int[] move) {
		isVisited = new boolean[4][4];
		int cnt = 0;
		int r = sharkr;
		int c = sharkc;
		for (int i = 0; i < 3; i++) {
			
			int nr = r + sr[move[i]];
			int nc = c + sc[move[i]];
			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4)
				return;
			if (!temp[nr][nc].isEmpty() && !isVisited[nr][nc]) {
				isVisited[nr][nc] = true;
				cnt += temp[nr][nc].size();
			}
			r = nr;
			c = nc;
		}
		if(maxCnt  < cnt) {
			maxCnt = cnt;
			list.add(new Pos(cnt, move[0], move[1], move[2]));
		}
	}

	private static boolean checkMoveFish(int nr, int nc) {
		if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || smell[nr][nc] < 0 || (nr == sharkr && nc == sharkc))
			return false;
		else
			return true;
	}

	private static void moveFish() {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (map[r][c].isEmpty())
					continue;
				for (int k = 0; k < map[r][c].size(); k++) {
					int d = (int) map[r][c].get(k); // 바로 이동
					// 장애물 O
					if (!checkMoveFish(r + dr[d], c + dc[d])) {
						int cnt = 8;
						while (cnt-- > 0) {
							d--;
							if (d < 0) {
								d = 7;
							}
							if (checkMoveFish(r + dr[d], c + dc[d])) {
								temp[r + dr[d]][c + dc[d]].add(d); // 자리 이동
								break;
							}
						}
						if ((int) map[r][c].get(k) == d) { // 움직일 수 없음.
							temp[r][c].add(d);
						}
					}
					// 장애물 X
					else {
						temp[r + dr[d]][c + dc[d]].add(d); // 자리 이동
					}
				}
			}
		}
	}

	private static void copyOfFish() {
		temp = new ArrayList[4][4];
		copy = new ArrayList[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				temp[i][j] = new ArrayList<>();
				copy[i][j] = new ArrayList<>();
			}
		}
		copy = map;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		map = new ArrayList[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = new ArrayList<Integer>();
			}
		}
		smell = new int[4][4];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[r - 1][c - 1].add(d - 1);
		}
		st = new StringTokenizer(br.readLine(), " ");
		sharkr = Integer.parseInt(st.nextToken()) - 1;
		sharkc = Integer.parseInt(st.nextToken()) - 1;
	}
}