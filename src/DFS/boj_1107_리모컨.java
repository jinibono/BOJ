package DFS;

import java.util.*;
import java.io.*;

public class boj_1107_리모컨 {
	static int[] num;
	static int result = Integer.MAX_VALUE;
	static int N;
	static int maxlen;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		maxlen = Integer.toString(N).length();
		int M = Integer.parseInt(br.readLine());
		boolean[] not = new boolean[10];
		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				not[Integer.parseInt(st.nextToken())] = true;
		}
		num = new int[10 - M];
		int cnt = 0;
		result = Math.abs(N - 100);
		if (result == 0) {
			System.out.println(0);
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (not[i])
				continue;
			num[cnt++] = i;
		}
		for (int i = 0; i < num.length; i++) {
			int t = Math.abs(N - num[i]) + 1;
			dfs(num[i], 1, t, 0);
		}
		System.out.println(result);

	}

	static void dfs(int curNum, int len, int cnt, int gc) {
		result = Math.min(result, cnt);
		for (int i = 0; i < num.length; i++) {
//			if (num[i] == 0)
//				continue; // 앞에다가 0은 붙이지 말자
			int nextNum = curNum + (int) Math.pow(10, len) * num[i];
			int nextcnt = Math.abs(N - nextNum) + len + 1;
//			System.out.println(nextNum + " " + nextcnt);
			if (num[i] == 0 && gc + len <= 7) {
				dfs(nextNum, len + 1, nextcnt, gc + 1);
			} else if (cnt > nextcnt)
				dfs(nextNum, len + 1, nextcnt, gc);
		}

	}

}
