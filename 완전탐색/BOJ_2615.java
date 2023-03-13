package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] arr = new int[19][19];
		int[] dx = { 1, 0, 1, 1 }; // 오른쪽, 아래, 좌측 대각선, 우측 대각선
		int[] dy = { 0, 1, -1, 1 };

		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				// 1또는 2일때 연속되는 지 검사
				if (arr[i][j] == 1 || arr[i][j] == 2) {
					// 가로 세로 대각선 검사
					for (int k = 0; k < 4; k++) {
						int cnt = 1;
						int nx = i + dx[k];
						int ny = j + dy[k];
						int prev = arr[i][j];
						if (nx >= 19 || nx < 0 || ny >= 19 || ny < 0)
							continue;
						// 5칸 검사
						while (arr[nx][ny] == prev) {
							cnt++;
							nx += dx[k];
							ny += dy[k];
							if (cnt > 5)
								break;
							if (nx >= 19 || nx < 0 || ny >= 19 || ny < 0)
								break;
						}

						// 6개이면 break
						int px = i - dx[k];
						int py = j - dy[k];
						if (px >= 0 && px < 19 && py >= 0 && py < 19 && arr[px][py] == prev) {
							continue;
						}

						// 5칸 연속이라면,
						if (cnt == 5) {
							// 승리자 출력
							System.out.println(prev);
							
							// 가장 왼쪽에 있는 오목 출력
							if (j > ny - dy[k])
								System.out.println((nx - dx[k] + 1) + " " + (ny - dy[k]+ 1));
							else
								System.out.println((i + 1) + " " + (j + 1));

							return;
						}

					}
				}
			}
		}
		System.out.println(0);
		// 6개인지 검사
	}
}
