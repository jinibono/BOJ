package Backtracking;

import java.util.*;
import java.io.*;

public class boj_2023_신기한소수 {
	static int N;
	static TreeSet<Integer> set = new TreeSet<Integer>();
//	static boolean[] sosu = new boolean[100000001];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int a;
		int idx = 2;

		N = Integer.parseInt(br.readLine());
		dfs(2, 1, "2");
		dfs(3, 1, "3");
		dfs(5, 1, "5");
		dfs(7, 1, "7");
		for (Integer result : set) {
			sb.append(result).append('\n');
		}
		System.out.print(sb.toString());

	}

	private static void dfs(int i, int cnt, String str) {
		if (cnt == N) {

			if (isprime(Integer.parseInt(str)))
				set.add(Integer.parseInt(str));
			return;
		}
		if (!isprime(Integer.parseInt(str)))
			return;

		dfs(1, cnt + 1, str + "1");
		dfs(3, cnt + 1, str + "3");
		dfs(5, cnt + 1, str + "5");
		dfs(7, cnt + 1, str + "7");
		dfs(9, cnt + 1, str + "9");

	}

	static boolean isprime(int num) {
		boolean isPrime = true;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}

}
