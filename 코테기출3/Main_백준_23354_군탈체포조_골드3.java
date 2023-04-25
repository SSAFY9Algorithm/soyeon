package 코테기출3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_백준_23354_군탈체포조_골드3 {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, sr, sc, min = Integer.MAX_VALUE, distance, INF=Integer.MAX_VALUE;
	static int[][] map;
	static int[] numbers;
	static boolean[] isSelected;
	static boolean[][] isVisited;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int[][] dp;
	static List<Pos> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					sr = i;
					sc = j;
				}else if(map[i][j] == 0) {
					list.add(new Pos(i, j));
				}
			}
		}
		if(list.isEmpty()) {
			System.out.println(0);
			System.exit(0);
		}
		if(list.size() == 1) {
			distance = INF;
			isVisited = new boolean[N][N];
			go(sr, sc, list.get(0).r, list.get(0).c, 0);
			System.out.println(distance*2);
			System.exit(0);
		}
		dp = new int[list.size()+1][list.size()+1];
		
		for (int i = 0; i < list.size()+1; i++) {
			for (int j = 0; j < list.size()+1; j++) {
				if(i==j) continue;
				dp[i][j] = INF;
			}
		}
		
		// 조합으로 거리 저장
		numbers = new int[2];
		isSelected = new boolean[list.size()];
		comb(0, 0);
		
		for (int i = 0; i < list.size(); i++) {
			distance = INF;
			isVisited = new boolean[N][N];
			go(sr, sc, list.get(i).r, list.get(i).c, 0);
			dp[0][i+1] = distance;
			dp[i+1][0] = distance;
		}
		
		
		numbers = new int[list.size()];
		isSelected = new boolean[list.size()+1];
		perm(0, 0, 0);
		System.out.println(min);
	}
	private static void comb(int idx, int start) {
		if(idx == 2) {
			for (int i = 0, j = 0; i < list.size(); i++) {
				if(isSelected[i]) {
					numbers[j++] = i;
				}
			}
			distance = INF;
			isVisited = new boolean[N][N];
			go(list.get(numbers[0]).r, list.get(numbers[0]).c, list.get(numbers[1]).r, list.get(numbers[1]).c, 0);
			dp[numbers[0]+1][numbers[1]+1] = distance;
			dp[numbers[1]+1][numbers[0]+1] = distance;
			return;
		}
		for (int i = start; i < list.size(); i++) {
			isSelected[i] = true;
			comb(idx+1, i+1);
			isSelected[i] = false;
		}
	}
	private static void perm(int idx, int prev, int sum) {
		if(idx == list.size()) {
			sum += dp[0][numbers[idx-1]];
			min = Math.min(sum, min);
			return;
		}
		
		if(sum > min) return;
		for (int i = 1; i < list.size()+1; i++) {
			if(isSelected[i-1]) continue;
			isSelected[i-1] = true;
			numbers[idx] = i;
			perm(idx+1, numbers[idx], sum+dp[prev][numbers[idx]]);
			isSelected[i-1] = false;
		}
	}
	private static void go(int r, int c, int tr, int tc, int sum) {
		if(tr == r && tc == c) {
			distance = sum;
			return;
		}
		if(sum > distance) {
			return;
		}
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc]) continue;
			isVisited[nr][nc] = true;
			int num = map[nr][nc];
			if(num == -1) num = 0;
			go(nr, nc, tr, tc, sum+num);
			isVisited[nr][nc] = false;
		}
		
	}
}
