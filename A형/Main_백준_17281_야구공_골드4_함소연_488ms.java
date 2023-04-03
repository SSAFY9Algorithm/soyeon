package A형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17281_야구공_골드4_함소연_488ms {

	private static int[][] game;
	private static int[] numbers;
	private static int[] players;
	private static int N;
	private static boolean[] isSelected;
	private static int maxScore = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		game = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isSelected = new boolean[9];
		numbers = new int[8];
		players = new int[9];
		go(0);
		System.out.println(maxScore);
	}

	private static void go(int idx) {
		if(idx == 8) {
			for (int i = 0; i < 3; i++) {
				players[i] = numbers[i];
			}
			players[3] = 0;
			for (int i = 3; i < 8; i++) {
				players[i+1] = numbers[i];
			}
			// 점수계산
			score();
			return;
		}
		// 순열
		for (int i = 1; i < 9; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			numbers[idx] = i;
			go(idx + 1);
			isSelected[i] = false;
		}
	}

	private static void score() {
		int home = 0;
		int inning = 0;
		int idx = 0;
		while(inning < N) { // 이닝 수 만큼
			int out = 0;
			boolean[] ground = new boolean[3];
			while(out < 3 ) { // 3아웃시 경기 종료
				if(idx >= 9) { // 다시 1번 타자부터
					idx = 0;
				}
				switch (game[inning][players[idx]]) {
				case 0:
					out++;
					break;
				case 1: // 안타
					if(ground[2]) {
						home++;
						ground[2] = false;
					}
					if(ground[1]) {
						ground[1] = false;
						ground[2] = true;
					}
					if(ground[0]) {
//						ground[0] = false;
						ground[1] = true;
					}
					ground[0] = true;
					break;
				case 2: // 2루타
					if(ground[2]) {
						home++;
						ground[2] = false;
					}
					if(ground[1]) {
						home++;
//						ground[1] = false;
					}
					if(ground[0]) {
						ground[0] = false;
						ground[2] = true;
					}
					ground[1] = true;
					break;
				case 3: // 3루타
					if(ground[2]) {
						home++;
//						ground[2] = false;
					}
					if(ground[1]) {
						home++;
						ground[1] = false;
					}
					if(ground[0]) {
						home++;
						ground[0] = false;
					}
					ground[2] = true;
					break;
				case 4: // 홈런
					if(ground[2]) {
						home++;
						ground[2] = false;
					}
					if(ground[1]) {
						home++;
						ground[1] = false;
					}
					if(ground[0]) {
						home++;
						ground[0] = false;
					}
					home++;
					break;
				default:
					break;
				} // end of switch
				idx++;
			} // end of out
			inning++;
		} // end of inning
		maxScore = Math.max(home, maxScore);
	}
}
