package com.konroy.app.utils.common.DataBase;

import com.konroy.app.entity.actor.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsertActorName {
    private static Connection conn;
    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://192.168.235.46:3306/ererqi";
    private static String user = "root";
    private static String password = "root";


    private static Connection connect(){
        Connection connection=null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);

        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public static void insert(Actor actor){
        List<Actor> actorList=new ArrayList<Actor>();
        conn=connect();
        try {
            Statement statement=conn.createStatement();
           String sql="UPDATE movie_actor SET nick_name_collection =  '张翰`熊孩子`卓亦凡' WHERE real_name =  '张翰' and movie_name='战狼2'";
           statement.execute(sql);


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }


    }

}
