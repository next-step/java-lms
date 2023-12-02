package nextstep.courses.domain;

import nextstep.courses.exception.NotOpenSessionException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {

    private final Long id;
    private final CoverImage coverImage;
    private final Period period;
    private final Status status;
    private final Students students;

    public Session(Long id, CoverImage coverImage, Status status, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.coverImage = coverImage;
        this.period = new Period(startDate, endDate);
        this.status = status;
        this.students = new Students();
    }

    public void register(NsUser nsUser) {
        validateStatus();
        this.students.addStudent(nsUser);
    }

    private void validateStatus() {
        if (status != Status.OPEN) {
            throw new NotOpenSessionException();
        }
    }
}
