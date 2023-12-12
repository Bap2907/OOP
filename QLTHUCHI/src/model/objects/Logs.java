/*
<<<<<<< HEAD
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.objects;

/**
 *
 * @author sidac
 */
import java.util.Vector;
public class Logs {
    private Vector<LogO> logs;

    public Logs() {
        this.logs = new Vector<LogO>();
    }

    public Logs(Vector<LogO> logs) {
        this.logs = logs;
    }

    public Vector<LogO> getLogs() {
        return logs;
    }

    public void setLogs(Vector<LogO> logs) {
        this.logs = logs;
    }

    public int getSize(){
        return this.logs.size();
    }

    public LogO getLogAt(int index){
        return this.logs.get(index);
    }

    public void addLog(LogO log){
        this.logs.add(log);
    }

    public void addLogAt(LogO log, int index){
        this.logs.add(index, log);
    }

    public void removeLogAt(int index){
        this.logs.remove(index);
    }

    public void removeAfterIndex(int index){
        for(int i = this.logs.size() - 1; i > index; i--){
            this.logs.remove(i);
        }
    }

    public void removeBeforeIndex(int index){
        for(int i = 0; i < index; i++){
            this.logs.remove(0);
        }
    }

    public void setLogAt(LogO log, int index){
        this.logs.set(index, log);
    }

    public void clear(){
        this.logs.clear();
    }

    public void removeLog(LogO log){
        this.logs.remove(log);
    }

    public void removeLog(LogO log, int index){
        this.logs.remove(index);
    }
}
=======
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.objects;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Logs {
    private int ID_Log;
    private int ID_Type;
    private double Price;
    private String Note;
    private Date DatesString;
    private int User_ID;
    private int Group_ID;

    public int getID_Log() {
        return ID_Log;
    }

    public void setID_Log(int ID_Log) {
        this.ID_Log = ID_Log;
    }

    public int getID_Type() {
        return ID_Type;
    }

    public void setID_Type(int ID_Type) {
        this.ID_Type = ID_Type;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public Date getDatesString() {
        return DatesString;
    }

    public void setDatesString(Date DatesString) {
        this.DatesString = DatesString;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    public int getGroup_ID() {
        return Group_ID;
    }

    public void setGroup_ID(int Group_ID) {
        this.Group_ID = Group_ID;
    }
    
    
}
>>>>>>> 5700697411fd1d0c4908684aa132beb38c23a87c
