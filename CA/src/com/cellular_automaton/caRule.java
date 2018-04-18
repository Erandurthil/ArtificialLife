package com.cellular_automaton;


import java.util.HashMap;


public class caRule {
    int neighbourhood;
    public HashMap ruleMap = new HashMap();

    public caRule (int decimalRule, int neighbourhood){
        this.neighbourhood = neighbourhood;


        String binRule =  new StringBuilder(convertBinary(decimalRule, 32)).reverse().toString();

        if (neighbourhood == 1) {
            for (int i = 1; i <= Math.pow(2, neighbourhood * 2 + 1 ); i++) {
                ruleMap.put(convertBinary(i-1, 3), Character.getNumericValue(binRule.charAt(i-1)));
            }
        }
        else {
            for (int i = 1; i <= Math.pow(2, neighbourhood * 2 + 1 ); i++) {
                ruleMap.put(convertBinary(i-1, 5), Character.getNumericValue(binRule.charAt(i-1)));
            }
        }
    }

    public String convertBinary(int decimal, int binaryPlaces) {
        String bin = "000000000000000000000000000000000" + Integer.toString(decimal,2);
        bin = bin.substring(bin.length() - binaryPlaces);

        return bin;
    }

}
