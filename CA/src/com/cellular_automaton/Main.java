package com.cellular_automaton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {

        public static int ca[][];
        public static int neighbourhood;
        public static caRule rule;

        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter timesteps t to compute:");
            try{
                int t = Integer.parseInt(br.readLine());

                System.out.print("Enter radius r of neighbourhood(Either 1 or 2):");

                neighbourhood = Integer.parseInt(br.readLine());

                System.out.print("Enter a rule for the CA in decimal representation of the Wolfram Notation:");

                int decimalRule = Integer.parseInt(br.readLine());

                System.out.print("Enter r for random stating condition, enter s for a seed in cell #42:");
                if (br.readLine().contains("r")) {
                initCA(t, decimalRule, true);
                }
                else {  initCA(t, decimalRule, false);}

            }catch(NumberFormatException nfe){
                System.err.println("Invalid Format!");
            }
        }



        public static void initCA(int t, int decimalRule, boolean rand) {
            Random randomNum = new Random();
            ca = new int[t][84];
            if (!rand) {ca[0][42] = 1;}
            else {
                for (int i = 2; i <= 82; i++){
                   ca[0][i] = randomNum.nextInt(2);
                }
            }
            boundaryConfirmation(0);

            rule = new caRule(decimalRule, neighbourhood);

            if (neighbourhood == 1) {
                for (int i = 1; i < t; i++) {
                    for (int a = 2; a <= 81; a++) {
                        ca[i][a] = (int) rule.ruleMap.get(Integer.toString(ca[i - 1][a - 1]) + Integer.toString(ca[i - 1][a]) + Integer.toString(ca[i - 1][a + 1]));
                    }
                }
            }
            else {
                for (int i = 1; i < t; i++) {
                    for (int a = 2; a <= 81; a++) {
                        ca[i][a] = (int) rule.ruleMap.get(Integer.toString(ca[i - 1][a - 2])+ Integer.toString(ca[i - 1][a - 1]) +
                                Integer.toString(ca[i - 1][a]) + Integer.toString(ca[i - 1][a + 1]) + Integer.toString(ca[i - 1][a + 2]));
                    }
                }
            }

            System.out.println(Arrays.deepToString(ca).replace("], ", "]\n"));
        }

        public static void boundaryConfirmation(int currentTimestep){
            ca[currentTimestep][0] = 0;
            ca[currentTimestep][1] = 0;
            ca[currentTimestep][82] = 0;
            ca[currentTimestep][83] = 0;
        }

}
