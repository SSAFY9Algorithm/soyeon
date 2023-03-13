package 연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_23309_철도공사 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> list = new LinkedList<Integer>();
		int N = Integer.parseInt(st.nextToken()); // 역의 개수
		int M = Integer.parseInt(st.nextToken()); // 공사 횟수

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(i, Integer.parseInt(st.nextToken()));
		}

		int idx;
		int jdx;
		int change;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String key = st.nextToken();
			switch (key) {
			case "BN":
				idx = Integer.parseInt(st.nextToken());
				jdx = Integer.parseInt(st.nextToken());
				change = list.indexOf(idx)+1;
			
				if(change >= list.size()) {change = 0;}
				sb.append(list.get(change)+"\n");
				list.add(change, jdx);
				break;
			case "BP":
				idx = Integer.parseInt(st.nextToken());
				jdx = Integer.parseInt(st.nextToken());
				change = list.indexOf(idx)-1;
				if(change < 0) {change = list.size() - 1;}
				sb.append(list.get(change)+"\n");
				list.add(change+1, jdx);
				break;
			case "CN":
				idx = Integer.parseInt(st.nextToken());
				change = list.indexOf(idx)+1;
				if(change >= list.size()) {change = 0;}
				sb.append(list.get(change)+"\n");
				list.remove(change);
				break;
			case "CP":
				idx = Integer.parseInt(st.nextToken());
				change = list.indexOf(idx)-1;
				if(change < 0) {change = list.size() - 1;}
				sb.append(list.get(change)+"\n");
				list.remove(change);
				break;
			default:
				break;
			}
		}
		System.out.println(sb); // 시간초과;;
	}
}
