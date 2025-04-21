package nextstep.stub.factory;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.entity.CourseEntity;
import nextstep.courses.factory.CourseFactory;
import nextstep.courses.factory.SessionFactory;
import nextstep.courses.factory.SessionsFactory;

public class TestCourseFactory extends CourseFactory {
    private final Course createResult;
    private int createCalled = 0;

    public TestCourseFactory(SessionFactory sessionFactory, Course createResult) {
        super(sessionFactory);
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
