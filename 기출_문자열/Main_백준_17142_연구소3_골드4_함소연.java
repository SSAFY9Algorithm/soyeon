package 기출_문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_백준_17142_연구소3_골드4_함소연 {
	static class Virus{
		int r, c, status;
		
		public Virus(int r, int c, int status) {
			this.r = r;
			this.c = c;
			this.status = status;
		}

		public Virus(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	static int N, M, min;
	static int[][] map;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	static List<Virus> virus;
	static boolean[][] isVisited;
	static boolean isLinked;
	static int empty;
	public static void main(String[] args) throws IOException {
		input();
		go();
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	private static void go() {
		changeMap();
		if(virus.size() == 0) {
			System.out.println(0);
			System.exit(0);
		}
		if(virus.size() < M)
			M = virus.size();
		comb(0, 0);
	}
	
	private static void comb(int idx, int start) {
		if (idx == M) {
			spread(empty);
			return;
		}
		
		for (int i = start; i < virus.size(); i++) {
			Virus v = virus.get(i);
			virus.get(i).status = 0;
			comb(idx+1, i+1);
			virus.get(i).status = -1;
		}
	}

	private static void changeMap() {
		Deque<Virus> queue = new ArrayDeque<Virus>();
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(map[i], N);
		}
		virus.forEach( vir -> {
			if(map[vir.r][vir.c] == 2) {
				isVisited = new boolean[N][N];
				queue.offer(new Virus(vir.r, vir.c));
				map[vir.r][vir.c] = 1;
				isVisited[vir.r][vir.c] = true;
				isLinked = true;
				EX:while(queue.size() > 0) {
					Virus v = queue.poll();
					int nr, nc;
					for (int i = 0; i < 4; i++) {
						nr = v.r + dr[i];
						nc = v.c + dc[i];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc]) continue;
						if(map[nr][nc] == 0) {
							isLinked = false;
							break EX; // 모두 바이러스가 아니면 탈출
						}
						if(map[nr][nc] == 1) continue;
						if(map[nr][nc] == 2) {
							map[nr][nc] = 1;
							isVisited[nr][nc] = true;
							queue.offer(new Virus(nr, nc));
						}
					}
				}
				if(!isLinked) {
					for (int i = 0; i < N; i++) {
						map[i] = Arrays.copyOf(temp[i], N);
					}
				}else {
					for (int i = 0; i < N; i++) {
						temp[i] = Arrays.copyOf(map[i], N);
					}
				}
			}
		});
		int size = virus.size();
		for (int i = size-1; i >= 0; i--) {
			Virus v = virus.get(i);
			if(map[v.r][v.c] == 1)
				virus.remove(i);
		}
	}

	private static void spread(int empty) {
		Deque<Virus> queue = new ArrayDeque<Virus>();
		isVisited = new boolean[N][N];
		virus.forEach( v -> {
			if(v.status == 0) {
				queue.offer(new Virus(v.r, v.c, v.status));
				isVisited[v.r][v.c] = true;
			}
		});
		while(queue.size() > 0) {
			Virus v = queue.poll();
			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = v.r + dr[i];
				nc = v.c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1 || isVisited[nr][nc]) continue;
				if(map[nr][nc] == 0) empty--;
				if(empty == 0) {
					min = Math.min(min, v.status+1);
					return;
				}
				isVisited[nr][nc] = true;
				queue.offer(new Virus(nr, nc, v.status+1));
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		virus = new ArrayList<Virus>();
		empty = 0;
		map = new int[N][N];
		int num;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num == 2) {
					virus.add(new Virus(i, j, -1));
				}else if(num == 0) empty++;
			}
		}
		
		min = Integer.MAX_VALUE;
	}
}
