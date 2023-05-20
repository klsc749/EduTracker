package dao;

import model.Activity;
import model.Mark;
import model.MarkItem;
import model.Module;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ModuleDaoTest {
    
    ModuleDao moduleDao = new ModuleDao();
    private ArrayList<String> idArrayList = new ArrayList<>();
    private ArrayList<String> markItemName = new ArrayList<>();
    private ArrayList<MarkItem> markItems = new ArrayList<MarkItem>();

    
    ModuleDaoTest(){
        idArrayList.add("123");
        idArrayList.add("234");
        idArrayList.add("345");
        idArrayList.add("456");
        idArrayList.add("567");
        idArrayList.add("678");
        idArrayList.add("789");

        markItemName.add("123");
        markItemName.add("234");
        markItemName.add("345");
        markItemName.add("456");
        markItemName.add("567");
        markItemName.add("678");
        markItemName.add("789");

        for(String string_name:markItemName) {
            MarkItem markItem = new MarkItem(string_name, 100, 0.8);
            markItems.add(markItem);
        }
    }
    
    @Test
    void testSave(){
        Module module = new Module();
        module.setDegree("Bachelor");
        module.setMark(new Mark());
        module.setName("Software Engineering");
        module.setType(Activity.ActivityType.CLASS);
        module.setSkillTags(new ArrayList<String>());

        moduleDao.save(module);
    }

    @Test
    void testGetModuleById(){
        Module module = new Module();
        for(String string_id:idArrayList) {
            module = moduleDao.getModuleById(string_id);
            System.out.println(module);
        }
    }

    @Test
    void testDeleteModuleById(){
        for(String string_id:idArrayList){
            moduleDao.deleteModuleById(string_id);
            if(moduleDao.getModuleById(string_id)!=null){
                System.out.println("delete failed");
            }
        }
    }

    @Test
    void testAddMarkItem(){
        String string_id = idArrayList.get(1);
        for(MarkItem markItem:markItems){
            moduleDao.addMarkItem(string_id,markItem);
        }
    }

    @Test
    void testUpdateMarkItem(){
        String string_id = idArrayList.get(1);
        for(String string_name:markItemName){
            MarkItem markItem = new MarkItem(string_name,99,0.9);
            moduleDao.updateMarkItem(string_id,string_name,markItem);
        }
    }

    @Test
    void testGetAllModules(){
        List<Module> modules = new ArrayList<>();
        modules = moduleDao.getAllModules();

        for(Module module:modules){
            System.out.println(module.getClass());
        }
    }
}
