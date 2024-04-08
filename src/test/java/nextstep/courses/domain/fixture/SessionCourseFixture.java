package nextstep.courses.domain.fixture;

import nextstep.courses.domain.Course;

import static nextstep.courses.domain.fixture.IdFixture.NS_USER_ID;

public class SessionCourseFixture {

    public static final String COURSE_TITLE = "TDD, 클린 코드 with Java 18기";

    public static Course course() {
        return new Course(COURSE_TITLE, NS_USER_ID);
    }

}
