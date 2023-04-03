package 서로소집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1717_집합의표현_골드4_함소연_924ms {
	static int[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list = new int[N + 1];
		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			switch (op) {
			case 0: // 합집합
				int x = find(a); // 루트 노드 찾디
				int y = find(b);
				if (x < y) // 더 작은 루트노드를 부모로 설정
					list[y] = x;
				else
					list[x] = y;
				break;
			case 1: // 확인
				if (find(a) == find(b)) // 루트 노드가 같으면 YES
					sb.append("YES");
				else
					sb.append("NO"); // 다르면 NO
				sb.append("\n");
				break;
			}
		}
		System.out.println(sb.toString());
	} // end of main

	public static int find(int i) {
		if (i == list[i])
			return i;
		return list[i] = find(list[i]); // 경로 압축
	} // end of find
} // end of class
