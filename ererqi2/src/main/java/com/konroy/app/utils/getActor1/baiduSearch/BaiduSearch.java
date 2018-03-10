package com.konroy.app.utils.getActor1.baiduSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaiduSearch {

    public static void main(String[] args) throws IOException {
        BaiduSearch content=new BaiduSearch();
        String key="熊孩子";
        String text=content.search(key,"战狼2");
        System.out.println(text);
        System.out.println("distance"+new BaiduSearch().distance(text,key,"张翰"));
    }
    /**
     * get the result querying the name in baidu
     * @param name
     * @return
     * @throws IOException
     */
    public static String search(String name,String moviename) throws IOException {
        String url="http://www.baidu.com/s?wd="+name+moviename;
        //System.out.println(url);
        URL realurl=new URL(url);
        URLConnection conn=realurl.openConnection();
        conn.setReadTimeout(60*1000);
        conn.setConnectTimeout(60*1000);

        String content="";
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String line=null;
        while((line = br.readLine())!=null){
            content+=line+"\n";
        }

        //Pattern titleP=Pattern.compile("([^\\x00-\\xff])");//get chinese only
        Pattern titleP=Pattern.compile("([^\\\\x00-\\\\xff]{1,12}"+name+"[^\\\\x00-\\\\xff]{1,12})");//get 30 words around key name
        Matcher titleM=titleP.matcher(content);
        String text=getString(titleM);

        return text;
    }



    /**
     * extracting string  from the matcher object
     * @param matcher
     * @return
     */
    private static String getString(Matcher matcher){
        String str="";
        while(matcher.find()){
            str+=matcher.group(1).replaceAll("[.,;()（）【】》《?!。，；？！]","\n");
        }
        return str.trim();
    }

    public static int distance(String text,String name,String actor){
        int distance=Integer.MAX_VALUE;

            Pattern t_a=Pattern.compile(name+"(.*?)"+actor);//get word between actor and target
            Matcher matcher=t_a.matcher(text);

            Pattern t_a_R=Pattern.compile(actor+"(.*?)"+name);//get word between  target and actor
            Matcher matcherR=t_a_R.matcher(text);

            List<String> list=new ArrayList<String>();

            int min=Integer.MAX_VALUE;
            while (matcher.find()){
                String m=matcher.group(1).trim();
                int size=m.length();
                if(size<min){
                    min=size;
                    list.add(m);
                }
            }
            while (matcherR.find()){
                String m=matcherR.group(1).trim();
                int size=m.length();
                if(size<min){
                    min=size;
                    list.add(m);
                }
            }

            for(String s:list){
                //System.out.println(s);
                s=s.replaceAll(actor,"");
                s=s.replaceAll(name,"");
                int temp=s.length();
                if(distance>temp){
                    distance=temp;
                }

            }

        return distance;
    }
}
