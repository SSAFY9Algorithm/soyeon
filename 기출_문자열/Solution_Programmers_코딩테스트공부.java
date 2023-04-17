package 기출_문자열;

import java.util.Arrays;

public class Solution_Programmers_코딩테스트공부 {
	public static void main(String[] args) {
		int alp = 10;
		int cop = 0;
		int[][] problems = {{10,15,2,1,2},{20,20,3,3,4}};
		
		//2
//		int alp = 0;
//		int cop = 0;
//		int[][] problems = {{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}};
		
		System.out.println(solution(alp, cop, problems));
	}

	public static int solution(int alp, int cop, int[][] problems) {
		int A = 0; int C = 0;
		int answer = 0;
		for (int[] p : problems) {
			A = Math.max(A, p[0]);
			C = Math.max(C, p[1]);
		}
		if(alp >= A) alp = A;
		if(cop >= C) cop = C;
		
		int[][] dp = new int[A+2][C+2];
		
		for (int i = alp; i <= A; i++) {
			for (int j = cop; j <= C; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dp[alp][cop] = 0;
		for (int i = alp; i <= A; i++) {
			for (int j = cop; j <= C; j++) {
				
			}
		}
//		while(true) {
//			if(i == problems.length) break;
//			if(alp >= problems[i][0] && cop >= problems[i][1]) {
//				alp += problems[i][2];
//				cop += problems[i][3];
//				answer += 2;
//				i++;
//			}
//			else if (alp < problems[i][0] && cop >= problems[i][1]){
//				answer += problems[i][0] - alp;
//				alp = problems[i][0];
//			} else if (alp >= problems[i][0] && cop < problems[i][1]) {
//				answer += problems[i][1] - cop;
//				cop = problems[i][1];
//			}else {
//				int sum = problems[i][0] - alp + problems[i][1] - cop;
//				alp = problems[i][0];
//				cop = problems[i][1];
//				answer += sum;
//			}
//			System.out.println(answer);
//		}
		return answer;
	}
}
