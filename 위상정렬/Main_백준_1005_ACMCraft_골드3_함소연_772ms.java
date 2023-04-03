package 위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_백준_1005_ACMCraft_골드3_함소연_772ms {
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] list;
	static int[] delay;
	static int[] indegree;
	static int N, W;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 건물 개수
			int K = Integer.parseInt(st.nextToken()); // 규칙 수

			delay = new int[N + 1]; // 건설시간
			indegree = new int[N + 1]; // 인접 차수
			list = new ArrayList[N + 1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				delay[i] = Integer.parseInt(st.nextToken());
				list[i] = new ArrayList<Integer>();
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				indegree[b]++;
			}
			
			W = Integer.parseInt(br.readLine());
			
			TopologicalSort();
		}
		System.out.println(sb.toString());
	} // end of main

	public static void TopologicalSort() {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		int[] res = new int[N+1];
		for (int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
				res[i] = delay[i];
			}
		}
		while (queue.size() != 0) {
			int prev = queue.poll();
			if(prev == W) { // 최종 건물에 도달하면 return
				sb.append(res[prev]).append("\n");
				return;
			}
			for (int now : list[prev]) {
				res[now] = Math.max(res[now], res[prev] + delay[now]);
				indegree[now]--;
				if (indegree[now] == 0) {
					queue.offer(now);
				}
			}
		}
	}
} // end of class

