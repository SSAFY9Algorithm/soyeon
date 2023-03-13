package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1991_트리순회 {
	static int[][] tree;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new int[26][2];
		
		
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			int node = temp[0].charAt(0) - 'A';
			char left = temp[1].charAt(0);
			char right = temp[2].charAt(0);
			if(left == '.') { // 왼쪽에 자식 노드가 없음
				tree[node][0] = -1;
			}else {
				tree[node][0] = left -'A';
			}
			
			if(right == '.') { // 오른쪽에 자식 노드가 없음
				tree[node][1] = -1;
			}else {
				tree[node][1] = right - 'A';
			}
		}
		preOrder(0);
		sb.append("\n");
		inOrder(0);
		sb.append("\n");
		postOrder(0);
		sb.append("\n");
		System.out.println(sb);
	}
	/** 가운데 - 왼쪽 - 오른쪽 */
	public static void preOrder(int i) {
		if(i == -1) return; // 자식노드가 없으면 종료
		sb.append((char) (i + 'A')); // 현재
		preOrder(tree[i][0]); // 왼쪽
		preOrder(tree[i][1]); // 오른쪽
	}
	/** 왼쪽 - 가운데 - 오른쪽 */
	public static void inOrder(int i) {
		if(i == -1) return; // 자식노드가 없으면 종료
		inOrder(tree[i][0]); // 왼쪽
		sb.append((char) (i + 'A')); // 현재
		inOrder(tree[i][1]); // 오른쪽
	}
	/** 왼쪽 - 오른쪽 - 가운데 */
	public static void postOrder(int i) {
		if(i == -1) return; // 자식노드가 없으면 종료
		postOrder(tree[i][0]); // 왼쪽
		postOrder(tree[i][1]); // 오른쪽
		sb.append((char) (i + 'A')); // 현재
	}
	
}
