package DP;

import java.util.*;
import java.io.*;

public class boj_1315_RPG {
	static int N;
	static int[] STR;
	static int[] INT;
	static int[] PNT;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			STR[i] = Integer.parseInt(st.nextToken());
			INT[i] = Integer.parseInt(st.nextToken());
			PNT[i] = Integer.parseInt(st.nextToken());
		}

	}
	// 하나는 힘(STR)이고, 다른 하나는 지력(INT)이다. 캐릭터를 생성했을 때, 두 스탯은 모두 1이다.

}
