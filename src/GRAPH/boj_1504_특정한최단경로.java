package GRAPH;

import java.util.*;
import java.io.*;

public class boj_1504_특정한최단경로 {
	static int N;
	static int M;
	static ArrayList<Edge> adj[];
	static int start, end;
	static int dist[];

	static class Edge {
		int index, weight;

		public Edge(int index, int weight) {
			super();
			this.index = index;
			this.weight = weight;
		}

		public Edge() {
			super();
			// TODO Auto-generated constructor stub
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		int u, v, w;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v, w));
			adj[v].add(new Edge(u, w));
		}
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int start1, start2;

		djik(1);
//		System.out.println(Arrays.toString(dist));
		start1 = dist[b];
		start2 = dist[c];
		if (start1 >= (int) 1e9 || start2 >= (int) 1e9) {
			System.out.println(-1);
			return;
		}

		djik(b);

		int b_to_c = dist[c];
		int b_to_end = dist[N];
		djik(c);

		int c_to_b = dist[b];
		int c_to_end = dist[N];
		int min = Math.min(start1 + b_to_c + c_to_end, start2 + c_to_b + b_to_end);

		System.out.println(min);

	}

	private static void djik(int s) {
		dist = new int[N + 1];
		for (int i = 1; i <= N; i++)
			dist[i] = (int) 1e9;
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o1.weight - o2.weight;
			}
		});
		dist[s] = 0;
		pq.add(new Edge(s, dist[s]));
		while (!pq.isEmpty()) {
			Edge p = pq.poll();
			for (Edge y : adj[p.index]) {
				if (dist[y.index] > dist[p.index] + y.weight) {
					dist[y.index] = dist[p.index] + y.weight;
					pq.add(new Edge(y.index, dist[y.index]));
				}
			}
		}
	}

}
