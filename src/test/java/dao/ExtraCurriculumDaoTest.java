package dao;

import model.ExtraCurriculum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class ExtraCurriculumDaoTest {
    ExtraCurriculumDao extraCurriculumDao = new ExtraCurriculumDao();

    @Test
    void testSave() {
        for ( int i = 0; i < 1; i++){
            ExtraCurriculum extraCurriculum = new ExtraCurriculum();
            extraCurriculum.setId(UUID.randomUUID().toString());
            extraCurriculum.setName("ABC");
            extraCurriculum.setType(ExtraCurriculum.ActivityType.EXTRA);
            extraCurriculum.setStartDate(new Date());
            extraCurriculum.setEndDate(new Date());
            extraCurriculum.setDirectors(Arrays.asList("1", "2", "3"));
            extraCurriculum.setContent("EC1");
            extraCurriculum.setTeammates(Arrays.asList("4", "5", "6"));
            extraCurriculumDao.save(extraCurriculum);
        }
    }

    @Test
    void getAllExtraCurriculums() {
        System.out.println(extraCurriculumDao.getAllExtraCurriculums());
    }

    @Test
    void getExtraCurriculumById() {
        System.out.println(extraCurriculumDao.getExtraCurriculumById("055a1f06-f7cd-4781-b05d-15e6024040ad"));
    }
}
