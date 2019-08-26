package com.tets.sms;


import com.github.qcloudsms.SmsSingleSenderResult;
import com.google.gson.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJsonDateToMysql {

    private static final String url = "jdbc:mysql://localhost:3306/sms?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "12345";
    private static Connection con;

    public TestJsonDateToMysql(Connection con) {
    }

    static Connection getconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public TestJsonDateToMysql(Connection con, SmsSingleSenderResult result){
        JsonParser parser = new JsonParser() ;
        JsonObject object;
        try {
            object = (JsonObject) parser.parse(String.valueOf(result));
            JsonArray array = object.get("rows").getAsJsonArray();
            for(int i = 0; array.size() > i; i++) {
                JsonObject arrayObject = array.get(i).getAsJsonObject();
                PreparedStatement psql = con.prepareStatement("insert into jsontest1 (result,errmsg,ext,sid,fee) values(?,?,?,?,?)");
                // System.out.println(arrayObject.get("name").getAsString());
                psql.setInt(1, arrayObject.get("result").getAsInt());
                psql.setString(2, arrayObject.get("errmsg").getAsString().toString());
                psql.setString(3, arrayObject.get("ext").getAsString().toString());
                psql.setString(4, arrayObject.get("sid").getAsString().toString());
                psql.setString(5, arrayObject.get("fee").getAsString().toString());
                // psql.setString(5,arrayObject.getAsString());
//                String date = arrayObject.get("starttime").getAsString().toString();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date myDate = dateFormat.parse(date);
//                Format format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String str=format.format(myDate);
//                psql.setString(5, str);
                psql.executeUpdate();
                psql.close();
            }
        } catch (JsonIOException e1) {
            e1.printStackTrace();
        } catch (JsonSyntaxException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}