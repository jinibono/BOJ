package SegMentTree;

import java.util.*;
import java.io.*;

public class boj_11505_구간곱구하기 {
	static long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] arr = new long[N];
		for (int i = 0; i < N; i++)
			arr[i] = Long.parseLong(br.readLine());
		int size = (int) Math.sqrt(N) + 1;
		size = size * size * 2 + 1;
		long tree[] = new long[4000000];
		init(tree, arr, 1, 1, N);
		int a = 0;
		int b = 0;
		int c = 0;

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				long diff = (long) (c - arr[b - 1]);
				update(tree, 1, N, 1, b, c);
				arr[b - 1] = c;

			} else if (a == 2) {
				System.out.println(search(tree, 1, 1, N, b, c));
			}
		}

	}

	private static long search(long[] tree, int node, int start, int end, int left, int right) {

		if (left > end || right < start) {
			return 1;
		}

		// 범위 안에 있는 경우
		if (left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		return ((search(tree, node * 2, start, mid, left, right))
				* (search(tree, node * 2 + 1, mid + 1, end, left, right))) % MOD;

	}

	private static long init(long[] tree, long[] copylist, int node, int start, int end) {
		if (start == end)
			return tree[node] = copylist[start - 1];
		else {
			int mid = (start + end) / 2;
			return tree[node] = (init(tree, copylist, node * 2, start, mid)
					* init(tree, copylist, node * 2 + 1, mid + 1, end)) % MOD;
		}

	}

	public static long update(long[] tree, int start, int end, int node, int idx, long val) {
		// arr[idx]를 x라 하자.
		// tree에서 값이 x인 인덱스를 target이라고 할 때,
		// target과 연결된 가지 부분을 전체 갱신해야 함.

		// 범위 밖에 있는 경우
		if (idx < start || idx > end) {
			return tree[node];
		}

		// 리프 노드 업데이트
		if (start == end) {
			return tree[node] = val;
		}

		int mid = (start + end) / 2;
		return tree[node] = (update(tree,start, mid, node * 2, idx, val) * update(tree,mid + 1, end, node * 2 + 1, idx, val))
				% MOD;
	}

}
