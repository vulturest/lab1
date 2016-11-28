package lab1;
import java.util.*;
import java.io.*;

public class expression {
	public static int expression(String infor,Vector<HashMap<Character,Integer>> V){
		int NumTemp=1;
		int temp=0;
		boolean NumChange=false;
		HashMap<Character,Integer> Term=new HashMap<Character,Integer>();
		V.removeAll(V);
		int ret = 0;
		for(int i=0;i<infor.length();i++){
			if(judge.Right(infor.charAt(i))==0){
				System.out.println("Error, no variable");
				ret = -1;
			}
			if(infor.charAt(i)==' ' || infor.charAt(i)=='\t'){
				continue;
			}
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
				}
				if(NumChange){
					//if(temp==0)
					//{
						//NumTemp=0;
					//}
					Term.put(' ', NumTemp);
				}else{
					Term.put(' ',1);
				}
				V.addElement(Term);
				Term=new HashMap<Character,Integer>();
				NumTemp=1;
				temp=0;
				NumChange=false;
			}
			else{
				if(Term.get(infor.charAt(i)) == null){
					Term.put(infor.charAt(i), 1);
				}
				else{
					Term.put(infor.charAt(i), Integer.valueOf(Term.get(infor.charAt(i))+1));
				}
			}
		}
		if(temp!=0){
			NumTemp=NumTemp*temp;
		}
		Term.put(' ', NumTemp);
		V.addElement(Term);
		return ret;
	}

}
