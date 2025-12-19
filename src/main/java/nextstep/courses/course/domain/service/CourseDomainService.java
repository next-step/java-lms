package nextstep.courses.course.domain.service;

import nextstep.courses.course.domain.Course;
import nextstep.courses.enrollment.domain.Enrollment;

public class CourseDomainService {

    public Enrollment registerEnrollment(Course course, Long cohortId, Long studentId) {
        // 결제정보도 적용필요

        if (!course.isCanEnrollBy(cohortId)) {
            throw new IllegalArgumentException("해당기수는 수강신청 할 수 없는 상태입니다");
        }
        course.plusOnePresentStudent(cohortId);

        return new Enrollment(studentId, cohortId);
    }
}
