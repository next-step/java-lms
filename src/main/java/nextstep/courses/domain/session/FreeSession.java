package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import nextstep.courses.dto.EnrolmentInfo;

import java.time.LocalDate;

import static nextstep.courses.domain.session.SessionStatus.isNotProgressing;

public class FreeSession extends Session {

    public FreeSession(Long id, PayType payType, SessionStatus sessionStatus, CoverImage coverImage, LocalDate startDate, LocalDate endDate, Students students) {
        super(id, payType, sessionStatus, coverImage, students, startDate, endDate);
    }

    @Override
    public Student enroll(EnrolmentInfo enrolmentInfo) {
        validateStatus();

        Student student = new Student(id, enrolmentInfo.getNsUserId());
        this.students.add(student);

        return student;
    }

    private void validateStatus() {
        if (isNotProgressing(sessionStatus)) {
            throw new IllegalArgumentException(String.format("해당 강의의 현재 %s입니다.", sessionStatus.description()));
        }
    }
}
