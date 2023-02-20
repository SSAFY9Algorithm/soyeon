package 순열_조합_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 다음 순열 : 
 * 	1. arr[i-1] < arr[i] && i > 0 을 만족하는 i 중 가장 큰 i
 * 	2. arr[i-1]을 기준으로 두 덩이로 나눠, arr[i-1]보다 큰 수를 찾아 arr[i-1]과 swap
 *		if(arr[i-1] <= arr[j]) swap(i-1, j)
 *	3. i부터 j까지의 구간을 오름차순 정렬
 */	
public class BOJ_10972 {
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		for (int i = N-1; i > 0; i--) {
			if(arr[i-1] < arr[i]) {
				idx = i;
				break;
			}
		}
		
		// 내림차순으로 정렬된 상태, -1 출력
		if(idx == 0) {
			System.out.println(-1);
			return;
		}
		
		for (int j = N-1; j >= idx; j--) {
			if(arr[idx-1] <= arr[j]) {
				swap(idx-1, j);
				break;
			}
		}
		
		int j = arr.length-1;
		while(idx < j) {
			swap(idx, j);
			idx++;
			j--;
		}
//		Arrays.sort(arr, idx, N);
		
		for (int a : arr) {
			System.out.print(a + " ");
		}
		
	}
	public static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
