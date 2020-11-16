package BitMask;

import java.util.*;
import java.io.*;

public class boj_1102_발전소 {
	static int N;
	static int[][] arr;
	static int[] dp;
	static int max = 10000000;
	static int result = 10000000;
	static int cnt = 0;
	static int P;
	static int allcnt = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[1 << N];
		Arrays.fill(dp, max);
		int bit = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		String word = br.readLine();
		String tmp = "";
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == 'Y') {
				cnt++;
				tmp += "1";
			} else
				tmp += "0";
		}
		P = Integer.parseInt(br.readLine());
		bit = Integer.parseInt(tmp, 2);
//		dp[bit] = 0;
//		System.out.println(bit);
		dfs(bit, cnt, 0);
		if(result!=10000000)
		System.out.println(result);
		else
			System.out.println(-1);
//		System.out.println(allcnt);
	}

	static void dfs(int bit, int cnt, int cost) // 현재 비트상태, 현재Y의 갯수,cost
	{
//		System.out.println(Integer.toBinaryString(bit) + " " + cnt + " " + cost);
//		allcnt++;
		if(cost>result)
			return;
		if(dp[bit]>cost)
		{
			dp[bit]=cost;
		}
		else return;
		if (cnt >= P) {
			result = Math.min(result, cost);
			return;
		}

		// 100 => 110 or 101
		String bitword = Integer.toBinaryString(bit);
		String zero = "";
		for(int i=0;i<N-bitword.length();i++)
			zero+="0";
		bitword = zero+bitword;
		for (int i = 0; i < bitword.length(); i++) {
			if (bitword.charAt(i) == '0')
				continue;
			// i 위치해서 발전소를 키는 비용을 고려할 것이다.
			for (int next = 0; next < bitword.length(); next++) {

				if ((bit & 1 << (N - next - 1)) > 0)
					continue;
				dfs(bit | (1 << (N - next - 1)), cnt + 1, cost + arr[i][next]);

			}
		}

	}

}
