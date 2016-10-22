/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.txie.java.cc;

import java.util.*;

/**
 *
 * @author Tom
 */
public class OA_iSENTIUM {
    public static void main(String[] args){
        OA_iSENTIUM oa = new OA_iSENTIUM();
        String s1 = "aba";
        String s2 = "cab";
        oa.matchingDegree(s1, s2);
    }
    
    
    /**
     * This method takes two strings as parameters and output two integers in n1:n2 format.
     * n1 is the number of letters in one string that each match in value and in position to a letter in the other string.
     * n2 is number of letters in one string that each match in value, but not in position to a letter in the other string.
     * @param s1
     * @param s2 
     */
    public void matchingDegree(String s1, String s2){
        int n1 = 0, n2 = 0;
        if(s1==null || s2==null || s1.length()==0 || s2.length()==0) {output(n1,n2);return;}
        
        // make s1 the shorter string
        if(s1.length()>s2.length()){
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        // Put chars from s2 into hashmap with location information
        HashMap<Character, HashSet<Integer>> charLocFromS2 = new HashMap<>();
        for(int i=0; i<s2.length(); i++){
            char key = s2.charAt(i);
            if(charLocFromS2.containsKey(key)){
                HashSet<Integer> loc = charLocFromS2.get(key);
                loc.add(i);
                charLocFromS2.put(key, loc);
            }
            else{
                HashSet<Integer> loc = new HashSet<>();
                loc.add(i);
                charLocFromS2.put(s2.charAt(i), loc);
            }
        }
        // Loop through S1 chars in charLocFromS2 to calculate n1 and n2
        for(int i=0; i<s1.length(); i++){
            char key = s1.charAt(i);
            if(charLocFromS2.containsKey(key) && charLocFromS2.get(key).contains(i)) n1++;
            else if(charLocFromS2.containsKey(key) && !charLocFromS2.get(key).contains(i)) n2++;
        }
        output(n1, n2);
    }
    
    
    public void output(int n1, int n2){
        System.out.println(String.format("%d,%d", n1, n2));
    }
}
