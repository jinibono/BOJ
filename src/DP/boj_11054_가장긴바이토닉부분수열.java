package DP;

import java.util.*;
import java.io.*;

public class boj_11054_가장긴바이토닉부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int result = 1;
		int dp[] = new int[N + 1];
		int dp2[] = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			dp[i] = 1;
			dp2[i] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (arr[j] >= arr[i])
					continue;
				dp[i] = Math.max(dp[i], dp[j] + 1);

			}
		}
		for (int i = N; i >= 1; i--) {
			for (int j = N; j > i; j--) {
				if (arr[j] >= arr[i])
					continue;
				dp2[i] = Math.max(dp2[i], dp2[j] + 1);

			}
		}
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, dp[i] + dp2[i] - 1);
		}

//		System.out.println(Arrays.toString(dp));
//		System.out.println(Arrays.toString(dp2));
		System.out.println(result);

	}

}
