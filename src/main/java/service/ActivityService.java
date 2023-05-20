package service;

import dao.ExtraCurriculumDao;
import dao.ModuleDao;
import model.Activity;
import model.ExtraCurriculum;
import model.Module;

import java.util.ArrayList;
import java.util.List;

public class ActivityService {
    private ModuleDao moduleDao = new ModuleDao();
    private  ExtraCurriculumDao extraCurriculumDao = new ExtraCurriculumDao();


    public List<Activity> getAllActivities() {
        List<Module> modules = moduleDao.getAllModules();
        List<ExtraCurriculum> extraCurriculums = extraCurriculumDao.getAllExtraCurriculums();

        List<Activity> activities = new ArrayList<>();
        activities.addAll(modules);
        activities.addAll(extraCurriculums);
        return activities;
    }
}
