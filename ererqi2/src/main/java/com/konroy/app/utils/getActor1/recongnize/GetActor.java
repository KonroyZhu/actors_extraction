package com.konroy.app.utils.getActor1.recongnize;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.konroy.app.entity.actor.Actor;
import com.konroy.app.utils.getActor1.actorList.ActorListDb;
import  com.konroy.app.utils.getActor1.extract.*;
import com.konroy.app.utils.getActor1.baiduSearch.*;
import  com.konroy.app.utils.getActor1.distance.*;

public class GetActor {

    public static void main(String[] args){
//        String sentence="悟空很帅气";
//        String sentence1="吴京生黑小子,陈博士生黑丫头.在海外遇到危险时,身后还有强大的祖国展开救援、没有放弃,身为中国国民就非常自豪了;抛开主角光环,铁丝网拦火箭筒、木剑射杀、封喉等各种激烈打斗,正义惩恶的凶狠就让人过足血瘾,这分明就是铁狼刚狼宇宙狼了;只是涉及国家庄严的话题,插入太多幽默元素有失观赏时心态的严肃";
//        String sentence2="这居然是一张信仰充值卡。";
//        String object=getStr(sentence1,"战狼2(终).txt");
//        System.out.println("评价对象："+object);
//        System.out.println(tag(sentence1,"战狼2(终).txt"));
        String sentence="吴京太帅达康书记的枪法不错熊孩子张翰也可以真的很好看的电影看的热血沸腾";
        String movie="战狼2";
        System.out.println("句子："+sentence+"\n电影："+movie);
        System.out.println(new GetActor().getStr(sentence,movie));
    }

    /**
     * the function of dividing comment into smaller pieces of sentences  is added
     * @param sentence
     * @param moive
     * @return
     */
    public static List<String> getSen(String sentence,String moive){
        List<String> list=new ArrayList<String>();
        String[] sen=sentence.split("[。？！；.?!;]");
        String result="";
        for(String s:sen){
            String orientation=getStr(s,moive);
            result+=orientation;
        }
        String[] array=result.split(" ");
        for(String s:array){
            list.add(s);
        }
        return list;
    }
    /**
     * enter the sentence and return a tagged sentence
     * @param sentence
     * @return
     */
    public static String tag(String sentence,String movie){

        String reslut="";
        GetActor gat=new GetActor();
        String actors=gat.getStr(sentence,movie);
        String[] actorList=actors.split(" ");
        String regex="";
        for(String a:actorList){
            String original=a.split("-")[0];
            regex+=original+"|";
        }
        try{
            regex=regex.substring(1,regex.length()-1);
            //System.out.println(regex);
            String[] strlist=sentence.split(regex);
            Pattern pattern=Pattern.compile("("+regex+")");
            Matcher matcher=pattern.matcher(sentence);
            List<String> oriList=new ArrayList<String>();
            while(matcher.find()){
                oriList.add(matcher.group(1));
            }
            //System.out.println(oriList);
            for(int i=0;i<strlist.length;i++){

                String a="";
                if(i<oriList.size()){
                    a=oriList.get(i);
                    reslut+=strlist[i]+"<ACTOR>"+a+"</ACTOR>";
                }else {
                    reslut+=strlist[i];
                }
            }
        }catch (Exception e){
            return sentence;
        }

        return reslut;
    }


