package com.konroy.app.utils.dataCleaning.process;

import com.konroy.app.utils.common.file.ReadFile;
import com.konroy.app.utils.common.file.WriteFile;
import com.konroy.app.utils.getActor1.parsing.Parser;
import com.konroy.app.utils.getActor1.recongnize.GetActor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cleaning {
    public static void main(String[] args){
        List<String> comment=new ReadFile().read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi2\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\raw\\战狼2.txt");
        WriteFile wf=new WriteFile("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi2\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\data\\战狼2(终).txt");

        double progress=0;
        List<String> wantedComment=new ArrayList<String>();
            for(String c:comment){
                DecimalFormat df   = new DecimalFormat("######0.00");
                System.out.println("进度: "+df.format((progress++)/comment.size()*100)+"%");
                String objStr=new GetActor().getStr(c,"战狼2(终).txt");
                String[] obj=objStr.split(" ");
                String oStr="";
                for(String o:obj){                    oStr+=" "+o;
                }
                String cStr="";
                List<String> com=new Parser().parse(c);
                for(String o:com){
                    cStr+=" "+o;
                }
                if( oStr.contains("-")){
                    //wantedComment.add(c+"  评价对象： "+oStr+"  评价词： "+cStr+"\n");
                    String result=c+"  评价对象： "+oStr+"  评价词： "+cStr+"\n";
                    System.out.println(result);
                    System.out.println("评价对象："+oStr);
                    System.out.println("评价词："+cStr);
                    wf.write(result);
                }
                System.out.println();
            }

//            String content="";
//            for(String c:wantedComment){
//                System.out.println(c);
////                content+=c+"\n";
//                wf.write(c);
//            }


    }



}
