package 코테기출2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_20057_마법사상어와토네이도_골드3_함소연_452ms {
	private static int[][] map;
	private static int ans = 0;
	private static int N;
	private static int time;
	private static int[] dr = {0, 1, 0, -1}; // 좌 하 우 상
	private static int[] dc = {-1, 0, 1, 0};
	private static int d = 1;
	private static int cnt = 0;
	private static int turn = 0;
	private static int r;
	private static int c;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		go();
		System.out.println(ans);
	} // end of main

	private static void go() {
		time = N*N;
		r = N/2;
		c = N/2;
		while(time-- >0) {
			move();
		}
	}


	private static void move() {
//		System.out.println(r+" "+c);
		int nr = r + dr[turn];
		int nc = c + dc[turn];
		if(nr == 0 && nc == -1) return;
//		System.out.println(nr+" "+nc);

		if(map[nr][nc] != 0) {
			tornado(nr, nc);
		}
		System.out.println("turn: "+turn +" d:"+d+" cnt:"+cnt);
		print(nr, nc);
		cnt++;
		if( d == cnt ) {
			cnt = 0;
			turn++;
			if(turn == 2) {
				d++;
			}
			if(turn == 4) {
				d++;
				turn = 0;
			}
		}
		map[r][c] = 0;
		r = nr; c = nc;
	}

	private static void print(int r, int c) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == r && j == c) {
					System.out.print("■ ");
				}else {
					System.out.print(map[i][j]+" ");
				}
			}
			System.out.println();
		}
	}

	private static void tornado(int nr, int nc) {
		int mr = 0 ; int mc = 0;
		int amount = 0;
		switch (turn) {
		case 0:
			// 1%
			amount = (int) (map[nr][nc] * 0.01);
			mr = nr + 1;
			mc = nc + 1;
			getAmount(amount, mr, mc);
			mr = nr - 1;
			mc = nc + 1;
			getAmount(amount, mr, mc);
			// 2%
			amount = (int) (map[nr][nc] * 0.02);
			mr = nr + 2;
			mc = nc;
			getAmount(amount, mr, mc);
			mr = nr - 2;
			mc = nc;
			getAmount(amount, mr, mc);
			// 5%
			amount = (int) (map[nr][nc] * 0.05);
			mr = nr;
			mc = nc - 2;
			getAmount(amount, mr, mc);
			// 7%
			amount = (int) (map[nr][nc] * 0.07);
			mr = nr + 1;
			mc = nc;
			getAmount(amount, mr, mc);
			mr = nr - 1;
			mc = nc;
			getAmount(amount, mr, mc);
			// 10%
			amount = (int) (map[nr][nc] * 0.1);
			mr = nr - 1;
			mc = nc - 1;
			getAmount(amount, mr, mc);
			mr = nr + 1;
			mc = nc - 1;
			getAmount(amount, mr, mc);
			// a
			amount = (int) (map[nr][nc] * 0.55);
			mr = nr;
			mc = nc - 1;
			getAmount(amount, mr, mc);
			break;

		default:
			break;
		}
		
		mr = nr + dr[turn]; 
		mc = nc + dc[turn];
		
		
	}


	private static void getAmount(int amount, int mr, int mc) {
		if(mr > 0 || mr <= N || mc > 0 || mc <= N ) {
			ans += amount;
		}else {
			map[mr][mc] += amount;
		}
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

} // end of class
