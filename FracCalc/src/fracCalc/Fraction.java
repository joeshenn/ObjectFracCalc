package fracCalc;
import java.util.*;
public class Fraction {
	private int[] parts; 
	private String StrFrac; 
public Fraction() {
	parts =  new int[] {0,0,1};
	StrFrac ="";
}
public Fraction(String op) {
	if(op.indexOf("/") == -1){
		parts[1] = Integer.parseInt(op);
	}
	else if(op.indexOf("_") != -1) {
		String[] splimp = toImproperFrac(op).split("/");
		parts [1] = Integer.parseInt(splimp[0]);
		parts [2] = Integer.parseInt(splimp[1]);
	}
}
public int[] getParts() {
	return this.parts;
}
public String getStrFrac() {
	return StrFrac;
}
public String doMath( String operator, int[] op2) {
	int answerNum;
	int answerDenom;
	int gcf =1;
	if (operator.equals("+")) {
		answerNum = (parts[0]*op2[1])+(op2[0]*parts[1]);
		answerDenom = (parts[1]*op2[1]);
		 gcf = gcf(answerNum,answerDenom);
		answerNum = answerNum/gcf;
		answerDenom = answerDenom/gcf;
	}
	else if(operator.equals("-")) {
		answerNum = (parts[0]*op2[1])-(op2[0]*parts[1]);
		answerDenom = (parts[1]*op2[1]);
	    gcf = gcf(answerNum,answerDenom);
		answerNum = answerNum/gcf;
		answerDenom = answerDenom/gcf;
	}
	else if (operator.equals("*")) {
		answerNum = (parts[0]*op2[0]);
		answerDenom = (parts[1]*op2[1]);
		 gcf = gcf(answerNum,answerDenom);
		answerNum = answerNum/gcf;
		answerDenom = answerDenom/gcf;
	}
	else  {
		//division
		answerNum = (parts[0]*op2[1]);
		answerDenom = (parts[1]*op2[0]);
		 gcf = gcf(answerNum,answerDenom);
		answerNum = answerNum/gcf;
		answerDenom = answerDenom/gcf;
	}
    return (toMixedNum(answerNum,answerDenom));
}
public static String toImproperFrac(String input) {
	String[] sa1 = input.split("_");
	String[] sa2 = sa1[1].toString().split("/");
	int intpart = Integer.parseInt(sa1[0].toString());
	int fracnum = Integer.parseInt(sa2[0].toString());
	int fracdenom = Integer.parseInt(sa2[1].toString());
	if(intpart>=0) {
	sa2[0] = ((intpart*fracdenom)+fracnum)+"";
	}
	else {
	sa2[0] = ((intpart*fracdenom)-fracnum)+"";
	}
	return sa2[0]+"/"+sa2[1];
}
public static String toMixedNum(int numerator, int denominator) {
	if(numerator == denominator) {
		return "1";
	}
	else if(numerator ==0) {
		return "0";
	}
	else if(denominator ==0) {
		return "can't divide by zero";
	}
	else if((numerator/denominator==0 && denominator>0 && numerator>0)||(numerator/denominator==0 && denominator>0 && numerator<0)) {
		return numerator+"/"+denominator;
	}
	else if(numerator/denominator==0 && denominator<0 && numerator>0 || (numerator/denominator==0 && denominator<0 && numerator<0)) {
		return (numerator*-1)+"/"+denominator*-1;
	}
	else {
	if(Math.abs(numerator%denominator)==0) {
		return numerator/denominator+"";
	}
	return (numerator/denominator)+"_"+Math.abs(numerator%denominator)+"/"+Math.abs(denominator);
	}
}
public static int gcf(int num1, int num2) {
		int smaller = min(num1, num2);
		int bigger = (int) max(num1, num2);
		int count = smaller;
		if(count>0) {
			while(count>1) {
				if(bigger%count==0 && smaller%count==0) {
					return count;
			}
		else {
			count--;
			}
		}
		} else {
			while(count<-1) {
				if(bigger%count==0 && smaller%count==0) {
					return count;
			}
		else {
			count++;
			}
			}
		}
		if(count==0) {
			return 1;
		}
		return count;
	} 
public static int max(int num1, int num2) {
		if(num1 >= num2) 
			return num1;
			return num2;
	}
  public static int min(int num1, int num2) {
		if(num1 <= num2) 
			return num1;
			return num2;
	}
}
