package com.konroy.app.utils.getActor1.actorList;

import com.konroy.app.entity.actor.Actor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ActorList {
    public static void main(String[] args) throws IOException {
            List<Actor> list=new ActorList().get("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi\\src\\main\\java\\com\\konroy\\app\\utils\\getActor1\\actorList\\actor.txt");
            System.out.println(list);
    }
    private static final String DEFAULTPATH="C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi\\src\\main\\java\\com\\konroy\\app\\utils\\getActor1\\actorList\\actor.txt";

    /**
     * get actor list with the defaulted path
     * @return
     * @throws IOException
     */
    public List<Actor> get()  {
        List<Actor> list=new ArrayList<Actor>();
        File file=new File(DEFAULTPATH);
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String line=null;
            while((line=br.readLine())!=null){
                String[] N=line.split("\t");
                String stageName=N[0];
                String name=N[N.length-1];
                Actor actor=new Actor(stageName,name);
                list.add(actor);
            }
        }catch(Exception e){
            e.printStackTrace();
        }



        return list;
    }

    /**
     * get user with the defined path
     * @param path
     * @return
     * @throws IOException
     */
    public  List<Actor> get(String path) {
        List<Actor> list=new ArrayList<Actor>();
        File file=new File(path);
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String line=null;
            while((line=br.readLine())!=null){
                String[] N=line.split("\t");
                String stageName=N[0];
                String name=N[N.length-1];
                Actor actor=new Actor(stageName,name);
                list.add(actor);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }







}
