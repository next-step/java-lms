package nextstep.stub;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.entity.CourseEntity;

public class TestCourseRepository implements CourseRepository {
    private int saveCalled = 0;
    private int findByIdCalled = 0;
    private final Long saveResult;
    private final CourseEntity findByIdResult;

    public TestCourseRepository(Long saveResult, CourseEntity findByIdResult) {
        this.saveResult = saveResult;
        this.findByIdResult = findByIdResult;
    }

    @Override
    public Long save(CourseEntity courseEntity) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public CourseEntity findById(Long id) {
        findByIdCalled++;
        return findByIdResult;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getFindByIdCalled() {
        return findByIdCalled;
    }
}
