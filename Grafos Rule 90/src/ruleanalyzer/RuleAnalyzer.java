/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleanalyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jedua
 */

public class RuleAnalyzer {
    public int rule;
    public int len;
    
    // Agrega ceros a la izq de un numero binario
    public String leftPad(String originalString) {
      String paddedString = originalString;
      while (paddedString.length() < len) {
         paddedString = '0' + paddedString;
      }
      return paddedString;
    }
    
    // Recibe Estado actual en decimal, convierte a arreglo de celdas de tamaño [len]
    private boolean[] decode(int state){
        String pad = leftPad(Integer.toBinaryString(state));
        boolean result[] = new boolean[len];
        for(int i = len-1; i>=0; i--) result[i] = pad.charAt(i) == '1';
        return result;
    }
    
    // Recibe celdas como arreglo de booleanos, retorna decimal.
    private int encode(boolean[] state){
        int n = 0, i = 0;
        for (; i < state.length; ++i)
            n = (n << 1) + (state[i] ? 1 : 0);
        return n;
    }
    
    // 
    public boolean[] getRules(){
        boolean rules[] = new boolean[8];
        String str = Integer.toBinaryString(rule);
        char[] chr = str.toCharArray();
        int div = 8 - chr.length;
        for (int i = 0; i < chr.length; i++) rules[i + div] = (chr[i] == '1');
        return rules;
    }

    public int nextOf(int state, boolean[] rule){
        boolean cells[] = decode(state);
        boolean newCells[] = new boolean[len];
        
        for (int i = 0; i < len; i++) {
            // Ring Behaviour
            boolean prev = cells[(i==0)?len-1:i-1];
            boolean next = cells[(i==len-1)?0:i+1];

            //System.out.print("["+((prev)?"1":"0")+","+((cells[i])?"1":"0")+","+((next)?"1":"0")+"]");
            // Simulate according to rules
                 if(  prev &&  cells[i] &&  next && rule[0]) newCells[i] = true;
            else if(  prev &&  cells[i] && !next && rule[1]) newCells[i] = true;
            else if(  prev && !cells[i] &&  next && rule[2]) newCells[i] = true;
            else if(  prev && !cells[i] && !next && rule[3]) newCells[i] = true;
            else if( !prev &&  cells[i] &&  next && rule[4]) newCells[i] = true;
            else if( !prev &&  cells[i] && !next && rule[5]) newCells[i] = true;
            else if( !prev && !cells[i] &&  next && rule[6]) newCells[i] = true;
            else if( !prev && !cells[i] && !next && rule[7]) newCells[i] = true;
        }
        /*
        System.out.println("[cells]");
        for(boolean cell : cells) System.out.print((cell)?"1":"0");
        System.out.println("");
        
        System.out.println("[newCells]");
        for( boolean cell : newCells) System.out.print((cell)?"1":"0");
        System.out.println("");
        */
        return encode(newCells);
    }

    public static void main(String[] args) throws IOException {
        RuleAnalyzer rl = new RuleAnalyzer();
        Scanner s = new Scanner(System.in);
        int[] equiv = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,15,18,19,22,23,24,25,26,27,28,29,30,32,33,34,35,36,37,38,40,41,42,43,4,45,46,50,51,54,56,57,58,60,72,73,74,76,77,78,90,104,105,106,108,128,130,132,133,134,136,137,138,140,142,146,150,152,156,160,161,162,164,170,172,178,184,200,204,232};
        for(int e = 0;e < 83;e++){
            new File("./output/"+equiv[e]).mkdir();
            rl.rule = equiv[e];
            boolean rules[] = rl.getRules();
            for(int len = 0;len < 18;len++){
                rl.len = len;
                System.out.println("Generando ["+equiv[e]+":"+rl.len+"]");
                BufferedWriter writer = new BufferedWriter(new FileWriter("./output/"+equiv[e]+"/"+rl.len+".txt"));
                writer.write("Graph[{");        
                for(int i = 0; i <= Math.pow(2,rl.len); i++){
                    writer.write(i+"->"+rl.nextOf(i,rules));
                    if(i != Math.pow(2,rl.len)) writer.write(",");
                }
                writer.write("},VertexSize -> Tiny, VertexStyle -> Black, ImageSize->{1000,1000}]");
                writer.close();
            }
        }
    }   
}