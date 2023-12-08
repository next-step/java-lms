package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

import static nextstep.courses.domain.session.Status.isNotRecruiting;

public class FreeSession extends Session {

    public FreeSession(Long id, PayType payType, Status status, CoverImage coverImage, Students students, LocalDate startDate, LocalDate endDate) {
        super(id, payType, status, coverImage, students, startDate, endDate);
    }

    @Override
    public Student enroll(NsUser user, EnrolmentInfo enrolmentInfo) {
        validateStatus();

        Student student = new Student(this, user);
        this.students.add(student);

        return student;
    }

    private void validateStatus() {
        if (isNotRecruiting(status)) {
            throw new IllegalArgumentException(String.format("해당 강의의 현재 %s입니다.", status.description()));
        }
    }
}
