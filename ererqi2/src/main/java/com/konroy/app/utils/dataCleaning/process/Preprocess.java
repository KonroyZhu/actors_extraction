package com.konroy.app.utils.dataCleaning.process;

import java.util.ArrayList;
import java.util.List;

public class Preprocess {

    public List<String> preprocess(List<String> raw_data,int option){
        List<String> nameList=new ArrayList<String>();
        for(String c:raw_data){
            String chunk=c.split("评价对象：")[1].split("评价词：")[option];
            if(option ==0){// actor name was wanted
                String[] list=chunk.split(" ");
                for(String s:list){
                    if(!s.equals("")) nameList.add(s);
                }
            }else if(option ==1){// comments and words are wanted

            }

        }
        return nameList;
    }
}
