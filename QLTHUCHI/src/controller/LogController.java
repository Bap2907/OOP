package controller;

import model.connection.LogModel;
<<<<<<< HEAD
import model.objects.LogO;
=======
import model.objects.Logs;
>>>>>>> 5700697411fd1d0c4908684aa132beb38c23a87c

public class LogController {
    private LogModel logDao;
    public LogController(){
        logDao = new LogModel();
    }
<<<<<<< HEAD
    public void addLog(LogO log){
=======
    public void addLog(Logs log){
>>>>>>> 5700697411fd1d0c4908684aa132beb38c23a87c
        logDao.addLog(log);
    }
}
