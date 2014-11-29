/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Isra
 */

public class pruebas {
    
    
    
    public static void main(String args[]){
        
      double valor = 9.99936;
      String val = valor+"";
      BigDecimal big = new BigDecimal(""+valor);
      big = big.setScale(2, RoundingMode.HALF_UP);
      System.out.println("Número : "+big);
      
        Double num = 879.08;
        Double num4 = 24.00;
        Double div = 879.08 / 24;
       //new BigDecimal("0.1").add("0.2");
    BigDecimal num1 = new BigDecimal(""+num);
    BigDecimal num3 = new BigDecimal(""+num4);
    BigDecimal sum = num1.divide(num3,2, RoundingMode.UP);
    Double num2 = num;
        System.out.println(num);
        System.out.println("LA SUMA ES: "+sum);
        System.out.println("LA DIVISION ES: "+div);
    }
    
}
