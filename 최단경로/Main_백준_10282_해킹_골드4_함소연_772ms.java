package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 다익스트라 + 우선순위 큐 */
public class Main_백준_10282_해킹_골드4_함소연_772ms {
	static StringBuilder sb = new StringBuilder();
	static boolean isVisited[];
	static int cnt = 0, totalTime = 0;
	static int[] time;
	static ArrayList<Node>[] list;
	static class Node implements Comparable<Node>{
		int n;
		int t;
		public Node(int n, int t) {
			this.n = n;
			this.t = t;
		}
		public int compareTo(Node o) {
			return this.t - o.t;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
			int D = Integer.parseInt(st.nextToken()); // 의존성 개수
			int C = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호 
			
			time = new int[N+1]; // 소요 시간 저장 배열
			list = new ArrayList[N+1];
			for (int i = 0; i < N+1; i++) {
				time[i] = Integer.MAX_VALUE;
				list[i] = new ArrayList<Node>();
			}
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken()); // a컴퓨터
				int b = Integer.parseInt(st.nextToken()); // b컴퓨터 // a가 b를 의존 
				int t = Integer.parseInt(st.nextToken()); // b가 감염되면 s초 후 a 감염
				list[b].add(new Node(a, t)); 
			}
			
			cnt = 0; // 감염된 컴퓨터 수
			totalTime = 0; // 시간
			isVisited = new boolean[N+1];
			Dijkstra(C);
			
			for (int i = 0; i < N+1; i++) {
				if(time[i] != Integer.MAX_VALUE) { 
					totalTime = Math.max(totalTime, time[i]); // 최대 소요 시간 => total
				}
			}
			
			sb.append(cnt).append(" ").append(totalTime).append("\n");
		} // end of testcase
		System.out.println(sb.toString());
	} // end of main
	
	private static void Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		pq.offer(new Node(start, 0));
		time[start] = 0;
		
		while(pq.size() != 0) {
			Node node = pq.poll();
			if(isVisited[node.n]) continue;
			isVisited[node.n] = true;
			cnt++;
			for (Node next : list[node.n]) {
				// 다른 노드를 거쳐가는 것이 더 시간이 적은지 확인
				// 저장된 소요 시간 > 현재 소요 시간 + 다음 소요 시간
				// 더 작으면 갱신
				if(time[next.n] > time[node.n] + next.t) {
					time[next.n] = time[node.n] + next.t;
					pq.add(new Node(next.n, time[next.n]));
				}
			}
		}
		
	}
	
} // end of class
