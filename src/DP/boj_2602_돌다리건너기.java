package DP;

import java.util.*;
import java.io.*;

public class boj_2602_돌다리건너기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		String bridge[] = new String[2];
		for (int i = 0; i < 2; i++)
			bridge[i] = br.readLine();
		int[][][] dp = new int[2][bridge[0].length()][word.length()];
		int result = 0;
		if (word.length() > bridge[0].length()) {
			result = 0;
		} else {
			// int w = 0;
			int cnt = 0;
			for (int i = 0; i < 2; i++) {
				cnt = 0;
				for (int j = 0; j < bridge[i].length(); j++) {
					if (word.charAt(0) == bridge[i].charAt(j))
						cnt++;
					dp[i][j][0] = cnt;
				}
			}
			for (int w = 1; w < word.length(); w++) {
				for (int i = 0; i < 2; i++) {
					// cnt = -1;
					for (int j = w; j < bridge[i].length(); j++) {
						if (word.charAt(w) == bridge[i].charAt(j) && dp[(i + 1) % 2][j - 1][w - 1] > 0) {
							// cnt++;
							dp[i][j][w] = dp[i][j - 1][w] + dp[(i + 1) % 2][j - 1][w - 1];

						} else
							dp[i][j][w] = dp[i][j - 1][w];
					}
				}
			}
			result = dp[0][bridge[0].length() - 1][word.length() - 1]
					+ dp[1][bridge[1].length() - 1][word.length() - 1];
//			for (int w = 0; w < word.length(); w++) {
//				for (int i = 0; i < 2; i++) {
//					for (int j = 0; j < bridge[i].length(); j++) {
//						System.out.print(dp[i][j][w]);
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}

		}
		System.out.println(result);

	}

}
