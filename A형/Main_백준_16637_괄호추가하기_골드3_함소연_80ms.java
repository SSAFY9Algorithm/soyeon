package A형;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_16637_괄호추가하기_골드3_함소연_80ms {
	private static int N;
	private static char[] op;
	private static int[] num;
	private static int MAX = Integer.MIN_VALUE;
	private static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		op = new char[N / 2];
		num = new int[N / 2 + 1];
		int nIdx = 0, oIdx = 0;
		for (int i = 0; i < N; i++) {
			if(i % 2 == 0) {
				num[nIdx++] = str.charAt(i) - '0';
			}else {
				op[oIdx++] = str.charAt(i);
			}
		}
		isSelected = new boolean[N / 2];
		go(0);
		System.out.println(MAX);
	}

	private static void go(int idx) {
		if(idx == N/2) {
			for (int i = 0; i < isSelected.length - 1; i++) {
				if(isSelected[i] && isSelected[i+1]) {
					return;
				}
			}
			Cal();
			return;
		}
		
		isSelected[idx] = true;
		go(idx + 1);
		isSelected[idx] = false;
		go(idx + 1);
	}

	private static void Cal() {
		// 괄호 계산
		int total = 0;
		int[] temp = Arrays.copyOf(num, num.length);
		for (int i = 0; i < isSelected.length; i++) {
			if(!isSelected[i]) continue;
			switch (op[i]) {
			case '-':
				temp[i] = num[i] - num[i+1];
				temp[i+1] = 100; // pass
				total++;
				break;
			case '+':
				temp[i] = num[i] + num[i+1];
				temp[i+1] = 100; // pass
				total++;
				break;
			case '*':
				temp[i] = num[i] * num[i+1];
				temp[i+1] = 100; // pass
				total++;
				break;
			default:
				break;
			}
		}
		// 최종 계산
		int idx = 0;
		int[] newNum = new int[N / 2 + 1 - total];
		for (int i = 0; i < temp.length; i++) {
			if(temp[i] == 100) continue;
			newNum[idx++] = temp[i];
		}
		idx = 0;
		char[] newOp = new char[N / 2 - total];
		for (int i = 0; i < op.length; i++) {
			if(isSelected[i]) continue;
			newOp[idx++] = op[i];
		}
		int res = newNum[0];
		for (int i = 0; i < newOp.length; i++) {
			switch (newOp[i]) {
			case '-':
				res -= newNum[i+1];
				break;
			case '+':
				res += newNum[i+1];
				break;
			case '*':
				res *= newNum[i+1];
				break;
			default:
				break;
			}
		}
		MAX = Math.max(MAX, res);
	}
}

