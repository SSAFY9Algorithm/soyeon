package 코테기출;

public class Solution_Programmers_택배배달과수거하기_함소연 {
	class Solution {
	    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
	       // 맨 끝집부터 차례대로 가져갈 수 있는 택배상자 수 확인
			// 차례대로 수거할 수 있는 상자 확인
			int d = 0;
			int p = 0;
			long distance = 0;
			long answer = 0;
			int cnt = 1;
			for (int i = n-1; i >= 0; i--) {
				if(deliveries[i] == 0 && pickups[i] == 0) {
					continue;
				}
				d -= deliveries[i];
				p -= pickups[i];
				cnt = 0;
				while(d < 0 || p < 0) {
					d += cap;
					p += cap;
					cnt++;
				}
				
				distance += cnt * (i+1) * 2;
			}
			answer = distance;
			return answer;
	    }
	}

}
