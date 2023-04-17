package 기출_문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_백준_2015_수들의합4_골드4_함수연 {
	static int N, K;
	static long res;
	static int[] arr;
	static Map<Integer, Long> map = new HashMap<Integer, Long>();
	public static void main(String[] args) throws IOException {
		input();
		System.out.println(res);
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i] += arr[i-1];
			if(arr[i] == K) res++;
				res += map.getOrDefault(arr[i] - K, (long) 0);
				map.put(arr[i], map.getOrDefault(arr[i], (long) 0) + 1);
		}
	}
}

