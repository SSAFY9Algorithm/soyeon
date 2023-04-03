package 큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 * 큐리스트
 *
 */
public class BOJ_14713_앵무새 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<String> check = new LinkedList<>();
		Queue<String> arr[];
		int N = Integer.parseInt(br.readLine());
		arr = new LinkedList[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = new LinkedList<String>();
			String s = br.readLine();
			String[] srr = s.split(" ");
			sum+= srr.length;
			for(int j = 0; j < srr.length; j++) {
				arr[i].offer(srr[j]);				
			}
		}

		String a = br.readLine();
		String[] ans = a.split(" ");
		
		// 반례 : 받아적은 문장과 앵무새가 말한 단어의 개수가 다를 때.
		if(sum != ans.length) {System.out.println("Impossible"); return;}
		
		boolean flag = false;
		for (int i = 0; i < ans.length; i++){
			flag = false;
			for (int j = 0; j < N; j++) {
				if(ans[i].equals(arr[j].peek())) {
					arr[j].poll();
					flag = true;
					break;
				}
			}
			if(!flag) break;
		}
		
		if(flag) System.out.println("Possible");
		else System.out.println("Impossible");

	} // end of main
} // end of class
