package lab1;
import java.util.*;
import java.io.*;

public class derivative {
	public static void derivative(String infor,Vector<HashMap<Character,Integer>> exp){
		char c = infor.charAt(4);
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
		if(!GET){
			System.out.println("Error, no variable");
			return;
		}
		judge.check(exp);
		printAll.printAll(exp);
	}
}
