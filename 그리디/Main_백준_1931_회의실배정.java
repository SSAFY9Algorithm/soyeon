package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_백준_1931_회의실배정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 회의 수
		int[][] arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
			arr[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
		}
		
		// 회의 종료 시간이 빠른 것부터 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) return o1[0] - o2[0]; // 종료시간이 같으면 시작시간 정렬
				return o1[1] - o2[1]; // 오름차순
			}
		});
		int ans = 0;
		int time = 0;
		for (int i = 0; i < N; i++) {
			if(time <= arr[i][0]) { // 끝나는 시간과 같거나 큰 다음 시작 시간이 있다면,
				time = arr[i][1];
				ans++;
			}
		}
		System.out.println(ans);
	}
}
