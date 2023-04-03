package 최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
/**
 * 최소 신장 트리 : 크루스칼 O(ElogE)
 * 		- 정점 N개를 가지는 그래프에서 N-1개의 간선을 연결해야 한다.
 * 		- 모든 정점이 연결되어야 하나, 싸이클이 되면 안됨.
 * 		- union(), find()로 싸이클 여부를 찾음.
 */
public class Main_백준_21924_도시건설_골드4_함소연_시간초과 {
	static int[][] graph;
	static int[] parent;
	static long total = 0;
	static long ans = 0;
	static boolean[] isVisited;
	/** 싸이클 여부를 찾음 */
	public static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA < parentB)
			parent[parentB] = parentA;
		else
			parent[parentA] = parentB;
			
	}
	
	public static int find(int i) {
		if (parent[i] == i) // 부모가 맞음
			return i;
		return find(parent[i]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 건물의 개수
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수
		
		graph = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			int w = Integer.parseInt(st.nextToken()); // 가중치
			graph[i][0] = a;
			graph[i][1] = b;
			graph[i][2] = w;
			total += w;
		}
		
		
		// 가중치 정렬
		Arrays.sort(graph, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		// 연결 여부 확인 && 부모 노드 저장
		parent = new int[N+1];
		isVisited = new boolean[N+1];
		isConnected(1);
		for (int i = 1; i < N+1; i++) {
			parent[i] = i; // 부모 노드 저장
			if(!isVisited[i]) {
				System.out.println(-1);
				return;
			}
		}
		
		// 크루스칼
		for (int i = 0; i < graph.length; i++) {
			// 둘의 부모가 같지 않으면 (싸이클이 없음) 간선 선택
			if(find(graph[i][0]) != find(graph[i][1])) {
				ans += graph[i][2];
				union(graph[i][0], graph[i][1]);
			}
			// 둘의 부모가 같으면 (싸이클이 있음), 간선 비선택
		}
		System.out.println(total - ans);
	}
	
	public static void isConnected(int b) {
		isVisited[b] = true;
		for (int i = 0; i < graph.length; i++) {
			if(graph[i][0] == b && !isVisited[graph[i][1]]) {
				isConnected(graph[i][1]);
			}
		}
	}
}
