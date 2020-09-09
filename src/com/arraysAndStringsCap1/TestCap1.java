package com.arraysAndStringsCap1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestCap1 {
    public static void main(String args[]){
        System.out.println("Is Unique");
        System.out.println(isUnique("Ferando"));
        System.out.println(isUnique("fernando"));
        System.out.println("******************************************\n\n");

        System.out.println("Is permutation");
        System.out.println("With lib");
        System.out.println(isPermutationWithLib("Fernando","donanerF"));
        System.out.println(isPermutationWithLib("Fernando","doRanerF"));
        System.out.println("******************************************\n\n");

        System.out.println("Without lib");
        System.out.println(isPermutationNoLib("Fernando", "odnanreF"));
        System.out.println(isPermutationNoLib("Fernando", "odnanrtF"));
        System.out.println("******************************************\n\n");

        System.out.println("Urlify");
        System.out.println("With replace");
        System.out.println(urlifyWithReplace("Jose Fernando Arellano Saldaña"));
        System.out.println("******************************************\n\n");

        System.out.println("Without Replace");
        System.out.println(urlyfyWithoutReplace("Jose Fernando Arellano Saldaña"));

        System.out.println("******************************************\n\n");
        System.out.println("One Away Example");
        System.out.println("adding 1 " +isOneAwayEdit("Fernando", "Fernandos"));
        System.out.println("adding 2 "+isOneAwayEdit("Fernando", "Fernandoso"));
        System.out.println("editing 1 " + isOneAwayEdit("Fernando", "Fernfndo"));
        System.out.println("editing 2 " + isOneAwayEdit("Fernando", "Frrnfndo"));
        System.out.println("Deleting 1 " + isOneAwayEdit("Fernando", "Fernndo"));
        System.out.println("Deleting 2 " + isOneAwayEdit("Fernando", "Fernan"));
        System.out.println("******************************************\n");

        System.out.println("String Compression");
        System.out.println(compressedString("aaabbbbcfgtrrreddddabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"));
        System.out.println("******************************************\n");

        System.out.println("is rotation");
        System.out.println(isRotation("Fernando", "nandoFer"));
        System.out.println(isRotation("Fernando", "nandoFen"));

    }

    private static boolean isRotation(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }
        return (s1+s1).contains(s2);
    }

    //aaaaabbbbffffrrradfg -> a5b4f4r3a1d1f1g1
    private static String compressedString(String s) {
        int ocurrences=1;
        StringBuilder builder = new StringBuilder();
        for(int i= 0; i<s.length();i++){
            if(i+1 < s.length() ){
                if(s.charAt(i) == s.charAt(i+1)){
                    ocurrences++;
                }
                else{
                    builder.append(s.charAt(i) +""+ ocurrences);
                    ocurrences=1;
                }
            }
            else{
                builder.append(s.charAt(i)+""+ocurrences);
                ocurrences=1;
            }

        }
        return builder.toString().length() < s.length() ? builder.toString() : s;
    }

    //is any char repeated in the string
    //a and A are not equal, clarify if it doesnt matter the exact char
    public static boolean isUnique(String word){
        //128 if ascii value of the char cant be higher than this
        boolean boolarray [] = new boolean[128];
        for(int i=0;i<word.length();i++){
            int val = word.charAt(i);
            if(boolarray[val]){
                return false;
            }
            boolarray[val] = true;
        }
        return true;
    }

    //check permutations, given 2 string check if one is a permutation of the other
    //using libs
    public static boolean isPermutationWithLib(String s1, String s2){
        if(s1.length()!= s2.length()){
            return false;
        }
        return sort(s1).equals(sort(s2));
    }


    public static String sort(String s){
        char [] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public static boolean isPermutationNoLib(String s1, String s2){
        if(s1.length()!= s2.length()){
            return false;
        }
        return getCharstotalCount(s1) == getCharstotalCount(s2);

    }

    //notice each char from char array can be get as int directly
    private static int getCharstotalCount(String s) {
        char [] charArray = s.toCharArray();
        int total = 0;
        for(int c : charArray){
            total += c;
        }
        System.out.println(s + " total of: " + total);
        return total;
    }

    //urlify a string add %20 on each space
    //with replace
    private static String urlifyWithReplace(String s){
        return s.replaceAll(" ", "%20");
    }

    private static String urlyfyWithoutReplace(String s){
        char [] charArray = s.toCharArray();
        StringBuilder b = new StringBuilder();
        for(int i=0; i<charArray.length;i++){
            int val = charArray[i];
            if(val==32){
                b.append("%20");
            }
            else{
                b.append(charArray[i]);
            }

        }
        return b.toString();
    }

    private static boolean isOneAwayEdit(String s1, String s2) {
        if(s1.length()== s2.length()){
            return compareModificationEdit(s1,s2);
        }
        else if(s1.length()>s2.length() && s1.length() == s2.length()+1 ){
            return compareAdditionDeletionEdit(s1,s2);
        }
        else if(s1.length()< s2.length() && s1.length()+1 == s2.length()){
            return compareAdditionDeletionEdit(s2,s1);
        }
        return false;
    }

    private static boolean compareModificationEdit(String s1, String s2) {
        boolean foundDifference = false;
        for(int i=0; i<s1.length();i++){
            if(s1.charAt(i) != s2.charAt(i)){
                if(foundDifference){
                    return false;
                }
                foundDifference= true;
            }
        }
        return true;
    }

    private static boolean compareAdditionDeletionEdit(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        fillMap(s1.toCharArray(), map1);
        fillMap(s2.toCharArray(), map2);
        return compareMaps(map1,map2);
    }

    private static boolean compareMaps(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        boolean foundDifference= false;
        for(Character s :map1.keySet()){
            int val1 = map1.get(s);
            if(map2.containsKey(s)){
                int val2 = map2.get(s);
                if(val1 == val2){
                    continue;
                }else{
                    if(Math.abs(val1-val2)==1 && !foundDifference){
                        foundDifference = true;
                    } else {
                        return false;
                    }
                }
            }
            else{
                if(val1 == 1 && !foundDifference){
                    foundDifference = true;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    private static void fillMap(char[] array, Map<Character, Integer> map) {
        for(char c : array){
            if(map.containsKey(c)){
                int newValue = map.get(c)+1;
                map.put(c,newValue);
            } else {
                map.put(c,1);
            }
        }
    }
}
