package nextstep.stub.factory;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.entity.CourseEntity;
import nextstep.courses.factory.CourseFactory;
import nextstep.courses.factory.SessionFactory;

public class TestCourseFactory extends CourseFactory {
    private final Course createResult;
    private int createCalled = 0;

    public TestCourseFactory() {
        this(new TestSessionFactory(), null);
    }

    public TestCourseFactory(Course createResult) {
        this(new TestSessionFactory(), createResult);
    }

    public TestCourseFactory(SessionFactory sessionFactory, Course createResult) {
        super(sessionFactory);
        this.createResult = createResult;
    }

    @Override
    public Course createCourse(CourseEntity courseEntity, SessionEntityImageMap sessionEntityImageMap) {
        createCalled++;
        return createResult;
    }

    public int getCreateCalled() {
        return createCalled;
    }
}
