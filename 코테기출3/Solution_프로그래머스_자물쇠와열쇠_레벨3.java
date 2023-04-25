package 코테기출3;
import java.util.Arrays;

public class Solution_프로그래머스_자물쇠와열쇠_레벨3 {
	public static void main(String[] args) {
//		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
//		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
//		int[][] key = {{0, 0, 0}, {0, 0, 0}, {0, 0, 1}};
//		int[][] lock = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		int[][] key = {{0, 0}, {0, 0}};
		int[][] lock = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		System.out.println(solution(key, lock));
	}
	static int M, N;
	public static boolean solution(int[][] key, int[][] lock) {
		M = key.length;
		N = lock.length;
		boolean flag=  false;
		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock.length; j++) {
				if(lock[i][j] != 0) {
					flag = true;
					break;
				}
			}
		}
		if(!flag) return true;
		int[][] map = new int[N+M*2-2][N+M*2-2];
		for (int i = 0; i < map.length-M+1; i++) {
			for (int j = 0; j < map.length-M+1; j++) {
				int[][] temp1 = new int[M][M];
				int[][] temp2 = key;
				// 90도 회전
				for (int k = 0; k < 4; k++) {
					for (int r = 0; r < M; r++) {
						for (int c = 0; c < M; c++) {
							temp1[r][c] = temp2[c][M-1-r];
						}
					}
					map = new int[N+M*2-2][N+M*2-2];
					map = change(map, temp1, i, j);
					print(temp1);
					print(map);
					if(check(map, lock))
						return true;
					for (int d = 0; d < M; d++) {
						temp2[d] = Arrays.copyOf(temp1[d], M);
					}
				}
			}
		}
		return false;
	}

	private static int[][] change(int[][] map, int[][] temp, int sr, int sc) {
		for (int i = sr, r = 0; i < sr+M; i++, r++) {
			for (int j = sc, c = 0; j < sc+M; j++, c++) {
				map[i][j] = temp[r][c];
			}
		}
		return map;
	}

	private static boolean check(int[][] map, int[][] lock) {
		for (int i = N-1, r = 0; r < N; i++, r++) {
			for (int j = N-1, c = 0; j < N; j++, c++) {
				if(lock[r][c] == 1 && map[i][j] == 1) return false;
				if(lock[r][c] == 0 && map[i][j] == 0) return false;
			}
		}
		return true;
	}
	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}