package 스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * stack : 
 * 1. 현재 수열 값 >= 자연수 : push 
 * 2. 현재 수열 값 < 자연수 : pop
 */
public class BOJ_1874_스택수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		int N = Integer.parseInt(br.readLine());
		int index = 1; // 자연수
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine()); // 현재 수열 값
//			* 1. 현재 수열 값 >= 자연수 : push
			if (num >= index) {
				while (num >= index) {
					stack.push(index++);
					sb.append("+\n");
				}
			}
//			* 2. 현재 수열 값 < 자연수 : pop
			else if (stack.peek() > num) { // 스택 맨 위의 수보다 큰 자연수는 찾을 수가 없음.
				System.out.println("NO");
				return;
			}
			stack.pop();
			sb.append("-\n");
		}
		System.out.println(sb);
	}
}