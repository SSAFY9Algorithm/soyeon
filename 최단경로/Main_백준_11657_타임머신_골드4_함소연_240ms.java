package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 벨만 포드 : 음수가중치 */
public class Main_백준_11657_타임머신_골드4_함소연_240ms {
	static long[] dist;
	static ArrayList<Node> list;
	private static int N;
	private static int M;
	static class Node{
		int a;
		int b;
		int cost;
		public Node(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 버스 노선의 개수
		
		dist = new long[N+1]; // 최단 거리 배열
		Arrays.fill(dist, Long.MAX_VALUE);
		
		list = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시작 도시
			int b = Integer.parseInt(st.nextToken()); // 도착 도시
			int c = Integer.parseInt(st.nextToken()); // 이동 시간
			
			list.add(new Node(a, b, c));
		}
		
		if(BellmanFord(1)) {
			System.out.println(-1);
			return;
		}
		
		for (int i = 2; i < dist.length; i++) {
			if(dist[i] == Long.MAX_VALUE) { // 가는 경로가 없음
				sb.append(-1).append("\n");
			}
			else sb.append(dist[i]).append("\n"); // 가는 경로 존재
		}
		System.out.println(sb.toString());
	} // end of main
	private static boolean BellmanFord(int start) {
		dist[start] = 0;
		for (int i = 0; i < N; i++) { // 정점의 개수만큼
			for (int j = 0; j < M; j++) { // 간선의 개수만큼
				Node node = list.get(j);
				// 최단 경로 갱신
				if(dist[node.a] != Long.MAX_VALUE && dist[node.b] > dist[node.a] + node.cost) {
					dist[node.b] = dist[node.a] + node.cost;
					if(i == N-1) return true;
				}
			}
		}
		return false;
	}
} // end of class
