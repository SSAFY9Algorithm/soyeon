package 코테기출4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main_백준_22255_호석사우르스_골드2_180ms {
	static int N, M, sr, sc, er, ec, INF = Integer.MAX_VALUE, res = -1;
	static int[][] map;
	static int[][][] adj;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static class Node implements Comparable<Node> {
		int r, c, index, cost;
		public Node(int r, int c, int index, int cost) {
			this.r = r;
			this.c = c;
			this.index = index;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	public static void main(String[] args) throws IOException {
		input();
		go();
		if(res == -1) {
			System.out.println(-1);
		}else
			System.out.println(adj[er][ec][res%3]);
	}
	private static void go() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(sr, sc, 1, 0));
		int nr, nc = 0;
		while(pq.size() > 0) {
			Node n = pq.poll();
			if(n.r == er && n.c == ec) {
				res = n.index;
				return;
			}
			if(n.index%3 == 0) {
				for (int i = 0; i < 4; i++) {
					nr = n.r + dr[i];
					nc = n.c + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == -1) continue;
					if(map[nr][nc] + n.cost < adj[nr][nc][(n.index+1)%3]) {
						adj[nr][nc][(n.index+1)%3] = map[nr][nc] + n.cost;
						pq.offer(new Node(nr, nc, n.index+1, adj[nr][nc][(n.index+1)%3]));
					}
				}
			}else if(n.index%3 == 1) {
				for (int i = 0; i < 2; i++) {
					nr = n.r + dr[i];
					nc = n.c + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == -1) continue;
					if(map[nr][nc] + n.cost < adj[nr][nc][(n.index+1)%3]) {
						adj[nr][nc][(n.index+1)%3] = map[nr][nc] + n.cost;
						pq.offer(new Node(nr, nc, n.index+1, adj[nr][nc][(n.index+1)%3]));
					}
				}
			}else {
				for (int i = 2; i < 4; i++) {
					nr = n.r + dr[i];
					nc = n.c + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == -1) continue;
					if(map[nr][nc] + n.cost < adj[nr][nc][(n.index+1)%3]) {
						adj[nr][nc][(n.index+1)%3] = map[nr][nc] + n.cost;
						pq.offer(new Node(nr, nc, n.index+1, adj[nr][nc][(n.index+1)%3]));
					}
				}
			}
		}
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		adj = new int[N][M][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int j2 = 0; j2 < 3; j2++) {
					adj[i][j][j2] = INF;
				}
			}
		}
		adj[sr][sc][1] = 0;
	}
}