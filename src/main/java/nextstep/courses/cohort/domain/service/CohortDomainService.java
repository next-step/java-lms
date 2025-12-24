package nextstep.courses.cohort.domain.service;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import nextstep.courses.cohort.domain.Cohort;
import nextstep.courses.enrollment.domain.Enrollment;
import nextstep.qna.exception.unchecked.WrongRequestException;

public class CohortDomainService {

    public Enrollment registerEnrollment(Cohort cohort, Long studentId, Long courseId) {
        if (!cohort.isCanResist()) {
            throw new WrongRequestException("해당기수는 수강신청 할 수 없는 상태입니다");
        }
        if (!cohort.isSameCourseId(courseId)) {
            throw new IllegalArgumentException("결제정보와 강의정보가 상이합니다.");
        }

        cohort.registerStudent();

        // Cohort에서 studentId를 전달받아 Enrollment 반환 vs 외부에서 조합해서 Enrollment 반환하는 대신 id getter 오픈
        // Cohort에서 Enrollment를 생성하는 동작을 가지고 있는것 보다 외부에서 하는며 두객체의 결합도를 낮추는게 더 좋다고 생각합니다
        return new Enrollment(studentId, cohort.getId());
    }

    public void updateStateToRecruitEnd(Cohort cohort) {
        if (isNull(cohort)) {
            return;
        }

        cohort.putOnRecruitEnd(LocalDateTime.now());
    }
}
