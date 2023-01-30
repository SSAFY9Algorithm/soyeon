package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * 문자열 : 
 *
 */
public class BOJ_1120 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String A = st.nextToken();
		String B = st.nextToken();
		
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= B.length()-A.length(); i++) {
			int not = 0;
			for (int j = 0; j < A.length(); j++) {
				if(A.charAt(j) != B.charAt(i+j)) {
					not++;
				}
				if(not > min) break;
			}
			if(not < min) min = not;
		}
		System.out.println(min);
	}
}