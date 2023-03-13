package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** BFS */
public class Main_백준_1389_케빈베이컨의6단계법칙 {
	static int[][] list;
	static boolean[] isVisited;
	static int N, M, ans;
	static StringBuilder sb = new StringBuilder();
	static int min = Integer.MAX_VALUE;
	static class Friend{
		int node; // 탐색 시작 노드
		int count; // 친구 수
		public Friend(int node, int count) {
			this.node = node;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new int[N+1][N+1];
		isVisited = new boolean[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a][b] = 1; // 양방향
			list[b][a] = 1;
		}
		
		//모든 노드에서 시작
		for (int i = 1; i <= N; i++) {
			Arrays.fill(isVisited, false);
			BFS(i);
		}
		System.out.println(ans);
	} // end of main

	public static void BFS(int node) {
		Queue<Friend> queue = new ArrayDeque<Friend>();
		queue.offer(new Friend(node, 0)); // 큐에 삽입
		isVisited[node] = true; // 방문
		int cnt = 0; //
		while(!queue.isEmpty()) {
			Friend friend = queue.poll(); // 맨 앞의 값 꺼내기
			cnt += friend.count; 
			
			for (int i = 1; i <= N; i++) {
				// list[node][ ] 전체 탐색 && 방문하지 않음
				if(list[friend.node][i] == 1&& !isVisited[i] ) {
					isVisited[i] = true;
					queue.offer(new Friend(i, friend.count + 1)); // 가중치 + 1
				}
			}
		} // end of while
		if(min > cnt) {
			min = cnt;
			ans = node;
		}
	} // end of BFS
} //  end of class
