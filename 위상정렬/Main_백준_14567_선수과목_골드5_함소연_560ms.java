package 위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// 위상정렬 안 쓴 위상정렬 문제
public class Main_백준_14567_선수과목_골드5_함소연_560ms {
	static ArrayList<Integer>[] list;
	static int[] degree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 과목의 수
		int M = Integer.parseInt(st.nextToken()); // 선수 조건의 수

		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		degree = new int[N + 1]; // 진입 차수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			for (int b : list[i]) {
				degree[i] = Math.max(degree[i], degree[b]+1); // 최대 학기
			}
			sb.append(degree[i]+1).append(" ");
		}
		System.out.println(sb.toString());
	} // end of main
} // end of class
