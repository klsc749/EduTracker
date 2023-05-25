package service;

import com.alibaba.fastjson2.JSONObject;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import dao.StudentDao;
import javafx.scene.image.Image;
import model.Student;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
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
     * update student email and save the information in student.txt
     * @param email user's input of new email address
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
     * Get Student from DAO
     * @return Student object
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
     * @param time a input time
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
     * @return ArrayList<String> containing all awards Content
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
     * @return HashMap<String,String> Time+Award Content
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
    public javafx.scene.image.Image loadStudentImage() {
        return studentDao.loadImage();
    }


    /**
     * Read Student's PS
     * @return String PS COntent
     */
    public String loadStudentPS(){
        return studentDao.loadPS();
    }

    /**
     * Modify Student PS Content with the input
     * @param Content the input new Content
     * @return the new Modified PS Content in String format
     */
    public String ModifyStudentPS(String Content){
        String newContent=studentDao.ModifyContentToPS(Content);
        return newContent;
    }

    /**
     *Use FileChooser to choose a file from system and replace the old photo.jpg
     * @param newimage the choosen new image
     * @return true, save successfully; false, have Exception or not save well
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
     * @param file the chosen file
     * @return true, the file is an image; false, the file isn't
     */
    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
                || fileName.endsWith(".gif") || fileName.endsWith(".bmp");
    }

    /**
     * save a new image file from filechooser and load the student image
     * @param newimage The new file choosen
     * @return Image The new student photo
     * @throws Exception
     */
    public Image updateStudentImage(File newimage) throws Exception {
        if(SaveNewImage(newimage)){
            Image image=loadStudentImage();
            return image;
        }
        else{
            throw new Exception("Invalid file");
        }
    }


    /**
     * Export a CV in pdf format based on the student.txt and PS.txt
     * @param outputDirectory the directory you want to save
     * @return a pdf File
     * @throws Exception
     */
    public File ExportCV(String outputDirectory) throws Exception{
        StringBuilder outputdir=new StringBuilder(outputDirectory);
        outputdir.append("/CV.pdf");
        JSONObject stu=studentDao.FileToJson();
        File outputFile=new File(outputdir.toString());

        Font contentFont = getBaseFont();

        // Create a PDF document
        Document document = new Document();
        if(document!=null){
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();
            //document=createCV(stu);
            // Add the name and studentId
            String name = stu.getString("name");
            String studentId = stu.getString("studentId");
            Paragraph nameAndIdParagraph = new Paragraph("Name: " + name + "\nStudent ID: " + studentId, contentFont);
            document.add(nameAndIdParagraph);

            // Add the email
            String email = stu.getString("email");
            Paragraph emailParagraph = new Paragraph("Email: " + email, contentFont);
            document.add(emailParagraph);

            // Add the degree
            String degree = stu.getString("degree");
            Paragraph degreeParagraph = new Paragraph("Degree: " + degree, contentFont);
            document.add(degreeParagraph);

            // Add the awards
            JSONObject awards = stu.getJSONObject("awards");
            StringBuilder awardsContent = new StringBuilder();
            for (String awardDate : awards.keySet()) {
                String awardName = awards.getString(awardDate);
                awardsContent.append(awardDate).append(": ").append(awardName).append("\n");
            }
            Paragraph awardsParagraph = new Paragraph("Awards:\n" + awardsContent.toString(), contentFont);
            document.add(awardsParagraph);

            String PSContent=studentDao.ReadFromPS();
            System.out.println(PSContent);
            Paragraph PSpara=new Paragraph("PS: "+PSContent);
            document.add(PSpara);
            document.close();

            return outputFile;
        }else{
            throw new Exception();
        }

    }

    /**
     * supports Chinese character in the document
     * @return ChineseFont
     * @throws DocumentException
     * @throws IOException
     */
    private Font getBaseFont() throws DocumentException, IOException {
        BaseFont chineseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        return new Font(chineseFont);
    }
}
