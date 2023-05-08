package 코테기출4;

import java.util.Arrays;

public class Solution_프로그래머스_이모티콘할인행사 {
	static float[] discount;
	static int[] answer;
	public static void main(String[] args) {
//		int[][] users = {{40, 10000}, {25, 10000}};
//		int[] emoticons = {7000, 9000};
		int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
		int[] emoticons = {1300, 1500, 1600, 4900};
		System.out.println(Arrays.toString(solution(users, emoticons)));
	}

	public static int[] solution(int[][] users, int[] emoticons) {
		answer = new int[2];
		discount = new float[emoticons.length];
		perm(emoticons.length, 0, users, emoticons);
		return answer;
	}

	private static void perm(int N, int idx, int[][] users, int[] emoticons) {
		if(N == idx) {
			check(users, emoticons);
			return;
		}
		for (int i = 10; i <= 40; i+=10) {
			discount[idx] = i;
			perm(N, idx+1, users, emoticons);
		}
	}

	private static void check(int[][] users, int[] emoticons) {
		int plus = 0; float price = 0;
		for (int i = 0; i < users.length; i++) {
			float sum = 0;
			for (int j = 0; j < discount.length; j++) {
				if(users[i][0] <= discount[j]) {
					sum += (1 - discount[j]/100) * emoticons[j] ;
				}
			}
			if(sum >= users[i][1]) {
				plus++;
			}else {
				price += sum;
			}
		}
		
		if(answer[0] < plus) {
			answer[0] = plus;
			answer[1] = (int) price;
		}else if(answer[0] == plus && answer[1] < price) {
			answer[1] = (int) price;
		}
	}
}
