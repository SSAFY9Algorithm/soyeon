package 연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1406_에디터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<String> left = new Stack<String>();
		Stack<String> right = new Stack<String>();
		String s = br.readLine();
		String[] srr = s.split("");
		for (int i = 0; i < s.length(); i++) {
			left.add(srr[i]);
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			switch (input[0]) {
			case "L":
				if(!left.isEmpty())
					right.push(left.pop());
				break;
			case "D":
				if(!right.isEmpty())
					left.push(right.pop());
				break;
			case "B":
				if(!left.isEmpty())
					left.pop();
				break;
			default:
				left.push(input[1]);
				break;
			}
		}
		
		while(!left.isEmpty()) {
			right.push(left.pop());
		}
		while(!right.isEmpty()) {
			sb.append(right.pop());
		}
		System.out.println(sb);
	}
}
