package DP;

import java.io.*;
import java.util.*;

public class boj_2651_자동차경주대회 {
	static int maxmove;
	static int[] lenmap;
	static int[] costmap;
	static int[] memo;
	static long min = Long.MAX_VALUE;
	static String result = "";
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		File file = new File("input.txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		maxmove = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		lenmap = new int[N + 1];
		costmap = new int[N + 1];
		memo = new int[N + 1];
		Arrays.fill(memo, Integer.MAX_VALUE);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i <= N; i++) {
			lenmap[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			costmap[i] = Integer.parseInt(st.nextToken());
		}
		dfs(1, 0, maxmove - lenmap[0], "", 0);
		if (min == Long.MAX_VALUE)
			min = 0;
		System.out.println(min);
		int len = result.split(" ").length;
		if (result.equals(""))
			len = 0;
		System.out.println(len);
		if (len == 0)
			return;
		System.out.println(result);

	}

	static void dfs(int idx, int cost, int move, String str, int mode) {

		if (idx == N + 1) {
//			System.out.println(Arrays.toString(memo));
//			System.out.println(cost);
//			System.out.println(str);
			if (min > cost) {
				result = str;
				min = cost;
			}
			return;// 도착지에 도달했을 때
		}
		if (mode == 1) {
			if (memo[idx] <= cost) {
				return;
			}
			memo[idx] = cost;
		}
		if (maxmove - lenmap[idx] >= 0)
			dfs(idx + 1, cost + costmap[idx], maxmove - lenmap[idx], str + "" + idx + " ", 1); // 정비소를 다 방문

		if (move - lenmap[idx] >= 0) {
			dfs(idx + 1, cost, move - lenmap[idx], str, 0);
		}

	}

}
