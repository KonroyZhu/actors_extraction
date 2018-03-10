package com.konroy.app.utils.dataCleaning.process;

import com.konroy.app.utils.common.file.ReadFile;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Statistic2 {
    private static List<String> commonList;
    public static void main(String[] args){
        commonList= preprocess("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi2\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\data\\战狼2(终).txt");
        System.out.println(getComment("吴京"));
    }
    public static String getComment(String actor){

        String commentText="";
        for(String s:commonList){
            String[] c=s.split("评价对象：");
            if(c[1].contains(actor)){
                commentText+=c[0].replaceAll(actor,"");
            }
        }
        return commentText;

    }
    public static List<String> preprocess(String path){
        List<String> commenlist=new ArrayList<String>();
        List<String> result=new ReadFile().read(path);
        for(String r:result){
            try{
                String name=r.split("-")[1];//加入有评价对象的
                //System.out.println(r);
                commenlist.add(r);
            }catch (Exception e){

            }
        }
        return commenlist;
    }
}
