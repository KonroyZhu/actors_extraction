package com.konroy.app.utils.dataCleaning.process;

import com.konroy.app.utils.common.file.ReadFile;
import org.apache.commons.collections.map.LinkedMap;

import java.util.Iterator;
import java.util.List;

public class Statistic {
    public static void main(String[] args){
        ReadFile readFile=new ReadFile();
        List<String> comment=readFile.read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi2\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\data\\战狼2(终).txt");
        LinkedMap keyWords=new GetWords().getWords(comment);


        LinkedMap counter=count(keyWords.get("吴京").toString().split(" "));
        Iterator iter=counter.entrySet().iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    /**
     * compute the frequency the word occur;
     * @param words
     * @return
     */
    private static LinkedMap count(String[] words){
        LinkedMap map=new LinkedMap();

        for(String w:words){

            if(!map.keySet().contains(w)){

                map.put(w,1);
            }else{
                int frequency=Integer.parseInt(map.get(w).toString());
                map.remove(w);
                map.put(w,frequency+1);
            }
        }

        return map;
    }
}
