package nextstep.courses.domain;

import nextstep.courses.exception.ExceedingMaximumStudentException;
import nextstep.courses.exception.NotEligibleRegistrationStatusException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegistration {

    private final SessionStatus status;
    private final List<NsUser> students;
    private final int studentCapacity;

    public SessionRegistration(SessionStatus status, int studentCapacity) {
        this(status, new ArrayList<>(), studentCapacity);
    }

    public SessionRegistration(SessionStatus status, List<NsUser> students, int studentCapacity) {
        this.status = status;
        this.students = students;
        this.studentCapacity = studentCapacity;
    }

    public void register(NsUser student) {
        validate();
        students.add(student);
    }

    private void validate() {
        if (students.size() >= studentCapacity) {
            throw new ExceedingMaximumStudentException();
        }
        if (!status.isRecruiting()) {
            throw new NotEligibleRegistrationStatusException();
        }
    }

}
