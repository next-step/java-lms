package nextstep.courses.domain;

import nextstep.courses.utils.BaseEntity;

public class Semester extends BaseEntity {
    private final Course course;

    public Semester(Course course) {
        this.course = course;
    }
}
