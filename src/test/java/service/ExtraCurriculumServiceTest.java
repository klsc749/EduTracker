package service;
import model.ExtraCurriculum;
import org.junit.jupiter.api.Test;

public class ExtraCurriculumServiceTest {
    ExtraCurriculumService extraCurriculumService=new ExtraCurriculumService();

    @Test
    public void testSaveExtraCurriculum(){
        ExtraCurriculum extraCurriculum = new ExtraCurriculum("New Extracurriculum Activity", "Go out");
        extraCurriculumService.saveExtraCurriculum(extraCurriculum);
        assert extraCurriculum != null;
        assert extraCurriculum.getName().equals(extraCurriculum.getName());
        assert extraCurriculum.getContent().equals(extraCurriculum.getContent());

    }
    @Test
    public void testGetAllExtraCurriculums(){
        System.out.println(extraCurriculumService.getAllExtraCurriculums());
    }


}
