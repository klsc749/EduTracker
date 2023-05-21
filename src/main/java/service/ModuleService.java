package service;

import java.util.List;

import dao.ModuleDao;
import model.MarkItem;
import model.Module;

public class ModuleService {
    private ModuleDao moduleDao = new ModuleDao();

    public void saveModule(Module module) {
        moduleDao.save(module);
    }

    public Module getModuleById(String id) {
        return moduleDao.getModuleById(id);
    }

    public void deleteModuleById(String id) {
        moduleDao.deleteModuleById(id);
    }

    public void updateModuleById(Module moduleUpdated) {
        moduleDao.updateModuleById(moduleUpdated);
    }

    public void addMarkItem(String id, MarkItem markItem) {
        moduleDao.addMarkItem(id, markItem);
    }

    public void updateMarkItem(String module_id, String mark_name, MarkItem markItem) {
        moduleDao.updateMarkItem(module_id, mark_name, markItem);
    }

    public double calculateGPA() {
        List<Module> modules = moduleDao.getAllModules();
        double totalCredit = 0;
        double totalMark = 0;
        for (Module module : modules) {
            totalCredit += module.getCredit();
            totalMark += module.getCredit() * module.getMark().calculateMark();
        }
        return totalMark / totalCredit;
    }

    public List<Module> getAllModules() {
        return moduleDao.getAllModules();
    }
}
