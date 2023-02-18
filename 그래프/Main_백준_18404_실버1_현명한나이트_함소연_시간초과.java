package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_백준_18404_실버1_현명한나이트_함소연_시간초과 {
	static int[][] arr;
	static boolean[][] isVisited;
	static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {-1, +1, -2, 2, -2, 2, -1, 1};
	static int N, M;
    static StringBuilder sb = new StringBuilder();
	static class ChessBoard{
		int x;
		int y;
		int count;
		public ChessBoard(int x, int y, int count) {
			super();
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
		arr = new int[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		arr[x][y] = 1;
		for (int i = 0; i < M; i++) {
			isVisited = new boolean[N+1][N+1]; 
			st = new StringTokenizer(br.readLine());
			int kx = Integer.parseInt(st.nextToken());
			int ky = Integer.parseInt(st.nextToken());
			arr[kx][ky] = -1;
			BFS(x, y, 1);
		}
		System.out.println(sb.toString());
	} // end of main
	public static void BFS(int x, int y, int cnt) {
		Deque<ChessBoard> queue = new ArrayDeque<ChessBoard>();
		queue.add(new ChessBoard(x, y, cnt));
		isVisited[x][y] = true;
		while(!queue.isEmpty()) {
			ChessBoard board = queue.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = board.x + dx[i];
				int ny = board.y + dy[i];
				if(nx > 0 && nx < N+1 && ny > 0 && ny < N+1 && !isVisited[nx][ny]) {
					if(arr[nx][ny] == -1) {
						arr[nx][ny] = 0;
						sb.append(board.count).append(" ");
						return;
					}
					isVisited[nx][ny] = true;
					queue.offer(new ChessBoard(nx, ny, board.count+1));
				}
			}
		}
	} // end of BFS
} // end of class
