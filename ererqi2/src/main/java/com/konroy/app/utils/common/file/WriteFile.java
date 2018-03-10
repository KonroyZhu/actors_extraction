package com.konroy.app.utils.common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class WriteFile {
    public static void main(String[] args){
        WriteFile wf=new WriteFile("C:\\Users\\Konroy\\Documents\\ideaProject\\ererqi\\src\\main\\java\\com\\konroy\\app\\utils\\extract\\file\\Wd.txt");
        wf.write("something");


    }

    private String path;

    /**
     * create and write the string passed in a file
     * @param path
     */
    public WriteFile(String path) {
        this.path=path;
    }

    public void write(String content){
        File f=new File(this.path);
        try{
//            FileOutputStream out=new FileOutputStream(this.path);
//            out.write(content.getBytes());
            //only after the writer is closed the content can be written into the file
            FileWriter writer = new FileWriter(f, true);
            writer.write(content);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
