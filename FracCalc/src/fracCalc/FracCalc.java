package fracCalc;

import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args) 
    {
    	Scanner userInput = new Scanner(System.in);
    	boolean repeat = true;
    	while(repeat==true) {
    		System.out.println("Please enter an expression to be calculated. Type \"quit\" to stop");
    		String expression = userInput.nextLine();
    		if(expression.equals("quit") == true) {
    			repeat = false;
    			break;
    		} else {
    			String[] longInput = expression.split(" ");
    			int addOn = 0;
    			for(int i=0;i<longInput.length;i++) {
    				if(longInput[i].equals("*") || longInput[i].equals("/")) {
    					addOn += Integer.parseInt(Calculate(longInput[i-1]+" "+longInput[i]+" "+longInput[i+1]));
    					longInput[i-1] = longInput[i+1] = "0";
    					longInput[i] = "+";
    				}
    			}
        		for(int i=0;i<=longInput.length-2;i+=2) {
        			longInput[i+2] = produceAnswer(longInput[i].toString()+" "+longInput[i+1].toString()+" "+longInput[i+2].toString());
        		}
        		System.out.println(Calculate(Integer.parseInt(longInput[longInput.length-1])+" "+"+ "+addOn));
    		}
    	} 

    	userInput.close();
    } 
    public static String produceAnswer(String input){ 
    	String[] longInput = input.split(" ");
		for(int j=0;j<=longInput.length-2;j+=2) {
			longInput[j+2] = Calculate(longInput[j].toString()+" "+longInput[j+1].toString()+" "+longInput[j+2].toString());
		}
		return longInput[longInput.length-1];
    }
    public static String Calculate(String input){ 
    	String[] rawInput = input.split(" ");
    	String operator = rawInput[1];
    	Fraction operand1 = new Fraction(rawInput[0].toString());
    	Fraction operand2 = new Fraction(rawInput[2].toString());
    	operand1.doMath(operator, operand2.getParts());
    	return operand1.getAnswer();
  
    }
}
