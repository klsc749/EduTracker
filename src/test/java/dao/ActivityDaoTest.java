package dao;

import model.Activity;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class ActivityDaoTest {
    ActivityDao activityDao = new ActivityDao();


    @Test
    void testSave() {
        for ( int i = 0; i < 12; i++){
            Activity activity = new Activity();
            activity.setId(UUID.randomUUID().toString());
            activity.setName("Microservice");
            activity.setType(Activity.ActivityType.CLASS);
            activity.setStartDate(new Date());
            activity.setEndDate(new Date());
            activity.setDirectors(Arrays.asList("1", "2", "3"));

            activityDao.save(activity);
        }
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
