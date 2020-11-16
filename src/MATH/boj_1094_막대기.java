package MATH;

import java.io.*;
import java.util.*;

public class boj_1094_막대기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = new String("");
		for (int i = 6; i >= 0; i--) {
			if (Math.pow(2, i) <= N) {
				str += '1';
				N -= Math.pow(2, i);
			} else {
				str += '0';
			}
		}
		str += "";
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)=='1')
			count++;
		}
//		System.out.println(str);
		System.out.println(count);

	}

}
