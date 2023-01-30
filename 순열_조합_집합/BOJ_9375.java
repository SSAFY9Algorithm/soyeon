package 순열_조합_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * 
 * 패션왕 신해빈 : HashMap을 이용한 조합
 * 		모자가 4개 안경이 2개일 경우,
 * 		하나만 쓸 경우를 포함하여 [headgear] = {1, 2, 3, 4, NULL}, [eyewear] = {1, 2, NULL}
 * 		둘 다 NULL인 경우, 아무것도 안 입을 경우의 수 1개를 빼준다.
 * 		5C1 * 3C1 -1 = 14
 *
 */

public class BOJ_9375 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken(); // 버림
				String key = st.nextToken(); // 키
				// 처음 들어온 key라면 0으로 reset
				if(map.get(key) == null) {
					map.put(key, 0);
				}
				map.put(key, map.get(key)+1);
			}

			int result = 1;
			// value 개수에 안 입을 경우 NULL 추가 == v + 1
			for (int v : map.values()) {
				result *= v+1;
			}
			// 아무것도 안 입었을 경우 -1
			sb.append(result-1);
			sb.append("\n");
		} // end of TestCase
		System.out.print(sb);
	} // end of main
} // end of class
