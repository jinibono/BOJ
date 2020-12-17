package DP;

import java.util.*;
import java.io.*;

public class boj_1495_기타리스트 {
	static int N, S, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] V = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			V[i] = Integer.parseInt(st.nextToken());
		boolean[][] dp = new boolean[N + 1][M+1];
		
		dp[0][S] = true;
	
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for(int j=0;j<=M;j++)
			{
				if(!dp[i][j]) continue;
				if(j+V[i]<=M)
					dp[i+1][j+V[i]]=true;
				if(j-V[i]>=0)
					dp[i+1][j-V[i]]=true;
			}
		}
		for(int r = M;r>=0;r--)
		{
			if(dp[N][r])
			{
				System.out.println(r);
				return;
			}
		}
		System.out.println(-1);
		
			

	}

}
