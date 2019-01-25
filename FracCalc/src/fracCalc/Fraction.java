package fracCalc;
public class Fraction {
	private int[] parts; 
	private String answer;
public Fraction() {
	parts =  new int[] {0,0,1};
	answer = "0";
}
public Fraction(String op) {
	parts =  new int[] {0,0,1};
	answer = "0";
	if(op.indexOf("/") == -1){
		parts[1] = Integer.parseInt(op);
		parts[2]=1;
		parts[0]=0;
	}
	else if(op.indexOf("_") != -1) {
		String[] splimp = toImproperFrac(op).split("/");
		parts [0] = 0;
		parts [1] = Integer.parseInt(splimp[0]);
		parts [2] = Integer.parseInt(splimp[1]);
	}
	else {
		String[] common = op.split("/");
		parts [0]= 0;
		parts [1] = Integer.parseInt(common[0]);
		parts[2] = Integer.parseInt(common[1]);
	}
}
public int[] getParts() {
	return this.parts;
}
public String getAnswer() {
	return this.answer;
}
public void doMath( String operator, int[] op2) {
	int answerNum;
	int answerDenom;
	int gcf =1;
	if (operator.equals("+")) {
		answerNum = (parts[1]*op2[2])+(op2[1]*parts[2]);
		answerDenom = (parts[2]*op2[2]);
		 gcf = gcf(answerNum,answerDenom);
		answerNum = answerNum/gcf;
		answerDenom = answerDenom/gcf;
	}
	else if(operator.equals("-")) {
		answerNum = (parts[1]*op2[2])-(op2[1]*parts[2]);
		answerDenom = (parts[2]*op2[2]);
	    gcf = gcf(answerNum,answerDenom);
		answerNum = answerNum/gcf;
		answerDenom = answerDenom/gcf;
	}
	else if (operator.equals("*")) {
		answerNum = (parts[1]*op2[1]);
		answerDenom = (parts[2]*op2[2]);
		 gcf = gcf(answerNum,answerDenom);
		answerNum = answerNum/gcf;
		answerDenom = answerDenom/gcf;
	}
	else  {
		//division
		answerNum = (parts[1]*op2[2]);
		answerDenom = (parts[2]*op2[1]);
		 gcf = gcf(answerNum,answerDenom);
		answerNum = answerNum/gcf;
		answerDenom = answerDenom/gcf;
	}
	answer = toMixedNum(answerNum,answerDenom);
   // return (toMixedNum(answerNum,answerDenom));
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
