package BitMask;
import java.util.*;
import java.io.*;

public class boj_1029_그림교환 {
	static int N;
	static int[][] arr;
	static int res = 0;
	static int[][][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String num = br.readLine();
			for (int j = 0; j < N; j++)
				arr[i][j] = num.charAt(j) - '0';
		}
		dp = new int[N][1 << N][10];
		dp[0][1][0] = 1;
		dfs(0, 0, 1, 1);
		System.out.println(res);
	}

	private static void dfs(int idx, int price, int bit, int len) {
		dp[idx][bit][price] = len;
		res = Math.max(res, len);
		
		for (int i = 1; i < N&&res!=N; i++) {
			if (arr[idx][i] < price || (bit & (1 << i)) > 0)
				continue;
			if (dp[i][bit | (1 << i)][arr[idx][i]] < len + 1)
				dfs(i, arr[idx][i], (bit | 1 << i), len + 1);

		}
	}

}
