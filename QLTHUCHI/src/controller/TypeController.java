/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.util.List;
import model.connection.TypeModel;
<<<<<<< HEAD
import model.objects.TypeO;
=======
import model.objects.Types;
>>>>>>> 5700697411fd1d0c4908684aa132beb38c23a87c

/**
 *
 * @author LENOVO
 */
public class TypeController {
    private TypeModel typeDao;
    public TypeController(){
        typeDao = new TypeModel();
    }
<<<<<<< HEAD
    public List<TypeO> getAllTypeExpenses(){
=======
    public List<Types> getAllTypeExpenses(){
>>>>>>> 5700697411fd1d0c4908684aa132beb38c23a87c
        return typeDao.getAllTypeExpenses();
    }
}
