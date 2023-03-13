package A형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_백준_17471_게리맨더링_골드4_함소연_96ms {
	static ArrayList<Integer>[] list;
	static int[] person;
	static boolean[] isSelected;
	private static int min = Integer.MAX_VALUE;
	private static int N;
	private static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 구역의 개수

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		person = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}

		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()); // 인접 구역 개수
			for (int j = 0; j < n; j++) {
				int area = Integer.parseInt(st.nextToken());
				list[i].add(area);
			}
		}

		isSelected = new boolean[N + 1];

		subSet(1);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	public static void subSet(int idx) {
		if (idx == N + 1) {
			int A = 0;
			int B = 0;
			for (int i = 1; i <= N; i++) {
				if (isSelected[i])
					A++;
				else
					B++;
			}
			if (A == N || B == N)
				return;
			if (check(A, true) && check(B, false)) { // true : A , false : B
				getDiff();
			}
			return;
		}
		isSelected[idx] = true;
		subSet(idx + 1); // 사용
		isSelected[idx] = false;
		subSet(idx + 1); // 미사용
	}

	private static boolean check(int size, boolean AorB) {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		int cnt = 1;
		isVisited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (isSelected[i] == AorB) {
				isVisited[i] = true;
				queue.offer(i);
				break;
			}
		}
		while (queue.size() > 0) {
			int n = queue.poll();
			for (int x : list[n]) {
				if (isSelected[x] == AorB && !isVisited[x]) {
					isVisited[x] = true;
					cnt++;
					queue.offer(x);
				}
			}
		}
		if (cnt == size)
			return true;
		return false;
	}

	private static void getDiff() {
		int A = 0;
		int B = 0;
		for (int i = 1; i <= N; i++) {
			if (isSelected[i])
				A += person[i];
			else
				B += person[i];
		}
		min = Math.min(min, Math.abs(A - B));
	}
}
