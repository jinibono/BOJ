package DP;
import java.io.*;
import java.util.StringTokenizer;

public class boj_1010_다리놓기 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		long[][] dp = new long[31][31];
		dp[1][0] = 1;
		dp[1][1] = 1;
		for (int i = 2; i <= 30; i++) {
			dp[i][0] = 1l;
			dp[i][i] = 1l;
			for (int j = 1; j < i; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int size = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int n, r;
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			sb.append(dp[n][r]).append('\n');
		}
		System.out.println(sb.toString());
	}

}
