package 스택
;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/**
 * 
 * 커서를 가운데에 놓고 좌우 stack 이동
 *
 */
public class BOJ_5397_키로거 {
	char left = '<';
	char right = '>';
	char back = '-';
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			Stack<String> left = new Stack<String>();
			Stack<String> right = new Stack<String>();
			String s = br.readLine();
			String[] input = s.split("");
			for (int i = 0; i < input.length; i++) {
				switch (input[i]) {
				case "<":
					if(!left.isEmpty())
						right.push(left.pop());
					break;
				case ">":
					if(!right.isEmpty())
						left.push(right.pop());
					break;	
				case "-":
					if(!left.isEmpty())
						left.pop();
					break;	
				default:
					left.push(input[i]);
				}
			}
			
			while(!left.isEmpty()) {
				right.push(left.pop());
			}
			while(!right.isEmpty()) {
				sb.append(right.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
