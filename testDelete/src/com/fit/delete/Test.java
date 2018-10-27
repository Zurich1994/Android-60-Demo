package com.fit.delete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> poList1=new ArrayList<String>();
		poList1.add("1101");
		poList1.add("1102");
		poList1.add("1103");
		poList1.add("1104");
		poList1.add("1105");
		poList1.add("1106");

		List<String> poList2=new LinkedList<String>();
		poList2.add("1101");
		poList2.add("1103");
		poList2.add("1107");
		poList2.add("1108");
		poList2.add("1104"); 
		poList2.add("1104"); 
		poList2.add("1104");
		poList2.add("1106");
		poList2.add("1106");
//		for(int m=0;m<poList2.size()-1;m++){
//			for(int n=poList2.size()-1;n>m;n--){
//				if(poList2.get(m).equals(poList2.get(n))){
//					poList2.remove(n);
//				}
//			}
//		}
		HashSet<String> hash = new HashSet<String>(poList2);
		poList2.clear();
		poList2.addAll(hash);
		
		for(int i=0;i<poList1.size();i++){
			String po1=poList1.get(i);
			for(int j=0;j<poList2.size();j++){
				String po2=poList2.get(j);
				if(po2.equals(po1)){
					poList2.remove(po2);
				}
			}
		}
		
		for(int k=0;k<poList2.size();k++){
			System.out.println(poList2.get(k));
		}
	}

}
