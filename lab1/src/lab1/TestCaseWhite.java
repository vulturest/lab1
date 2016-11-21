package lab1;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Vector;

import org.junit.Test;

public class TestCaseWhite {

	@Test
	public void test1() {
			HashMap<Character,Integer> Term=new HashMap<Character,Integer>();
			Vector<HashMap<Character, Integer>> exp;
			exp = new Vector<HashMap<Character, Integer>>();
			String infor="x";
			Term.put(' ', 1);
			Term.put(infor.charAt(0), 2);
			exp.addElement(Term);
			lab1 testexp = new lab1();
			testexp.derivative("!d/dx", exp);			
	}
	@Test
	public void test2() {
			HashMap<Character,Integer> Term1=new HashMap<Character,Integer>();
			HashMap<Character,Integer> Term2=new HashMap<Character,Integer>();
			Vector<HashMap<Character, Integer>> exp;
			exp = new Vector<HashMap<Character, Integer>>();
			String infor="x";
			Term1.put(' ', 3);
			Term2.put(' ', 1);
			Term2.put(infor.charAt(0), 2);
			exp.addElement(Term1);
			exp.addElement(Term2);
			lab1 testexp = new lab1();
			testexp.derivative("!d/dx", exp);			
	}	
	@Test
	public void test3() {
			HashMap<Character,Integer> Term1=new HashMap<Character,Integer>();
			HashMap<Character,Integer> Term2=new HashMap<Character,Integer>();
			Vector<HashMap<Character, Integer>> exp;
			exp = new Vector<HashMap<Character, Integer>>();
			String infor="x";
			Term1.put(' ', 3);
			Term2.put(' ', 1);
			Term2.put(infor.charAt(0), 1);
			exp.addElement(Term1);
			exp.addElement(Term2);
			lab1 testexp = new lab1();
			testexp.derivative("!d/dx", exp);			
	}	
	@Test
	public void test4() {
			HashMap<Character,Integer> Term1=new HashMap<Character,Integer>();
			HashMap<Character,Integer> Term2=new HashMap<Character,Integer>();
			Vector<HashMap<Character, Integer>> exp;
			exp = new Vector<HashMap<Character, Integer>>();
			String infor="x";
			Term1.put(' ', 3);
			Term2.put(' ', 1);
			Term2.put(infor.charAt(0), 2);
			exp.addElement(Term1);
			exp.addElement(Term2);
			lab1 testexp = new lab1();
			testexp.derivative("!d/da", exp);			
	}	

}
