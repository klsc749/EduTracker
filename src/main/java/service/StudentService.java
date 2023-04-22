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
     * Add an award record
     * @param time yyyy-MM-dd, key should be unique
     * @param awardcontent the award name and content
     * @return HashMap
     * @throws Exception
     */
    public HashMap<String,String> AddAwards(String time, String awardcontent) throws Exception {
        Student stu=studentDao.getStudentInfo();
        HashMap<String,String> awards=stu.getAwards();
        if (!awards.containsKey(time) && isValidDate(time)) {
            awards.put(time, awardcontent);
            stu.setAwards(awards);
            studentDao.save(stu);
        } else if (awards.containsKey(time)) {
            throw new Exception("Date Key already exists");
        } else if (!isValidDate(time)) {
            throw new Exception("Invalid time");
        }
        return awards;
    }

    /**
     * get the award content by time key（yyyy-MM-dd）
     * @param time
     * @return String: award content
     * @throws Exception
     */
    public String GetContentByDate(String time) throws Exception{
        Student stu=studentDao.getStudentInfo();
        HashMap<String,String> awards=stu.getAwards();
        if(isValidDate(time) && awards.containsKey(time)){
            return awards.get(time);
        }else if(!isValidDate(time)){
            throw new Exception("Invalid time");
        }else if(!awards.containsKey(time)){
            throw new Exception("Time Not Exist");
        }
        return null;
    }

    /**
     * Get all awards content
     * @return ArrayList<String>
     */
    public ArrayList<String> GetAllAwardContents(){
        Student stu=studentDao.getStudentInfo();
        HashMap<String,String> awards=stu.getAwards();
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
        Student stu=studentDao.getStudentInfo();
        HashMap<String,String> awards=stu.getAwards();
        return awards;
    }

    /**
     * Check whether the input date is valid date type "yyyy-MM-dd
     * @param time input time string
     * @return true valid
     */
    public boolean isValidDate(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try{
            sdf.parse(time);
        }catch(ParseException e){
            return false;
        }
        return true;
    }

    //TODO: Add GetAwardContentByTimePeriod

}
