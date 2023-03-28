package 코테기출;

public class Solution_Programmers_파괴되지않은건물_함소연 {
	class Solution {
	  	public int solution(int[][] board, int[][] skill) {
			int mcnt = 0;
			int N = board.length * board[0].length;
			for (int k = 0; k < skill.length; k++) {
				int type = skill[k][0];
				
				int r1 = skill[k][1];
				int c1 = skill[k][2];
				int r2 = skill[k][3];
				int c2 = skill[k][4];
				
				int degree = skill[k][5];
				if(type == 1) {
					degree *= -1;
				}
				for (int i = r1; i <= r2; i++) {
					for (int j = c1; j <= c2; j++) {
						boolean flag = false;
						if(board[i][j] <= 0) { // 파괴
							flag = true;
						}
						board[i][j] += degree;
						if(board[i][j] <= 0 && !flag) { // 파괴
							mcnt--;
						}else if(board[i][j] > 0 && flag) {
							mcnt++;
						}
					}
				}
			}
	        System.out.println(N);
			return N + mcnt;
		}
	}
}
