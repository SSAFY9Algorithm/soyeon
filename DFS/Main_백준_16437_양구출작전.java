package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 후위 순회 트리 */
public class Main_백준_16437_양구출작전 {
	static int N;
	static ArrayList<Integer> Island[]; // 트리 저장
	static long[] animalCnt; // 동물 수 저장
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Island = new ArrayList[N+1];
		animalCnt = new long[N+1];
		for (int i = 1; i <= N; i++) {
			Island[i] = new ArrayList<Integer>();
		}
		
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char animal = st.nextToken().charAt(0);
			int count = Integer.parseInt(st.nextToken());
			if(animal == 'W') { // 늑대 수는 음수
				count *= -1;
			}
			animalCnt[i] = count;
			int bridge = Integer.parseInt(st.nextToken());
			Island[bridge].add(i);
		}
		
		DFS(1, 0);
		System.out.println(animalCnt[1]);
	} // end of main
	
	public static void DFS(int now, int prev) {
		for (int next : Island[now]) {
			DFS(next, now);
		}
		if(prev == 0) { // 루트노드면 종료
			return;
		}
		if(animalCnt[now] > 0) // 양이면 부모 노드에 sum
			animalCnt[prev] += animalCnt[now];
	}
} // end of class
