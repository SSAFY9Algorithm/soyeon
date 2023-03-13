package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1068_트리 {
	
	static int[] tree;
	static ArrayList<Integer> child[];
	static int cnt = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int root = 0;
		tree = new int[N]; 
		child = new ArrayList[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			child[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if(tree[i] == -1) { // 부모노드가 없으면 루트노드
				root = i;
				continue;
			}
			child[tree[i]].add(i); // 자식 노드에 부모 노드 정보 
		}
		
		int del = Integer.parseInt(br.readLine());
		
		// 루트노드를 지우는 것이 아니라면,
		if(del != root) {
			delete(del);
			search(root);
		}else {
			System.out.println(cnt);
			return;
		}
		System.out.println(cnt);
	
		
	}
	public static void search(int node) {
		// 자식이 없으면 리프노드
		if(child[node].size() == 0) {
			cnt++;
			return;
		}
		// 자식까지 탐색
		for (int i = 0; i < child[node].size(); i++) {
			search(child[node].get(i));
		}
	}
	
	public static void delete(int del) {
		for (int i = 0; i < child[tree[del]].size(); i++) {
			if(child[tree[del]].get(i) == del) {
				child[tree[del]].remove(i);
				break;
			}
		}
	}

}
