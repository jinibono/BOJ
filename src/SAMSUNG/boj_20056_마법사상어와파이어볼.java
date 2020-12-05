package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_20056_마법사상어와파이어볼 {
	static int N, M, K;

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class ball {
		int x, y, m, s, d;

		public ball(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		public ball() {
			super();
			// TODO Auto-generated constructor stub
		}

		public void move() {
			x = this.x + dr[d] * s;
			y = this.y + dr[d] * s;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<ball> list = new ArrayList<>();
		ArrayList<ball> temp = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r, c, m, s, d;
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			list.add(new ball(r, c, m, s, d));
		}
		while (K > 0) {

			for (int i = 0; i < list.size(); i++)
				list.get(i).move();
			Collections.sort(list, new Comparator<ball>() {

				@Override
				public int compare(ball o1, ball o2) {
					// TODO Auto-generated method stub
					return o1.x - o2.x;
				}
			});
			
			//이동 후 정렬이 다 되어있음

			K--;
		}
	}

}
