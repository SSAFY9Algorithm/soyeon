package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_백준_18404_실버1_현명한나이트_함소연_260ms {
	static int[][] arr;
	static boolean[][] isVisited;
	static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {-1, +1, -2, 2, -2, 2, -1, 1};
	static int N, M;
    static StringBuilder sb = new StringBuilder();
	static class ChessBoard{
		int x;
		int y;
		public ChessBoard(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		isVisited = new boolean[N+1][N+1];
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		BFS(x, y);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int kx = Integer.parseInt(st.nextToken());
			int ky = Integer.parseInt(st.nextToken());
			sb.append(arr[kx][ky]).append(" ");
		}
		
		System.out.println(sb.toString());
	} // end of main
	
	public static void BFS(int x, int y) {
		Deque<ChessBoard> queue = new ArrayDeque<ChessBoard>();
		queue.offer(new ChessBoard(x, y));
		isVisited[x][y] = true;
		while(!queue.isEmpty()) {
			ChessBoard board = queue.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = board.x + dx[i];
				int ny = board.y + dy[i];
				if(nx > 0 && nx < N+1 && ny > 0 && ny < N+1 && !isVisited[nx][ny]) {
					arr[nx][ny] = arr[board.x][board.y] + 1;
					queue.offer(new ChessBoard(nx, ny));
					isVisited[nx][ny] = true;
				}
			}
		}
	} // end of BFS
} // end of class
