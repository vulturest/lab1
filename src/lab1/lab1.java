package lab1;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;
public class lab1{
	public static void main(String[] args) {
		String copy="";
		Vector<HashMap<Character,Integer>> exp=new Vector<HashMap<Character,Integer>>();
		Scanner command=new Scanner(System.in);
		while(true){
			String infor=command.nextLine();
			if(infor.charAt(0)!='!'){
				copy=infor;
				if(expression(infor,exp)==0){
					PrintAll(exp);
				}
				else
					continue;
			}
			else if(infor.length()>=9 && infor.substring(0,9).equals("!simplify")){
				simplify(infor,exp,copy);
			}
			else if(infor.length()>4 && infor.substring(0,4).equals("!d/d")){
				derivative(infor,exp,copy);
			}
			else{
				System.out.println("Error, no variable");
			}
		}
	}
	public static void PrintAll(Vector<HashMap<Character,Integer>> V){
		for(int i=0;i<V.size();i++){
			Iterator iter = V.get(i).entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if(key.equals(' ')){
					System.out.print(val);
				}
				else{
					for(int n=0;n<(int)val;n++){
						System.out.print("*"+key);
					}
				}
			}
			if(i!=V.size()-1){
				System.out.print("+");
			}
		}
		System.out.println("");
	}
	public static void Check(Vector<HashMap<Character,Integer>> v){
		int sum=0;
		for(int i=0;i<v.size();i++){
			if(v.get(i).size()==1 && v.get(i).get(' ')!=null){
				sum=sum+(int)v.get(i).get(' ');
				v.remove(i);
				i=i-1;
			}
		}
		HashMap<Character,Integer> SUM=new HashMap<Character,Integer>();
		if(sum!=0){
			SUM.put(' ', sum);
			v.add(SUM);
		}
	}
	public static int Right(char x){
		if(x>='0' && x<='9' || x>='a' &&x<='z' || x=='+' ||x=='*' ||x==' ' ||x=='\t')
			return 1;
		else
			return 0;
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
					if(temp==0){
						NumTemp=0;
					}
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
	public static void simplify(String infor,Vector<HashMap<Character,Integer>> exp,String stcopy){
		Vector<HashMap<Character,Integer>> expClone=new Vector<HashMap<Character,Integer>>();
		expClone=(Vector)exp.clone();
		if(infor.length()==9){
			PrintAll(exp);
		}
		else{
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
						if(expClone.get(j).get(x)!=null){
							//GET=true;
							mi=exp.get(j).get(x);
							for(int n=0;n<mi;n++){
								expClone.get(j).put(' ',expClone.get(j).get(' ')*value);
							}
							expClone.get(j).remove(x);
						}
					}
					value=0;mi=0;item=0;
				}
			}
			Check(expClone);
			PrintAll(expClone);
			expression(stcopy,exp);
		}
	}
	public static void derivative(String infor,Vector<HashMap<Character,Integer>> exp,String stcopy){
		char c=infor.charAt(4);
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
		Check(exp);
		PrintAll(exp);
		expression(stcopy,exp);
	}
}