package A형;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 궁수 3명
 * 하나의 칸에는 1명의 궁수
 * 턴마다 궁수는 적 하나 공격
 * 모든 궁수는 동시 공격
 * 거리가 D이하인 적 중에서 가장 가까운 적, 여러 명이면 가장 왼쪽에 있는 적 공격
 * 같은 적이 여러 궁수에게 공격당할 수 있음
 * 공격이 끝나면 적은 아래로 한 칸 이동
 * 성에 도달하면 제외
 * 모든 적이 제외되면 게임 끝
 * 
 */
public class Main_백준_17135_캐슬디펜스_골드3_함소연_224ms{
	static class Pos{
		int x;
		int y;
		int dist;
		public Pos(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	private static int[][] map;
	private static int[][] archers;
	private static boolean[][] isCatched; 
	private static int[] dx = { 0, -1, 0};
	private static int[] dy = { -1, 0, 1}; // 좌 상 우
	private static int D;
	private static int M;
	private static int N;
	private static int MAX = Integer.MIN_VALUE;
	private static int die;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N+1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		archers = new int[3][2];
		
		// 궁수 좌표 설정
		setPos(0, 0, 3);
		System.out.println(MAX);
	}

	private static void setPos(int idx, int start, int R) {
		if (idx == R) {
			Run();
			return;
		}

		for (int i = start; i < M; i++) {
			archers[idx][0] = N;
			archers[idx][1] = i;
			setPos(idx + 1, i + 1, R);
		}
	}
	

	private static void Run() {
		die = 0;
		
		// 원본 배열 복사
		int[][] round = new int[N+1][M];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < M; j++) {
				round[i][j] = map[i][j];
			}
		}
		
		while (true) {
			if (checkClear()) { // 적이 map에 없다면
				MAX = Math.max(MAX, die);
				map = round; // map 복원
				return; // 적이 모두 나가면 종료
			}
			
			isCatched = new boolean[N+1][M];
			for (int i = 0; i < 3; i++) {
				int x = archers[i][0];
				int y = archers[i][1];
				shoot(x, y);
			}
			// 잡은 궁수 표시
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(isCatched[i][j]) {
						map[i][j] = 0;
					}
				}
			}
			map = nextTurn(); // 적이 아래로 이동
		}
	}
	private static void shoot(int x, int y) {
		Deque<Pos> queue = new ArrayDeque<Pos>();
		int nx, ny;
		queue.offer(new Pos(x, y, 0));
		while(queue.size() > 0) {
			Pos p = queue.poll();
			if(p.dist == D) { // 최대 거리면 종료
				return;
			}
			for (int d = 0; d < 3; d++) {
				nx = p.x + dx[d];
				ny = p.y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (map[nx][ny] == 1) { // 적 발견 시 종료
					if(!isCatched[nx][ny]) { // 공격하지 않은 적이라면 die, 이미 공격했다면 pass
						isCatched[nx][ny] = true;
						die++;
					}
					return;
				}else { // 다시 탐색
					queue.offer(new Pos(nx, ny, p.dist+1));
				}
			}
		}
	}

	private static boolean checkClear() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0)
					return false;
			}
		}
		return true;
	}

	public static int[][] nextTurn() {
		int[][] temp = new int[N+1][M];
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M; j++) {
				temp[i + 1][j] = map[i][j];
			}
		}
		return temp;
	}
}
