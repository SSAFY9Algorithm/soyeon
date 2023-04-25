package 코테기출3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_프로그래머스_메뉴리뉴얼_레벨2 {
	static Map<String, Integer> map;
	static int max = 0;
	static List<String> list = new ArrayList<>();
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		solution(orders, course);
	}
	    public static String[] solution(String[] orders, int[] course) {
	    	
	    	for (int i = 0; i < course.length; i++) {
	    		map = new HashMap<>();
	    		max = 0;
	    		for (int j = 0; j < orders.length; j++) {
	    			char[] ch = orders[j].toCharArray();
	    			Arrays.sort(ch);
	    			orders[j] = new String(ch);
	    			comb(orders[j], course[i], "", 0, 0);
	    		}
	    		
	    		for (String key : map.keySet()) {
	    			max=Math.max(max, map.get(key));
	    		}
	    		
	    		
	    		for (String key : map.keySet()) {
	    			if(map.get(key) > 1 && map.get(key) == max) {
	    				list.add(key);
	    			}
	    		}
			}
	    	
	    	Collections.sort(list);
	        String[] answer = list.toArray(new String[list.size()]);
	        return answer;
	    }

		private static void comb(String order, int end, String temp, int idx, int start) {
			if(idx == end) {
				map.put(temp, map.getOrDefault(temp, 0)+1);
				return;
			}
			
			for (int i = start; i < order.length(); i++) {
				comb(order, end, temp+order.charAt(i), idx+1, i+1);
			}
		}
}