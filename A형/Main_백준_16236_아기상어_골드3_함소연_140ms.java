package A형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_백준_16236_아기상어_골드3_함소연_140ms {
	static class Pos {
		int x;
		int y;
		int dist;

		public Pos(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	private static int minX;
	private static int minY;
	private static int[][] map;
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, -1, 0, 1 };
	private static int time = 0;
	private static int shark = 2;
	private static int N;
	private static int x, y;
	private static int minDist;
	private static int[][] dist;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		dist = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					x = i;
					y = j;
					map[i][j] = 0;
				}
			}
		}
		go();
		System.out.println(time);
	}

	public static void go() {
		while (true) {
			init(); // 초기화
//			if (!checkCatch())
//				return;
			bfs();
			// 먹을 수 있는 물고기를 찾은 경우
			if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
				time += dist[minX][minY];
				cnt++;
				// 먹은 수와 상어의 크기가 같으면 상어크기 증가 후 물고기 수 초기화
				if (cnt == shark) {
					shark++;
					cnt = 0;
				}
				map[minX][minY] = 0; // 먹은 물고기 위치를 0으로

				// 상어 위치 갱신
				x = minX;
				y = minY;
			} else // 먹을 수 있는 물고기가 없는 경우
				return;
		}
	}

	// 잡을 수 있는 물고기가 있는 지 확인
	public static boolean checkCatch() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && map[i][j] != 9 && map[i][j] < shark)
					return true;
			}
		}
		return false;
	}

	// 지나갈 수 있는 지 확인
	public static boolean checkMove(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			return false; // 범위 체크
		if (map[nx][ny] > shark)
			return false; // 크기 체크
		return true;
	}

	// 가장 가까운 물고기 찾기
	public static void bfs() {
		Deque<Pos> queue = new ArrayDeque<Pos>();
		queue.offer(new Pos(x, y, 0));
		dist[x][y] = 0;

		while (queue.size() > 0) {
			Pos p = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				// 범위 체크
				if (!checkMove(nx, ny))
					continue;
				// 이미 방문했거나, 상어 크기보다 큰 물고기 건너 뛰기
				if (dist[nx][ny] != -1 || map[nx][ny] > shark)
					continue;
				// nx, ny에 있는 물고기까지의 이동거리 갱신
				dist[nx][ny] = dist[p.x][p.y] + 1;
				// 먹을 수 있는 물고기일 경우
				if(map[nx][ny] != 0 && map[nx][ny] < shark) {
					// 현재 물고기까지의 이동시간이 더 짧은 경우
					if (minDist > dist[nx][ny]) {
						minX = nx;
						minY = ny;
						minDist = dist[nx][ny];
						// 현재 물고기까지의 이동시간이 같으면 가장 위쪽, 가장 왼쪽 찾기
					} else if (minDist == dist[nx][ny]) {
						// 가장 위쪽에 있다면 가장 왼쪽인지 확인
						if (minX == nx) {
							if (minY > ny) {
								minX = nx;
								minY = ny;
							}
						// 가장 위쪽인지 확인
						} else if (minX > nx) {
							minX = nx;
							minY = ny;
						}
					}
				}
				queue.offer(new Pos(nx, ny, dist[nx][ny]));
			}
		}
	}

	public static void init() {
		minDist = Integer.MAX_VALUE;
		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = -1;
			}
		}
	}

//	public static void print() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (i == x && j == y)
//					System.out.print("■" + " ");
//				else
//					System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
}
