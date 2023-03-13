package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/** BFS */
public class Main_백준_2589_보물섬 {
	static int N, M, maxCnt;
	static char[][] map;
	static boolean[][] isVisited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static class treasureMap{
		int x;
		int y;
		int count;
		public treasureMap(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		// 지도 탐색
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'L') { // 육지일 때만 탐색
					isVisited = new boolean[N][M];
					BFS(i, j);
					ans = Math.max(ans, maxCnt); // 최대값과 비교
				}
			}
		}
		System.out.println(ans);
	} // end of main
	
	static void BFS(int posX, int posY) {
		Queue<treasureMap> queue = new ArrayDeque<treasureMap>();
		queue.offer(new treasureMap(posX, posY, 0));
		isVisited[posX][posY] = true;
		maxCnt = 0;
		while(!queue.isEmpty()) {
			treasureMap t = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nowX = t.x + dx[i]; 
				int nowY = t.y + dy[i]; 
				// 범위 내의 이동 && 방문하지 않음 && 육지
				if(nowX >= 0 && nowX < N && nowY >= 0 && nowY < M && !isVisited[nowX][nowY] && map[nowX][nowY] == 'L') {
					isVisited[nowX][nowY] = true;
					queue.offer(new treasureMap(nowX, nowY, t.count + 1)); // 가중치 + 1
					maxCnt = Math.max(maxCnt, t.count + 1); // 해당 시작점에서 가장 먼 곳 찾기
				}
			}
		} // end of while
	} // end of BFS
} // end of class
