package service;

import model.Activity;
import model.Mark;
import model.Module;
import org.junit.jupiter.api.Test;

public class ExtraCurriculumServiceTest {
    ExtraCurriculumService extraCurriculumService=new ExtraCurriculumService();

    @Test
    public void testSaveExtraCurriculum(){
        Module module = new Module();
        module.setDegree("2");
        module.setName("Programing");
        module.setMark(new Mark());
        module.setId("111");
        module.setType(Activity.ActivityType.CLASS);
        moduleService.saveModule(module);

        Module module2 = moduleService.getModuleById(module.getId());

        assert module2 != null;
        assert module2.getName().equals(module.getName());
        assert module2.getDegree().equals(module.getDegree());

    }
    @Test
    public void testGetAllExtraCurriculums(){
        System.out.println(extraCurriculumService.getAllExtraCurriculums());
    }


}
