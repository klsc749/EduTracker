package service;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import model.Mark;
import model.MarkItem;
import model.Module;
import model.Activity.ActivityType;

public class ModuleServiceTest {
    ModuleService moduleService = new ModuleService();
    private String moduleId = "111";
    
    @Test
    public void testSaveModule() {
        Module module = new Module();
        module.setDegree("2");
        module.setName("Programing");
        module.setMark(new Mark());
        module.setId("111");
        module.setType(ActivityType.CLASS);
        moduleService.saveModule(module);

        Module module2 = moduleService.getModuleById(module.getId());

        assert module2 != null;
        assert module2.getName().equals(module.getName());
        assert module2.getDegree().equals(module.getDegree());
    }

    @Test
    public void testAddMarkItem(){
        Mark mark = new Mark();
        MarkItem markItem = new MarkItem("Assignment", 50, 0.5);
        mark.setMarkItems(Arrays.asList(markItem));
        Module module = moduleService.getModuleById(moduleId);
        module.setMark(mark);

        moduleService.addMarkItem(module.getId(), markItem);
        Module module2 = moduleService.getModuleById(module.getId());
        assert module2 != null;
        assert module2.getMark().getMarkItems().contains(markItem);
    }

    @Test
    public void testDeleteModuleById(){
        moduleService.deleteModuleById(moduleId);
        Module module2 = moduleService.getModuleById(moduleId);
        assert module2 == null;
    }
}
