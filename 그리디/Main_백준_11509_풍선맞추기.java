package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_11509_풍선맞추기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] balloon = new int[N+1];
		int[] arrow = new int[1000001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			balloon[i] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0; 
		int height = 0;
		for (int i = 1; i <= N; i++) {
			height = balloon[i]; 
			// 맞춘 적이 없다면
			if(arrow[height] == 0) {
				arrow[height-1]++; // 다음에 와야할 풍선 높이에 check
				cnt++; // 화살++
			}else { // 맞춘 적이 있다면
				arrow[height]--; // 맞춰서 check 풀기
				arrow[height-1]++; // 다음에 와야할 풍선 높이에 check
			}
		}
		System.out.println(cnt);
	}
}
