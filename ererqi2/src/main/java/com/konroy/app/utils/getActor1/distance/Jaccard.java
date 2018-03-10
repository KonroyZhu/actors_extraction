package com.konroy.app.utils.getActor1.distance;

import java.util.ArrayList;
import java.util.List;

public class Jaccard {
    public static void main(String[] args){
        String str1="成龙";
        String str2="龙哥";
        String str3="吴京";
        String str4="杨幂";
        String str5="幂幂";
        String str6="卢靖姗";
        String str7="吴京古";
        String str8="翰哥";
        String str9="张翰";



        System.out.println(new Jaccard().distance(str3,str7));
    }

    /**
     * get the jaccard distance between 2 strings
     * @param str1
     * @param str2
     * @return
     */
    public  double distance(String str1,String str2){
        int u=unionAmount(str1,str2);

        int i=intersectionAmount(str1,str2);

        return ((double)i)/u;
    }

    /**
     * calculate how many items that are in both string
     * @param str1
     * @param str2
     * @return
     */
    private static int intersectionAmount(String str1,String str2){
        int len=0;
        List<String> com=new ArrayList<String>();
        for(char c:str2.toCharArray()){
            if(str1.contains(c+"") && !com.contains(c+"")) com.add(c+"");
        }
        return com.size();
    }
    /**
     * return the number of char contain in the set of 2 string
     * @param str1
     * @param str2
     * @return
     */
    private static int unionAmount(String str1,String str2){
        String s=str1+str2;
        String r="";
        for( char c: s.toCharArray()){
            if(!r.contains(c+"")) r+=c;
        }
        return r.length();
    }
}
