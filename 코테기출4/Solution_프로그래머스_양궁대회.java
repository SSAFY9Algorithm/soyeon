package 코테기출4;

import java.util.Arrays;

public class Solution_프로그래머스_양궁대회 {
	static int[] answer;
	static int max = 0;
	public static void main(String[] args) {
//		int n = 1;
//		int[] info = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int n = 9;
		int[] info = { 0,0,1,2,0,1,1,1,1,1,1};
//		int n = 3;
//		int[] info = {0,0,1,0,0,0,0,0,0,1,0};
//		int n = 10;
//		int[] info = {0,0,0,0,0,0,0,0,3,4,3};
//		int n = 5;
//		int[] info = { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };
		
		System.out.println(Arrays.toString(solution(n, info)));
	}

	public static int[] solution(int n, int[] info) {
		answer = new int [11];
		int[] ans = new int[11];
		int[] fail = {-1};
		comb(info, ans, n, 0, 0, 0);
		if(max == 0) {
			return fail;
		}
		return answer;
	}

	private static void comb(int[] info, int[] ans, int N, int idx, int score, int apeach) {
		if (idx == 10) {
			if(N > 0) {
				ans[10] = N;
			}
			if(max < score - apeach) {
				max = score - apeach;
				answer = ans.clone();
			}
			if(max == score - apeach) {
				if(N > 0) {
					ans[10] = N;
				}
				for (int i = 9; i >= 0; i--) {
					if(answer[i] == ans[i]) continue;
					if(answer[i] < ans[i] ) {
						answer = ans.clone();
					}
					break;
				}
			}
			ans[10] = 0;
			return;
		}
		
		if (N > info[idx]) { // 라이언 승
			ans[idx] = info[idx]+1;
			comb(info, ans, N - info[idx]-1, idx + 1, score+(10-idx), apeach);
			ans[idx] = 0;
		}
		if(info[idx] > 0) // 어피치 승
			comb(info, ans, N, idx + 1, score, apeach+(10-idx));
		if(info[idx] == 0)// 무승부
			comb(info, ans, N, idx + 1, score, apeach);
	}
}
