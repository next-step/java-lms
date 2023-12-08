package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.Student;

import java.time.LocalDate;

import static nextstep.courses.domain.session.Status.isNotRecruiting;

public class FreeSession extends Session {

    public FreeSession(Long id, PayType payType, Status status, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        super(id, payType, status, coverImage, startDate, endDate);
    }

    @Override
    public Student enroll(EnrolmentInfo enrolmentInfo) {
        validateStatus();

        Student student = new Student(id, enrolmentInfo.userId());
        this.students.add(student);

        return student;
    }

    private void validateStatus() {
        if (isNotRecruiting(status)) {
            throw new IllegalArgumentException(String.format("해당 강의의 현재 %s입니다.", status.description()));
        }
    }
}
