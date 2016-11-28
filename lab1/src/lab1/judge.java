package lab1;
import java.util.*;
import java.io.*;

public class judge {
	public static void check(Vector<HashMap<Character, Integer>> v) {
		int sum = 0;
		for (int i = 0; i < v.size(); i++) {
			if (v.get(i).size() == 1 && v.get(i).get(' ') != null) {
				sum = sum + (int) v.get(i).get(' ');
				v.remove(i);
				i = i - 1;
			}
		}
		
		if (sum != 0) {
			HashMap<Character, Integer> SUM = new HashMap<Character, Integer>();
			SUM.put(' ', sum);
			v.add(SUM);
		}
	}
	public static int Right(char x){
		int flag=0;
		if (x >= '0' && x <= '9' || x >= 'a' && x <= 'z' 
				|| x == '+' || x == '*' || x == ' ' || x == '\t') {
			flag = 1;
		} else {
			flag = 0;
		}
		return flag;
	}

}
