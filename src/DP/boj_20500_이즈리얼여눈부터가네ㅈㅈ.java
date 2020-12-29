package DP;

import java.util.*;
import java.io.*;

public class boj_20500_이즈리얼여눈부터가네ㅈㅈ {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N + 3];
		dp[2] = 1;
		dp[3] = 1;
		for (int i = 4; i <= N; i++) {
			dp[i] = (((dp[i - 2] * 2) % 1000000007) + dp[i - 1]) % 1000000007;
		}
		System.out.println(dp[N]);

	}

}
