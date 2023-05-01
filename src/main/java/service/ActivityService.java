package service;

import dao.ActivityDao;
import dao.ExtraCurriculumDao;
import model.Activity;

import java.util.List;

public class ActivityService {
    private ActivityDao activityDao = new ActivityDao();
    public void saveActivity(Activity activity) {
        activityDao.save(null);
    }

    public Activity getActivityById(String id) {
        return activityDao.getActivityById(id);
    }

    public List<Activity> getAllActivities() {
        return activityDao.getAllActivities();
    }
}
