package DP;

import java.util.*;
import java.io.*;

public class boj_1256_사전 {
	static ArrayList<String> list;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[][] dp = new long[201][201];
		list = new ArrayList<String>();
		char[] arr = new char[N + M];
		for (int i = 0; i < N; i++)
			arr[i] = 'a';
		for (int i = N; i < N + M; i++)
			arr[i] = 'z';
//		permutation(arr, 0, N + M, N + M);
//		Collections.sort(list);
//		list = new ArrayList<String>(new TreeSet<>(list));
//		for (int i = 0; i < list.size(); i++)
//			System.out.println((i + 1) + " " + list.get(i));
		sb = new StringBuilder();

		for (int i = 0; i <= (N + M); i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
		}
		for (int i = 2; i <= (N + M); i++) {
			for (int j = 1; j <= i; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				dp[i][j] = Math.min(dp[i][j], 1000000000l);
			}
		}
		int len = N + M;
		int dif = 0;
		int curlen = len;
		int mid = 0;
        if(dp[N+M][N]<K)
        {
            System.out.println(-1);
            return;
        }
		while (sb.toString().length() < (len)) {
			
			if (dp[N + M - 1][N - 1] + dif >= K) {
				sb.append('a');
				N--;
			} else {
				dif += dp[N + M - 1][N - 1];
				sb.append('z');
				M--;
			}
			if(N==0)
			{
				for(int i=0;i<M;i++)
					sb.append('z');
			}
			if(M==0) {
				for(int i=0;i<N;i++)
					sb.append('a');
			}
//			System.out.println(sb.toString());

		}
		System.out.println(sb.toString());

	}

	static void permutation(char[] arr, int depth, int n, int r) {
		if (depth == r) {
			sb = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i] + "");
			}
			list.add(sb.toString());
			return;
		}

		for (int i = depth; i < n; i++) {
			swap(arr, depth, i);
			permutation(arr, depth + 1, n, r);
			swap(arr, depth, i);
		}
	}

	static void swap(char[] arr, int depth, int i) {
		char temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}

}
