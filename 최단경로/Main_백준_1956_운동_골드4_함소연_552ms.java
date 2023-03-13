package 최단경로;

/**
 * 최단경로 4가지
 * 		1. 하나의 정점에서 다른 하나의 정점까지의 최단경로
 * 		2. 하나의 정점에서 다른 모든 정점까지의 최단경로 
 * 		3. 하나의 목적지로 가는 모든 최단경로
 * 		4. 모든 최단경로
 * 
 * 최단경로 알고리즘 4가지
 * 		- BFS O(V + E) : 가중치 X, 노드간 최소 이동 횟수 (1, 2, 3, 4)
 * 		- 다익스트라 O((V+E)logV) : 음의 가중치 X (1, 2, 3)
 * 		- 벨만 포드 O(VE) : 음의 가중치 O (1, 2, 3)
 * 		- 플로이드 워셜 O(N^3) : 음의 가중치 O (4)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최단경로 : 플로이드 워셜 O(N^3) 
 * 		- 모든 점에서 모든 점으로의 최단 거리 
 * 		- 현재 가려는 정점의 거리가 다른 점을 거쳐서 가는 것보다 멀다면, 
 * 		- 거쳐서 가는 거리값 저장 
 * 		- 음의 가중치 사용 가능 
 * 		- Math.min((시작점 + 끝점), (시작점 + 중간점 + 끝점))
 */
public class Main_백준_1956_운동_골드4_함소연_552ms {
	static boolean[] isVisited;
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); // 마을의 개수
		int E = Integer.parseInt(st.nextToken()); // 도로의 개수
		
		graph = new int[V+1][V+1];
		
		for (int i = 1; i <= V; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
			graph[i][i] = 0;
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			int w = Integer.parseInt(st.nextToken()); // 가중치
			
			if(graph[a][b] > w) {
				graph[a][b] = w;
			}
		}
		
		// 플로이드 워셜
		// 시작점 : i, 중간점 : k, 끝점 : j
		// 모든 노드를 중간점으로 지정하여 모든 노드의 최단 거리를 구한다.
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				if(graph[i][k] == Integer.MAX_VALUE) continue;
				for (int j = 1; j <= V; j++) {
					if(graph[k][j] == Integer.MAX_VALUE) continue;
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}

		// 사이클 존재 확인
		int ans = Integer.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if(i == j) continue;
				// 두 도시끼리 왕복할 수 있는 도로가 있음 => 사이클 존재
				if(graph[i][j] != Integer.MAX_VALUE && graph[j][i] != Integer.MAX_VALUE) {
					ans = Math.min(ans, graph[i][j] + graph[j][i]);
				}
			}
		}
		
		ans = ans == Integer.MAX_VALUE ? - 1: ans;
		System.out.println(ans);
	} // end of main
} // end of class
