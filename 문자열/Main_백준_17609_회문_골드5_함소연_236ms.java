package 문자열;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_백준_17609_회문_골드5_함소연_236ms {
	public static StringBuilder sb = new StringBuilder();
	public static int min = 3;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			min = 3;
			find(ch, 0, ch.length-1, 0);
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void find(char[] ch, int s, int e, int p) {
		int C = ch.length/2; // 글자 개수 / 2
		if(ch.length%2 == 1) // 홀수
			C += 1;
		if(s >= e) {
			min = Math.min(min, p);
			return;
		}
		// 회문
		if(ch[s] == ch[e]) {
			find(ch, s+1, e-1, p);
		}
		// 유사 회문
		else if(ch[s] != ch[e] && p == 0) {
			// 1. 왼쪽을 오른쪽으로
			find(ch, s+1, e, p+1);
			// 2. 오른쪽을 왼쪽으로
			find(ch, s, e-1, p+1);
		}else {
			min = Math.min(min, p + 1);
			return;
		}
	}
}
