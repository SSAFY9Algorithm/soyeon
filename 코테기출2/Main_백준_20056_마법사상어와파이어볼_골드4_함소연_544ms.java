package 코테기출2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_20056_마법사상어와파이어볼_골드4_함소연_544ms{
	static class Pos {
		int m;
		int s;
		int d;

		public Pos(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	private static int ans = 0;
	private static int K;
	private static int N;
	private static ArrayList<Pos>[][] list;
	private static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		input();
		go();
	} // end of main

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		list = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				list[i][j] = new ArrayList<Pos>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken()); // 질량
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 방향
			list[r][c].add(new Pos(m, s, d));
		}
	}

	private static void go() {
		while (K-- > 0) {
			move();
			sum();
		}
		getAmount();
	}

	private static void getAmount() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (list[i][j].size() == 0)
					continue;
				for (int k = 0; k < list[i][j].size(); k++) {
					ans += list[i][j].get(k).m;
				}
			}
		}
		System.out.println(ans);
	}

	private static void sum() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = list[i][j].size();
				if (size == 0 || size == 1)
					continue; // 0개 , 1개
				// 2개 이상
				int mSum = 0;
				int sSum = 0;
				int cnt = size;
				int dAll = 0;
				boolean flag = false; // f : 0, 2, 4, 6 t : 1, 3, 5, 7
				for (int k = 0; k < size; k++) {
					Pos p = list[i][j].get(k);
					mSum += p.m; // 질량
					sSum += p.s; // 속력
					if (k == 0) { // 방향
						dAll = p.d % 2;
					} else {
						if (dAll != p.d % 2) {
							flag = true;
						}
					}
				}
				list[i][j].clear();
				int m = mSum / 5;
				if (m == 0)
					continue;
				int s = sSum / cnt;
				if (!flag) { // f : 0, 2, 4, 6 t : 1, 3, 5, 7
					for (int k = 0; k < 8; k += 2) {
						list[i][j].add(new Pos(m, s, k));
					}
				} else {
					for (int k = 1; k < 8; k += 2) {
						list[i][j].add(new Pos(m, s, k));
					}
				}
			}
		}
	}

	private static void move() {
		ArrayList<int[]> temp = new ArrayList<>();
		// 이동
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = list[i][j].size();
				if (size == 0)
					continue;
				for (int k = size - 1; k >= 0; k--) {
					Pos p = list[i][j].get(k);
					int nr = i + dr[p.d] * p.s;
					int nc = j + dc[p.d] * p.s;
					if (nr < 0) {
						nr = nr % N;
						if(nr != 0) {
							nr += N;
						}
					} else if (nr >= N) {
						nr = nr % N;
					}
					if (nc < 0) {
						nc = nc % N;
						if(nc != 0) {
							nc += N;
						}
					} else if (nc >= N) {
						nc = nc % N;
					}
					int[] a = { nr, nc, p.m, p.s, p.d };
					temp.add(a);
					list[i][j].remove(k); // 초기화
				}
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			list[temp.get(i)[0]][temp.get(i)[1]].add(new Pos(temp.get(i)[2], temp.get(i)[3], temp.get(i)[4]));
		}
	}
} // end of class
