package nextstep.stub.repository;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.entity.CourseEntity;

public class TestCourseRepository implements CourseRepository {
    private final Long saveResult;
    private int saveCalled = 0;
    private int deleteCalled = 0;

    public TestCourseRepository(Long saveResult) {
        this.saveResult = saveResult;
    }

    @Override
    public Long save(CourseEntity courseEntity) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public void delete(Long id) {
        deleteCalled++;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getDeleteCalled() {
        return deleteCalled;
    }
}
