package DP;

import java.util.*;
import java.io.*;

public class boj_2011_암호코드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		code = " " + code;
		int[] sum = new int[5002];
		int DIV = 1000000;
		sum[0] = 1;
		for (int i = 1; i < code.length(); i++) {
			if (code.charAt(i) - '0' >= 1)
				sum[i] = (sum[i] + sum[i - 1]) % DIV;

			int temp = (code.charAt(i - 1) - '0') * 10 + (code.charAt(i) - '0');
			if (i - 2 >= 0)
				if (10 <= temp && temp < 27)
					sum[i] = (sum[i] + sum[i - 2]) % DIV;
		}
		System.out.println(sum[code.length() - 1]);

	}

}
