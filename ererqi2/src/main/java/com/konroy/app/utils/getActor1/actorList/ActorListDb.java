package com.konroy.app.utils.getActor1.actorList;


import com.konroy.app.entity.actor.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorListDb {
    private static Connection conn;
    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://192.168.235.46:3306/ererqi";
    private static String user = "root";
    private static String password = "root";
    public static void main(String[] args){
        System.out.println("西游记之孙悟空三打白骨精  演员列表：");
        System.out.println(get("西游记之孙悟空三打白骨精"));
    }

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
    public static List<Actor> get(String movie){
        List<Actor> actorList=new ArrayList<Actor>();
        conn=connect();
        try {
            Statement statement=conn.createStatement();
            String sql="SELECT real_name, role_name\n" +
                    "FROM  `movie_actor` \n" +
                    "WHERE movie_name =  '"+movie+"' ";

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String roleSet=rs.getString("role_name");
                String realNme=rs.getString("real_name");
                String[] roles=roleSet.split("/");
                for(String r:roles){
                    Actor a=new Actor(r,realNme);
                    actorList.add(a);
                }


            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }

        return actorList;
    }

}
