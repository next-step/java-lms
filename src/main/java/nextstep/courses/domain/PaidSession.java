package nextstep.courses.domain;

import nextstep.courses.exception.OverMaxStudentsException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class PaidSession extends Session {

    private final int maxStudents;

    public PaidSession(Long id, CoverImage coverImage, Status status, LocalDate startDate, LocalDate endDate, int maxStudents) {
        super(id, coverImage, status, startDate, endDate);
        this.maxStudents = maxStudents;
    }

    @Override
    public void register(NsUser nsUser) {
        validateMaxStudents();
        this.students.addStudent(nsUser);
    }

    private void validateMaxStudents() {
        if (this.students.isRegistrationFull(maxStudents)) {
            throw new OverMaxStudentsException(maxStudents);
        }
    }
}
