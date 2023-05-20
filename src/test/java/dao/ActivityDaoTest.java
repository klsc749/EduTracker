package dao;

import model.Module;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class ActivityDaoTest {
    ActivityDao activityDao = new ActivityDao();


    @Test
    void testSave() {
        Module module = new Module();
        module.setId(UUID.randomUUID().toString());
        module.setName("Software Engineering");
        module.setDegree("MSc");
        activityDao.save(module);
    }

    @Test
    void getAllActivities() {
        System.out.println(activityDao.getAllActivities());
    }

    @Test
    void getActivityById() {
        System.out.println(activityDao.getActivityById("055a1f06-f7cd-4781-b05d-15e6024040ad"));
    }
}
