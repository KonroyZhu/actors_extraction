package com.konroy.app.utils.dataCleaning.process;

import com.konroy.app.utils.common.file.ReadFile;
import com.konroy.app.utils.common.file.WriteFile;
import com.konroy.app.utils.getActor1.recongnize.GetActor;

import java.text.DecimalFormat;
import java.util.List;

public class Testing {
    public static void main(String[] args){
            List<String> testInput=new  ReadFile().read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi2\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\raw\\战狼2.txt");
            WriteFile testOutput=new WriteFile("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi2\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\data\\战狼2(终).txt");
            String movie="战狼2";

            double size=testInput.size();
            for(int i=0;i<size;i++){
                DecimalFormat df   = new DecimalFormat("######0.00");
                System.out.println("进度： "+df.format((i)/size*100)+"%");
                String comment=testInput.get(i);

                String orientation=new GetActor().getStr(comment,movie);
                String result=comment+" 评价对象： "+orientation+"\n";
                System.out.println(" 评论："+comment);
                System.out.println(" 评价对象： "+orientation);
                testOutput.write(result);
            }

    }
}
