package 최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 프림 알고리즘 */
public class Main_백준_16398_행성연결_골드4_함소연_804ms {
	static int[][] graph;
	static boolean[] isVisited;
	static int N;
	static long ans;
	
	static class Node implements Comparable<Node>{
		int v;
		int w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 행성의 수
		graph = new int[N][N];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Prim(0);
		System.out.println(ans);
	} // end of main
	
	private static void Prim(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		isVisited = new boolean[N];
		
		pq.offer(new Node(start, 0));
		
		while(pq.size() != 0) {
			Node node = pq.poll();
			
			if(!isVisited[node.v]) {
				isVisited[node.v] = true;
				ans += node.w;
				for (int i = 0; i < N; i++){
					if(i == node.v) continue;
					if(!isVisited[i]) {
						pq.offer(new Node(i, graph[node.v][i]));
					}
				}
			}
		}
	} // end of Prim
} // end of class
