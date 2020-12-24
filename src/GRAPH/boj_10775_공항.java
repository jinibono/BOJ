package GRAPH;

import java.io.*;
import java.util.*;

public class boj_10775_공항 {
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}
		int ans = 0;
		for (int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			int emptyGate = find(g);

			if (emptyGate == 0) {
				break;
			}

			ans++;
			merge(emptyGate, emptyGate - 1);
		}
		System.out.println(ans);
	}

	public static int find(int u) {
		if (u == parent[u]) {
			return u;
		}
		return parent[u] = find(parent[u]);

	}

	public static void merge(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}

		parent[u] = v;

	}
}
