package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main_백준_2668_숫자고르기 {
	static int[] arr;
	static boolean[] isVisited;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] =  Integer.parseInt(br.readLine());
		}
		isVisited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(isVisited, false);
			DFS(i, i);
		}
		Collections.sort(list);
		sb.append(list.size()).append("\n");
		for (Integer i : list) {
			sb.append(i).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main
	
	public static void DFS(int node, int target) {
		// 사이클이 같을 때 추가하고 종료
		if(arr[node] == target) {
			list.add(node);
			return;
		}
		// 사이클 X
		if(isVisited[node]) {
			return;
		}
		// 사이클 재귀
		isVisited[node] = true;
		DFS(arr[node], target);
	} // end of DFS
} // end of class
