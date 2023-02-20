package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_10971_외판원순회2_실버2_함소연_96ms {
	static int[][] arr;
	static int N, from, min = Integer.MAX_VALUE;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시의 수
		arr = new int[N][N]; // 비용 행렬
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			isVisited = new boolean[N];
			from = i;
			DFS(i, 1, 0);
		}

		System.out.println(min);
	}
	public static void DFS(int start, int cnt, int sum) { // start : 현재 도시, cnt : 이동 횟수, sum : 이동 비용 합
		if (cnt == N) { // 마지막 목적지까지 가면 다시 되돌아옴.
			if(arr[start][from] == 0) return; // 갈 수 없으면 return
			sum += arr[start][from]; 
			min = Math.min(sum, min);
			return;
		} else {
			isVisited[start] = true;
			for (int i = 0; i < N; i++) {
				if (!isVisited[i] && arr[start][i] != 0) {
					isVisited[i] = true;
					sum += arr[start][i];
					if(sum <= min) { // 최소 비용보다 크면 이동하지 X
						DFS(i, cnt + 1, sum);
					}
					sum -= arr[start][i];
					isVisited[i] = false;
				}
			}
		}
	}
}
