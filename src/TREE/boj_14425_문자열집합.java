package TREE;
import java.util.*;
import java.io.*;

public class boj_14425_문자열집합 {

	static class TreeNode {
		TreeNode[] child = new TreeNode[26];
		boolean isterminal;
	}

	static class tree {
		TreeNode root;

		public tree() {
			root = new TreeNode();
		}

		boolean insert(String key) {
			TreeNode cur = root;

			int idx;
			for (int i = 0; i < key.length(); i++) {
				idx = key.charAt(i) - 'a';
				if (cur.child[idx] == null)
					cur.child[idx] = new TreeNode();
				cur = cur.child[idx];

				
				if (i == key.length() - 1)
					cur.isterminal = true;

			}
			return true;
		}

		boolean find(String key) {
			TreeNode cur = root;
			boolean contain = true;
			int idx;
			for (int i = 0; i < key.length(); i++) {
				idx = key.charAt(i) - 'a';
				if (cur.child[idx] == null) {
					 contain = false;
					 break;
				}
				cur = cur.child[idx];
			}
//			System.out.println(key+" "+(contain&&cur.isterminal));
			return contain&&cur.isterminal;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String word = "";
		int ans = 0;
		tree Trie = new tree();
		for (int i = 0; i < N; i++) {
			word = br.readLine();
			Trie.insert(word);
		}
		for (int i = 0; i < M; i++) {
			word = br.readLine();
			if (Trie.find(word)) {
				ans++;
//				System.out.println(word);
			}
		}
		System.out.println(ans);

	}

}
