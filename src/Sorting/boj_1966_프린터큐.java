package Sorting;

import java.util.*;
import java.io.*;

public class boj_1966_프린터큐 {

	static class point {
		int num, idx;

		public point(int num, int idx) {
			super();
			this.num = num;
			this.idx = idx;
		}

		public point() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "point [num=" + num + ", idx=" + idx + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int size = 0;
		int idx = 0;
		for (int tc = 1; tc <= T; tc++) {
			int count = 0;
			int max = 0;
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			idx = Integer.parseInt(st.nextToken());
			Queue<point> queue = new LinkedList();
			PriorityQueue<point> maxq = new PriorityQueue<>(new Comparator<point>() {

				@Override
				public int compare(point o1, point o2) {

					return o2.num - o1.num;
				}
			});
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < size; i++) {
				int x = Integer.parseInt(st.nextToken());
				max = Math.max(max, x);
				queue.add(new point(x, i));
				maxq.add(new point(x, i));
			}
			max = maxq.poll().num;
			while (!queue.isEmpty()) {
				point poll = queue.poll();
				int num = poll.num;
				int id = poll.idx;
//				System.out.println(poll+" "+"idx : "+idx +" max : "+max);
				if (num < max) {
					queue.add(new point(num, id));
					continue;
				}
				if (id == idx) {
					count++;
					break;
				}
				if (num == max) {
					count++;
					max = maxq.poll().num;
					continue;
				}
			}
			sb.append(count).append('\n');
			queue.clear();
//			System.out.println(count);
		}
		System.out.print(sb.toString());
	}

}
