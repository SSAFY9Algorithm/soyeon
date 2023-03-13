package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_백준_19583_싸이버개강총회_실버2_함소연_616ms {
	static HashMap<String, Boolean> map;
	static int check = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		String start = st.nextToken();
		String finish = st.nextToken();
		String end = st.nextToken();
		int[] startArr = splitTime(start);
		int[] finishArr = splitTime(finish);
		int[] endArr = splitTime(end);
		map = new HashMap<String, Boolean>();
		
		String input = "";
		while ((input = br.readLine()) != null && input.length() > 0) {
			st = new StringTokenizer(input, " ");
			String time = st.nextToken();
			String nickname = st.nextToken();
			int[] userArr = splitTime(time);
			// 개강 총회 시작 전 ~ 시작 직전까지
			if((startArr[0] > userArr[0]) || (startArr[0] == userArr[0] && startArr[1] >= userArr[1])) {
				map.put(nickname, false);
			}
			// 개강 총회 종료 후 ~ 스트리밍 종료 직전까지
			if((finishArr[0] == userArr[0] && finishArr[1] <= userArr[1]) || (finishArr[0] < userArr[0])) {
				if((endArr[0] > userArr[0]) || (endArr[0] == userArr[0] && endArr[1] >= userArr[1])) {
					if(map.containsKey(nickname) && !map.get(nickname)) {
						map.put(nickname, true);
						check++;
					}
				}
			}
		}
		System.out.println(check);
	} // end of main
	
	/** 시간 문자열 배열 -> 시간 정수형 배열 */
	private static int[] splitTime(String str) {
		String[] arr = str.split(":");
		
		int[] res = new int[2];
		for (int i = 0; i < 2; i++) {
			res[i] = Integer.parseInt(arr[i]);
		}
		
		return res;
	}
	
} // end of class
