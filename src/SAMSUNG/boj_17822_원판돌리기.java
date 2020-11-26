package SAMSUNG;

import java.util.*;
import java.io.*;

public class boj_17822_원판돌리기 {
	static int[][] arr;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x, d, k;

			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			k %= M;

			if (d == 1) {
				k = M - k;
			}
			int start = x - 1;

			while (start <= N) {
				int temparr[] = new int[M];
				for (int idx = 0; idx < M; idx++) {
					temparr[(idx + k) % M] = arr[start][idx];
				}
				for (int idx = 0; idx < M; idx++) {
					arr[start][idx] = temparr[idx];
				}
				start += x;

			}

			del();
		}
		// Shift 완료

		int result = 0;
		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {
				result += arr[i][j];
				
			}
			
		}

		System.out.println(result);
	}

	static void del() {
		int tc = 0;
		boolean[][] check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (arr[i][j] == 0)
					continue;
				// 구역내 연산

				if (arr[i][j] == arr[i][(j + 1) % M]) {
					check[i][j] = true;
					check[i][(j + 1) % M] = true;
					tc++;
				}
				// 주변 구역 연산
				if (i < N - 1) {
					if (arr[i][j] == arr[i + 1][j]) {
						check[i][j] = true;
						check[i + 1][j] = true;
						tc++;
					}
				}

			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (check[i][j])
					arr[i][j] = 0;
			}
		}
		if(tc==0)
		av();

	}

	static void av() {
		int count = 0;
		int sum = 0;
		double av;
		av = 0.0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (arr[i][j] == 0)
					continue;
				count++;
				sum += arr[i][j];
			}

		}
		if (count > 0)
			av = sum / (double) count;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (arr[i][j] == 0)
					continue;
				if (av < arr[i][j])
					arr[i][j]--;
				else if (av > arr[i][j])
					arr[i][j]++;

			}
		}
	}

}
