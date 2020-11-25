package DP;
import java.util.*;
import java.io.*;

public class boj_1309_동물원 {
	static int N, M;
	static long mod = 9901l;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		long dp[] = new long[100001];
		dp[1] = 3;
		dp[0] = 1;
		for(int i=2;i<=N;i++)
		{
			dp[i] = (dp[i-1]*2+dp[i-2])%mod;
		}
		System.out.println(dp[N]%mod);
	}
}
