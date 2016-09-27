package lab1;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;
public class lab1 {
	public static void main(String[] args) {
		String copy="";
		Vector<HashMap<Character,Integer>> exp=new Vector<HashMap<Character,Integer>>();
		Scanner command=new Scanner(System.in);
		while(true){
			String infor=command.nextLine();
			if(infor.charAt(0)!='!'){
				copy=infor;
				if(expression(infor,exp)==0){
					Print(exp);
				}
				else
					continue;
			}
			else if(infor.substring(1,4).equals("d/d")){
				derivative(infor,exp,copy);
			}
			else if(infor.substring(1,9).equals("simplify")){
				simplify(infor,exp,copy);
			}
			else{
				System.out.println("Error, no variable");
			}
		}
	}
 	public static int expression(String infor,Vector<HashMap<Character,Integer>> poly){
		int NumTemp=1;
		int temp=0;
		boolean Changeflag=false;
		HashMap<Character,Integer> Term=new HashMap<Character,Integer>();
		poly.removeAll(poly);
		for(int i=0;i<infor.length();i++){
			if(!(infor.charAt(i)>='0' && infor.charAt(i)<='9' || infor.charAt(i)>='a' && infor.charAt(i)<='z' || infor.charAt(i)=='+' ||infor.charAt(i)=='*' ||infor.charAt(i)==' ' ||infor.charAt(i)=='\t')){
				System.out.println("Error, no variable");
				return -1;
			}
			if(infor.charAt(i)==' ' || infor.charAt(i)=='\t') continue;
			if(infor.charAt(i)>=48 && infor.charAt(i)<=57){
				temp=temp*10+infor.charAt(i)-'0';
			}
			else if(infor.charAt(i)=='*'){
				if(temp!=0){
					NumTemp=NumTemp*temp;
					Changeflag=true;
					temp=0;
				}
			}
			else if(infor.charAt(i)=='+'){
				if(temp!=0){
					NumTemp=NumTemp*temp;
					Changeflag=true;
				}
				if(Changeflag==true)
					Term.put(' ', NumTemp);
				else
					Term.put(' ',0);
				poly.addElement(Term);
				Term=new HashMap<Character,Integer>();
				NumTemp=1;
				temp=0;
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
		poly.addElement(Term);
		return 0;
	}
	public static void simplify(String infor,Vector<HashMap<Character,Integer>> exp,String inforcopy){
		Vector<HashMap<Character,Integer>> expClone=new Vector<HashMap<Character,Integer>>();
		expClone=(Vector)exp.clone();
		if(infor.length()==9){
			Print(exp);
		}
		else{
			char x=infor.charAt(10);
			int value=0;
			int mi=0;
			int item=0;
			for(int i=10;i<infor.length();i++){
				if(i<infor.length()-1 && infor.charAt(i)<48 && infor.charAt(i)>57 && infor.charAt(i+1)=='='){
					x=infor.charAt(i);
					continue;
				}
				if(infor.charAt(i)>=48 && infor.charAt(i)<=57){
					value=value*10+infor.charAt(i)-'0';
				}
				if(infor.charAt(i)==' ' || i==infor.length()-1){
					for(int j=0;j<exp.size();j++){
						if(expClone.get(j).get(x)!=null){
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
			Print(expClone);
			expression(inforcopy,exp);
		}
	}
	public static void derivative(String infor,Vector<HashMap<Character,Integer>> exp,String inforcopy){
		char var=infor.charAt(4);
		boolean flag=false;
		for(int i=0;i<exp.size();i++){
			if(exp.get(i).size()==1){
				exp.remove(i);
				i--;
			}
			else if(exp.get(i).get(var)==null){
				exp.remove(i);
				i--;
				continue;
			}
			else{
				flag=true;
				exp.get(i).put(' ', exp.get(i).get(' ')*exp.get(i).get(var));
				exp.get(i).put(var, exp.get(i).get(var)-1);
				if(exp.get(i).get(var)==0){
					exp.get(i).remove(var);
				}
			}
		}
		if(flag==false){
			System.out.println("Error, no variable");
			return;
		}
		Print(exp);
		expression(inforcopy,exp);
	}

public static void Print(Vector<HashMap<Character,Integer>> poly){
	for(int i=0;i<poly.size();i++){
		Iterator iter = poly.get(i).entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			if(key.equals(' ')){
				System.out.println(value);
			}
			else{
				for(int n=0;n<(int)value;n++){
					System.out.println("*"+key);
				}
			}
		}
		if(i<poly.size()){
			System.out.print("+");
		}
	}
}