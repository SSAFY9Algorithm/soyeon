package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_10815_숫자카드_실버5_함소연_1016ms {
	public static int[] cards;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 상근이가 가진 카드 수

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		cards = new int[N];
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(cards); // 오름차순 정렬

		int M = Integer.parseInt(br.readLine()); // 구해야 할 카드 수
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int find = Integer.parseInt(st.nextToken());
			BinarySearch(find);
		}
		System.out.println(sb.toString());
	}

	private static void BinarySearch(int find) {
		int start = 0, end = cards.length - 1, mid = end / 2;

		while (start <= end) {
			mid = start + (end - start)/2;
			if (cards[mid] == find) { // 카드를 찾으면 return;
				sb.append(1 + " ");
				return;
			}
			else if (cards[mid] < find) { // 찾으려는 카드가 오른쪽에 있다면
				start = mid + 1;
				
			}else {
				end = mid - 1;
			}
		}
		sb.append(0+" ");
	}
}
