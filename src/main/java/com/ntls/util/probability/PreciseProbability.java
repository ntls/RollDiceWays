
package com.ntls.util.probability;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 *
 * @author ntls
 */
public class PreciseProbability {
    
    private final static int DEFAULT_PROBABILITY_PRECISION = 19;
    private final static RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    
    private int probabilityPrecision;
    private RoundingMode mode;

    public PreciseProbability() {
        this(DEFAULT_PROBABILITY_PRECISION, DEFAULT_ROUNDING_MODE);
    }

    public PreciseProbability(int probabilityPrecision) {
        this(probabilityPrecision, DEFAULT_ROUNDING_MODE);
    }

    public PreciseProbability(int probabilityPrecision, RoundingMode mode) {
        this.probabilityPrecision = probabilityPrecision;
        this.mode = mode;
    }
      
    public int getProbabilityPrecision() {
        return probabilityPrecision;
    }

    public RoundingMode getMode() {
        return mode;
    }
    
    public BigDecimal getProbability(BigDecimal dividend, BigDecimal divisor)
            throws ArithmeticException{
        
        if( dividend.equals(BigDecimal.ZERO) ){ return BigDecimal.ZERO; }
        
        return dividend.divide(divisor, probabilityPrecision, mode);
    }
    
    public BigDecimal getProbability(BigInteger dividend, BigInteger divisor) 
            throws ArithmeticException{
        
        if( dividend.equals(BigInteger.ZERO) ){ return BigDecimal.ZERO; }
        
        BigDecimal numerator = new BigDecimal(dividend);
        BigDecimal denominator = new BigDecimal(divisor);
        return numerator.divide(denominator, probabilityPrecision, mode);
    }
        
}
