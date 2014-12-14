package com.ntls;

import com.ntls.model.Die;
import com.ntls.util.probability.PreciseProbability;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 
 * @author ntls
 */
public class Test {
  
    
    public static void main(String[] args) {
        // for simplicity every die has the same amount of sides
        int sum = 80;
        int dices = 10;
        int sides = 8;
        
        //create an array with dice
        Die d1 = new Die(sides);
        ArrayList<Die> dice = new ArrayList<>();
        for (int i = 0; i < dices; i++) {
            dice.add(d1);
        }
        
        //compute the ways to roll the dice, total and for a given sum
        DiceRollWays diceRollWays = new DiceRollWays(dice);
        BigInteger waysToRoll = diceRollWays.getWaysToRoll(sum);
        BigInteger totalWaysToRoll = diceRollWays.getTotalWays();
        System.out.println("Ways to roll the dice and get "+ sum +" is "+ waysToRoll +" out of " + totalWaysToRoll );
        
        
        //compute the probability to roll the given sum with high precision
        PreciseProbability preciseProbability = new PreciseProbability();
        BigDecimal probability = preciseProbability.getProbability( waysToRoll, totalWaysToRoll );
        System.out.println("The probability to get "+ sum +" is " + probability);
        
    }
}
