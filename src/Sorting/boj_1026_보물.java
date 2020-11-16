package Sorting;
import java.util.*;
import java.io.*;
public class boj_1026_보물 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> min = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
		});
		PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
		{
			min.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
		{
			max.add(Integer.parseInt(st.nextToken()));
		}
		int sum=0;
		for(int i=0;i<N;i++)
		{
			sum+=max.poll()*min.poll();
		}
		System.out.println(sum);
	}

}
