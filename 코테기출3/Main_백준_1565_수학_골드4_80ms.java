package 코테기출3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1565_수학_골드4_80ms {
	static int D, M, cnt = 0;
	static int[] arrD, arrM;
	public static void main(String[] args) throws IOException {
		input();
		go();
	}

	private static void go() {
		long num1 = arrD[0];
		for (int i = 0; i < arrD.length; i++) {
			num1 = lcm(num1, arrD[i]);
		}
		
		long num2 = arrM[0];
		for (int i = 0; i < arrM.length; i++) {
			num2 = gcd(num2, arrM[i]);
		}
		// 약수 구하기
		int i = 0;
		for (i = 1; i*i < num2 ; i++) {
			if(num2%i == 0) {
				if(i%num1 == 0) cnt++;
				if((num2 / i) % num1 == 0) cnt++;
			}
		}
		if(i*i == num2 && (i%num1== 0)) cnt++;
		System.out.println(cnt);
	}
	
	

	private static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}

	private static long gcd(long a, long b) {
		long c;
		while(b != 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		D = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		arrD = new int[D];
		for (int i = 0; i < D; i++) {
			arrD[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		arrM = new int[M];
		for (int i = 0; i < M; i++) {
			arrM[i] = Integer.parseInt(st.nextToken());
		}
	}
}