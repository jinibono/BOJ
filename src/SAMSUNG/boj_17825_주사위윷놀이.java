package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_17825_주사위윷놀이 {
	static int cnt = 0;
	static ArrayList[] list = new ArrayList[50];
	static int[] dice;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dice = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++)
			dice[i] = Integer.parseInt(st.nextToken());
		list[0] = new ArrayList<Integer>();
		list[10] = new ArrayList<Integer>();
		list[20] = new ArrayList<Integer>();
		list[25] = new ArrayList<Integer>();
		list[30] = new ArrayList<Integer>();
		for (int i = 0; i <= 40; i = i + 2)
			list[0].add(i);
		list[10].add(10);
		list[10].add(13);
		list[10].add(16);
		list[10].add(19);

		list[30].add(30);
		list[30].add(28);
		list[30].add(27);
		list[30].add(26);

		list[20].add(20);
		list[20].add(22);
		list[20].add(24);

		list[25].add(25);
		list[25].add(30);
		list[25].add(35);
		list[25].add(40);

		list[10].addAll(list[25]);
		list[30].addAll(list[25]);
		list[20].addAll(list[25]);

		for (int i = 1; i <= 4; i++)
			dfs(i, 0, new int[10]);

		System.out.println(result);
//		System.out.println(cnt);
	}

	private static void dfs(int num, int stage, int[] array) {
		array[stage] = num;
		if (stage == 9) {
			result = Math.max(result, process(array));

			return;
		}
		for (int i = 1; i <= 4; i++)
			dfs(i, stage + 1, array);

	}

	static int process(int[] array) {
		int[][] map = new int[51][2];
		int[] shortcut = new int[5];
		int[] point = new int[5];
		int[] depth = new int[5];
		int sum = 0;

		for (int i = 0; i < array.length; i++) {
			if (!move(array[i], map, point, shortcut, dice[i], depth))
				return 0;
			if (point[array[i]] == 50)
				continue; // 도착한 경우에는 점수를 합산하지 않음
			sum += point[array[i]];
		}
		return sum;

	}

	static boolean move(int dicenum, int[][] map, int[] point, int[] shortcut, int len, int[] depth) {
		// map 현재 위치에 누가 있는지
		// point 다이스가 어디에 있는지
		boolean canmove = false;
		int cur = point[dicenum];
		if (shortcut[dicenum] == 0) { // 내가 지름길이 아닌데 지름길인 숫자를 방문하면
			if (point[dicenum] == 10 || point[dicenum] == 20 || point[dicenum] == 30) {
				shortcut[dicenum] = point[dicenum];
				depth[dicenum] = 0;
			}
		}
		int bit = 0;
		if (shortcut[dicenum] > 0)
			bit = 1;
		// bit 가 1이면 나는 현재 지름길 루트에 있단 소리다
		if (cur == 50)
			return false;
		// 내가 이미 도착한 말이면 더이상 이동x
		map[cur][bit] = 0;
		if (cur == 40) { // 내가 만약에 40위치에 있으면 무조건 도착할 것이다
			map[cur][0] = 0;
			map[cur][1] = 0;
			point[dicenum] = 50;// 도착
			return true; // 40
		}

		int idx = depth[dicenum];
		// 현재 list의 idx 위치에 있다.

		if (idx + len >= list[shortcut[dicenum]].size()) {
			canmove = true;

			point[dicenum] = 50;// 도착
		} else {
			int next = (int) list[shortcut[dicenum]].get(idx + len);
			// 다음에 next를 방문할건데
			if (map[next][bit] != 0) // bit 0 1 지름길의 30 -> 30 1 일반루트 30 -> 30 0
				return false; // 가려는 위치에 누가 있으면 못간다
			if (next == 40) {
				if (map[next][0] != 0 || map[next][1] != 0)
					return false;
			}
			// 갈 수 있으면 현 위치를 0으로 바꾼다
			canmove = true;
			if (bit == 0) {
				map[cur][0] = 0;

			} else// bit ==1
			{
				if (idx == 0)
					map[cur][0] = 0;
				else
					map[cur][bit] = 0;
			}

			map[next][bit] = dicenum;
			depth[dicenum] += len;
			point[dicenum] = next;

		}

		return canmove;
	}

}