    /**
     * return the string formatted orientation
     * @param sentence
     * @return
     */
    public static String getStr(String sentence,String movie)  {
        BaiduSearch searcher=new BaiduSearch();
        List<String> object=new  ArrayList<String>();
        try{
            List<Actor> actors=new ActorListDb().get(movie);

            for(Actor a:actors){
                CustomDictionary.add(a.getNickName());
                CustomDictionary.add(a.getRealName());
            }
            List<Term> termList= HanLP.segment(sentence);

            List<String> nz=new ExtractTarget().extract(termList);
            List<String> candiName=new ArrayList<String>();

            //Step 1
            for(String z:nz){
                double max=0;

                for(Actor a:actors){

                    //计算演员名与角色名的jaccard系数
                    double temp1=new Jaccard().distance(a.getRealName(),z);
                    double temp2=new Jaccard().distance(a.getNickName(),z);
                    double temp=temp1>=temp2 ? temp1:temp2;

                    //double temp=temp1;
                    if(temp>max && temp>=0.3){
                        //System.out.println(z+"  "+a.getRealName()+"  "+a.getNickName());
                        max=temp;
                        candiName.add(a.getRealName());
                        candiName.add(a.getNickName());
                        //System.out.println(a.getRealName()+" "+z+" "+max);
                        object.add(z+"-"+a.getRealName());
                    }
                }
                //System.out.println(candiName);
            }
            //System.out.println(nz);
            //Step 2
            for(String z:nz){

                if(!candiName.contains(z)){
                    System.out.println("searching for "+z);
                    String text=searcher.search(z,movie);

                    int min=Integer.MAX_VALUE;
                    String N="";
                    for(Actor a:actors){
                        int temp=searcher.distance(text,z,a.getRealName());
                        if(temp<min){
                            min=temp;
                            N=a.getRealName();
                        }
                    }
                    if(!N.equals(""))object.add(z+"-"+N);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        String result="";
        for(String o:object){
            result+=" "+o;
        }
        return result;
    }


    /**
     * get the amount of name from text
     * @param text
     * @param name
     * @return
     */
    private static int count(String text,String name){
        String[] array=text.split(name);
        return array.length-1;
    }
    /**
     * extract the actor from a sentence
     * @param sentence
     * @return
     */
//    public static List<String> get(String sentence)  {
//        List<String> object=new  ArrayList<String>();
//        try{
//            List<Actor> actors=new ActorList().get();
//
//            for(Actor a:actors){
//                CustomDictionary.add(a.getNickName());
//                CustomDictionary.add(a.getRealName());
//            }
//            List<Term> termList= HanLP.segment(sentence);
//            //System.out.println(termList);
//
//            List<String> nr=new ExtractTarget().extract(termList,"nr");
//            for(String r:nr){
//                object.add(r+"-"+r);
//            }
//
//
//            List<String> nz=new ExtractTarget().extract(termList,"nz");
//            List<String> candiName=new ArrayList<String>();
//
//
//            for(String z:nz){
//                double max=0;
//
//                for(Actor a:actors){
//                    double temp1=new Jaccard().distance(a.getRealName(),z);
//                    double temp2=new Jaccard().distance(a.getNickName(),z);
//                    double temp=temp1>=temp2 ? temp1:temp2;
//                    //double temp=temp1;
//                    if(temp>max && temp>=0.5){
//                        //System.out.println(z+"  "+a.getRealName()+"  "+a.getNickName());
//                        max=temp;
//                        candiName.add(a.getRealName());
//                        //System.out.println(a.getRealName()+" "+z+" "+max);
//                        object.add(z+"-"+a.getRealName());
//                    }
//
//                    //System.out.println(a.getRealName()+"  "+z+" "+new Jaccard().distance(a.getRealName(),z));
//                }
//                //System.out.println(candiName);
//                if(candiName.size()==0){
//                    String text=new BaiduSearch().search(z);
//                    //System.out.println(text);
//                    for(Actor a:actors){
//                        String n=a.getRealName();
//
//                        // remove the nz that was found from badidu but totally irrelevant to the actor
//                        //if(!nr.contains(n) && text.contains(n) && new Jaccard().distance(z,n)>0.01 ) nr.add(n);
//                        if( text.contains(n) && new Jaccard().distance(z,n)>0.01 ) {
//
//                            object.add(z+"-"+n);
//                        }
//
//                    }
//                }
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//
//
//        return object;
//    }
}
