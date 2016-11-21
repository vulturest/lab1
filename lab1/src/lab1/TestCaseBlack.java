package lab1;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Vector;

import org.junit.Test;

public class TestCaseBlack {

	@Test
	public void test1() {
			Vector<HashMap<Character, Integer>> exp;
			exp = new Vector<HashMap<Character, Integer>>();
			lab1 testexp = new lab1();
			testexp.expression("x+x",exp);
			testexp.printAll(exp);				
	}
	@Test
	public void test2() {
			Vector<HashMap<Character, Integer>> exp;
			exp = new Vector<HashMap<Character, Integer>>();
			lab1 testexp = new lab1();
			testexp.expression("3*x*y",exp);
			testexp.printAll(exp);				
	}
	@Test
	public void test3() {
			Vector<HashMap<Character, Integer>> exp;
			exp = new Vector<HashMap<Character, Integer>>();
			lab1 testexp = new lab1();
			testexp.expression("4xy+5z",exp);
			testexp.printAll(exp);				
	}
	@Test
	public void test4() {
			Vector<HashMap<Character, Integer>> exp;
			exp = new Vector<HashMap<Character, Integer>>();
			lab1 testexp = new lab1();
			testexp.expression("4x^4+3",exp);
			testexp.printAll(exp);				
	}
}

