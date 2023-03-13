package A형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 미세먼지 확산 (모든 미세먼지가 동시에 이루어짐)
 * 1. A/5 :남은 미세먼지 양
 * 2. A - A/5 * (확산된 방향 개수)
 * 3. 공기청정기가 있는 칸은 확산 X
 * 
 * 공기청정기 작동
 * 1. 공기청정기 기준 (x, y) ~ (N, y) 아래 행은 시계 방향
 * 2. 공기청정기 기준 (x-1, y) ~ (0, y) 윗 행은 반시계 방향 
 */
public class Main_백준_17144_미세먼지안녕_골드4_함소연_312ms {
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int[][] room;
	private static int[] airCleaner;
	private static int amount = 0;
	private static int R;
	private static int C;
	private static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 집 크기 R
		C = Integer.parseInt(st.nextToken()); // 집 크기 C
		T = Integer.parseInt(st.nextToken()); // T 초 후 
		
		int idx = 0;
		airCleaner = new int[2];
		room = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == -1) { // 공기 청정기
					airCleaner[idx++] = i;
				}
			}
		}
		go();
		System.out.println(count());
	}
	// 작동
	private static void go() {
		// T초동안 작동 : T초가 되면 return;
		int time = 0;
		while(time < T) {
			int[][] temp = new int[R][C];
			// 1. 미세먼지 확산
			spread(temp);
			// 2. 공기청정기 작동
			on(temp);
			time++;
			room = temp;
		}
	}
	// 미세먼지 확산
	private static void spread(int[][] temp) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(room[i][j] == 0 || room[i][j] == -1) continue; 
				
				int cnt = 0;
				// 미세먼지 발견 시 확산
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					// 범위 체크
					if(nx < 0 || nx >= R || ny < 0 || ny >= C || room[nx][ny] == -1) continue;
					
					// 해당 자리로 미세먼지 확산
					temp[nx][ny] += room[i][j] / 5;
					cnt++;
				}
					
				// 원래 있던 미세먼지 자리 갱신
				temp[i][j] += room[i][j] - (room[i][j] / 5) * cnt;
			}
		}
		temp[airCleaner[0]][0] = -1;
		temp[airCleaner[1]][0] = -1;
	}
	
	// 공기청정기 작동
	private static void on(int[][] temp) {
		int save1; int save2; int save3;
		// 반시계 방향
		int x = airCleaner[0];
		save1 = temp[0][0];
		save2 = temp[x][C-1];
		save3 = temp[0][C-1];
		
		for (int i = 0; i < C-1; i++) {
			temp[0][i] = temp[0][i+1];
		}

		for (int i = x; i > 1; i--) {
			temp[i][0] = temp[i-1][0];
		}
		temp[x][0] = -1; // 공기청정기가 흡수
		
		for (int i = C-1; i > 1; i--) {
			temp[x][i] = temp[x][i-1];
		}
		temp[x][1] = 0; // 공기 청정기를 통과하면 정화
		
		for (int i = 0; i < x; i++) {
			temp[i][C-1] = temp[i+1][C-1];
		}
		
		temp[1][0] = save1;
		temp[x-1][C-1] = save2;
		temp[0][C-2] = save3;
		
		// 시계 방향
		x = airCleaner[1];
		save1 = temp[x][C-1];
		save2 = temp[R-1][C-1];
		save3 = temp[R-1][0];
		
		for (int i = C-1; i > 0; i--) {
			temp[x][i] = temp[x][i-1];
		}
		temp[x][1] = 0; // 공기 청정기를 통과하면 정화
		
		for (int i = R-1; i > x; i--) {
			temp[i][C-1] = temp[i-1][C-1];
		}
		
		for (int i = 0; i < C-1; i++) {
			temp[R-1][i] = temp[R-1][i+1];
		}
		temp[x][1] = 0; // 공기 청정기를 통과하면 정화
		

		for (int i = x; i < R-1; i++) {
			temp[i][0] = temp[i+1][0];
		}
		temp[x][0] = -1; // 공기청정기가 흡수
		
		temp[x+1][C-1] = save1;
		temp[R-1][C-2] = save2;
		temp[R-2][0] = save3;
	}
	// 남아있는 미세먼지 양
	private static int count() {
		amount = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(room[i][j] == 0 || room[i][j] == -1) continue;
				amount += room[i][j];
			}
		}
		return amount;
	}
//	// 프린트
//	private static void print(int[][] arr) {
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				if(arr[i][j] == -1)
//					System.out.print("■"+" ");
//				else System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
