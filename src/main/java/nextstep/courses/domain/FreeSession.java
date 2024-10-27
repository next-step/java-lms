package nextstep.courses.domain;

import nextstep.courses.domain.vo.session.CoverImage;
import nextstep.courses.domain.vo.session.DateRange;
import nextstep.courses.domain.vo.session.Status;
import nextstep.courses.domain.vo.session.Students;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class FreeSession extends Session {
    private final Students students;

    public FreeSession(long id,
                       long creatorId,
                       DateRange dateRange,
                       CoverImage coverImage,
                       Status status) {
        super(id, dateRange, coverImage, status, creatorId);
        this.students = new Students();
    }

    public void register(NsUser student) {
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeSession that = (FreeSession) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}
