package DP;

import java.util.*;
import java.io.*;

public class boj_2662_기업투자 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] cost = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			for (int k = 0; k < M; k++)
				cost[idx][k] = Integer.parseInt(st.nextToken());
		}
		

	}

}
