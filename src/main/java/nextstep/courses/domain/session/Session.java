package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import nextstep.courses.dto.EnrolmentInfo;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Session {

    protected Long id;
    protected PayType payType;
    protected Status status;
    protected CoverImage coverImage;
    protected Students students;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Session(Long id, PayType payType, Status status, CoverImage coverImage, Students students, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.payType = payType;
        this.status = status;
        this.coverImage = coverImage;
        this.students = students;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract Student enroll(EnrolmentInfo enrolmentInfo);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && payType == session.payType && status == session.status && Objects.equals(coverImage, session.coverImage) && Objects.equals(students, session.students) && Objects.equals(startDate, session.startDate) && Objects.equals(endDate, session.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payType, status, coverImage, students, startDate, endDate);
    }
}
