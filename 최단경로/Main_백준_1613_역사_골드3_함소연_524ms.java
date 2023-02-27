package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 플로이드 워셜 */
public class Main_백준_1613_역사_골드3_함소연_524ms {
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 사건 개수
		int K = Integer.parseInt(st.nextToken()); // 관계 개수
		
		graph = new int[N+1][N+1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = -1; // 정순
			graph[b][a] = 1; // 역순
		}
		
		// 플로이드 워셜
		// 경유지 k, 출발지 i, 도착지 j
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(i == k) continue; // 출발지와 경유지 겹침
				if(graph[i][k] == 0) continue; // 경유지 X
				for (int j = 1; j <= N; j++) {
					if(i == j || j == k) continue; // 출발지와 도착지 겹침, 경유지와 도착지 겹침
					if(graph[k][j] == 0) continue; // 경유지 X
					if(graph[i][j] == 0) { // 경유지 탐색
						if(graph[i][k] == -1 && graph[k][j] == -1) { // 정순이면 -1
							graph[i][j] = -1;
						}
						if(graph[i][k] == 1 && graph[k][j] == 1) { // 역순이면 1
							graph[i][j] = 1;
						}
					}
				}
			}
		}
		int S = Integer.parseInt(br.readLine()); // 알고 싶은 사건 쌍의 수
		
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(graph[a][b]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
