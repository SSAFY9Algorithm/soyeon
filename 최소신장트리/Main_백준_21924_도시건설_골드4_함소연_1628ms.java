package 최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 최소 신장 트리 : 프림 O(ElogV)
 * 		- 시작점에서 갈 수 있는 정점 중 가장 가중치가 작은 정점 연결
 * 		- 시작점과 연결된 정점들을 집합이라 할 때, 
 * 		- 이 집한에 속하지 않은 정점들에 대해 정점연결 반복
 * 		- 새로운 정점 연결할 때마다 아직 연결되지 않은 정점들에 대한 간선 추가
 * 		- Priority Queue를 이용한 최소 힙으로 구현
 * 
 * 크루스칼 : 정점이 많을 때 유리
 * 프림 	: 간선이 많을 때 유리
 */
public class Main_백준_21924_도시건설_골드4_함소연_1628ms {
	static long total = 0;
	static long ans = 0;
	static boolean[] isVisited;
	static ArrayList<Node>[] list;
	static PriorityQueue<Node> pq;
	private static int N;
	static class Node implements Comparable<Node>{
		int v;
		int w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		// 가중치 정렬
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 건물의 개수
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수
		
		list = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			int w = Integer.parseInt(st.nextToken()); // 가중치
			
			list[a].add(new Node(b, w));
			list[b].add(new Node(a, w));
			total += w;
		}
		
		Prim(1);
	} // end of main
	
	public static void Prim(int start) {
		isVisited = new boolean[N + 1];
		int cnt =0;
		pq = new PriorityQueue<>(); // 우선순위 큐
		pq.offer(new Node(start, 0));
		
		while(pq.size() != 0) {
			Node node = pq.poll();
			
			if(!isVisited[node.v]) {
				isVisited[node.v] = true;
				ans += node.w;
				cnt++;
				for (Node n : list[node.v]) {
					if(!isVisited[n.v]) {
						pq.offer(n);
					}
				}
			}
		} // end of while
		// 건물의 개수 == cnt : 모든 건물이 연결되어 있음
		if(cnt == N) System.out.println(total - ans);
		else System.out.println(-1);
	} // end of Prim
} // end of class
