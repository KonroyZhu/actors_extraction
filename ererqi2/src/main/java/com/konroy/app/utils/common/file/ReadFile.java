package com.konroy.app.utils.common.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static void main(String[] args){
            System.out.println(new ReadFile().read("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi\\src\\main\\java\\com\\konroy\\app\\utils\\dataCleaning\\raw\\战狼2(终).txt.txt"));
    }

    /**
     * read the file and return a list object containing the content of the file with defaulted charaset(utf-8)
     * @param path
     * @return
     */
    public List<String> read(String path){
        File f=new File(path);

        List<String> content=new ArrayList<String>();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));
            String line=null;
            while((line=br.readLine())!=null){
                content.add(line);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * read the file and return a list object containing the content of the file
     * @param path
     * @param charaset
     * @return
     */
    public List<String> read(String path,String charaset){
        File f=new File(path);

        List<String> content=new ArrayList<String>();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f),charaset));
            String line=null;
            while((line=br.readLine())!=null){
                content.add(line);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
