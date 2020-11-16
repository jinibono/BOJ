package DP;
import java.util.*;
import java.io.*;

public class boj_1038_감소하는수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[][][] dp = new String[11][10][10000];
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			dp[1][i][0] = Integer.toString(i);
			list.add(Integer.toString(i));
		}

		int idx = 0;
		int cdx = 0;
		// dp [ len ] [ fnum] [ idx]

		for (int len = 2; len <=10; len++) {
			for (int fnum = 1; fnum <= 9; fnum++) {
				idx = 0;
				for (int bnum = 0; bnum < fnum; bnum++) {
					cdx = 0;
					while (true) {
						if(dp[len-1][bnum][cdx]==null)
							break;
						dp[len][fnum][idx++] = Integer.toString(fnum) + dp[len - 1][bnum][cdx++];
						list.add(dp[len][fnum][idx-1]);
					}
				}
			}
		}
		if(list.size()>N)
		System.out.println(list.get(N));
		else
			System.out.println(-1);
//		System.out.println(list.get(list.size()-1));

	}

}
