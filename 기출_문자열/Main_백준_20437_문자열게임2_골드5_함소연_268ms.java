package 기출_문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_백준_20437_문자열게임2_골드5_함소연_268ms {
	static StringBuilder sb = new StringBuilder();
	static int T, K, ans1, ans2;
	static char[] W;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
	} // end of main
	
	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			W = br.readLine().toCharArray();
			K = Integer.parseInt(br.readLine());
			if(K == 1) {
				sb.append("1 1"+"\n");
				continue;
			}
			ans1 = 987654321;
			ans2 = 0;
			go();
			if(ans1 == 987654321 ) 
				sb.append(-1+"\n");
			else
				sb.append(ans1+" "+ans2+"\n");
		}
		
		System.out.println(sb.toString());
	} // end of input
	
	private static void go() {
		int l, r, cnt;
		char start;
		int[] ch = new int[26];
		// 최소 K개 이상인 알파벳 찾기
		for (int i = 0; i < W.length; i++) {
			ch[W[i] - 'a']++;
		}
		
		// 가장 짧은 문자열, 가장 긴 문자열 찾기
		for (int i = 0; i < 26; i++) {
			if(ch[i] >= K) {
				start = (char)( i + 'a'); // K개 이상인 알파벳
				find(start, ch[i]-1);
			}
		}
	} // end of go

	private static void find(char s, int idx) {
		int l, cnt, time, t = 0;
		for (int i = 0; i < W.length; i++) {
			if(t == idx) return; // 마지막 문자는 뒤를 찾을 수 없으므로 return
			if(W[i] == s) {
				cnt = 1; l = i; time = 2;
				for (int r = l + 1; r < W.length; r++)  {
					if(W[l] == W[r]) {
						cnt++;
						if(cnt == K) {
							ans1 = Math.min(ans1, time);
							ans2 = Math.max(ans2, time);
							break;
						}
					}
					time++;
				}
			}
		}
	}
} // end of class
