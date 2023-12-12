/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.connection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
<<<<<<< HEAD
import model.objects.LogO;
import Utils.Utils;
=======
import model.objects.Logs;

>>>>>>> 5700697411fd1d0c4908684aa132beb38c23a87c
/**
 *
 * @author LENOVO
 */
public class LogModel {
<<<<<<< HEAD
    public void addLog(LogO log){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "insert into Log (ID_Type, Price, Note, Date, User_ID) values(?,?,?,?,?)";
=======
    public void addLog(Logs log){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "insert into Log (ID_Type, Price, Note, Date) values(?,?,?,?)";
>>>>>>> 5700697411fd1d0c4908684aa132beb38c23a87c
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, log.getID_Type());
            preparedStatement.setDouble(2, log.getPrice());
            preparedStatement.setString(3,log.getNote());
<<<<<<< HEAD
            String utilDate = Utils.convertToSqlDate(log.getDateString());
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           
            preparedStatement.setString(4, utilDate);
            preparedStatement.setInt(5,log.getUser_ID());
=======
            Date utilDate = log.getDatesString();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(4, sqlDate);
>>>>>>> 5700697411fd1d0c4908684aa132beb38c23a87c
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
