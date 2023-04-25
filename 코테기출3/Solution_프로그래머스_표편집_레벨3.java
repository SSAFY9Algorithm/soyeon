package 코테기출3;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_프로그래머스_표편집_레벨3 {
	public static void main(String[] args) {
//		int n = 8;
//		int k = 2;
//		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		System.out.println(solution(n, k, cmd));
	}

	static int cur;
	static StringTokenizer st;
	static List<String> list = new LinkedList<>();
	static Stack<Integer> del = new Stack<>();
	static StringBuilder sb;
	public static String solution(int n, int k, String[] cmd) {
		sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			list.add("O");
		}
		cur = k; int num = 0; int temp = 0; int cnt = 0;
		for (int i = 0; i < cmd.length; i++) {
			st = new StringTokenizer(cmd[i], " ");
			switch (st.nextToken()) {
			case "U":
				num = Integer.parseInt(st.nextToken());
				cnt = 0;
				for (int j = cur-1; j >= 0; j--) {
					if(list.get(j) == "X") continue;
					if(cnt == num) break;
					cnt++;
					cur = j;
				}
				break;
			case "D":
				num = Integer.parseInt(st.nextToken());
				cnt = 0;
				for (int j = cur+1; j < n; j++) {
					if(list.get(j) == "X") continue;
					if(cnt == num) break;
					cnt++;
					cur = j;
				}
				break;
			case "C":
				list.set(cur, "X");
				del.add(cur);
				temp = cur;
				for (int j = cur+1; j < n; j++) {
					if(list.get(j) == "X") continue;
					cur = j;
					break;
				}
				if(cur == temp) {
					for (int j = temp; j >= 0; j--) {
						if(list.get(j) == "X") continue;
						cur = j;
						break;
					}
				}
				break;
			case "Z":
				list.set(del.pop(), "O");
				break;
			default:
				break;
			}
		}
		for (int i = 0; i < n; i++) {
			sb.append(list.get(i));
		}
		return sb.toString();
	}
}