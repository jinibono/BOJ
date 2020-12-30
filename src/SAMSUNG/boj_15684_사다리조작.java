package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_15684_사다리조작 {

	static int N, M, H;
	static int a, b;
	static boolean[][] curve;
	static int min = 5;
	static ArrayList<point> list = new ArrayList<point>();

	static class point {
		int x, y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public point() {
			super();
			// TODO Auto-generated constructor stub
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		curve = new boolean[H + 1][N + 2];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			curve[a][b] = true;
		}
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				if (curve[i][j] || curve[i][j - 1] || curve[i][j + 1])
					continue;
				if (j < N)
					list.add(new point(i, j));
			}
		}
		int[] arr = new int[list.size()];

		for (int i = 0; i < arr.length; i++)
			arr[i] = i;

		int len = Math.min(arr.length, 3);
		for (int i = 0; i <= len; i++) {
			comb(arr, new int[i], 0, 0);
			if (min != 5)
				break;
		}
		if (min != 5)
			System.out.println(min);
		else
			System.out.println(-1);
	}

	static void comb(int[] arr, int[] sel, int N, int K) {
		if (sel.length == K) {
			process(sel);
			return;

		}
		if (arr.length == N)
			return;
		sel[K] = arr[N];
		comb(arr, sel, N + 1, K + 1);
		comb(arr, sel, N + 1, K);

	}

	private static void process(int[] sel) {
		boolean copy[][] = new boolean[H + 1][N + 2];
//		System.out.println(Arrays.toString(sel));
		boolean pos = true;
//		System.out.println("호출");
		for (int i = 0; i < sel.length; i++) {
			point point = list.get(sel[i]);
			copy[point.x][point.y] = true;
//			System.out.println(point.x + " " + point.y + "에 추가");
		}

		for (int i = 1; i <= H; i++) {
			if (!pos)
				break;
			for (int j = 1; j <= N; j++) {

				if (copy[i][j] && copy[i][j - 1]) {
					pos = false;
					break;
				}
				if (copy[i][j])
					continue;
				copy[i][j] = curve[i][j];
				if (copy[i][j] && copy[i][j - 1]) {
					pos = false;
					break;
				}
			}
		}
//		System.out.println(copy[1][1]);
//		System.out.println(copy[2][1]);
		if (!pos)
			return;
		boolean alpos = true;
		int sx = 0;
		int sy = 0;

		for (int i = 1; i <= N; i++) {
			sx = 0;
			sy = i;
//			System.out.println(i+"시작");
			while (sx <= H) {
				if (copy[sx][sy]) {
					sx = sx + 1;
					sy = sy + 1;
				} else if (copy[sx][sy - 1]) {
					sx = sx + 1;
					sy = sy - 1;
				} else {
					sx = sx + 1;
					sy = sy;
				}
//				System.out.println("중간 : "+sx+" "+sy);
			}
//			System.out.println(sy+" 도착");
			if (sy != i) {
				alpos = false;
				break;
			}

		}
		if (alpos) {
			min = sel.length;
			return;
		}

	}

}
