package service;

import dao.ExtraCurriculumDao;
import model.ExtraCurriculum;

import java.util.List;

public class ExtraCurriculumService {
    private ExtraCurriculumDao extraCurriculumDao = new ExtraCurriculumDao();

    public void saveExtraCurriculum(ExtraCurriculum extraCurriculum) {
        extraCurriculumDao.save(null);
    }

    public ExtraCurriculum getExtraCurriculumById(String id) {
        return extraCurriculumDao.getExtraCurriculumById(id);
    }

    public List<ExtraCurriculum> getAllExtraCurriculums() {
        return extraCurriculumDao.getAllExtraCurriculums();
    }

}
