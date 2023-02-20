package 큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335_트럭 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> queue = new LinkedList<Integer>();
		Queue<Integer> bridge = new LinkedList<Integer>();

		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			queue.offer(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < W; i++) {
			bridge.offer(0);
		}

		int l = 0; // 무게
		int time = 0; // 시간
		
		while (!bridge.isEmpty()) {
			time++;
			l -= bridge.poll(); // 다리에서 빠져 나감
			if(!queue.isEmpty()) {
				// 다리 위를 지나갈 수 있는 경우
				if(queue.peek() + l <= L) {
					l += queue.peek();
					bridge.offer(queue.poll());
				}else {
					bridge.offer(0);
				}
			}
		}
		System.out.println(time);
	}
}
