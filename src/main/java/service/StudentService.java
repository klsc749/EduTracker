package service;

import dao.StudentDao;
import model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentService {
    private StudentDao studentDao = new StudentDao();

    /**
        update student email and save the information in student.txt

        @param email user's input of new email address

     */
    public void updateEmail(String email) throws Exception{
        Student stu=studentDao.getStudentInfo();
            if(studentDao.CheckEmailLegal(email)){
                stu.setEmail(email);
                studentDao.save(stu);
            }
            else
                throw new Exception();
    }

    /**
        Get student's information
     */
    public Student getStudent(){
        return studentDao.getStudentInfo();
    }

    /**
     * Record the awards student receive
     * @param time yyyy-MM-dd, key should be unique
     * @param awardcontent the award name and content
     * @throws Exception
     */
    public void setAwards(String time, String awardcontent) throws Exception {
        HashMap<String,String> awards=studentDao.getStudentAward();
        if(!awards.containsKey(time) && isValidDate(time)){
            awards.put(time,awardcontent);
        }else if(awards.containsKey(time)){
            throw new Exception("Same time key");
        }else if(!isValidDate(time)){
            throw new DateTimeException("Invalid date type");
        }
    }

    /**
     * get the award content by time key
     * @param time
     * @return String: award content
     * @throws Exception
     */
    public String GetAwardByTime(String time) throws Exception{
        HashMap<String,String> awards=studentDao.getStudentAward();
        if(isValidDate(time) && awards.containsKey(time)){
            return awards.get(time);
        }else if(awards.containsKey(time)){
            throw new Exception("Same time key");
        }else if(!isValidDate(time)){
            throw new DateTimeException("Invalid date type");
        }
        return null;
    }

    /**
     * Get all awards content
     * @return ArrayList<String>
     */
    public ArrayList<String> GetAllAwardContents(){
        HashMap<String,String> awards=studentDao.getStudentAward();
        ArrayList<String> awardscontent =new ArrayList<>();
        for(String time: awards.keySet()){
            awardscontent.add(awards.get(time));
        }
        return awardscontent;
    }

    /**
     * Get all awards, including time and content
     * @return HashMap<String,String>
     */
    public HashMap<String,String> GetAllAwards(){
        return studentDao.getStudentAward();
    }

    /**
     * Check whether the input date is valid date type "yyyy-MM-dd
     * @param time input time string
     * @return true valid
     */
    public boolean isValidDate(String time){
        boolean datetype=true;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try{
            sdf.parse(time);
        }catch (ParseException e) {
            return false;
        }
        return true;
    }

}
