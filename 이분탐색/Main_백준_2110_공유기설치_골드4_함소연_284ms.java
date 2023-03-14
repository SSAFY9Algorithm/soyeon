package 이분탐색;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_2110_공유기설치_골드4_함소연_284ms{
	private static int[] arr;
	private static int N;
	private static int C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		BinarySearch();
	}

	private static void BinarySearch() {
		int start = 0;
		int end = arr[N-1];
		
		int cnt = 0;
		int ans = 0;
		while(start <= end) {
			int mid = (start + end) / 2;
			
			// mid 거리 일 때 공유기 개수 구하기
			cnt = 1; // 처음 집부터 
			int prev = arr[0];
			for (int i = 1; i < N; i++) {
				int now = arr[i];
				if(now - prev >= mid) {
					cnt++;
					prev = now;
				}
			}
			
			// 공유기 개수보다 작으면
			if(cnt < C) {
				end = mid - 1;
			}else { // 같거나 크면
				ans = mid;
				start = mid + 1;
			}
		}
		System.out.println(ans);
	}
}