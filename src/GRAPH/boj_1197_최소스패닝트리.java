package GRAPH;

import java.io.*;
import java.util.*;

public class boj_1197_최소스패닝트리 {
	static int N;

	static int cnt;
	static int[] parent;

	static class node {
		int x, y, cost;

		public node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "node [x=" + x + ", y=" + y + ", cost=" + cost + "]";
		}
		

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<node> pq = new PriorityQueue<>(new Comparator<node>() {

			@Override
			public int compare(node o1, node o2) {
				// TODO Auto-generated method stub
				return o1.cost - o2.cost;
			}
		});
		int K;
		int a, b, cost;
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		cnt = 0;

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			pq.add(new node(a, b, cost));
		}
		while (!pq.isEmpty()) {
			node poll = pq.poll();
			int x = poll.x;
			int y = poll.y;
			cost = poll.cost;
//			System.out.println(poll);
			merge(x,y,cost);
//			for(int i=1;i<=N;i++)
//				System.out.print(parent[i]+" ");
//			System.out.println();
		}

		sb.append(cnt).append('\n');

		System.out.print(sb.toString());

	}

	public static int find(int u) {
		if (u == parent[u]) {
			return u;
		}
		return parent[u] = find(parent[u]); // 압축

		// return find(parent[u]) // 압축x
	}

	
	public static void merge(int u, int v, int cost) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		cnt += cost;
		parent[u] = v;
	}

	

}
