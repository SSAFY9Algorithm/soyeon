package 코테기출2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17825_주사위윷놀이_골드2 {
	private static int ans = 0;
	private static int[] dice;
	private static int[] arr;
	private static int[][] map = {
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0},
			{10, 13, 16, 19},
			{20, 22, 24},
			{30, 28, 27, 26},
			{25, 30, 35, 40, 0}};
	static class Node{
		int score;
		int idx;
		int m;
		public Node(int score, int idx, int m) {
			this.score = score;
			this.idx = idx;
			this.m = m;
		}
	}
	
	public static void main(String[] args) throws IOException {
		input();
		go();
		System.out.println(ans);
	}
	

	private static void go() {
		gerPlayer(0);
	}

	private static void roll(int[] arr) {
		Node[] node = new Node[4];
		for (int i = 0; i < node.length; i++) {
			node[i] = new Node(0, 0, 0);
		}
		for(int t = 0; t < 10; t++) {
			int player = arr[t]; // 현재 플레이어 
			if(node[player].idx == 0 || node[player].idx == 4) {
				if(node[player].m + dice[t] >= map[node[player].idx].length) { // 범위 초과 시 재조정
					node[player].m = map[node[player].idx].length-1;
					continue;
				}else {
					node[player].m += dice[t];
				}
			}else {
				if(node[player].m + dice[t] >= map[node[player].idx].length) { // 범위 초과 시 재조정
					node[player].m = node[player].m + dice[t] - map[node[player].idx].length;
					node[player].idx = 4;
				}else {
					node[player].m += dice[t];
				}
			}
			int score = map[node[player].idx][node[player].m]; // 이동한 말판의 점수 
			if(node[player].idx == 0) {
				switch (score) {
				case 10:
					node[player].idx = 1;
					node[player].m = 0;
					break;
				case 20:
					node[player].idx = 2;
					node[player].m = 0;
					break;
				case 30:
					node[player].idx = 3;
					node[player].m = 0;
					break;
				default:
					break;
				}
			}
			// 말 이동을 마치는 칸에 다른 말이 있으면 고를 수 없다. 
			for (int i = 0; i < 4; i++) {
				if(i == player) continue;
				// 25 30 35 40 체크
				if(node[i].idx == node[player].idx && node[i].m == node[player].m) {
					return;
				}
				if(map[node[i].idx][node[i].m] == score && score == 40) {
					return;
				}
			}
			node[player].score += score;
			
		}
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += node[i].score;
		}
		ans = Math.max(ans, sum);
	}

	private static void gerPlayer(int start) {
		if(start == 10) {
			roll(arr);
			return;
		}
		for (int i = 0; i < 4; i++) {
			arr[start] = i;
			gerPlayer(start+1);
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		arr = new int[10];
	}
}