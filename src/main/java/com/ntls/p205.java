
package com.ntls;

import com.ntls.model.Die;
import com.ntls.util.probability.PreciseProbability;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Solution for the problem 205 in Project Euler
 * 
 * @author ntls
 */
public class p205 {
    
    private static final int SIDES_OF_PYRAMID = 4;
    private static final int SIDES_OF_CUBE    = 6;
    
    public static void main(String[] args) {
        
        //init Peter
        int numOfPetersDice = 9;
        List<Die> diceOfPeter = new ArrayList<>();
        Die pyramidal = new Die(SIDES_OF_PYRAMID);
        
        for (int i = 0; i < numOfPetersDice; i++) {
            diceOfPeter.add(pyramidal);
        }
               
        DiceRollWays peter = new DiceRollWays(diceOfPeter);
        
        //init Colin
        int numOfColinsDice = 6;
        List<Die> diceOfColin = new ArrayList<>();
        Die cubic = new Die(SIDES_OF_CUBE);
        
        for (int i = 0; i < numOfColinsDice; i++) {
            diceOfColin.add(cubic);
        }
        
        DiceRollWays colin = new DiceRollWays(diceOfColin);
        
        //count how many ways there are so that Peter wins Colin
        BigInteger count = BigInteger.ZERO;
        for (int i = 0; i < peter.getDistribution().size(); i++) {
            for (int j = i+1; j < colin.getDistribution().size(); j++) {
                count = count.add( colin.getWaysToRoll(j+numOfColinsDice).multiply(peter.getWaysToRoll(i+numOfPetersDice)) );
            }
        }
        
        // multiply the ways Peter can roll his dice with the ways Colin can roll her dice
        BigInteger totalWaysForBoth = peter.getTotalWays().multiply(colin.getTotalWays());
        
        //Print the probability Peter to win colin with 7-digit precision
        PreciseProbability pp = new PreciseProbability(7);
        System.out.println("Peter wins Colin with " + pp.getProbability(count, totalWaysForBoth) + " chance.");
    }
}
