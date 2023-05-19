package service;

import dao.StudentDao;
import javafx.scene.image.Image;
import model.Student;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**
     * Read Student's image
     * @return image
     */
    //TODO:fix bug
    public javafx.scene.image.Image loadStudentImage() {
        return studentDao.loadImage();
    }


    /**
     * Read Student's PS
     * @return String
     */
    public String loadStudentPS(){
        return studentDao.loadPS();
    }

    public String ModifyStudnetPS(String Content){
        String newContent=studentDao.ModifyContentToPS(Content);
        return newContent;
    }

    /**
     *Use FileChooser to choose a file from system and replace the old photo.jpg
     * @param newimage
     * @return boolean
     */
    public boolean SaveNewImage(File newimage) throws IOException {
        Path destDirPath = Paths.get("src/main/resources/image/student");
        // create the destination file path
        Path destFilePath = destDirPath.resolve("photo.jpg");

        if(!isImageFile(newimage)){
            return false;
        }else if(newimage.getName().toLowerCase().endsWith(".jpg")){
            // Delete the existing photo.jpg file if it exists
            Files.deleteIfExists(destFilePath);
            // Copy the new image file to the destination directory
            Files.copy(newimage.toPath(), destFilePath);
            return true;
        }else{
            // Load the image file
            BufferedImage image = ImageIO.read(newimage);

            // Convert the image to JPEG format
            BufferedImage jpegImage = new BufferedImage(image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            jpegImage.createGraphics().drawImage(image, 0, 0, null);

            // Delete the existing photo.jpg file if it exists
            Files.deleteIfExists(destFilePath);

            // Save the JPEG image to a file
            File destFile = new File(String.valueOf(destDirPath), "photo.jpg");
            ImageIO.write(jpegImage, "jpg", destFile);
            return true;
        }
    }

    /**
     * checks if the file has a valid image file extension
     * @param file
     * @return
     */
    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
                || fileName.endsWith(".gif") || fileName.endsWith(".bmp");
    }

    /**
     * save a new image file from filechooser and load the student image
     * @param newimage
     * @return Image
     * @throws Exception
     */
    //TODO：fix bug
    public Image updateStudentImage(File newimage) throws Exception {
        if(SaveNewImage(newimage)){
            Image image=loadStudentImage();
            return image;
        }
        else{
            throw new Exception("Invalid file");
        }
    }

    //TODO: 导出CV
    public File ExportCV(){
        return new File("");
    }

}
