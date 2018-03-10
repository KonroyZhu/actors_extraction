package com.konroy.app.utils.getActor1.extract;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import java.util.ArrayList;
import java.util.List;

public class ExtractTarget {

    public static void main(String[] args){
        List<Term> list=HanLP.segment("塘主实力饰演熊孩子，搞笑担当。");
        List<String> nr=new ExtractTarget().extract(list);
        System.out.println(nr);
        System.out.println(list);
    }
    /**
     * get the targed attribute word from the term list of segmented sentence
     * @param termList
     * @param tag
     * @return
     */
    public  List<String> extract(List<Term> termList,String tag){
        List<String> result=new ArrayList<String>();
        for(Term t:termList){

            if(t.nature.toString()==tag){
                result.add(t.word);
            }
        }
        return result;
    }


    /**
     * get the targed attribute word from the term list of segmented sentence
     * @param termList
     * @return
     */
    public  List<String> extract(List<Term> termList){
        List<String> result=new ArrayList<String>();
        for(Term t:termList){

            String nature=t.nature.toString();
            if((nature=="nz"    || nature=="nr" || nature=="nrf"  )){
                result.add(t.word);
            }
        }
        return result;
    }

}
