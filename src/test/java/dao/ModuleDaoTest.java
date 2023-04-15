package dao;

import model.Mark;
import model.Module;
import org.junit.jupiter.api.Test;

public class ModuleDaoTest {

    ModuleDao moduleDao = new ModuleDao();

    @Test
    void testSave(){
        Module module=new Module();
        module.setMark(new Mark());
        module.setDegree("2");
        module.setName("Programing");
        moduleDao.save(module);
    }
}
