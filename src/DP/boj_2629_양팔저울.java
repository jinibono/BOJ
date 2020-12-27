package DP;

import java.util.*;
import java.io.*;

public class boj_2629_양팔저울 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arrsum = 0;
		int[] cost = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			arrsum += cost[i];
		}
		boolean maked[] = new boolean[arrsum + 1];
		maked[0] = true;
		Queue<Integer> queue = new LinkedList<Integer>();

		int size = 0;
		int tempmax = cost[0]; // 이제까지 만들어진 조합

		for (int i = 0; i < N; i++) {
			for (int idx = 0; idx <= tempmax; idx++) {

				if (!maked[idx])
					continue;
				queue.add(Math.abs(cost[i] - idx));
				queue.add(idx + cost[i]);
				tempmax = Math.max(tempmax, idx + cost[i]);
			}
			while (!queue.isEmpty())
				maked[queue.poll()] = true;
//			System.out.println(Arrays.toString(maked));
//			System.out.println();
		}
		StringBuffer sb = new StringBuffer();
		int K = Integer.parseInt(br.readLine());
//		System.out.println(K);
		int idx = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			idx = Integer.parseInt(st.nextToken());
			if (idx < maked.length && maked[idx])
				sb.append("Y ");
			else
				sb.append("N ");
		}
		System.out.println(sb.toString());

	}

}
