package Sorting;

import java.util.Comparator;
import java.io.*;
import java.util.*;
public class boj_1931_회의실배정 {

	static class Meeting implements Comparable<Meeting>{
		public int starttime;
		public int endtime;
		
		public Meeting() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Meeting(int starttime, int endtime) {
			super();
			this.starttime = starttime;
			this.endtime = endtime;
		}
		@Override
		public int compareTo(Meeting o) {
			if(endtime==o.endtime)
				return starttime-o.starttime;
		
		return endtime-o.endtime;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Meeting> list = new ArrayList<Meeting>(N);
		for(int i=0;i<N;i++)
		{
			String readLine = br.readLine();
			String[] split = readLine.split(" ");
				list.add(new Meeting(Integer.parseInt(split[0]),Integer.parseInt(split[1])));
		}
		Collections.sort(list);
		int cnt = 0;
		int lt = 0;
		for(Meeting m : list)
		{
			if(m.starttime>= lt)
			{
				cnt++;
				lt = m.endtime;
			}
		}
		System.out.println(cnt);
		

	}

}

