package SegMentTree;

import java.util.*;
import java.io.*;

public class boj_2042_구간합구하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] arr = new long[(int) N];
		for (int i = 0; i < N; i++)
			arr[i] = Long.parseLong(br.readLine());
		int size = (int) Math.sqrt(N) + 1;
		size = size * size * 2 + 1;
		long tree[] = new long[size * 4];
		init(tree, arr, 1, 1, N);
		long a = 0;
		long b = 0;
		long c = 0;

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Long.parseLong(st.nextToken());
			b = Long.parseLong(st.nextToken());
			c = Long.parseLong(st.nextToken());
			if (a == 1) {
				long diff = (long) (c - arr[(int) (b - 1)]);
				update(tree, 1, 1, N, b, diff);
				arr[(int) (b - 1)] = c;

			} else if (a == 2) {
				System.out.println(search(tree, 1, 1, N, b, c));
			}
		}

	}

	private static long search(long[] tree, int node, long start, long n, long left, long right) {
		if (left == start && right == n)
			return tree[node];
		else {
			long mid = (start + n) / 2;
			// mid가 5일떄 //3~5 등 둘다 왼쪽범위면
			if (right <= mid) {
				return search(tree, node * 2, start, mid, left, right);
			} else if (left > mid) {
				// 오른쪽 범위만 탐색하면됨
				return search(tree, node * 2 + 1, mid + 1, n, left, right);
			} else {
				// mid가 5인데 3~7같은 애를 찾고싶다?
				// 3~5 6~7을 통합시켜야댐
				return search(tree, node * 2, start, mid, left, mid)
						+ search(tree, node * 2 + 1, mid + 1, n, mid + 1, right);
			}
		}

	}

	private static long init(long[] tree, long[] copylist, int node, long start, long end) {
		if (start == end)
			return tree[node] = copylist[(int) (start - 1)];
		else {
			long mid = (start + end) / 2;
			return tree[node] = init(tree, copylist, node * 2, start, mid)
					+ init(tree, copylist, node * 2 + 1, mid + 1, end);
		}

	}

	static void update(long[] tree, int node, long start, long end, long b, long diff) {

		tree[node] += diff;

		long mid = (start + end) / 2;
		if (end == b && start == b)
			return;
		if (b <= mid)
			update(tree, node * 2, start, mid, b, diff);
		else
			update(tree, node * 2 + 1, mid + 1, end, b, diff);
	}

}
