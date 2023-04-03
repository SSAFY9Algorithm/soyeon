package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1062_가르침_골드4_함소연_280ms {
	static boolean[] isVisited;
	static char[][] word;
	static int N, K, max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		isVisited = new boolean[26]; // 'a' = 97
		word = new char[N][15];
		isVisited[0] = true; isVisited['n'-'a'] = true; isVisited['t'-'a'] = true; isVisited['i'-'a'] = true; isVisited['c'-'a'] = true;
		
		if(K < 5) {
			System.out.println(0);
			return;
		}
		for (int k = 0; k < N; k++) {
			char[] ch = br.readLine().toCharArray();
			word[k] = ch;
		}
		find(0, 0);
		System.out.println(max);
	}
	public static void find(int idx, int cnt) {
		if(cnt == K - 5) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = true;
				for (int j = 0; j < word[i].length; j++) {
					int cur = word[i][j] -'a';
					if(!isVisited[cur]) {
						flag = false;
						break;
					}
				}
				if(flag) {
					temp++;
				}
			}
			max = Math.max(temp, max);
			return;
		}
		for (int i = idx; i < 26; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				find(i, cnt+1);
				isVisited[i] =false;
			}
		}
	}
	
}
