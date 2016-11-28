package lab1;
import java.util.*;
import java.io.*;
public class printAll {
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

}
