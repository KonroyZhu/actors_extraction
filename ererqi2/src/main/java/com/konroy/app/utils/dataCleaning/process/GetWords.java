package com.konroy.app.utils.dataCleaning.process;

import com.konroy.app.utils.common.file.ReadFile;
import org.apache.commons.collections.map.LinkedMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetWords {
    public  static  void main(String[] args){
        ReadFile readFile=new ReadFile();
        List<String> comment=readFile.read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\data\\战狼2(终).txt.txt");
        LinkedMap keyWords=new GetWords().getWords(comment);

        //out print
        Iterator iter =keyWords.entrySet().iterator();
        while(iter.hasNext()){
            System.out.println(iter.next().toString());
        }
    }

    public LinkedMap getWords(List<String> comment){
        List<String> key=new GetKeys().getKey(comment);
        LinkedMap keyWords=new LinkedMap();

        for(String c:preprocess(comment)){
            String name=c.split("评价词：")[0];
            String words=c.split("评价词：")[1];
            //System.out.println(name);
            for(String k:key){

                if(name.contains(k)){
                    if(!keyWords.keySet().contains(k)){
                        keyWords.put(k,words);
                    }else{
                        String wordsMore=keyWords.get(k).toString()+words;
                        keyWords.remove(k);
                        keyWords.put(k,wordsMore);
                    }
                }
            }
            //System.out.println(c);
        }


        return keyWords;
    }

    /**
     * get both actor and comments which is the second chunk split by "评价对象：" in a comment
     * @param raw_data
     * @return
     */
    private static List<String> preprocess(List<String> raw_data){
        List<String> actorAndComments=new ArrayList<String>();
        for(String c:raw_data){
            String chunk=c.split("评价对象：")[1];
            actorAndComments.add(chunk);
        }
        return actorAndComments;
    }

}
