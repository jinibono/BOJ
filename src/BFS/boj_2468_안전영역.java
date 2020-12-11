package BFS;

import java.io.*;
import java.util.*;

public class boj_2468_안전영역 {

	static int std; // 기준
	static int N;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				std = Math.max(std, map[i][j] = Integer.parseInt(st.nextToken()));
		}
		System.out.println(std);

	}

}
