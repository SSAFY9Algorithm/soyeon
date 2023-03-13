package 순열_조합_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/**
 * 
 * 차집합 : HashSet으로 중복제거
 * 			A + B - 2 * A∩B
 */
public class BOJ_1269 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		HashSet<Integer> set = new HashSet<Integer>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++) {
			int a = Integer.parseInt(st.nextToken());
			set.add(a);
		}
		
		int hap = 0; // 합집합의 개수
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B; i++) {
			int b = Integer.parseInt(st.nextToken());
			
			if(set.contains(b)) {
				hap++; // A∩B
			}
			else {
				set.add(b); // A + B
			}
		}
//		System.out.println(A + B - 2 * hap);
		System.out.println(set.size() -  hap);
		
	}
}

