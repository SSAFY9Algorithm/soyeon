package 기출_문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main_백준_16934_게임닉네임_골드3_함소연_724ms {
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Character> list = new ArrayList<Character>();
	static Map<String, Integer> check = new HashMap<String, Integer>();
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		System.out.println(sb.toString());
	}
	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Trie trie = new Trie();
		String str = "";
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			if(check.get(str) == null) {
				check.put(str, 1);
			}else {
				check.put(str, check.get(str) + 1);
				sb.append(str+check.get(str)+"\n");
				continue;
			}
			trie.search(str);
			list.forEach( c -> { sb.append(c);});
			sb.append("\n");
			trie.insert(str);
			list.clear();
		}
	}
	static class Node{
		// 자식 노드 
		Map<Character, Node> childNodes = new HashMap<Character, Node>();
		// 단어의 끝 체크
		boolean endWord;
	}
	
	static class Trie{
		Node rootNode = new Node();
		
		// 문자열 저장
		void insert(String str) {
			Node node = this.rootNode;
			for (int i = 0; i < str.length(); i++) {
				node = node.childNodes.computeIfAbsent(str.charAt(i), key -> new Node());
			}
			node.endWord = true; // 단어의 끝을 명시
		}
		
		boolean search(String str) {
			Node node = this.rootNode;
			for (int i = 0; i < str.length(); i++) {
				node = node.childNodes.getOrDefault(str.charAt(i), null);
				list.add(str.charAt(i));
				if(node == null) {
					return false;
				}
			}
			
			return node.endWord;
		}
	}
}
