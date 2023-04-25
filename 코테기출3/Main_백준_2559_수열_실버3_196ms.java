package 코테기출3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2559_수열_실버3_196ms {
	static int N, K, num, max = -987654321;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		arr[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < K; i++) {
			num = Integer.parseInt(st.nextToken());
			arr[i] = arr[i-1] + num;
		}
		max = arr[K-1];
		if(K == N) {
			System.out.println(max);
		}else {
			for (int i = K; i < N; i++) {
				num = Integer.parseInt(st.nextToken());
				arr[i] += arr[i-1] + num;
				if(max < arr[i] - arr[i-K]) {
					max = arr[i] - arr[i-K];
				}
			}
			System.out.println(max);
		}
	}
}
