package com.konroy.app.utils.dataCleaning.process;

import com.konroy.app.utils.common.file.ReadFile;

import java.text.DecimalFormat;
import java.util.List;

public class Compare {
    public static void main(String[] args){
        ReadFile rf=new ReadFile();
        List<String> originate=rf.read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\raw\\西游伏魔测试加标签");
        List<String> expect=rf.read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\data\\西游伏魔测试.txt");
        DecimalFormat df   = new DecimalFormat("######0.0000");
        double total=513;
        double hit=0;

        for(int i=0;i<500;i++){//513条之后没有标注！！！
            String ori=originate.get(i);
            String exp=expect.get(i);
            String orientation1;//手工标注
            String orientation2;//预测
            try{

                orientation1=ori.split("评价对象：")[1];
                orientation2=exp.split("评价对象：")[1];
            }catch (Exception e){
                orientation1="";
                orientation2="";
            }

            if(orientation1.equals(orientation2)){
                hit++;
            }else{
                String[] orientation=orientation1.split("，");
                double score=0;
                double marks=1.0/orientation.length;
                for(String s:orientation){
                    System.out.println(orientation2+"  contains "+s+" = "+orientation2.contains(s.trim()));

                    if(orientation2.contains(s.trim())) {
                        System.out.println("+"+df.format(marks));
                        score+=marks;
                    }else{
                        System.out.println("+"+0.0000);
                    }
                }

                hit+=score;
            }
        }
        System.out.println(hit);
        System.out.println(hit/total);
    }


}
