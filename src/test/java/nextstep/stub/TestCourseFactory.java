package nextstep.stub;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.entity.CourseEntity;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.factory.CourseFactory;
import nextstep.courses.factory.SessionsFactory;

import java.util.List;

public class TestCourseFactory extends CourseFactory {
    private int createCalled = 0;
    private final Course createResult;

    public TestCourseFactory(SessionsFactory sessionsFactory, Course createResult) {
        super(sessionsFactory);
        this.createResult = createResult;
    }

    @Override
    public Course create(CourseEntity courseEntity, SessionEntityImageMap sessionEntityImageMap) {
        createCalled++;
        return createResult;
    }

    public int getCreateCalled() {
        return createCalled;
    }
}
