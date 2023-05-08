package 코테기출4;

public class Solution_프로그래머스_미로탈출명령어 {
	public static void main(String[] args) {
		System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
	}
	static int[][] map;
	static String answer = "impossible";
	static boolean flag = false;
	static int[] dr = {1, 0, 0, -1};
	static int[] dc = {0, -1, 1, 0};
	static String[] str = {"d","l","r","u"};
	public static String solution(int n, int m, int x, int y, int r, int c, int k) {
		char[] ch = new char[k+1];
		map = new int[n][m];
		go(x-1, y-1, r-1, c-1, "", 0, k);
		return answer;
	}
	private static void go(int x, int y, int r, int c, String string, int cnt, int k) {
		if(flag) return;
		if(cnt == k) {
			if(x == r && y == c) {
				flag = true;
				answer = string;
			}
			return;
		}
		int dist = Math.abs(x-r) + Math.abs(y-c);
		if(dist > k - cnt) return;
		if(k-cnt - dist%2 == 1) {
			return;
		}
		int nr, nc = 0;
		for (int i = 0; i < 4; i++) {
			nr = x + dr[i];
			nc = y + dc[i];
			if(nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) continue;
			string += str[i];
			go(nr, nc, r, c, string, cnt+1, k);
			string = string.substring(0, string.length()-1);
		}
	}
}
