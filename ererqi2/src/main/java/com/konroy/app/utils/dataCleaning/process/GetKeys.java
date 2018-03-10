package com.konroy.app.utils.dataCleaning.process;

import com.konroy.app.utils.common.file.ReadFile;
import com.konroy.app.utils.getActor1.recongnize.GetActor;
import org.apache.commons.collections.map.LinkedMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetKeys {
    public static void main(String[] atgs){
        //Extract actors from all comment
        ReadFile readFile=new ReadFile();
        List<String> comment=readFile.read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\data\\战狼2(终).txt(3).txt");

        List<String> list=new GetKeys().getKey(comment);
        System.out.println(list);


    }


    /**
     * Extract the most mentioned actors from the comment list
     * @param comment
     * @return
     */
    public  List<String> getKey(List<String> comment){
        List<String> keyList=new ArrayList<String>();

        List<String> rawActors=preprocess(comment);
        //System.out.println(rawActors);

        LinkedMap actorSet=compute(rawActors);
        Iterator iter =actorSet.entrySet().iterator();
        while(iter.hasNext()){
            String chunk=iter.next().toString();
            int frequency=Integer.parseInt(chunk.split("=")[1]);
            String name=chunk.split("=")[0];
            System.out.println(name+" "+frequency);
            if(frequency>=15){
                //System.out.println(chunk);
                keyList.add(name);
            }
        }
        return keyList;
    }
    /**
     * extract the actors' name from the raw comment data under the data folder
     * @param raw_data
     * @return
     */
    private static List<String> preprocess(List<String> raw_data){
        List<String> nameList=new ArrayList<String>();
        for(String c:raw_data){
            String chunk=c.split("评价对象：")[1].split("评价词：")[0];
            String[] list=chunk.split(" ");
            for(String s:list){
                if(!s.equals("")) nameList.add(s);
            }
        }
        return nameList;
    }

    /**
     * conpute how many times does a certain actor occurs in the comment list
     * @param nameString
     * @return
     */
    private static LinkedMap compute(List<String> nameString){
        LinkedMap actorsmap=new LinkedMap();
        for(String a:nameString){
            if(!actorsmap.keySet().contains(a)){
                actorsmap.put(a,1);
            }else{
                int times=Integer.parseInt(actorsmap.get(a).toString())+1;
                actorsmap.remove(a);
                actorsmap.put(a,times);
            }
        }
        return actorsmap;
    }
    private static String getNeighbour(String name){
//        List<Actor> actors=new ActorList().get();
//        double max=0;
//        String actor="";
//        for(Actor a:actors){
//            double jaccard=new Jaccard().distance(a.getRealName(),name);
//            if(jaccard>max && jaccard>0.6){
//                max=jaccard;
//                actor=a.getRealName();
//                //System.out.println(actor);
//            }
//        }
        return name;
    }
}
