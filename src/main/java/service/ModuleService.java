package service;

import dao.ModuleDao;
import model.MarkItem;
import model.Module;

public class ModuleService {
    private ModuleDao moduleDao = new ModuleDao();

    public void saveModule(Module module) { moduleDao.save(null); }

    public Module getModuleById (String id) { return moduleDao.getModuleById(id); }

    public void deleteModuleById(String id) { moduleDao.deleteModuleById(id);}

    public void updateModuleById(Module moduleUpdated){ moduleDao.updateModuleById(moduleUpdated);}

    public void addMarkItem(String id, MarkItem markItem){moduleDao.addMarkItem(id,markItem);}

    public void updateMarkItem(String module_id,String mark_name, MarkItem markItem){ }
}
