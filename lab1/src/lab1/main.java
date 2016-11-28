package lab1;
import java.util.*;
import java.io.*;

import java.util.Scanner;
public class main {
	public static void main(String[] args) {
		Vector<HashMap<Character, Integer>> exp ,expre;
		exp = new Vector<HashMap<Character, Integer>>();
		expre = new Vector<HashMap<Character, Integer>>();
		@SuppressWarnings("resource")
		Scanner command = new Scanner(System.in);
		while (true) {
			String infor = command.nextLine();
			if (infor.charAt(0) != '!') {
				if (expression.expression(infor, exp) == 0) {
					printAll.printAll(exp);
				} else {
					continue;
				}
				copyExp(exp, expre);
			} else if (infor.length() >= 9 && infor.substring(0, 9).equals("!simplify")) {
				simplify.simplify(infor, exp);
				copyExp(expre, exp);
			} else if (infor.length() > 4 && infor.substring(0,4).equals("!d/d")){
				derivative.derivative(infor, exp);
				copyExp(expre, exp);
			} else {
				System.out.println("Error, no variable");
			}
		}
	}
	public static void copyExp(Vector<HashMap<Character, Integer>> a, Vector<HashMap<Character, Integer>> b) {
		b.removeAll(b);
		for (int i = 0; i < a.size(); i++){
			HashMap<Character, Integer> t = new HashMap<Character, Integer>();
			t = (HashMap) ((HashMap) a.get(i)).clone();
			b.addElement(t);
		}
		return;
	}
}
