package nextstep.courses.data;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;

import java.time.LocalDate;

import static java.time.LocalDate.now;

public class SessionMaker {

    public static Session makeSession(Course course) {
        return Session.open(0L, course, now(), now(), "imageUrl", true, 1);
    }

    public static Session makeSession(Long id, Course course) {
        return Session.open(id, course, now(), now(), "imageUrl", true, 10);
    }


}
