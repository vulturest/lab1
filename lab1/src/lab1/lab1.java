package lab1;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;

/**
 * the code of lab1.
 * @author tmy
 */
public class lab1 {
	public static void copyExp(Vector<HashMap<Character, Integer>> a, Vector<HashMap<Character, Integer>> b) {
		b.removeAll(b);
		for (int i = 0; i < a.size(); i++){
			HashMap<Character, Integer> t = new HashMap<Character, Integer>();
			t = (HashMap) ((HashMap) a.get(i)).clone();
			b.addElement(t);
		}
		return;
	}
	public static void main(String[] args) {
		String copy = "";
		Vector<HashMap<Character, Integer>> exp, expre;
		exp = new Vector<HashMap<Character, Integer>>();
		@SuppressWarnings("resource")
		Scanner command = new Scanner(System.in);
		
		expre = new Vector<HashMap<Character, Integer>>();
		while (true) {
			String infor = command.nextLine();
			if (infor.charAt(0) != '!') {
				copy = infor;
				if (expression(infor, exp) == 0) {					
					printAll(exp);
				} else {
					continue;
				}
				copyExp(exp, expre);
			} else if (infor.length() >= 9 && infor.substring(0, 9).equals("!simplify")) {				
				simplify(infor, exp);
				copyExp(expre, exp);
			} else if (infor.length() > 4 && infor.substring(0,4).equals("!d/d")){
				derivative(infor, exp);
				copyExp(expre, exp);
			} else {
				System.out.println("Error, no variable");
			}
		}
	}
	/**
	 * print all the item in V.
	 * @param V the vector of expression
	 */
	public static void printAll(Vector<HashMap<Character, Integer>> V) {
		int flag = 0;
		for (int i = 0; i < V.size(); i++) {
			Iterator iter = V.get(i).entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (key.equals(' ')) {
					if (val.equals(1)) {
						flag = 1;
					} else {	
						System.out.print(val);
					}
				} else {
					for (int n = 0; n < (int) val; n++) {
						if (flag == 0) {
							System.out.print("*" + key);
						} else {
							System.out.print(key);
						}
						flag = 0;
					}
				}
			}
			if (i != V.size() - 1) {
				System.out.print("+");
			}
		}
		System.out.println("");
	}
	/**
	 * to change the expression to the default style. 
	 * @param v vector
	 */
	public static void check(Vector<HashMap<Character, Integer>> v) {
		int sum = 0;
		for (int i = 0; i < v.size(); i++) {
			if (v.get(i).size() == 1 && v.get(i).get(' ') != null) {
				sum = sum + (int) v.get(i).get(' ');
				v.remove(i);
				i = i - 1;
			}
		}
		HashMap<Character, Integer> SUM = new HashMap<Character, Integer>();
		if (sum != 0) {
			SUM.put(' ', sum);
			v.add(SUM);
		}
	}
	/**
	 * Judge whether x is in the input char set(0~9,a~z,+*). 
	 * @param x char
	 * @return 1 or 0
	 */
	public static int Right(char x){
		if (x >= '0' && x <= '9' || x >= 'a' && x <= 'z' 
				|| x == '+' || x == '*' || x == ' ' || x == '\t') {
			return 1;
		} else {
			return 0;
		}
	}
 	public static int expression(String infor,Vector<HashMap<Character,Integer>> V){
		int NumTemp=1;
		int temp=0;
		boolean NumChange=false;
		HashMap<Character,Integer> Term=new HashMap<Character,Integer>();
		V.removeAll(V);
		for(int i=0;i<infor.length();i++){
			if(Right(infor.charAt(i))==0){
				System.out.println("Error, no variable");
				return -1;
			}
			if(infor.charAt(i)==' ' || infor.charAt(i)=='\t') continue;
			if(infor.charAt(i)>=48 && infor.charAt(i)<=57){
				temp=temp*10+infor.charAt(i)-'0';
				NumChange=true;
			}
			else if(infor.charAt(i)=='*'){
				if(temp!=0){
					NumTemp=NumTemp*temp;
					NumChange=true;
					temp=0;
				}
			}
			else if(infor.charAt(i)=='+'){
				if(temp!=0){
					NumTemp=NumTemp*temp;
					NumChange=true;
				}
				if(NumChange==true){
					Term.put(' ', NumTemp);
				}
				else
					Term.put(' ',1);
				V.addElement(Term);
				Term=new HashMap<Character,Integer>();
				NumTemp=1;
				temp=0;
				NumChange=false;
			}
			else{
				if(Term.get(infor.charAt(i))!=null){
					Term.put(infor.charAt(i), Integer.valueOf(Term.get(infor.charAt(i))+1));
				}
				else{
					Term.put(infor.charAt(i), 1);
				}
			}
		}
		if(temp!=0){
			NumTemp=NumTemp*temp;
		}
		Term.put(' ', NumTemp);
		V.addElement(Term);
		return 0;
	}
	public static void simplify(String infor,Vector<HashMap<Character,Integer>> exp){
		if(infor.length()==9){
			printAll(exp);
		} else {
			char x=infor.charAt(10);
			int value=0;
			int mi=0;
			int item=0;
			for(int i=10;i<infor.length();i++){
				if(i<infor.length()-1 && (infor.charAt(i)<48 || infor.charAt(i)>57)&& infor.charAt(i+1)=='='){
					x=infor.charAt(i);
					continue;
				}
				if(infor.charAt(i)>=48 && infor.charAt(i)<=57){
					value=value*10+infor.charAt(i)-'0';
				}
				if(infor.charAt(i)==' ' || i==infor.length()-1){
					for(int j=0;j<exp.size();j++){
						if(exp.get(j).get(x)!=null){
							//GET=true;
							mi=exp.get(j).get(x);
							for(int n=0;n<mi;n++){
								exp.get(j).put(' ',exp.get(j).get(' ')*value);
							}
							exp.get(j).remove(x);
						}
					}
					value=0;mi=0;item=0;
				}
			}
			check(exp);
			printAll(exp);
		}
	}
	public static void derivative(String infor,Vector<HashMap<Character,Integer>> exp){
		char c = infor.charAt(5);
		Boolean GET=false;
		for(int i=0;i<exp.size();i++){
			
			if(exp.get(i).size()==1){
				exp.remove(i);
				i--;
			}
			else if(exp.get(i).get(c)==null){
				exp.remove(i);
				i--;
				continue;
			}
			else{
				GET=true;
				exp.get(i).put(' ', exp.get(i).get(' ')*exp.get(i).get(c));
				exp.get(i).put(c, exp.get(i).get(c)-1);
				if(exp.get(i).get(c)==0){
					exp.get(i).remove(c);
				}
			}
		}
		if(GET==false){
			System.out.println("Error, no variable");
			return;
		}
		check(exp);
		printAll(exp);
	}
}
