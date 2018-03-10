package com.konroy.app.utils.dataCleaning.process;

import com.konroy.app.entity.actor.Actor;
import com.konroy.app.utils.common.file.ReadFile;

import java.util.ArrayList;
import java.util.List;

public class GetRoleName {
    public static void main(String[] args){
        ReadFile rf=new ReadFile();
        List<String> commentlist=rf.read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi2\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\data\\战狼2测试.txt");

        for(String comment:commentlist){
            String namelist=comment.split("评价对象：")[1];
            String[] list2=namelist.split(" ");

            for(String l:list2){
                if(!l.trim().equals("")){
                    String names=l.trim();
                    String realname=names.split("-")[1];
                    String rolename=names.split("-")[0];
                    Actor actor=new Actor(rolename,realname);
                    System.out.println(realname);

                }
            }
        }
    }
}
