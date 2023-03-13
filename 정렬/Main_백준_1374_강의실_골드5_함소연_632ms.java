package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_1374_강의실_골드5_함소연_632ms {
	static class Lecture implements Comparable<Lecture>{
		int idx;
		int start;
		int end;
		public Lecture(int idx, int start, int end) {
			super();
			this.idx = idx;
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Lecture o) {
			if(this.start != o.start) { // 시작 시간이 빠른 순으로 정렬
				return this.start - o.start;
			}else { // 시작시간이 같으면 종료시간이 빠른 순으로 정렬
				return this.end - o.end;
			}
		}
	}
	static int room = 0;
	private static PriorityQueue<Integer> pq;
	private static Lecture[] lectures;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 강의 개수
		
		pq = new PriorityQueue<Integer>(); // 강의실 수
		
		lectures = new Lecture[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int idx = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			lectures[i] = new Lecture(idx, start, end);
		}
		
		Arrays.sort(lectures); // 강의 시간이 빠른 순으로 정렬 
		
		pq.offer(lectures[0].end); // 가장 먼저 시작하는 강의 offer
		
		for (int i = 1; i < N; i++) {
//			System.out.println(pq);
			if(lectures[i].start >= pq.peek()) { // 강의실 사용 가능할 경우 갱신
				pq.poll();
			}
			pq.offer(lectures[i].end);
		}
		
		System.out.println(pq.size());
	}
}
