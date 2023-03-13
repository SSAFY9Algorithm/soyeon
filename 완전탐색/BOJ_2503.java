package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2503 {
	static int[] arr;
	static int[] strike;
	static int[] ball;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int [N];
		strike = new int [N];
		ball = new int [N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			strike[i] = Integer.parseInt(st.nextToken());
			ball[i] = Integer.parseInt(st.nextToken());
		}
		int result = 0;
		for (int i = 123; i <= 987; i++) {
			boolean flag = false;
			
			
			int h = i/100;
			int t = i/10%10;
			int u = i%10;
			
			// 같은 수가 나올 경우 || 0이 나올 경우 continue
			if(h ==  t || h == u || t == u || t == 0 || u == 0) {
				continue;
			}
			
			for (int j = 0; j < N; j++) {
				int checkH = arr[j]/100;
				int checkT = arr[j]/10%10;
				int checkU = arr[j]%10;
				int cntS = 0;
				int cntB = 0;
				
				// strike
				if(h == checkH) cntS++;
				if(t == checkT) cntS++;
				if(u == checkU) cntS++;
				
				// ball
				if(h == checkT || h == checkU) cntB++;
				if(t == checkH || t == checkU) cntB++;
				if(u == checkH || u == checkT) cntB++;
				
				// ball과 strike 횟수가 같으면 true 아니면 false
				if(ball[j] != cntB || strike[j] != cntS) {
					flag = false;
					break;
				}else {
					flag = true;
				}
			}
			// N번 다 true일 경우 가능성있는 답 개수++
			if(flag) {
				
				result++;
			}
		}
		
		System.out.println(result);
	}
}
