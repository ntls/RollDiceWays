
package com.ntls;

import com.ntls.model.Die;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author ntls
 */
public class DiceRollWays {
    
    private final List<Die> dice; 
    private final List<BigInteger> distribution;

    private final Long maxDiceRoll; //The maximum throw the dice can roll.
    private final BigInteger totalWays;

    
    public DiceRollWays(List<Die> dice) {
        this.dice = dice;
        
        distribution = new ArrayList<>();
        computeDistribution();
        
        maxDiceRoll = computeMaxThrow();
        totalWays = computeTotalWays();        
    }
    
    public List<BigInteger> getDistribution() {
        return distribution;
    }

    public Long getMaxDiceRoll(){
        return maxDiceRoll;
    } 

    public BigInteger getTotalWays() {
        return totalWays;
    }


    /**
     * Calculates how many ways to roll a die are to form the given sum.
     * 
     * @param sum
     * @return 
     */
    public BigInteger getWaysToRoll(int sum){

        if ( sum < dice.size() || sum > getMaxDiceRoll().intValue() ) {
            return BigInteger.ZERO;
        }
        
        return distribution.get(sum-dice.size());
    }
    

    private Long computeMaxThrow(){
        return dice.parallelStream()
                .mapToLong((d) -> d.getSides())
                .sum();
    }
     
    private BigInteger computeTotalWays(){
        
        // Sum up and store to a map, how many dice are in the list with a
        // determinate number of sides. Key is the number of sides of a die 
        // and value is how many dice where found.
        Map<BigInteger, Integer> sumdices = new ConcurrentHashMap<>();
        dice.stream()
                .map( die -> new BigInteger(die.getSides().toString()) )
                .forEach((c) -> {
                    sumdices.compute(c, (k,v) -> {return v == null ? 1 : v + 1; });
                });
        
        // How many ways to roll a die are in total.
        return sumdices.entrySet().stream()
               .map(e -> { return e.getKey().pow(e.getValue()); })
               .reduce(BigInteger.ZERO, (a,b) -> a.add(b) );
        
    }
    
    private void computeDistribution(){
        
        distribution.add(BigInteger.ONE);
        
        dice.stream().forEach( die -> {
            
            //Probability distribution
            List<BigInteger> tmp = new ArrayList<>();
            
            List<BigInteger> zeros = addZeros(die.getSides()-1);

            tmp.addAll(zeros);
            tmp.addAll(distribution);
            tmp.addAll(zeros);
            
            for (int j = 0; j < tmp.size()-(die.getSides()-1); j++) {
                BigInteger newj = BigInteger.ZERO;
                for (int k = 0; k < die.getSides(); k++) { 
                    newj = newj.add( tmp.get(j+k) );
                }
                
                if( j >= distribution.size() ) distribution.add(j, newj);
                else distribution.set(j, newj);
            }
        });

    }
        
    private List<BigInteger> addZeros(int length){
        List<BigInteger> zero = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            zero.add(BigInteger.ZERO);
        }
        return zero;
    }
 
    
}
