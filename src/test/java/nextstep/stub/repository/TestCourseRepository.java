package nextstep.stub.repository;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.entity.CourseEntity;

public class TestCourseRepository implements CourseRepository {
    private final Long saveResult;
    private final CourseEntity findByIdResult;
    private int saveCalled = 0;

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
        return findByIdResult;
    }

    public int getSaveCalled() {
        return saveCalled;
    }
}
