package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_백준_1260_DFS와BFS_실버2_함소연_212ms {
	static int[][] arr;
	static int N, M, V;
	static boolean[] isVisited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		isVisited = new boolean[N+1];
		DFS(V);
		sb.append("\n");
		Arrays.fill(isVisited, false);
		BFS(V);
		
		System.out.println(sb.toString());
	}

	public static void DFS(int node) {
		if(node == arr.length) return;
		isVisited[node] = true;
		sb.append(node).append(" ");
		for (int i = 1; i < arr.length; i++) {
			if(!isVisited[i] && arr[node][i] == 1)
				DFS(i);
		}
	}

	public static void BFS(int node) {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(node);
		isVisited[node] = true;
		while (!queue.isEmpty()) {
			int n = queue.poll();
			sb.append(n).append(" ");
			for(int i = 1; i < arr.length; i++) {
				if (!isVisited[i] && arr[n][i] == 1) {
					queue.offer(i);
					isVisited[i] = true;
				}
			}
		}
	}
}
