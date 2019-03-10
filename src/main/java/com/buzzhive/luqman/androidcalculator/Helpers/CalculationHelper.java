package com.buzzhive.luqman.androidcalculator.Helpers;
import android.widget.Toast;
import java.util.ArrayList;


public class CalculationHelper {

    //main calculate function. segregates the multiple operand expression. adds Negative if available and calculates according to BODMAS
    public static double calculate(String expString) {

        double awn = 0.0;

        ArrayList<String> toBeCal = addNegatives(segregateExpr(expString));
        int noOfOperands = 0;
        for(String s : toBeCal) {
            if (s.compareTo("-") == 0|| s.compareTo("+") == 0 || s.compareTo("*") == 0 || s.compareTo("/") == 0)
                noOfOperands++;
        }
        try {
            for(int j = 0;j< (int)noOfOperands;j++) {
                for (int i = 0; i < toBeCal.size(); i++) {
                    double temp = 0.0;
                    if (toBeCal.get(i).compareTo("/") == 0) {
                        temp = Double.parseDouble(toBeCal.get(i - 1)) / Double.parseDouble(toBeCal.get(i + 1));
                        toBeCal.set(i - 1, Double.toString(temp));
                        toBeCal.remove(i);
                        toBeCal.remove(i);
                    } else if (toBeCal.get(i).compareTo("*") == 0) {
                        temp = Double.parseDouble(toBeCal.get(i - 1)) * Double.parseDouble(toBeCal.get(i + 1));
                        toBeCal.set(i - 1, Double.toString(temp));
                        toBeCal.remove(i);
                        toBeCal.remove(i);
                    }
                }
                for (int i = 0; i < toBeCal.size(); i++) {
                    double temp = 0.0;
                    if (toBeCal.get(i).compareTo("+") == 0) {
                        temp = Double.parseDouble(toBeCal.get(i - 1)) + Double.parseDouble(toBeCal.get(i + 1));
                        toBeCal.set(i - 1, Double.toString(temp));
                        toBeCal.remove(i);
                        toBeCal.remove(i);
                    } else if (toBeCal.get(i).compareTo("-") == 0) {
                        temp = Double.parseDouble(toBeCal.get(i - 1)) - Double.parseDouble(toBeCal.get(i + 1));
                        toBeCal.set(i - 1, Double.toString(temp));
                        toBeCal.remove(i);
                        toBeCal.remove(i);
                    }
                }
            }
        }catch (ArithmeticException e) {
            Toast t = Toast.makeText(null,"Invalid Expression",Toast.LENGTH_LONG);
            t.show();
        }
        awn = Double.parseDouble(toBeCal.get(0));
        return awn;
    }

    //Add support to calculate negative numbers
    private static ArrayList<String> addNegatives(ArrayList<String> segregatedExprList) {

        if(segregatedExprList.get(0).compareTo("-") == 0) {
            segregatedExprList.set(1,"-"+segregatedExprList.get(1));
            segregatedExprList.remove(0);
        }
        for (int i = 0; i < segregatedExprList.size(); i++) {
            String posString = segregatedExprList.get(i);

            if ((posString.compareTo("/") == 0) || (posString.compareTo("*") == 0) || (posString.compareTo("+") == 0) || (posString.compareTo("-") == 0)) {
                if (segregatedExprList.get(i + 1).compareTo("-") == 0) {
                    String s = segregatedExprList.get(i + 2);
                    s = "-" + s;
                    segregatedExprList.set(i + 1, s);
                    segregatedExprList.remove(i + 2);
                }
            }
        }

        return segregatedExprList;
    }

    //Segregates Multiple operand Expression by operand and return a Array list with individual numbers and operands
    private static ArrayList<String> segregateExpr(String expString) {
        ArrayList<String> expList = new ArrayList<>();
        StringBuilder tempSB = new StringBuilder();
        for (char posChar : expString.toCharArray())
            if ((posChar == '/') || (posChar == '*') || (posChar == '+') || (posChar == '-')) {
                if (tempSB.length() > 0) {
                    expList.add(tempSB.toString());
                    tempSB = new StringBuilder();
                }
                expList.add(Character.toString(posChar));
            } else
                tempSB.append(posChar);
        expList.add(tempSB.toString());
        return expList;
    }
}
