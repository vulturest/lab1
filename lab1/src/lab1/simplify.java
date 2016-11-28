package lab1;
import java.util.*;
import java.io.*;

public class simplify {
	public static void simplify(String infor,Vector<HashMap<Character,Integer>> exp){
		if(infor.length()==9){
			printAll.printAll(exp);
		} else {
			char x=infor.charAt(10);
			int value=0;
			
			for(int i=10;i<infor.length();i++){
				int mi=0;
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
					value=0;mi=0;
				}
			}
			judge.check(exp);
			printAll.printAll(exp);
		}
	}

}
